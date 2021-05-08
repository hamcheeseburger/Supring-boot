package com.example.supringboot.dao;

import java.util.ArrayList;

import com.example.supringboot.domain.Comment;

public interface CommentDao {
	public ArrayList<Comment> getAllCommentList(int post_id);
	public int insertComment(Comment comment);
	public int updateComment(Comment comment);
}
