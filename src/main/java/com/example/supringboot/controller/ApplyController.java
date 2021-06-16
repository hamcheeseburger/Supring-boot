package com.example.supringboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.service.ApplyService;
import com.example.supringboot.service.ApplyValidator;
import com.example.supringboot.service.ItemService;

@Controller
@SessionAttributes({"applyForm"})
public class ApplyController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private ApplyValidator applyValidator;
	
	@Value("item/applyForm")
	private String applyFormView;
	
	@Value("item/editApply")
	private String editApplyView;
	
	@Value("item/viewApply")
	private String viewApply;
	
	@Value("item/applySuccess")
	private String applySuccess;
	
	@ModelAttribute("applyForm")
	public ApplyForm createApplyForm() {
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
	@RequestMapping("/item/applyForm")
	public String orderForm(HttpServletRequest request, 
			@ModelAttribute("applyForm") ApplyForm applyForm) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		// 로그인된 사용자가 아닐 경우 로그인 페이지로 이동시키기
		if (userSession == null) {
			return "redirect:/account/signOnForm";
		}
		else {
			Account account = userSession.getAccount();
			
			int amount = Integer.parseInt(request.getParameter("amount")); // 공구 신청할 수량
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			Item item = itemService.getDetailItem(itemId);
			System.out.println("food name: " + item.getFood().getName());
			int total = amount * item.getItem_price() + item.getShip_price(); // 총 금액
			int itemTotal = amount * item.getItem_price();
			applyForm.setItemTotalPrice(itemTotal);
			
			applyForm.getOrder().initOrder(account, item, amount, total);
			
			return applyFormView;
		}
		
	}
	
	@RequestMapping("/item/apply/success")
	public String applySubmit(@ModelAttribute("applyForm") ApplyForm applyForm,
			BindingResult result,
			SessionStatus status) {
		applyValidator.validate(applyForm, result);
		
		if (result.hasErrors()) {
			return applyFormView;
		}
		
		applyService.applyItem(applyForm.getOrder());

		status.setComplete();
		return applySuccess; // 공구신청내역 상세보기 페이지
	}
	
	@RequestMapping("item/apply/cancel")
	public String cancelApplying(HttpServletRequest request, @RequestParam int applyId) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int userId = userSession.getAccount().getUser_id();
		
		if (applyService.cancelItem(applyId, userId)) {
			return "redirect:/account/myOrderList"; // 신청 취소하면 신청 내역 리스트 페이지로 이동
		}
		else {
			return viewApply;
		}
		
	}
	
	@RequestMapping("/item/apply/confirm")
	public String applyConfirm(HttpServletRequest request, @RequestParam int applyId, ModelMap model) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int userId = userSession.getAccount().getUser_id();
		
		System.out.println("신청 번호: " + applyId);
		Order_reg apply = applyService.getOrderById(applyId, userId);
		System.out.println("신청 식품: " + apply.getItem().getFood().getName());
		System.out.println("신청자 이름: " + apply.getUser().getName());
		
		model.put("itemTotalPrice", apply.getOrd_price() - apply.getItem().getShip_price());
		model.put("detail", apply);
		
		return viewApply;
	}
	
	@RequestMapping("/item/apply/updateForm")
	public ModelAndView editAccountForm(HttpServletRequest request, @RequestParam int applyId) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int userId = userSession.getAccount().getUser_id();
		
		ApplyForm applyForm = new ApplyForm();
		applyForm.setOrder(applyService.getOrderById(applyId, userId));
		
		int itemTotal = applyForm.getOrder().getQuantity() * applyForm.getOrder().getItem().getItem_price();
		applyForm.setItemTotalPrice(itemTotal);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(editApplyView);
		modelAndView.addObject("applyForm", applyForm);
		return modelAndView;
	}
	
	@RequestMapping("/item/apply/update")
	public String applyUpdate(@ModelAttribute("applyForm") ApplyForm applyForm, BindingResult result, SessionStatus status) {
		
		applyValidator.validate(applyForm, result);
		
		if (result.hasErrors()) {
			return editApplyView;
		}
		
		applyService.applyUpdate(applyForm.getOrder());

		status.setComplete();
		return applySuccess; // 공구신청내역 상세보기 페이지
	}
	
}
