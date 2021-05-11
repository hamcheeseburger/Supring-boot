package com.example.supringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.supringboot.controller.AccountForm;
import com.example.supringboot.dao.AccountDao;
import com.example.supringboot.domain.Account;

@Component
public class NewAccountFormValidator1 implements Validator {
	
	@Autowired
	AccountDao accountDao;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Account.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		AccountForm accountForm = (AccountForm) target;
		Account account = accountForm.getAccount();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.login_id", "required", "아이디를 입력하세요.");
	
		// 아이디 중복 여부 검증..
		Account result = accountDao.getAccountByLoginId(account.getLogin_id());
	
		if(result != null) {
			errors.reject("DuplicatedLoginId", "중복되는 아이디 입니다.");
		}
	}


}
