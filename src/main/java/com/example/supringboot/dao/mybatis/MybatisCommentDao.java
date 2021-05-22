package com.example.supringboot.dao.mybatis;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.supringboot.dao.CommentDao;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.mybatis.mapper.AccountMapper;
import com.example.supringboot.mybatis.mapper.CommentMapper;

@Repository
public class MybatisCommentDao implements CommentDao{
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public ArrayList<Comment> getAllCommentList(int post_id) {
		return commentMapper.selectCommentByPostId(post_id);
	}
	

	@Override
	public int insertComment(Comment comment) {
		return commentMapper.insertComment(comment);
	}

	@Override
	public int updateComment(Comment comment) {
		return commentMapper.updateComment(comment);
	}
	
	@Override
	public int deleteComment(int comment_id, int user_id) {
		int result = commentMapper.deleteComment(comment_id, user_id);
		
		return result;
	}
	
	@Override
	public int numOfComment(int post_id) {
		return commentMapper.numOfComment(post_id);
	}

//	@Override
//	public ArrayList<Comment> getAllCommentList(Comment comment) {
//		// TODO Auto-generated method stub
//		return commentMapper.selectCommentByPostId(comment);
//	}
//
//	@Override
//	public int numOfComment(Comment comment) {
//		// TODO Auto-generated method stub
//		return commentMapper.numOfComment(comment);
//	}
}
