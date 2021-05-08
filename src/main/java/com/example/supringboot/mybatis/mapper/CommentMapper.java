package com.example.supringboot.mybatis.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Comment;

@Mapper
public interface CommentMapper {
	int insertComment(Comment comment) throws DataAccessException;
	ArrayList<Comment> selectCommentByPostId(@Param("post_id")int post_id) throws DataAccessException;
	int updateComment(Comment comment) throws DataAccessException;
	
}
