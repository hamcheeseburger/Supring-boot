package com.example.supringboot.controller;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.example.supringboot.domain.Account;

@SuppressWarnings("serial")
public class AccountForm implements Serializable{
	private Account account;
	private boolean newAccount;
	private String repeatedPassword;
//	private String emailDomain;
	
	
	public AccountForm() {
		this.account = new Account();
		this.newAccount = true;
	}
	
	public AccountForm(Account account) {
		this.account = account;
		this.newAccount = false;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public boolean isNewAccount() {
		return newAccount;
	}
	public void setNewAccount(boolean newAccount) {
		this.newAccount = newAccount;
	}
	public String getRepeatedPassword() {
		return repeatedPassword;
	}
	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

//	public String getEmailDomain() {
//		return emailDomain;
//	}
//
//	public void setEmailDomain(String emailDomain) {
//		this.emailDomain = emailDomain;
//	}
}
