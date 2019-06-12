package com.eshequ.msa.finance;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.internal.runners.statements.RunAfters;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.eshequ.msa.finance.config.AppInit;

import junit.framework.TestCase;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class AppTest extends TestCase {
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private JedisPool jedisPool;
	
	
	@Test
	public void testRedis() {

		redisTemplate.opsForList().rightPush("test1", "a");
//		Jedis jedis = jedisPool.getResource();
//		System.out.println(jedis.blpop(0, "test1"));
		
		
		for (int i = 0; i < 1000; i++) {
			
			TestA test = new TestA("testKey"+String.valueOf(i), "value"+String.valueOf(i));
			Thread t = new Thread(test);
			System.out.println(t.getId());
			t.start();
		}
		
		
	}
	
	class TestA implements Runnable{
		
		String key;
		String value;
		
		public TestA(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}

		@Override
		public void run() {

//			Jedis jedis = jedisPool.getResource();
//			jedis.set(key, value);
			redisTemplate.opsForValue().set(key, value);
			System.out.println("set key :" + key);
		}
		
	}
	
	
	
}
