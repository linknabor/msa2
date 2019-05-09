/**
 * 
 */
package com.eshequ.msa.util.http;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eshequ.msa.exception.AppSysException;
import com.eshequ.msa.exception.BusinessException;

/**
 * @author davidhardson
 *
 */
@Component
public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	@Value("${http.defaultCharset:utf-8}")
	private String defaultCharset;

	@Autowired
	@Qualifier("defaultHttpClientBuilder")
	private HttpClientBuilder httpClientBuilder;
	
	
	/**
	 * 问号带参形式，默认字符集utf8
	 * @param url
	 * @return
	 */
	public String doGet(String url) {
		
		return doGet(url, defaultCharset);
	}

	/**
	 * 问号带参形式
	 * @param url
	 * @param charset
	 * @return
	 */
	public String doGet(String url, String charset) {
		String result = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpClient httpClient = getHttpClient();
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, charset);
			
		} catch (IOException e) {
			throw new AppSysException(e);
		}
		return result;
	}
	
	/**
	 * get请求，后面的map以键值对形式，默认字符集utf8
	 * @param url
	 * @param params
	 * @return
	 */
	public String doGet(String url, Map<Object, Object> params) {
		
		return doGet(url, params, defaultCharset);
	}
	
	/**
	 * 发送 GET 请求（HTTP），K-V形式, 默认utf8
	 *
	 * @param url
	 * @param params
	 * @return
	 */
	public String doGet(String url, Map<Object, Object> params, String charset) {
		StringBuffer param = new StringBuffer();
		int i = 0;
		for (Object key : params.keySet()) {
			if (i == 0)
				param.append("?");
			else
				param.append("&");
			param.append(key).append("=").append(params.get(key));
			i++;
		}
		url += param;
		String result = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpClient httpClient = getHttpClient();
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, charset);
		} catch (IOException e) {
			throw new AppSysException(e);
		}
		return result;
	}
	

	/**
	 * post默认字符集utf8
	 * @param url
	 * @param obj
	 * @return
	 */
	public String doPost(String url, Object obj) {

		return doPost(url, obj, defaultCharset);

	}

	@SuppressWarnings("unchecked")
	public String doPost(String url, Object obj, String charset) {
		
		String httpStr = "";
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {

			if (obj instanceof HashMap || obj instanceof TreeMap) {// 键值形式

				httpPost.setEntity(new UrlEncodedFormEntity(assembleRequestParams((Map<String, String>) obj),
						Charset.forName(charset)));

			} else if (obj instanceof String) {// json形式

				if (((String) obj).startsWith("<") && (((String) obj).endsWith(">"))) { // xml
					StringEntity stringEntity = new StringEntity(obj.toString(), charset);// 解决中文乱码问题
					stringEntity.setContentType("text/xml");
					httpPost.setEntity(stringEntity);
				} else {
					StringEntity stringEntity = new StringEntity(obj.toString(), charset);// 解决中文乱码问题
					stringEntity.setContentType("application/json");
					httpPost.setEntity(stringEntity);

				}

			} else {
				throw new BusinessException("invalid post object ! ");
			}
			httpPost.setHeader("Accept", "application/json");
			CloseableHttpClient httpClient = getHttpClient();
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			logger.info("response statusCode " + response.getStatusLine().getStatusCode());
			httpStr = EntityUtils.toString(entity, charset);

		} catch (Exception e) {

			throw new AppSysException(e);

		}

		return httpStr;

	}
	
	/**
	 * 获取httpClient实例，
	 * 从连接池中取不同的实例。
	 * 这里不用单例的CloseableHttpClient，100并发就上限了
	 * @return
	 */
	public CloseableHttpClient getHttpClient() {
		
		return httpClientBuilder.build();
	}
	
	/**
	 * 组装http请求参数
	 * 
	 * @param params
	 * @return
	 */
	private List<NameValuePair> assembleRequestParams(Map<String, String> params) {

		List<NameValuePair> nameValueList = new ArrayList<NameValuePair>(params.size());
		for (Map.Entry<String, String> entry : params.entrySet()) {
			NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
			nameValueList.add(pair);
		}
		return nameValueList;
	}

}
