package com.eshequ.msa.crm.service.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginFeignFallBack implements LoginRemote{
	private static final Logger logger = LoggerFactory.getLogger(LoginFeignFallBack.class);

	@Override
	public String checkSsoToken(String token,String sessionId) {
		logger.error("sso验证时，发生运行异常！！！");
		System.out.println("feign请求时，发生错误！");
		return "error";
	}

	
	

}
