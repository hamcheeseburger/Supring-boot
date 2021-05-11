package com.example.supringboot.mybatis.mapper;

import java.util.ArrayList;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.domain.Post;
import com.example.supringboot.domain.Item;

@Mapper
public interface AccountMapper {
	Account getAccountByLoginId(String login_id) throws DataAccessException;
	void insertAccount(Account account) throws DataAccessException;
	Account getAccount(@Param("login_id") String login_id, @Param("password") String password) throws DataAccessException;
	int updateAccount(Account account) throws DataAccessException;

//	진행중, 실패한 공구신청목록
	ArrayList<Order_reg> selectOrderRegWithUserId(@Param("user_id") int user_id) throws DataAccessException;
//	성공한 공구신청목록
	ArrayList<Order_reg> selectOrderWithUserId(@Param("user_id") int user_id) throws DataAccessException;
//	내가 판매 및 교환 등록한 식품 조회
	ArrayList<Post> selectPostsWithUserId(@Param("user_id") int user_id) throws DataAccessException;
// 	내가 게시한 댓글 목록                                             
	ArrayList<Comment> selectCommentsWithUserId(@Param("user_id") int user_id) throws DataAccessException;

	
	// 찜한 식품 확인
	List<Item> getWishItem(int user_id) throws DataAccessException;

}
