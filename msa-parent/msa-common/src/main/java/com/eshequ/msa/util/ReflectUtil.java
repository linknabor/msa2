package com.eshequ.msa.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);
	
	public final static String MAPPER_PATH = "com.eshequ.msa.batch.mapper.normal.";
	public final static String ENTITY_PATH = "com.eshequ.msa.batch.model.";
	
	public static Class<?> getMapper(String modelName){
		
		String mapperName = MAPPER_PATH + modelName + "Mapper";
		Class<?> clazz = null;
		try {
			clazz = Class.forName(mapperName);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return clazz;
	}
	
	public static Class<?> getNamespace(String modelName){
		
		String className = ENTITY_PATH + modelName;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return clazz;
	}
	
}
