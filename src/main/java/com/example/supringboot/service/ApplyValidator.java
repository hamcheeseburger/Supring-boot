package com.example.supringboot.service;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.supringboot.domain.Order_reg;

public class ApplyValidator {
	public boolean supports(Class<?> clazz) {
		return Order_reg.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		validateCreditCard((Order_reg) obj, errors);
		validateShippingAddress((Order_reg) obj, errors);
	}

	public void validateCreditCard(Order_reg order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCard", "CCN_REQUIRED", "FAKE (!) credit card number required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expiryDate", "EXPIRY_DATE_REQUIRED", "Expiry date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardType", "CARD_TYPE_REQUIRED", "Card type is required.");
		errors.setNestedPath("");
	}

	public void validateShippingAddress(Order_reg order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipToFirstName", "FIRST_NAME_REQUIRED", "Shipping Info: first name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipToLastName", "LAST_NAME_REQUIRED", "Shipping Info: last name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipAddress1", "ADDRESS_REQUIRED", "Shipping Info: address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipCity", "CITY_REQUIRED", "Shipping Info: city is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipState", "STATE_REQUIRED", "Shipping Info: state is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipZip", "ZIP_REQUIRED", "Shipping Info: zip/postal code is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipCountry", "COUNTRY_REQUIRED", "Shipping Info: country is required.");
		errors.setNestedPath("");
	}
}
