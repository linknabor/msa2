package com.eshequ.msa.crm.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.eshequ.msa.common.Constants;
import com.eshequ.msa.crm.model.repairmng.AccessToken;
import com.eshequ.msa.crm.model.repairmng.UserInfo;
import com.eshequ.msa.util.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class QiYeWeiXinUtil {
	private static Logger log = LoggerFactory.getLogger(QiYeWeiXinUtil.class);

	@Autowired
	private HttpUtil httpClientProxy;

	@Value("${qyweixin.cropid}")
	private String cropid;

	@Value("${qyweixin.agentid}")
	private String agentid;

	@Value("${qyweixin.corpsecret}")
	private String corpsecret;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public  AccessToken getAccessToken() {
		AccessToken accessToken=null;
		if(redisTemplate.opsForValue().get("accessToken") == null){
			Map<Object, Object> params = new HashMap<>();
			params.put("corpid",cropid);
			params.put("corpsecret",corpsecret);
			String result=httpClientProxy.doGet(Constants.ACCESS_TOKER_URL, params);
			log.info("企业微信获取到的accessToken:"+result);
			 ObjectMapper objectMapper = new ObjectMapper();
			 try {
				 accessToken = objectMapper.readValue(result, AccessToken.class);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			} 
			 if("0".equals(accessToken.getErrcode())){
				 redisTemplate.opsForValue().set("accessToken", accessToken);
				 redisTemplate.expire("accessToken", accessToken.getExpires_in()-100, TimeUnit.SECONDS);
			 }
		}else{
			accessToken=(AccessToken) redisTemplate.opsForValue().get("accessToken");
			log.info("redis获取到的accessToken:"+accessToken);
		}
		
		return accessToken;
	}
	
	public String getUserTicket(String accessToken,String code){
			String url = Constants.GET_OAUTH2_URL.replace("ACCESS_TOKEN", accessToken).replace("CODE",
					code);
			String response = httpClientProxy.doGet(url);
			ObjectMapper obj=new ObjectMapper();
			Map m= new HashMap<>();
			try {
				m = obj.readValue(response, Map.class);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
			String userTicket=m.get("user_ticket").toString();
			log.info("企业微信返回userTicket："+userTicket);
			if("0".equals(m.get("errcode").toString())){
				 redisTemplate.opsForValue().set("userTicket", userTicket);
				 redisTemplate.expire("userTicket", Long.parseLong(m.get("expires_in").toString())-100, TimeUnit.SECONDS);
			}
		return userTicket;
	}
	
	public String getUserId(String accessToken,String code){
			String url = Constants.GET_OAUTH2_URL.replace("ACCESS_TOKEN", accessToken).replace("CODE",
					code);
			String response = httpClientProxy.doGet(url);
			ObjectMapper obj=new ObjectMapper();
			Map m= new HashMap<>();
			try {
				m = obj.readValue(response, Map.class);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
			String userId=m.get("UserId").toString();
			log.info("企业微信返回userId："+userId);
			if("0".equals(m.get("errcode").toString())){
				 redisTemplate.opsForValue().set("userId", userId);
			}
		return userId;
	}
	
	//获取部门用户信息
	public List<UserInfo> getuserListByDepartMent(){
		AccessToken  accessToke=getAccessToken();
		String url = Constants.GET_DEPARTMENT_USERINFO.replace("ACCESS_TOKEN", accessToke.getAccess_token()).replace("DEPARTMENT_ID", "20");
		String result=httpClientProxy.doGet(url);
		ObjectMapper obj=new ObjectMapper();
		try {
			Map map=obj.readValue(result, Map.class);
			
		List list=	(List) map.get("userlist");
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		//TODO 返回集合 需测试
		return null;
	}
	
	//发送信息
	public String sendMessage(String userId,String repairId,String message){
		AccessToken  accessToke=getAccessToken();
		String url = Constants.SEND_MESSAGE.replace("ACCESS_TOKEN", accessToke.getAccess_token());
		Map<String,String> contentMap=new HashMap<>();
		contentMap.put("description", message);	
		contentMap.put("url", "https://test.e-shequ.com/workweixin/index1.html#/dingdan?repairId="+repairId+"&userId="+userId);
		contentMap.put("title", "报修订单提醒");
		Map<String,Object> map=new HashMap<>();
		map.put("touser", userId);
		map.put("agentid",agentid);
		map.put("msgtype", "textcard");
		map.put("textcard",contentMap);
		map.put("safe", "0");
		String result="";
		ObjectMapper obj=new ObjectMapper();
		try {
			String param=obj.writeValueAsString(map);
			String resp=httpClientProxy.doPost(url, param, "utf-8");
			Map objmap=obj.readValue(resp, Map.class);
			result= (int) objmap.get("errcode")+"";
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(),e);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}
	
}
