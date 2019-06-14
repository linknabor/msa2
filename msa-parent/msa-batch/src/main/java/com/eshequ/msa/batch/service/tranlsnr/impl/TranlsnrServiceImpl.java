package com.eshequ.msa.batch.service.tranlsnr.impl;

import java.lang.reflect.Method;
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

		Object tranDto = redisTemplat.opsForList().leftPop(PAY_ORDER_QUEUE, Protocol.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(tranDto);
			
			JsonNode jsonNode = objectMapper.readTree(json);
			List<String> node = jsonNode.findValuesAsText("entityName");
			String entityName = node.get(0);
			JsonNode entityNode = jsonNode.findValue("entity");

			logger.info("entityName is : " + entityName);
			logger.info("entity is : " + entityNode.toString());
			
			Object o = objectMapper.readValue(entityNode.toString(), ReflectUtil.getNamespace(entityName));
			Class<?> clazz = ReflectUtil.getMapper(entityName);
			
			Method method = clazz.getMethod("insert", Object.class);	//反射调用mapper的insert方法。这里不用关心是payOrder还是refundOrder，在数据组装时已经指明了order类型
			method.invoke(BeanUtil.getBean(clazz), o);
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		
		
		
		
	}
	

}
