package com.eshequ.msa.finance.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eshequ.msa.finance.web.controller.MsaBaseAcctInfoController;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class ObjectUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(MsaBaseAcctInfoController.class);
	
	public static String toUnderlineJSONString(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		mapper.configure(Feature.QUOTE_FIELD_NAMES, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
		String reqJson = mapper.writeValueAsString(object);
		return reqJson;
	}
	
}
