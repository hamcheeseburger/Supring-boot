package com.example.supringboot.mybatis.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Comment;

public interface SellTradeMapper {

	
	// 댓글 삭제
	int deleteComment(int comment_id) throws DataAccessException;
		
	// 댓글 목록 가져오기
	List<Comment> getAllComment(int post_id) throws DataAccessException;
	Account getUser(int user_id) throws DataAccessException;
		
	// 댓글 개수 가져오기
	int numOfComment(int post_id) throws DataAccessException;
}
