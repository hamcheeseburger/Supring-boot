package com.example.supringboot.service;

import java.util.ArrayList;

import com.example.supringboot.controller.PostForm;
import com.example.supringboot.domain.Post;

public interface PostService {
	public int registerPost(PostForm postForm);
	
	public int updatePost(PostForm postForm);
	
	public ArrayList<Post> getPostList();
	
	public Post getDetailPost(int post_id);
	
	public PostForm postToPostForm(Post post);
}
