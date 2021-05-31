package com.example.supringboot.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.supringboot.controller.ItemForm;

@Component
public class ItemFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ItemForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ItemForm itemForm = (ItemForm)target;
		try {
//			공백이거나 소수가 들어왔을때 에러 삽입
			int item_price = Integer.parseInt(itemForm.getStr_item_price());
			if (item_price <= 0) {
				errors.rejectValue("str_item_price", "numberFormatException", "1 이상의 정수만 입력 가능합니다.");
			}
		} catch (Exception e) {
			errors.rejectValue("str_item_price", "numberFormatException", "1 이상의 정수만 입력 가능합니다.");
		}
	}

}
