package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.company.filter.AuthenticationFilter;

@Configuration
@ComponentScan("com.infosys")
public class FilterConfig {
	
	@Autowired
	private AuthenticationFilter authenticationFilter;

	@Bean
	public FilterRegistrationBean authenticationFilter1() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(authenticationFilter);
		filterRegistrationBean.addUrlPatterns("/employee/*");
		return filterRegistrationBean;
	}
}
