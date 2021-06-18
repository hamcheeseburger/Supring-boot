package com.example.supringboot.mybatis.mapper;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.domain.Post;

@Mapper
public interface AccountMapper {
	Account getAccountById(int user_id) throws DataAccessException;
	Account getAccountByLoginId(String login_id) throws DataAccessException;
	int insertAccount(Account account) throws DataAccessException, SQLException;
	Account getAccount(@Param("login_id") String login_id, @Param("password") String password) throws DataAccessException;
	int updateAccount(Account account) throws DataAccessException;

//	진행중, 실패한 공구신청목록
	ArrayList<Order_reg> selectOrderRegWithUserId(@Param("user_id") int user_id) throws DataAccessException;
//	성공한 공구신청목록
	ArrayList<Order_reg> selectOrderWithUserId(@Param("user_id") int user_id) throws DataAccessException;
//	내가 판매 및 교환 등록한 식품 조회 (거래중)
	ArrayList<Post> selectPostsWithUserId(@Param("user_id") int user_id) throws DataAccessException;
// 	내가 게시한 댓글 목록                                             
	ArrayList<Comment> selectCommentsWithUserId(@Param("user_id") int user_id) throws DataAccessException;
//	내가 판매 및 교환 등록한 식품 조회 (거래완료)
	ArrayList<Post> selectCompletePostWithUserId(@Param("user_id") int user_id) throws DataAccessException;

}
