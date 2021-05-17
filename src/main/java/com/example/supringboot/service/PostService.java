package com.example.supringboot.service;

import java.util.ArrayList;

import com.example.supringboot.controller.PostForm;
import com.example.supringboot.domain.Post;

public interface PostService {
	public boolean registerPost(PostForm post);
	
	public boolean updatePost(PostForm post);
	
	public ArrayList<Post> getPostList();
}
