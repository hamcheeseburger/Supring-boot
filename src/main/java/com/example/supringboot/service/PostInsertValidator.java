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
//		if (postForm.getQuantity() % 1 != 0) {
//			errors.rejectValue("quantity","quantityIsNotInteger", "0 이상의 정수만 입력가능합니다.");
//		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "required", "수량은 필수 항목입니다.");
	}

}
