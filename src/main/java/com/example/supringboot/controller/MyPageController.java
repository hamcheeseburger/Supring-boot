package com.example.supringboot.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.service.EditAccountFormValidator;
import com.example.supringboot.service.SupringBootFacade;

@Controller
//@SessionAttributes("selection")
public class MyPageController {
	private static final Logger logger = LoggerFactory.getLogger(MyPageController.class);
	
	@Value("/Account/myPage")
	String myPageView;
	
	@Autowired
	EditAccountFormValidator editAccountFormValidator;
	
	@Autowired
	SupringBootFacade supringService;
	
	@GetMapping("/account/myPage")
	public String myPage() {
		return myPageView;
	}
	
	// 내정보 수정하기 폼
	@GetMapping("/account/editAccount")
	public ModelAndView editAccountForm(HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		AccountForm accountForm = new AccountForm();
		int user_id = userSession.getAccount().getUser_id();
		accountForm.setAccount(supringService.getAccountById(user_id));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(myPageView);
		modelAndView.addObject("accountForm", accountForm)
					.addObject("selection", 0);
		return modelAndView;
	}
	
	@PostMapping("/account/editAccount")
	public ModelAndView editAccount(HttpServletRequest request,
			@ModelAttribute("accountForm") AccountForm accountForm,  BindingResult result) {
		Account account = accountForm.getAccount();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(myPageView);
		modelAndView.addObject("selection", 0);
		
		logger.info("account.login_id : " + account.getLogin_id());
		
		editAccountFormValidator.validate(accountForm, result);
		
		if(result.getErrorCount() > 0) return modelAndView;
		
		if (supringService.updateAccount(account)) {
			modelAndView.addObject("message", "수정에 성공하였습니다.");
		} else {
			modelAndView.addObject("message", "수정에 실패하였습니다.");
		}
		
		return modelAndView;
	}
	
	@GetMapping("/account/myOrderList")
	public ModelAndView myOrderList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(myPageView);
		modelAndView.addObject("selection", 1);
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int user_id = userSession.getAccount().getUser_id();
		
		logger.info("user_id : " + user_id);
		
		HashMap<String, ArrayList<Order_reg>> hashMap = supringService.getMyOrderList(user_id);
		logger.info("OrderRegs length : " + hashMap.get("orderRegs").size());
		logger.info("Orders length : " + hashMap.get("orders").size());


		modelAndView.addObject("orderRegs", hashMap.get("orderRegs"))
					.addObject("orders", hashMap.get("orders"));
	
		return modelAndView;
	}
 
}
