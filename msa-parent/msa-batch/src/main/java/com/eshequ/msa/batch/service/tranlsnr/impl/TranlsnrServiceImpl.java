package com.eshequ.msa.batch.service.tranlsnr.impl;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.eshequ.msa.batch.service.tranlsnr.TranlsnrService;
import com.eshequ.msa.util.BeanUtil;
import com.eshequ.msa.util.ReflectUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Protocol;

@Service
public class TranlsnrServiceImpl implements TranlsnrService {
	
	private static Logger logger = LoggerFactory.getLogger(TranlsnrServiceImpl.class);
	
	private static final String PAY_ORDER_QUEUE = "UnionPayOrder";
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplat;
	
	@Override
	public void syncData() {

		try {
			Object tranDto = redisTemplat.opsForList().leftPop(PAY_ORDER_QUEUE, Protocol.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = (String)tranDto;	//由于在redie中已经序列化成了字符串，所以这里增强转，不使用objectMapper的writeValueAsString函数。如果使用writeValueAsString，会自动加上转义的斜杠，在取键值的时候会出问题。
			JsonNode jsonNode = objectMapper.readTree(json);
			
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
		}

	}


}
