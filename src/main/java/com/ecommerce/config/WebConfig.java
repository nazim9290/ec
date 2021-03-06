package com.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ecommerce.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/product/test",
				"/user/login",
				"/user/signup", "/user/doLogin", "/user/insert");

	}


}
