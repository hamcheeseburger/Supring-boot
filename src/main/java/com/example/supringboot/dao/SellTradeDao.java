package com.example.supringboot.dao;

import java.util.List;

import com.example.supringboot.domain.Comment;

public interface SellTradeDao {
	
	
	// 댓글 삭제
	public boolean deleteComment(int comment_id, int user_id);
	
	// 댓글 목록 가져오기
	List<Comment> getAllCommentList(int post_id);
	
	// 댓글 개수 가져오기
	int numOfComment(int post_id);
}
