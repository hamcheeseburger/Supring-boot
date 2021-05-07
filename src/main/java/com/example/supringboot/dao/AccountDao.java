package com.example.supringboot.dao;

import com.example.supringboot.domain.Account;

public interface AccountDao {
	public Account getAccountByLoginId(String login_id);
}
