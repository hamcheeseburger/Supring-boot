package com.example.supringboot.dao.mybatis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.supringboot.dao.SellTradeDao;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.mybatis.mapper.SellTradeMapper;

@Repository
public class MybatisSellTradeDao implements SellTradeDao {

	@Autowired
	private SellTradeMapper sellTradeMapper;
	
	@Override
	public boolean deleteComment(int comment_id, int user_id) {
		int result = sellTradeMapper.deleteComment(comment_id, user_id);
		
		if(result == 1) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<Comment> getAllCommentList(int post_id) {
		List<Comment> commentList = new ArrayList<Comment>();
		commentList = sellTradeMapper.getAllComment(post_id);
		
		if (commentList.isEmpty()) {
			return Collections.emptyList();
		}
		else {
			return commentList;
		}
	}

	@Override
	public int numOfComment(int post_id) {
		return sellTradeMapper.numOfComment(post_id);
	}

}
