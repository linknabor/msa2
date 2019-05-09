package com.eshequ.msa.crm.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.eshequ.msa.crm.filter.LoginFilter;

public class FilterConfig {
	
	@Autowired
	private LoginFilter loginFilter;
	
	@Bean
    public FilterRegistrationBean<Filter> charsetEncodingFilterRegistration() {
		
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
        registration.setFilter(new CharacterEncodingFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("encoding", "UTF-8");
        registration.addInitParameter("forceEncoding", "true");
        registration.setName("encodingFilter");
        registration.setOrder(1);	//顺序
        return registration;
    }
	
	@Bean
	public FilterRegistrationBean<Filter> loginFilterRegistration() {
		
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
        registration.setFilter(loginFilter);
        registration.addUrlPatterns("/*");
        registration.setOrder(2);	//顺序
        return registration;
		
	}


}
