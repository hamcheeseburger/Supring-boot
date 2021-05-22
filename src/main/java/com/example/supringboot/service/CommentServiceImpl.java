package com.example.supringboot.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supringboot.dao.mybatis.MybatisCommentDao;
import com.example.supringboot.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private MybatisCommentDao commentDao;
	
	private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

	@Override
	public ArrayList<Comment> getAllCommentList(int post_id) {
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		int commentTotalCount = commentDao.numOfComment(post_id);
		if (commentTotalCount > 0) {
			commentList = commentDao.getAllCommentList(post_id);
		}

		return commentList;
	}

	@Override
	public int insertComment(Comment comment) {
		int result = 0;
		
		result = commentDao.insertComment(comment);
		return result;
	}

	@Override
	public int updateComment(Comment comment) {
		int result = 0;
		
		result = commentDao.updateComment(comment);
		return result;
	}

	@Override
	public int deleteComment(int comment_id, int user_id) {
		int result = 0;
		
		result = commentDao.deleteComment(comment_id, user_id);
		
		return result;
	}

	@Override
	public ArrayList<Comment> timestampToStr(ArrayList<Comment> commentList) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ArrayList<Comment> newList = new ArrayList<Comment>();
		for(Comment c: commentList) {
			String cString = "";
			String mString = "";
			
			if (c.getCreated_dt() != null) {
				try {
					cString = formatter.format(c.getCreated_dt());
				} catch (Exception e) {
					// TODO: handle exception
				}		
			}
			if (c.getModified_dt() != null) {
				try {
					mString = formatter.format(c.getModified_dt());
				} catch (Exception e) {
					// TODO: handle exception
				}	
			}
			c.setStr_created_dt(cString);
			c.setStr_modified_dt(mString);
			newList.add(c);
		}
		return newList;
	}

	
}
