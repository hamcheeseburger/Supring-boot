package com.example.supringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supringboot.dao.mybatis.MybatisPostDao;
import com.example.supringboot.domain.Post;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	MybatisPostDao postDao;

	@Override
	public boolean registerPost(Post post) {
		int result = 0;
		
		result = postDao.insertPost(post);
		
		return (result == 1) ? true : false;
		
	}
	
	
}
