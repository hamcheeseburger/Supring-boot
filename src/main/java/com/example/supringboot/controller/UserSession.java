package com.example.supringboot.controller;

import java.io.Serializable;

import org.springframework.beans.support.PagedListHolder;

import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Item;

@SuppressWarnings("serial")
public class UserSession implements Serializable{
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public UserSession() {
		
	}
	
	public UserSession(Account account) {
		this.account = account;
	}
	
	private PagedListHolder<Item> myList;

	public void setMyList(PagedListHolder<Item> myList) {
		this.myList = myList;
	}

	public PagedListHolder<Item> getMyList() {
		return myList;
	}
}
