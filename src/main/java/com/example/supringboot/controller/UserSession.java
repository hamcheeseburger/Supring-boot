package com.example.supringboot.controller;

import java.io.Serializable;

import com.example.supringboot.domain.Account;

@SuppressWarnings("serial")
public class UserSession implements Serializable{
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
