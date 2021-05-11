package com.example.supringboot.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.domain.Post;
import com.example.supringboot.domain.Item;

public interface AccountDao {
	public Account getAccountByLoginId(String login_id);
	public void insertAccount(Account account);
	public Account getAccount(String login_id, String password);
	public boolean updateAccount(Account account);
//	진행중, 실패한 공구신청목록
	public ArrayList<Order_reg> selectMyOrderRegs(int user_id);
//	성공한 공구 신청 목록
	public ArrayList<Order_reg> selectMyOrders (int user_id);
//	내가 게시한 식품 교환 및 결제 게시글 목록
	public ArrayList<Post> selectMyPosts(int user_id);
//	내가 게시한 댓글 목록 (User 객체 삽입 안됨)
	public ArrayList<Comment> selectMyComments(int user_id);
	
	// 찜한 식품 확인
	public List<Item> getWishItem(int user_id);
}
