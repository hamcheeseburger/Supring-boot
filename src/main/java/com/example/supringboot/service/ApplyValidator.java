package com.example.supringboot.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.supringboot.controller.ApplyForm;
import com.example.supringboot.domain.Order_reg;

@Component
public class ApplyValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Order_reg.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		validateCreditCard((Order_reg) obj, errors);
		validateShippingAddress((Order_reg) obj, errors);
	}

	public void validateCreditCard(Order_reg order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "card_num", "CCN_REQUIRED", "카드 번호를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "card_exp_dt", "EXPIRY_DATE_REQUIRED", "카드 유효기간을 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "card_type", "CARD_TYPE_REQUIRED", "카드 타입을 선택하세요.");
		errors.setNestedPath("");
	}

	public void validateShippingAddress(Order_reg order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiver_name", "NAME_REQUIRED", "수령인 이름을 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ship_addr1", "ADDRESS_REQUIRED", "첫 번째 주소를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ship_addr2", "ADDRESS_REQUIRED", "두 번째 주소를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ship_zip", "ZIP_REQUIRED", "우편번호를 입력하세요.");
		errors.setNestedPath("");
	}
}
