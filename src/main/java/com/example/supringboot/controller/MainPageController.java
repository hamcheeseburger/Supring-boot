package com.example.supringboot.controller;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.supringboot.dao.ItemDao;
import com.example.supringboot.dao.PostDao;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Post;
import com.example.supringboot.service.ItemService;
import com.example.supringboot.service.PostService;

@Controller
public class MainPageController {
	@Value("/main")
	String mainView;
	
	@Value("/main/search")
	String searchView;
	
	@Autowired
	PostService postService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	PostDao postDao;
	
	@Autowired
	ItemDao itemDao;
	
//	@GetMapping("/")
//	public ModelAndView mainPage() {
//		ModelAndView modelAndView = new ModelAndView(mainView);
//		
//		ArrayList<Item> item3List = (ArrayList<Item>) itemService.getItemLatest3Rows();
//		ArrayList<Post> post3List = postService.getPostLatest3Rows();
//		
//		modelAndView.addObject("post3List", post3List)
//		.addObject("item3List", item3List);
//		
//		return modelAndView;
//	}
	
	@PostMapping("/main/search")
	public ModelAndView mainSearchPage(@RequestParam String keyword) {
		ModelAndView modelAndView = new ModelAndView(searchView);
		
		ArrayList<Post> postList = postDao.searchPostFromMain(keyword);
		ArrayList<Item> itemList = itemDao.searchItemFromMain(keyword);

		modelAndView.addObject("postList", postList)
		.addObject("itemList", itemList)
		.addObject("keyword", keyword);
		
		return modelAndView;
	}
}
