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
		validateCreditCard((ApplyForm) obj, errors);
		validateShippingAddress((ApplyForm) obj, errors);
	}

	public void validateCreditCard(ApplyForm order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "card_num", "CCN_REQUIRED", "카드 번호를 입력하세요.");
		
		String newCardNumFormat = card_num_format(order.getOrder().getCard_num());
		if(newCardNumFormat.length() > 0 && newCardNumFormat.length() != 19) {
			errors.rejectValue("card_num", "invalid_card_num", "카드 번호 형식이 올바르지 않습니다. ex) 0000-0000-0000-0000");
		} else {
			order.getOrder().setCard_num(newCardNumFormat);
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "card_exp_dt", "EXPIRY_DATE_REQUIRED", "카드 유효기간을 입력하세요.");
		
		String newCardExpFormat = card_exp_format(order.getOrder().getCard_exp_dt());
		if(newCardExpFormat.length() > 0 && newCardExpFormat.length() != 5) {
			errors.rejectValue("card_exp_dt", "invalid_card_exp", "유효 기간 형식이 올바르지 않습니다. ex) MM/yy");
		} else {
			order.getOrder().setCard_exp_dt(newCardExpFormat);
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "card_type", "CARD_TYPE_REQUIRED", "카드사를 선택하세요.");
		errors.setNestedPath("");
	}

	public void validateShippingAddress(ApplyForm order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiver_name", "NAME_REQUIRED", "수령인 이름을 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ship_addr1", "ADDRESS_REQUIRED", "첫 번째 주소를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ship_addr2", "ADDRESS_REQUIRED", "두 번째 주소를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ship_zip", "ZIP_REQUIRED", "우편번호를 입력하세요.");
		errors.setNestedPath("");
	}

	public String card_num_format(String number) { 
		String regEx = "(\\d{4})(\\d{4})(\\d{4})(\\d{4})"; 
		return number.replaceAll(regEx, "$1-$2-$3-$4");
	}
	
	public String card_exp_format(String number) { 
		String regEx = "(\\d{2})(\\d{2})"; 
		return number.replaceAll(regEx, "$1/$2");
	}
	
}
