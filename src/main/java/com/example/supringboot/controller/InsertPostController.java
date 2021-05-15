package com.example.supringboot.controller;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.supringboot.domain.Post;
import com.example.supringboot.service.PostService;




@Controller
@RequestMapping({"/post/createPost"})
@SessionAttributes({"postForm"})
public class InsertPostController {
	private static final Logger logger = LoggerFactory.getLogger(InsertPostController.class);

	
//	@Value("post/postInsertForm")
	@Value("index")
	private String postFormView;
	
	@Autowired
	private PostService postService;
	
	@ModelAttribute("postForm")
	public Post formBacking() {
		return new Post();
	}
	
	@ModelAttribute("trade_types")
	public String[] getTradeTypes() {
		return new String[] {"trade", "payment"};
	}
	
	@ModelAttribute("status")
	public String[] getStatus() {
		return new String[] {"0", "1"};
	}
	
	@ModelAttribute("ship_types")
	public String[] getShipTypes() {
		return new String[] {"0", "1"};
	}
	

	@GetMapping
	public String postForm() {
		return postFormView;
	}
}
