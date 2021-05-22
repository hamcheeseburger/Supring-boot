package com.example.supringboot.mybatis.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Criteria;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.domain.Post;

@Mapper
public interface PostMapper {
//	모든 POST 목록 가져오기
	ArrayList<Post> getAllPost(Post post) throws DataAccessException;
//	모든 POST 목록 가져오기 2
	ArrayList<Post> selectPostList(Post post) throws DataAccessException;
	
//	POST 검색
	ArrayList<Post> getPostListByTitle(@Param("title") String title) throws DataAccessException;
//	POST 상세보기
	Post getOnePostById(@Param("post_id") int post_id) throws DataAccessException;
//	POOST 삽입
	int insertPost(Post post) throws DataAccessException;
//	POST 수정
	int updatePost(Post post) throws DataAccessException;
//	POST 삭제
	int deletePost(@Param("post_id") int post_id) throws DataAccessException;
// 	POST 갯수 가져오기
	int getPostCount() throws DataAccessException;
	
//	검색 후 결과에 해당하는 게시글 갯수 가져오기
	int selectPostTotalCount(Post post) throws DataAccessException;

}
