package com.example.supringboot.service;

import com.example.supringboot.controller.PostForm;
import com.example.supringboot.domain.Post;

public interface PostService {
	public boolean registerPost(PostForm post);
}
