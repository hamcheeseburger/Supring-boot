package com.example.supringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.supringboot.controller.PostForm;

@Component
public class PostInsertValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return PostForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		PostForm postForm = (PostForm) target;

		try {
//			공백이거나 소수가 들어왔을때 에러 삽입
			int quantity = Integer.parseInt(postForm.getStr_quantity());
			if (quantity <= 0) {
				errors.rejectValue("str_quantity", "numberFormatException", "1 이상의 정수만 입력 가능합니다.");
			}
		} catch (Exception e) {
			errors.rejectValue("str_quantity", "numberFormatException", "1 이상의 정수만 입력 가능합니다.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "str_quantity", "required", "수량은 필수 항목입니다.");
		
		try {
//			공백이거나 소수가 들어왔을때 에러 삽입
			int price = Integer.parseInt(postForm.getStr_price());
			if (price < 0) {
				errors.rejectValue("str_price", "numberFormatException", "1 이상의 정수만 입력 가능합니다.");
			}
		} catch (Exception e) {
			errors.rejectValue("str_price", "numberFormatException", "1 이상의 정수만 입력 가능합니다.");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "str_price", "required", "수량은 필수 항목입니다.");
	}

}
