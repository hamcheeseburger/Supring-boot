package com.example.supringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import sp5.sp5chapcboot.interceptor.AuthCheckInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

//	@Autowired
//	private AuthCheckInterceptor authCheckInterceptor;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(authCheckInterceptor)
//				.addPathPatterns("/edit/**")
//				.excludePathPatterns("/edit/help/**");
//	}
	
	

}
