package com.example.supringboot.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.supringboot.controller.AccountForm;
import com.example.supringboot.domain.Account;

@Component
public class AccountFormValidator implements Validator{

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
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.login_id", "required", "필수항목입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.password", "required", "필수항목입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatedPassword", "required", "필수항목입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.email", "required", "필수항목입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.name", "required", "필수항목입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.addr1", "required", "필수항목입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.addr2", "required", "필수항목입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.zip", "required", "필수항목입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.phone", "required", "필수항목입니다.");
		
		if(!accountForm.getRepeatedPassword().equals(account.getPassword())) {
			errors.reject("PASSWORD_MISMATCH", "비밀번호가 일치하지 않습니다.");
		}
		
		String newPhoneFormat = phone_format(account.getPhone());
		account.setPhone(newPhoneFormat);
	}
	
	public String phone_format(String number) { 
		String regEx = "(\\d{3})(\\d{3,4})(\\d{4})"; 
		return number.replaceAll(regEx, "$1-$2-$3"); 
	}

}
