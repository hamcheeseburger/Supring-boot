package com.example.supringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.supringboot.domain.Account;

@Component
public class SignOnValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Account.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Account account = (Account) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login_id", "required", "아이디를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "비밀번호를 입력하세요.");
		
//		Account result = supringService.getAccount(account.getLogin_id(), account.getPassword());
//		if(result == null) {
//			errors.reject("LoginFail", "아이디 혹은 비밀번호가 일치하지 않습니다.");
//		}else {
//			result.setPassword("");
//			account = result;
//		}
	}
	
}
