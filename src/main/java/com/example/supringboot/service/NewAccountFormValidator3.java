package com.example.supringboot.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.supringboot.controller.AccountForm;
import com.example.supringboot.domain.Account;

@Component
public class NewAccountFormValidator3 implements Validator{

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
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.email", "required", "이메일을 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.name", "required", "이름을 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.addr1", "required", "첫 번째 주소를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.addr2", "required", "두 번째 주소를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.zip", "required", "우편번호를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.phone", "required", "핸드폰 번호를 입력하세요.");
		
		// 핸드폰 형식이 이상할 시에 변경
		String newPhoneFormat = phone_format(account.getPhone());
		account.setPhone(newPhoneFormat);
	}
	
	public String phone_format(String number) { 
		String regEx = "(\\d{3})(\\d{3,4})(\\d{4})"; 
		return number.replaceAll(regEx, "$1-$2-$3"); 
	}
}
