package com.example.supringboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
	
	@Value("Item/applyForm")
	private String applyFormView;
	
	@Value("Item/editApply")
	private String editApplyView;
	
	@Value("Item/viewApply")
	private String viewApply;
	
	@Value("Item/applySuccess")
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
	
	// ?????? ???????????? -> ?????? ?????? ????????????????????? ?????? ??????
	@RequestMapping("/item/applyForm")
	public String orderForm(HttpServletRequest request, 
			@ModelAttribute("applyForm") ApplyForm applyForm, ModelMap model, RedirectAttributes redirectAttr) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		// ???????????? ???????????? ?????? ?????? ????????? ???????????? ???????????????
		if (userSession == null) {
			return "redirect:/account/signOnForm";
		}
		else {
			Account account = userSession.getAccount();
			
			int amount = Integer.parseInt(request.getParameter("amount")); // ?????? ????????? ??????
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			
			// ?????? ?????? ????????? ????????? ??????
			boolean isApply = applyService.isApplyItem(account.getUser_id(), itemId);
			
			if (isApply) {
				redirectAttr.addFlashAttribute("isApply", isApply);
				
				// ?????? ????????? ?????? ?????? ???????????? ??????
				String referer = request.getHeader("Referer");
				System.out.println("?????? ?????????: " + referer);
				return "redirect:" + referer;
			}
			else {
				Item item = itemService.getDetailItem(itemId);
				
				int total = amount * item.getItem_price() + item.getShip_price(); // ??? ??????
				int itemTotal = amount * item.getItem_price();
				applyForm.setItemTotalPrice(itemTotal);
				
				applyForm.getOrder().initOrder(account, item, amount, total);
				
				return applyFormView;
			}
			
		}
		
	}
	
	@RequestMapping("/item/apply")
	public String applySubmit(@ModelAttribute("applyForm") ApplyForm applyForm,
			BindingResult result,
			SessionStatus status) {
		applyValidator.validate(applyForm, result);
		
		if (result.hasErrors()) {
			return applyFormView;
		}
		
		applyService.applyItem(applyForm.getOrder());

		status.setComplete();
		return applySuccess; // ?????????????????? ???????????? ?????????
	}
	
	@RequestMapping("item/apply/cancel")
	public String cancelApplying(HttpServletRequest request, @RequestParam int applyId) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int userId = userSession.getAccount().getUser_id();
		
		if (applyService.cancelItem(applyId, userId)) {
			return "redirect:/account/myOrderList"; // ?????? ???????????? ?????? ?????? ????????? ???????????? ??????
		}
		else {
			return viewApply;
		}
		
	}
	
	@RequestMapping("/item/apply/confirm")
	public String applyConfirm(HttpServletRequest request, @RequestParam int applyId, ModelMap model) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int userId = userSession.getAccount().getUser_id();
		
		System.out.println("?????? ??????: " + applyId);
		Order_reg apply = applyService.getOrderById(applyId, userId);
		System.out.println("?????? ??????: " + apply.getItem().getFood().getName());
		System.out.println("????????? ??????: " + apply.getUser().getName());
		
		model.put("itemTotalPrice", apply.getOrd_price() - apply.getItem().getShip_price());
		model.put("detail", apply);
		
		return viewApply;
	}
	
	@GetMapping("/item/apply/update")
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
	
	@PostMapping("/item/apply/update")
	public String applyUpdate(@ModelAttribute("applyForm") ApplyForm applyForm, BindingResult result, SessionStatus status) {
		
		applyValidator.validate(applyForm, result);
		
		if (result.hasErrors()) {
			return editApplyView;
		}
		
		applyService.applyUpdate(applyForm.getOrder());

		status.setComplete();
		return applySuccess; // ?????????????????? ???????????? ?????????
	}
	
}
