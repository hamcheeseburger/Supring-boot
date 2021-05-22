package com.example.supringboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.supringboot.controller.SignOnInterceptor;

import com.example.supringboot.interceptor.*;
//import sp5.sp5chapcboot.interceptor.AuthCheckInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private SignOnInterceptor interceptor;
	
	@Autowired
	private LoggerInterceptor loggerInterceptor;
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("main");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
				.addPathPatterns("/account/**")
				.excludePathPatterns("/account/signOnForm", "/account/signOff", "/account/newAccount/**");
		
		registry.addInterceptor(loggerInterceptor)
				.addPathPatterns("/post/**")
				.excludePathPatterns("/css/**", "/js/**", "/board/**", "/assets/**", "/adminLTE/**");
	}
	
	@Bean
	public MessageSource validationMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/validation");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(validationMessageSource());
		return bean;
	}
	
	
}
