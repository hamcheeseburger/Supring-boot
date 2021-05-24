package com.example.supringboot.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.supringboot.domain.Comment;

public interface CommentDao {
	public ArrayList<Comment> getAllCommentList(int post_id);
//	public ArrayList<Comment> getAllCommentList(Comment comment);
	public int insertComment(Comment comment);
	public int updateComment(Comment comment);
	
	// 댓글 삭제
	public int deleteComment(int comment_id, int user_id);
	// 댓글 개수 가져오기
	public int numOfComment(int post_id);
//	public int numOfComment(Comment comment);
}
