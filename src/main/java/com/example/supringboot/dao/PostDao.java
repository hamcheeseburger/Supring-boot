package com.example.supringboot.dao;

import java.util.ArrayList;

import com.example.supringboot.controller.PostForm;
import com.example.supringboot.domain.Criteria;
import com.example.supringboot.domain.Post;

public interface PostDao {
	public ArrayList<Post> getAllPostList(Post post);
//	post 제목 기준 검색
	public ArrayList<Post> searchPostList(String title);
	public Post detailPost(int post_id);
	public int updatePost(Post post, PostForm postForm);
	public int removePost(int post_id);
	public int getPostCount();
	public int insertPost(Post post);
	
	public ArrayList<Post> selectPostList(Post post);
	public int selectPostTotalCount (Post post);
}
