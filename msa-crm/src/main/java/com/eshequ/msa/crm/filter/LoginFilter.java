package com.eshequ.msa.crm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eshequ.msa.crm.service.login.LoginRemote;

@Component("loginFilter")
public class LoginFilter implements Filter {
	@Autowired
	private LoginRemote loginRemote;
	
	@Value("${sso.login.url}")
	private String ssoUrlLogin;
	
	@Value("${common.login.url}")
	private String commonLoginUrl;
	
	private static final String validateURI = "/sso/?reqUrl";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest)req;
//		HttpServletResponse response = (HttpServletResponse)resp;
//		HttpSession httpSession = request.getSession();
//		String requestUri = request.getRequestURI();
//		String ssoToken =request.getParameter("token");//url带来的token,就是sso创建的token
//		String sessionId = request.getParameter("sessionId");//sso的sessionId
//		String isToken = "";
//		String query = request.getQueryString();//url后的参数
//		if(query != null) {
//			requestUri = requestUri+"?"+query;
//		}
//		if (requestUri.contains("/actuator/health")) {
//			//health check, do nothing
//		}else if(requestUri.contains(validateURI)) {
//			//如果是检查token页面，不予拦截
//			chain.doFilter(request, response);
//		}else {
//			System.out.println(requestUri);
//			System.out.println(httpSession.getId());
//			Boolean isLogin = (Boolean) httpSession.getAttribute("isLogin");//根据是否有token判断是否登录(crm token)
//			StringBuffer urlBuf = request.getRequestURL();
//			String reqUrl  = urlBuf.toString();
//			
//			if(isLogin != null) {
//				//如果发现crm是登录状态，直接去目标页面
////				response.setHeader("location", reqUrl);
//				chain.doFilter(request, response);
//			}else if(ssoToken != null) {
//				isToken = loginRemote.checkSsoToken(ssoToken,sessionId);//检验token是否真实
//				if(isToken.equals("true")) {
//					//真实有效
//					//如果未登录，设置为登录状态
//					if(isLogin == null) {				
//						httpSession.setAttribute("isLogin", true);
//						httpSession.setAttribute("token", ssoToken);//储存token到crm，方便之后注销
//					}
////					response.setHeader("location", reqUrl);
//					chain.doFilter(request, response);
////					response.sendRedirect(reqUrl);
//					return;
//				}else {
//					//失败 登录页面
//					response.sendRedirect(ssoUrlLogin+"?loginStatus=0");
//					return;
//				}	
//			}
//			else {
//				//未登录，重定向到sso认证中心，为了正确的建立用户和sso认证中心的会话，不应该直接使用response.sendRedirect()
//				//因为这样它的实际效果是crm-->sso之间创建了会话，而不是user-->sso
//				//所以使用跳转到crm下的html页面，在从中ajax请求sso的认证中心，来保证session的正确
////				response.sendRedirect("http://192.168.0.101:9091/sso/ssoAuthentication?reqUrl="+reqUrl);
//				
//				response.sendRedirect(commonLoginUrl+"?reqUrl="+reqUrl);
////				http://192.168.0.112:8080/pass?reqUrl=http://192.168.0.101:9091/sso/error.html?token=&sessionId=68a258c6-47c2-4d39-8e4d-fe6a1006fece
////				response.sendRedirect("http://"+crmLocalhostIp+reqUrlCheckSsoToken+"?reqUrl="+reqUrl);
//				
//				return;
//			}
//			
			
//		}
		chain.doFilter(req, resp);	
		}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
