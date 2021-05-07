package com.example.supringboot.dao;

import com.example.supringboot.domain.Account;

public interface AccountDao {
	public Account getAccountByLoginId(String login_id);
	public void insertAccount(Account account);
	public boolean loginCheck(String login_id, String password);
	public boolean updateAccount(Account account);
}
