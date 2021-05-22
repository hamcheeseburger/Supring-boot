package com.example.supringboot.service;

import java.util.ArrayList;

import com.example.supringboot.domain.Comment;

public interface CommentService {
	public ArrayList<Comment> getAllCommentList(int post_id);
	
	public int insertComment(Comment comment);
	
	public int updateComment(Comment comment);
	
	// 댓글 삭제
	public int deleteComment(int comment_id, int user_id);
	
	// timestamp to str
	public ArrayList<Comment> timestampToStr(ArrayList<Comment> commentList);
}
