package com.example.supringboot.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.domain.Post;
import com.example.supringboot.domain.WishItem;
import com.example.supringboot.service.EditAccountFormValidator;
import com.example.supringboot.service.SupringBootFacade;
import com.example.supringboot.service.WishService;

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
	
	@Autowired
	WishService wishService;
	
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
		logger.info(accountForm.getAccount().getPassword());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(myPageView);
		modelAndView.addObject("accountForm", accountForm)
					.addObject("selection", 0);
		return modelAndView;
	}
	
	@PostMapping("/account/editAccount")
	public ModelAndView editAccount(HttpServletRequest request,
			@ModelAttribute("accountForm") AccountForm accountForm, BindingResult result) {
		logger.info(accountForm.getUpdatePassword());
		logger.info(accountForm.getNewPassword());
		logger.info(accountForm.getNewPasswordCheck());
		
		Account account = accountForm.getAccount();
		logger.info(account.getPassword()); // null..
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(myPageView);
		modelAndView.addObject("selection", 0);
		
		logger.info("account.login_id : " + account.getLogin_id());
		
		editAccountFormValidator.validate(accountForm, result);
		Account accountForPassword = supringService.getAccountById(account.getUser_id());
		
		if(!accountForm.getUpdatePassword().equals("") && !supringService.passwordCheck(accountForm.getUpdatePassword(), accountForPassword.getPassword())) {
			result.rejectValue("updatePassword", "passwordNotMatch", "비밀번호가 틀렸습니다.");
		}
		
		// error가 있다면 update 하지 않게 return
		if(result.getErrorCount() > 0) return modelAndView;
		
		// 새로운 비밀번호로 update
		if(!accountForm.getNewPassword().equals("")) {
			String newPassword = supringService.hashPassword(accountForm.getNewPassword());
			account.setPassword(newPassword);
		}else { // 새로운 비밀번호가 없다면 원래 password로 update
			account.setPassword(accountForPassword.getPassword());
		}
		
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
	
	@GetMapping("/account/myPostList")
	public ModelAndView myPostList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(myPageView);
		modelAndView.addObject("selection", 2);
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int user_id = userSession.getAccount().getUser_id();
		logger.info("user_id : " + user_id);
		
		HashMap<String, ArrayList<Post>> map = supringService.getMyPostList(user_id);
			
		modelAndView.addObject("postList", map.get("postList"));
		modelAndView.addObject("completePostList", map.get("completePostList"));

		return modelAndView;
	}
	
	@GetMapping("/account/wish/list")
	public ModelAndView myWishList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(myPageView);
		modelAndView.addObject("selection", 3);
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		
		int user_id = userSession.getAccount().getUser_id();
		logger.info("user_id : " + user_id);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<WishItem> list = wishService.getLikedItem(user_id);
		
		// 찜한 식품 전체 금액
		int totalPrice = wishService.totalMoney(user_id);
		
		if (list.isEmpty()) {
			System.out.println("비어있음");
		} else {
			System.out.println("식품 이름: " + list.get(0).getItem().getFood().getName());	
			System.out.println("totalPrice: " + totalPrice);
		}
		
		map.put("wishList", list);
		map.put("wishListCount", list.size());
		map.put("totalPrice", totalPrice);
		
		modelAndView.addObject("map", map);
		
		return modelAndView;
	}
	
	@GetMapping("/account/wish/remove")
	public String removeWish(@RequestParam int likedId) {
		wishService.cancelLikedItem(likedId);
		return "redirect:/account/wish/list";
	}
	
	@PostMapping("/account/wish/update")
	public String updateWish(HttpServletRequest request) {
		int likedId = Integer.parseInt(request.getParameter("likedId"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		WishItem wish = wishService.getOneWishItem(likedId);
		wish.setAmount(amount);
		
//		System.out.println("wish_id: " + wish.getLiked_id());
//		System.out.println("name: " + wish.getItem().getTitle());
		
		wishService.updateLikedItem(wish);
		
		return "redirect:/account/wish/list";
	}
	
	@GetMapping("/account/myCommentList")
	public ModelAndView myCommentList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(myPageView);
		modelAndView.addObject("selection", 4);
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		int user_id = userSession.getAccount().getUser_id();
		logger.info("user_id : " + user_id);
		
		ArrayList<Comment> commentList = supringService.getMyCommentList(user_id);
		
		modelAndView.addObject(commentList);
		return modelAndView;
	}
	
	
}
