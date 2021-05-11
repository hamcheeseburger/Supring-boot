package com.example.supringboot.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.supringboot.controller.AccountForm;
import com.example.supringboot.domain.Account;

@Component
public class NewAccountFormValidator2 implements Validator{

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
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.password", "required", "비밀번호를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatedPassword", "required", "비밀번호를 재입력하세요.");
		
		if(!accountForm.getRepeatedPassword().equals(account.getPassword())) {
			errors.reject("PASSWORD_MISMATCH", "비밀번호가 일치하지 않습니다.");
		}
	}

}
