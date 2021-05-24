package com.example.supringboot.mybatis.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Comment;

@Mapper
public interface CommentMapper {
	int insertComment(Comment comment) throws DataAccessException;
//	게시글 상세 조회시에는 post_id로 댓글 목록을 조회할 수 있을 수도..
	ArrayList<Comment> selectCommentByPostId(@Param("post_id")int post_id) throws DataAccessException;
//	ArrayList<Comment> selectCommentByPostId(Comment comment) throws DataAccessException;
	int updateComment(Comment comment) throws DataAccessException;
	int detailComment(@Param("comment_id") int comment_id) throws DataAccessException;
	// 댓글 삭제
	int deleteComment(@Param("comment_id") int comment_id, @Param("user_id") int user_id) throws DataAccessException;	
	// 댓글 개수 가져오기
	int numOfComment(@Param("post_id") int post_id) throws DataAccessException;
//	int numOfComment(Comment comment) throws DataAccessException;
}
