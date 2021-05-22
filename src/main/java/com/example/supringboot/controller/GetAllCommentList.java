package com.example.supringboot.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.supringboot.domain.Comment;
import com.example.supringboot.service.CommentService;


@RestController
public class GetAllCommentList {
	private static final Logger logger = LoggerFactory.getLogger(GetAllCommentList.class);
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping(value = "/comments/{post_id}")
	public ArrayList<Comment> getCommentList(@PathVariable("post_id") Long idx,
			@ModelAttribute("params") Comment comment) {
		int post_id = idx.intValue();
		ArrayList<Comment> commentList = commentService.getAllCommentList(post_id);
		
		return commentList;
	}
}
