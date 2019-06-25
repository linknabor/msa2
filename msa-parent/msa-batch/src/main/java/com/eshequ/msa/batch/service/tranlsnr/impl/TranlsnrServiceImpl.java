package com.eshequ.msa.batch.service.tranlsnr.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.eshequ.msa.batch.service.tranlsnr.TranlsnrService;
import com.eshequ.msa.util.BeanUtil;
import com.eshequ.msa.util.DateUtil;
import com.eshequ.msa.util.ReflectUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.lettuce.core.RedisCommandTimeoutException;

@Service
public class TranlsnrServiceImpl implements TranlsnrService {
	
	private static Logger logger = LoggerFactory.getLogger(TranlsnrServiceImpl.class);
	
	private static final String PAY_ORDER_QUEUE = "UnionPayOrder";
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	@Autowired
	private StringRedisTemplate stringRedisTemplat;
	
	@Override
	public void syncData() {

		String tranDto = "";
		try {
			tranDto = stringRedisTemplat.opsForList().leftPop(PAY_ORDER_QUEUE, Integer.MAX_VALUE, TimeUnit.SECONDS);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(tranDto);
			
			List<String> methodNode = jsonNode.findValuesAsText("callMethod");
			String callMethod = methodNode.get(0);
			
			List<String> nameNode = jsonNode.findValuesAsText("entityName");
			String entityName = nameNode.get(0);
			
			JsonNode entityNode = jsonNode.findValue("entity");
			
			logger.info("callMethod is : " + callMethod);
			logger.info("entityName is : " + entityName);
			logger.info("entity is : " + entityNode.toString());
			
			Object o = objectMapper.readValue(entityNode.toString(), ReflectUtil.getNamespace(entityName));
			Class<?> clazz = ReflectUtil.getMapper(entityName);
			Method method = clazz.getMethod(callMethod, Object.class);	//反射调用mapper的insert方法。这里不用关心是payOrder还是refundOrder，在数据组装时已经指明了order类型
			method.invoke(BeanUtil.getBean(clazz), o);
			
		
		} catch (Exception e) {
			
			logger.error(e.getMessage(), e);
			if (e instanceof RedisCommandTimeoutException || e instanceof QueryTimeoutException) {
				//do nothing
			}else {
				File file = new File(DateUtil.getSysDate()+DateUtil.getSysTime()+ "_" + System.currentTimeMillis() +"_msa.fail.queue.file");
				try {
					FileUtils.writeStringToFile(file, e.getCause().toString()+ LINE_SEPARATOR + LINE_SEPARATOR, "utf-8", false);
					FileUtils.writeStringToFile(file, tranDto, "utf-8", true);
				} catch (IOException e1) {
					logger.error(e1.getMessage(), e1);
				}
				
			}
			
		}

	}


}
