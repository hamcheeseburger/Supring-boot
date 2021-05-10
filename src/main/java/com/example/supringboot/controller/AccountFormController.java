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

import com.example.supringboot.service.AccountFormValidator;
import com.example.supringboot.service.SupringBootFacade;


@Controller
@RequestMapping({"/account/newAccount", "/account/editAccount"})
@SessionAttributes("accountForm")
public class AccountFormController {
	private static final Logger logger = LoggerFactory.getLogger(AccountFormController.class);
	@Value("EditAccountForm")
	private String formViewName;
	
	@Value("index")
	private String successViewName;
	
	@Autowired
	private SupringBootFacade supringService;
	
	@Autowired
	private AccountFormValidator formValidator;
	
	@ModelAttribute("accountForm")
	public AccountForm formBackingObject() {
		return new AccountForm();
	}
	
//	@ModelAttribute("emailDomains")
//	public ArrayList<String> emailDomainsObject() {
//		ArrayList<String> emailDomains = new ArrayList<String>();
//		emailDomains.add("@dongduk.ac.kr");
//		emailDomains.add("@gmail.com");
//		emailDomains.add("@naver.com");
//		emailDomains.add("@daum.com");
//		emailDomains.add("직접입력");
//		
//		return emailDomains;
//	}
	
	@GetMapping
	public String showForm() {
		logger.info("showForm()");
		return formViewName;
	}
	
	@PostMapping
	public String onSubmit(HttpServletRequest request,
			@Valid @ModelAttribute("accountForm") AccountForm accountForm,
			BindingResult result) {
		logger.info("onSubmit()");
		
		logger.info("account.login_id : " + accountForm.getAccount().getLogin_id());
		logger.info("newAccount : " + accountForm.isNewAccount());
		
		formValidator.validate(accountForm, result);
		
		if(result.hasErrors()) return formViewName;
		
		logger.info("Errors : " + result.getErrorCount());
		
		if(accountForm.isNewAccount()) {
			supringService.insertAccount(accountForm.getAccount());
		}
		
		return successViewName;
	}
}
