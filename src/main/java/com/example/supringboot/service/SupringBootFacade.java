package com.example.supringboot.service;

import com.example.supringboot.domain.Account;

public interface SupringBootFacade {
	void insertAccount(Account account);
	
	Account getAccount(String login_id, String password);
}
