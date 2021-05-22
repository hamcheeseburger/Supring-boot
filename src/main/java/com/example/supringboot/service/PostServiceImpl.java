package com.example.supringboot.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supringboot.controller.PostForm;
import com.example.supringboot.dao.mybatis.MybatisPostDao;
import com.example.supringboot.domain.Post;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	MybatisPostDao postDao;

	@Override
	public boolean registerPost(PostForm postForm) {
		int result = 0;
	
		Post post = new Post();
		post.setUser(postForm.getUser());
		post.setTitle(postForm.getTitle());
		post.setContent(postForm.getContent());
		post.setFood_name(postForm.getFood_name());
		post.setPrice(postForm.getPrice());
		post.setTrade_type(postForm.getTrade_type());
		post.setTrade_status(postForm.getTrade_status());
		post.setQuantity(postForm.getQuantity());
		post.setUnit(postForm.getUnit());	
//		postForm의 날짜를 Timestamp로 변환
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		    Date parsedDate = (Date) dateFormat.parse(postForm.getExp_dt_string());
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    post.setExp_dt(timestamp);
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		post.setShip_type(postForm.getShip_type());
		if (postForm.getImages() != null)
			post.setImages(postForm.getImages());
		
		result = postDao.insertPost(post);
		
		return (result == 1) ? true : false;
		
	}

	@Override
	public boolean updatePost(PostForm post) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Post> getPostList() {
		ArrayList<Post> postList = new ArrayList<Post>();
		int postCnt = postDao.getPostCount();
		
		if (postCnt > 0)
			postList = postDao.getAllPostList();
		
		return postList;
	}
	
	
}
