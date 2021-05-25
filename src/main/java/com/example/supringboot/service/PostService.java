package com.example.supringboot.service;

import java.util.ArrayList;

import com.example.supringboot.controller.PostForm;
import com.example.supringboot.domain.Criteria;
import com.example.supringboot.domain.Post;

public interface PostService {
	public int registerPost(PostForm postForm);
	
	public int updatePost(PostForm postForm);
	
	public int deletePost(int post_id);
	
	public ArrayList<Post> getPostList(Post post);
	
	public Post getDetailPost(int post_id);
	
	public PostForm postToPostForm(Post post);
	
	public ArrayList<Post> getPostLatest3Rows();
}
