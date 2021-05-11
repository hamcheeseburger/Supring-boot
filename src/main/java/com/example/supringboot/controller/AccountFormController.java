package com.example.supringboot.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.supringboot.service.NewAccountFormValidator1;
import com.example.supringboot.service.NewAccountFormValidator2;
import com.example.supringboot.service.NewAccountFormValidator3;
import com.example.supringboot.service.SupringBootFacade;


@Controller
@RequestMapping({"/account/newAccount/*"})
@SessionAttributes("accountForm")
public class AccountFormController {
	private static final Logger logger = LoggerFactory.getLogger(AccountFormController.class);
	@Value("Account/newAccountForm1")
	private String step1FormView;
	
	@Value("Account/newAccountForm2")
	private String step2FormView;
	
	@Value("Account/newAccountForm3")
	private String step3FormView;
	
	@Value("Account/successNewAccount")
	private String successView;
	
	@Autowired
	private SupringBootFacade supringService;
	
	@Autowired
	private NewAccountFormValidator1 step1Validator;
	@Autowired
	private NewAccountFormValidator2 step2Validator;
	@Autowired
	private NewAccountFormValidator3 step3Validator;
	
	
	@ModelAttribute("accountForm")
	public AccountForm formBackingObject() {
		return new AccountForm();
	}
	
	@GetMapping("/account/newAccount/step1")
	public String step1Form() {
		logger.info("step1Form()");
		return step1FormView;
	}

	@PostMapping("/account/newAccount/step1")
	public String step1Submit(HttpServletRequest request,
			@Valid @ModelAttribute("accountForm") AccountForm accountForm,
			BindingResult result) {
		logger.info("step1Submit()");
		
		logger.info("account.login_id : " + accountForm.getAccount().getLogin_id());
		
		step1Validator.validate(accountForm, result);
		
		if(result.hasErrors()) return step1FormView;
		
		logger.info("Errors : " + result.getErrorCount());
			
		return step2FormView;
	}
	
	@PostMapping("/account/newAccount/step2")
	public String step2Submit(HttpServletRequest request,
			@Valid @ModelAttribute("accountForm") AccountForm accountForm,
			BindingResult result) {
		logger.info("step2Submit()");
		logger.info("account.password : " + accountForm.getAccount().getPassword());
		logger.info("repeatedPassword : " + accountForm.getRepeatedPassword());
		
		step2Validator.validate(accountForm, result);
		
		if(result.hasErrors()) return step2FormView;
		
		logger.info("Errors : " + result.getErrorCount());
		
		return step3FormView;
	}
	
	@PostMapping("/account/newAccount/step3")
	public String step3Submit(HttpServletRequest request,
			@Valid @ModelAttribute("accountForm") AccountForm accountForm,
			BindingResult result, SessionStatus sessionStatus) {
		logger.info("step3Submit()");
		logger.info("account.email : " + accountForm.getAccount().getEmail());
		logger.info("account.name : " + accountForm.getAccount().getName());
		
		
		step3Validator.validate(accountForm, result);
		if(result.hasErrors()) return step3FormView;
		
		// DB에 회원정보 Insert
		if(accountForm.isNewAccount()) {
			supringService.insertAccount(accountForm.getAccount());
		}
		
		// session에서 객체 삭제..
		sessionStatus.setComplete();
		
		return successView;
	}
	
}
