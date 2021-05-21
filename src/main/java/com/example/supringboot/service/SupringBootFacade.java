package com.example.supringboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.domain.Post;
import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Comment;

public interface SupringBootFacade {
	void insertAccount(Account account);
	
	Account getAccount(String login_id, String password);
	
	boolean updateAccount(Account account);
	
	HashMap<String, ArrayList<Order_reg>> getMyOrderList(int user_id);
	
	Account getAccountById(int user_id);
	
	ArrayList<Post> getMyPostList(int user_id);
	
	ArrayList<Comment> getMyCommentList(int user_id);
}
