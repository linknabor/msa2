package com.eshequ.msa.crm.service.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface LoginRemote {
	//项目名/controller/接口名
	@RequestMapping(value="/sso/checkSsoToken",method=RequestMethod.POST)
	String checkSsoToken(@RequestParam("ssoToken") String token,@RequestParam("sessionId") String sessionId);
}
