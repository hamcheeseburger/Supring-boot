package com.example.supringboot.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Comment;

@Mapper
public interface SellTradeMapper {
	
	
	// 댓글 삭제
	int deleteComment(@Param("comment_id") int comment_id, @Param("user_id") int user_id) throws DataAccessException;
		
	// 댓글 목록 가져오기
	List<Comment> getAllComment(int post_id) throws DataAccessException;
		
	// 댓글 개수 가져오기
	int numOfComment(int post_id) throws DataAccessException;
}
