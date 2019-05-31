package com.eshequ.msa.config;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eshequ.msa.exception.AppSysException;

/**
 * 暂时没又加https握手 TODO,用了再加
 * 
 * @author david
 *
 */
@Configuration
public class HttpClientConfig {

	@Value("${http.maxTotal:100}")
	private int maxTotal;

	@Value("${http.maxPerRoute:50}")
	private int maxPerRoute;

	@Value("${http.connectTimeout:10000}")
	private int connectTimeout;

	@Value("${http.connectionRequestTimeout:10000}")
	private int connectionRequestTimeout;

	@Value("${http.socketTimeout:10000}")
	private int socketTimeout;

	@Value("${http.validateAfterInactivity:1000}") // TODO 值可能需要调整
	private int validateAfterInactivity;

	@Value("${http.retrytimes:3}")
	private int retryTimes;

	@Bean("defaultPoolingHttpClientConnectionManager")
	public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {

		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		// 设置连接池大小
		connManager.setMaxTotal(maxTotal);
		connManager.setDefaultMaxPerRoute(maxPerRoute);
		connManager.setValidateAfterInactivity(validateAfterInactivity);
		return connManager;
	}

	@Bean("defaultHttpClientBuilder")
	public HttpClientBuilder httpClientBuilder(
			@Qualifier("defaultPoolingHttpClientConnectionManager") PoolingHttpClientConnectionManager connManager,
			@Qualifier("httpClientRequestConfig") RequestConfig requestConfig,
			@Qualifier("httpRequestRetryHandler") HttpRequestRetryHandler httpRequestRetryHandler) {

		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setConnectionManager(connManager).setDefaultRequestConfig(requestConfig)
				.setRetryHandler(httpRequestRetryHandler);
		return builder;

	}

	/**
	 * 弱SSL
	 * @return
	 */
	@Bean("weakSslHttpClientBuilder")
	public HttpClientBuilder weakSslHttpClientBuild(@Qualifier("defaultPoolingHttpClientConnectionManager") PoolingHttpClientConnectionManager connManager,
			@Qualifier("httpClientRequestConfig") RequestConfig requestConfig, 
			@Qualifier("httpRequestRetryHandler") HttpRequestRetryHandler httpRequestRetryHandler) {
		
		HttpClientBuilder builder = null;
		try {
			//获得密匙库
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			
			//重写验证策略
			TrustStrategy strategy = new TrustSelfSignedStrategy(){
				@Override
				public boolean isTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					return true;
				}
			};
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(trustStore, strategy).build();
			
			/**
			 * 有需要的可以自己做验证
			 */
			HostnameVerifier verifier = new HostnameVerifier() {
				
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return hostname.equals(session.getPeerHost());
				}
			};
				
			LayeredConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, verifier);
			builder = HttpClients.custom().setSSLSocketFactory(sslSocketFactory)
			.setConnectionManager(connManager)
			.setDefaultRequestConfig(requestConfig).setRetryHandler(httpRequestRetryHandler);
			
		} catch (Exception e) {
			
			throw new AppSysException(e);
		}
		return builder;
		
		
	}

	@Bean("httpClientRequestConfig")
	public RequestConfig requestConfig() {

		RequestConfig.Builder builder = RequestConfig.custom();
		builder.setConnectTimeout(connectTimeout);
		builder.setConnectionRequestTimeout(connectionRequestTimeout);
		builder.setSocketTimeout(socketTimeout);
		return builder.build();
	}

	/**
	 * 重试机制handler
	 * 
	 * @return
	 */
	@Bean("httpRequestRetryHandler")
	public HttpRequestRetryHandler httpRequestRetryHandler() {

		HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {

			@Override
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {

				if (executionCount >= retryTimes) {// 如果已经重试了3次，就放弃
					return false;
				}
				if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
					return false;
				}
				if (exception instanceof InterruptedIOException) {// 超时
					return false;
				}
				if (exception instanceof UnknownHostException) {// 目标服务器不可达
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {// 连接被拒绝或超时
					return false;
				}
				if (exception instanceof SSLException) {// SSL握手异常
					return false;
				}

				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				// 如果请求是幂等的，就再次尝试
				if (!(request instanceof HttpEntityEnclosingRequest)) {
					return true;
				}
				return false;
			}
		};
		return httpRequestRetryHandler;
	}

}
