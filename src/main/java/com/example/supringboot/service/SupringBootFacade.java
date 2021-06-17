package com.example.supringboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.domain.Post;
import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.domain.Item;

public interface SupringBootFacade {
	boolean insertAccount(Account account);
	Account getAccountByLoginId(String login_id);
	Account getAccount(String login_id, String password);
	
	boolean updateAccount(Account account);
	
	HashMap<String, ArrayList<Order_reg>> getMyOrderList(int user_id);
	
	Account getAccountById(int user_id);
	
	HashMap<String, ArrayList<Post>> getMyPostList(int user_id);
	
	ArrayList<Comment> getMyCommentList(int user_id);
	
	HashMap<String, ArrayList<Item>> getAdminItemList(int user_id);
	
	ArrayList<Order_reg> getAdminRegisterList(int item_id);

	String hashPassword(String password);
	
	boolean passwordCheck(String password, String hashPassword);
}
