package com.eshequ.msa.config;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 底层实现用了httpclient
 * @author david
 *
 */
@Configuration
public class RestTempletConfig {
	
	@Value("${http.connectTimeout:10000}")
	private int connectTimeout;
	
	@Value("${http.connectionRequestTimeout:10000}")
	private int connectionRequestTimeout;
	
	@Value("${http.readTimeout:10000}")
	private int readTimeout;
	
	/**
	 * 一般http请求用
	 * @param factory
	 * @return
	 */
	@Bean("restTemplate")
    public RestTemplate restTemplate(@Qualifier("httpRequestFactory") HttpComponentsClientHttpRequestFactory factory){
	
		return new RestTemplate(factory);
    }
	
	/**
	 * 弱ssl验证
	 * @param factory
	 * @return
	 */
	@Bean("restTemplateWeakSsl")
    public RestTemplate restTemplateWeakSsl(@Qualifier("weakSslHttpRequestFactory") HttpComponentsClientHttpRequestFactory factory){
	
		return new RestTemplate(factory);
    }
	
	@Bean("httpRequestFactory")
	public HttpComponentsClientHttpRequestFactory httpRequestFactory(@Qualifier("defaultHttpClientBuilder")HttpClientBuilder httpClientBuilder) {

		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClientBuilder.build());// httpClient连接配置
		clientHttpRequestFactory.setBufferRequestBody(false);
		clientHttpRequestFactory.setConnectTimeout(connectTimeout);
		clientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
		clientHttpRequestFactory.setReadTimeout(readTimeout);
		return clientHttpRequestFactory;
	}
	
	
	@Bean("weakSslHttpRequestFactory")
	public HttpComponentsClientHttpRequestFactory weakSslHttpRequestFactory(@Qualifier("weakSslHttpClientBuilder")HttpClientBuilder httpClientBuilder) {
		
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClientBuilder.build());// httpClient连接配置
		clientHttpRequestFactory.setBufferRequestBody(false);
		clientHttpRequestFactory.setConnectTimeout(connectTimeout);
		clientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
		clientHttpRequestFactory.setReadTimeout(readTimeout);
		return clientHttpRequestFactory;
		
	}

}
