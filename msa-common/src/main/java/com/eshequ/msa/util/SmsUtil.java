package com.eshequ.msa.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eshequ.msa.exception.BusinessException;
import com.eshequ.msa.util.http.HttpUtil;

@Component
public class SmsUtil {

	private static Logger log = LoggerFactory.getLogger(SmsUtil.class);

	@Autowired
	private HttpUtil httpClientProxy;

	@Value("${sms.account}")
	private String account;

	@Value("${sms.password}")
	private String password;

	@Value("${sms.url}")
	private String url;

	/**
	 * 请求的参数封装成ap的键值对形式
	 * 
	 * @param map
	 * @return
	 */
	public boolean send(Map<String, String> map) {
		boolean ret = false;
		if (map == null) {
			throw new BusinessException("短信内容不能为空。");
		}
		map.put("account", account);
		map.put("pswd", password);
		map.put("needstatus", String.valueOf(true));
		String response = httpClientProxy.doPost(url, map, "utf-8");
		String status = response.substring(response.indexOf(",") + 1, response.indexOf("\n"));
		if ("0".equals(status)) {
			ret = true;
		}
		return ret;
	}
}
