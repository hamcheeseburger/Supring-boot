package com.example.supringboot.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.domain.Account;

@Component
public class SignOnInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(SignOnInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws ModelAndViewDefiningException {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
			if (userSession == null) {
				String url = request.getRequestURL().toString(); 
				String query = request.getQueryString();
				String previousUrl = request.getParameter("previousUrl");
				
				logger.info(url);
				logger.info(query);
				logger.info(previousUrl);
				
				
				
				ModelAndView modelAndView = new ModelAndView("/Account/signOnForm");
				
				if(previousUrl != null) {
					modelAndView.addObject("signonForwardAction", previousUrl);
				}
				else if (query != null) {
					modelAndView.addObject("signonForwardAction", url+"?"+query);
				}
				else {
					modelAndView.addObject("signonForwardAction", url);
				}
				modelAndView.addObject(new Account());
				throw new ModelAndViewDefiningException(modelAndView);
						
			}
			else {
				return true;
			}
			
	}
}
