package com.example.supringboot.dao;

import java.util.List;

import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Item;

public interface AccountDao {
	public Account getAccountByLoginId(String login_id);
	public void insertAccount(Account account);
	public boolean loginCheck(String login_id, String password);
	public boolean updateAccount(Account account);
	
	// 찜한 식품 확인
	public List<Item> getWishItem(int user_id);
}
