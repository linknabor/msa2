package com.eshequ.msa.crm.web.repairmng;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshequ.msa.common.Constants;
import com.eshequ.msa.crm.model.repairmng.AccessToken;
import com.eshequ.msa.crm.model.repairmng.UserInfo;
import com.eshequ.msa.crm.util.QiYeWeiXinUtil;
import com.eshequ.msa.crm.web.BaseController;
import com.eshequ.msa.util.http.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/oauth")
public class OAuth2Controller extends BaseController {
	@Autowired
	private HttpUtil httpClientProxy;
	@Value("${qyweixin.cropid}")
	private String cropid;
	@Value("${qyweixin.agentid}")
	private String agentid;
	@Autowired
	private QiYeWeiXinUtil qiYeWeiXinUtil;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private static Logger log = LoggerFactory.getLogger(OAuth2Controller.class);

	@RequestMapping("/getCode")
	public String getCode(HttpServletRequest request) {
		String requestUrl = request.getServerName();
		log.info(requestUrl);
		String contextPath = request.getContextPath();
		log.info(contextPath);
		String backUrl = "http://" + requestUrl + "/msa" + contextPath + "/oauth" + "/oauthBackUrl";
		log.info(backUrl);
		String redirect_uri = "";
		try {
			redirect_uri = URLEncoder.encode(backUrl, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		log.info(redirect_uri);
		String oauth2Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + cropid + "&redirect_uri="
				+ redirect_uri + "&response_type=code&scope=snsapi_userinfo&agentid=" + agentid
				+ "&state=STATE#wechat_redirect";
		return "redirect:" + oauth2Url;
	}

	@RequestMapping("/oauthBackUrl")
	public String oauthBackUrl(HttpServletRequest request, @RequestParam String code) {
		String url = "";
		AccessToken accessToken = qiYeWeiXinUtil.getAccessToken();
		String userId = qiYeWeiXinUtil.getUserId(accessToken.getAccess_token(), code);
		log.info("userId:" + userId);
		String response = getUserInfo(accessToken.getAccess_token(), userId);
		ObjectMapper obj = new ObjectMapper();

		UserInfo user = new UserInfo();
		try {
			Map m = obj.readValue(response, Map.class);
			user.setUserid(m.get("userid").toString());
			user.setGender(m.get("gender").toString());
			user.setPosition(m.get("position").toString());
			user.setMobile(m.get("mobile").toString());
			user.setName(m.get("name").toString());
			user.setAvatar(m.get("avatar").toString());
			log.info("user:" + user);
			redisTemplate.opsForValue().set(userId, user);

			if ("JAVA开发工程师".equals(user.getPosition())) {
				//维修工的跳转地址
				url = "https://test.e-shequ.com/workweixin/index1.html#/?userId="+userId;
				log.info("职位："+user.getPosition());
			} else {
				//客服添加报修跳转的地址
				url = "https://test.e-shequ.com/workweixin/index.html#/?userId="+userId;
				log.info("职位："+user.getPosition());
			}

		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		 return "redirect:" + url;
	}

	@RequestMapping("/getAccessToke")
	@ResponseBody
	public String getAccessToke() {
		AccessToken accessToken = qiYeWeiXinUtil.getAccessToken();
		return accessToken.getAccess_token();
	}

	@RequestMapping("/getuser")
	@ResponseBody
	public UserInfo getuser(String userId) {
		// String userId=(String) redisTemplate.opsForValue().get("userId");
		return (UserInfo) redisTemplate.opsForValue().get(userId);
	}


	/*
	 * public String getUserInfo(String accessToken, String userTicket) { String
	 * url = Constants.GET_UserInfo_URL.replace("ACCESS_TOKEN", accessToken);
	 * JSONObject jsonObject = new JSONObject(); jsonObject.put("user_ticket",
	 * userTicket); return httpClientProxy.doPost(url, jsonObject.toString(),
	 * "utf-8"); }
	 */

	public String getUserInfo(String accessToken, String userId) {
		String url = Constants.GET_USERINFO.replace("ACCESS_TOKEN", accessToken).replace("USERID", userId);
		return httpClientProxy.doGet(url);
	}

	// 提供微信前端配置
	@RequestMapping("/parameterConfiguration")
	@ResponseBody
	public Map<String, String> parameterConfiguration(HttpServletRequest requesturl) {
		AccessToken accessToken = qiYeWeiXinUtil.getAccessToken();
		String qiyeUrl = Constants.GET_jsapi_ticket.replace("ACCESS_TOKEN", accessToken.getAccess_token());
		String ticket = httpClientProxy.doGet(qiyeUrl);
		String url = requesturl.getParameter("url");
		ObjectMapper obj = new ObjectMapper();
		String jsapi_ticket = "";
		try {
			Map map = obj.readValue(ticket, Map.class);
			jsapi_ticket = map.get("ticket").toString();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		log.info("jsapi_ticket:" + jsapi_ticket);
		log.info("url:" + url);
		return sign(jsapi_ticket, url);
	}

	public Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> map = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		map.put("appId", cropid);
		map.put("timestamp", timestamp);
		map.put("nonceStr", nonce_str);
		// 注意这里参数名必须全部小写，且必须有序
		String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url="
				+ url;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			String signature = byteToHex(crypt.digest());
			map.put("signature", signature);
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		return map;
	}

	private String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	private String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}
