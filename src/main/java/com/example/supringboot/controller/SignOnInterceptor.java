package com.example.supringboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.domain.Account;

@Component
public class SignOnInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws ModelAndViewDefiningException {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
			if (userSession == null) {
				String url = request.getRequestURL().toString(); 
				String query = request.getQueryString();
				ModelAndView modelAndView = new ModelAndView("/Account/signOnForm");
				if (query != null) {
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
