package com.example.supringboot.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
			//키워드 검색이 먼저 수행되어야 함을 확인하는
			ValidationUtils.rejectIfEmpty(errors, "item_price", "notEmpty", "상품금액은 필수 입력 값입니다.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "notEmpty", "게시글 제목은 필수 입력 값입니다.");
			ValidationUtils.rejectIfEmpty(errors, "ship_price", "notEmpty", "배송비는 필수 입력 값입니다.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "created_dt", "notEmpty", "공구 시작일은 필수 입력 값입니다.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "end_dt", "notEmpty", "공구 마감일은 필수 입력 값입니다.");
			ValidationUtils.rejectIfEmpty(errors, "min_quantity", "notEmpty", "목표수량은 필수 입력 값입니다.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "notEmpty", "상세 설명은 필수 입력 값입니다.");
			
//			//상품금액, 배송비 빈칸 검사
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "item_price", "thissss", "상품금액은 필수 입력 값입니다.");
//			
//			
//			int item_price = Integer.parseInt(itemForm.getStr_item_price());
//			if (item_price <= 0) {
//				errors.rejectValue("str_item_price", "numberFormatException", "1 이상의 정수만 입력 가능합니다.");
//			}
		} catch (Exception e) {
			errors.rejectValue("str_item_price", "numberFormatException", "1 이상의 정수만 입력 가능합니다.");
		}
	}

}
