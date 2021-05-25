package com.example.supringboot.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Post;
import com.example.supringboot.service.ItemService;
import com.example.supringboot.service.PostService;

@Component
public class MainPageInterceptor implements HandlerInterceptor {
	@Autowired
	PostService postService;
	
	@Autowired
	ItemService itemService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		ArrayList<Item> item3List = (ArrayList<Item>) itemService.getItemLatest3Rows();
		ArrayList<Post> post3List = postService.getPostLatest3Rows();
		
		modelAndView.addObject("post3List", post3List)
		.addObject("item3List", item3List);
	}
	
}
