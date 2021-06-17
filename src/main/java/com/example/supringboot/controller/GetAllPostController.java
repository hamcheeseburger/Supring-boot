package com.example.supringboot.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.domain.Criteria;
import com.example.supringboot.domain.Post;
import com.example.supringboot.service.PostService;

import org.springframework.ui.Model;

@Controller
public class GetAllPostController {
	private static final Logger logger = LoggerFactory.getLogger(GetAllPostController.class);
	
	@Value("post/postList")
	private String postListView;
	
	@Autowired
	private PostService postService;
	
	@GetMapping(value = "/post/getPostList")
	public String openPostList(@ModelAttribute("params") Post post, 
			Model model, HttpServletRequest request) {
		ArrayList<Post> postList = postService.getPostList(post);
		model.addAttribute("postList", postList);
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		int user_id = -1;
		if (userSession != null) {
			user_id = userSession.getAccount().getUser_id();
		}
		model.addAttribute("user", user_id);
		
		return postListView;
	}
}
