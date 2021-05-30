package com.example.supringboot.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.example.supringboot.domain.Account;
import com.example.supringboot.service.SignOnValidator;
import com.example.supringboot.service.SupringBootFacade;

@Controller
@RequestMapping({"/account/signOnForm"})
@SessionAttributes({"userSession", "account"})
public class SignOnController {
	private static final Logger logger = LoggerFactory.getLogger(SignOnController.class);
	
	@Autowired
	private SupringBootFacade supringService;
	
	@Value("/Account/signOnForm")
	private String signOnFormView;
	
	@Autowired
	private SignOnValidator signOnValidator;
	
	
	@ModelAttribute("account")
	public Account formBacking() {
		return new Account();
	}
	
	@GetMapping
	public String signOnForm() {
		logger.info("signOnForm()");
		return signOnFormView;
	}
	
	@PostMapping
	public ModelAndView handleLogin(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("account") Account account,  BindingResult result,
			@RequestParam(value="forwardAction", required=false) String forwardAction,
			Model model) {
		logger.info("handleLogin()");
		
		signOnValidator.validate(account, result);
		if(result.hasErrors()) return new ModelAndView(signOnFormView, "account", account);
			
		Account dbAccount = supringService.getAccountByLoginId(account.getLogin_id());
				
		if(dbAccount == null) {
			result.reject("LoginFail", "존재하지 않는 아이디 입니다.");
			return new ModelAndView(signOnFormView, "account", account);
		}

//		방법1
		if(!supringService.passwordCheck(account.getPassword(), dbAccount.getPassword())) {
			result.reject("LoginFail", "비밀번호가 일치하지 않습니다.");
			return new ModelAndView(signOnFormView, "account", account);
		}
		
		
		// 유저 세션 생성(sessionAttributes 때문에 자동으로 session에 저장됨)
		UserSession userSession = new UserSession();
		userSession.setAccount(dbAccount);
		model.addAttribute("userSession", userSession);
		
		if(forwardAction != null) {
			return new ModelAndView("redirect:" + forwardAction);
		}
		
		return new ModelAndView("redirect:/");
	}
}
