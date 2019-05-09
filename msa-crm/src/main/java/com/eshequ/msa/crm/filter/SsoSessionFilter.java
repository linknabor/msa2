package com.eshequ.msa.crm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 过滤下session
 * @author davidhardson
 *
 */
@Component("ssoSessionFilter")
@Order(Integer.MIN_VALUE + 49)	//这个在sessionRepositoryFilter之前执行，不要改变这个顺序
public class SsoSessionFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		String requestUri = request.getRequestURI();
		if (requestUri.contains("/actuator/health")) {
			//do nothing
			return;
		}else {
			chain.doFilter(req, resp);
		}
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
