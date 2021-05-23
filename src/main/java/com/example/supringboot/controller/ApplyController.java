package com.example.supringboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Item;
import com.example.supringboot.service.ApplyValidator;
import com.example.supringboot.service.WishServiceImpl;

@Controller
@SessionAttributes({"applyForm"})
public class ApplyController {
	
	@Autowired
	private WishServiceImpl wishService;
	
	@Autowired
	private ApplyValidator applyValidator;
	
	@Value("item/applyForm")
	private String applyFormView;
	
	@Value("item/viewApply")
	private String applySuccess;
	
	@ModelAttribute("applyForm")
	public ApplyForm createApplyForm(HttpServletRequest request) {
		return new ApplyForm();
	}
	
	@ModelAttribute("creditCardTypes")
	public List<String> referenceData() {
		ArrayList<String> creditCardTypes = new ArrayList<String>();
		creditCardTypes.add("Visa");
		creditCardTypes.add("MasterCard");
		creditCardTypes.add("American Express");
		return creditCardTypes;
	}
	
	// 공구 신청하기 -> 공구 식품 상세페이지에서 버튼 클릭, 찜하기 목록에서 각 식품 옆에 주문하기 버튼 클릭?
	@GetMapping("/item/apply")
	public String orderForm() {
		return applyFormView;
	}
	
	@PostMapping("/item/apply")
	public String applySubmit(HttpServletRequest request,
			@Valid @ModelAttribute("applyForm") ApplyForm applyForm,
			BindingResult result) {
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Account account = userSession.getAccount();
		
		int amount = Integer.parseInt(request.getParameter("amount")); // 공구 신청할 수량
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		Item item = wishService.getDetailItem(itemId);
		int total = amount * item.getItem_price(); // 총 금액
		
		applyForm.getOrder().initOrder(account, item, amount, total);
		applyValidator.validate(applyForm, result);
		
		if (result.hasErrors()) return applyFormView;
		
		wishService.applyItem(applyForm.getOrder());

		return "applySuccess";
	}
	
	@RequestMapping("/item/apply/confirm")
	public String applyConfirm() {
		
		return "Item/itemList";
	}
	
}
