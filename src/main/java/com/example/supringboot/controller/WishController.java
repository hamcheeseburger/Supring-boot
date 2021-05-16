package com.example.supringboot.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.domain.WishItem;
import com.example.supringboot.service.SupringBootFacade;

@Controller
//@SessionAttributes({"userSession"})
public class WishController {
	
	@Autowired
	private SupringBootFacade supringService;
	
	// 공구식품 찜하기
	@RequestMapping("/wish/add")
	public String insertWishItem(@ModelAttribute WishItem wish, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		int userId = userSession.getAccount().getUser_id();
		wish.setUser_id(userId);
		
		boolean isWishItem = supringService.isWishItem(userId, wish.getItem().getItem_id());
		
		// 찜하기 목록에 추가하려는 식품이 있을 경우
		if (isWishItem) {
			supringService.updateQuantity(wish);
		} else {
			// 없을 경우 새롭게 추가
			supringService.likeItem(wish);
		}
		
		return "redirect:/wish/list";
	}
	
	// 찜한 식품 목록 보기
	@RequestMapping("/wish/list")
	public ModelAndView wishList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/Wish/list");
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		int userId = userSession.getAccount().getUser_id();
		System.out.println("user_id: " + userId);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<WishItem> list = supringService.getLikedItem(userId);
		// 찜한 식품 전체 금액
		int totalPrice = supringService.totalMoney(userId);
		
		if (list.isEmpty()) {
			System.out.println("비어있음");
		} else {
			System.out.println("식품 이름: " + list.get(0).getItem().getFood().getName());	
			System.out.println("totalPrice: " + totalPrice);
		}
		
		map.put("wishList", list);
		map.put("wishListCount", list.size());
		map.put("totalPrice", totalPrice);
		
//		mav.setViewName("Wish/list");
		mav.addObject("map", map);
		
		return mav;
	}
	
	// 찜한 식품 목록에서 삭제하기
	@RequestMapping("/wish/remove")
	public String deleteWishList(@RequestParam int likedId) {
		supringService.cancelLikedItem(likedId);
		return "redirect:/wish/list";
	}
	
	// 공구 식품 상세 페이지에서 삭제하기 -> 찜하기 버튼 다시 누르면 삭제
	@RequestMapping("/wish/detail/remove")
	public String deleteItemDetail(@RequestParam int likedId, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int userId = userSession.getAccount().getUser_id();
		
		supringService.cancelDetailLikedItem(userId, likedId);
		return "redirect:/item/detail"; // 삭제 후 공구 식품 상세페이지로 이동
	}
	
	// 찜한 목록 다 삭제하기
	@RequestMapping("/wish/remove/list")
	public String deleteAllWishList(HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int userId = userSession.getAccount().getUser_id();
		supringService.deleteAllLikedItem(userId);
		
		return "redirect:/wish/list";
	}
	
	// 찜한 식품 수량 수정하기
	@RequestMapping("/wish/update")
	public String updateWishItem(HttpServletRequest request) {	
		int likedId = Integer.parseInt(request.getParameter("likedId"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		WishItem wish = supringService.getOneWishItem(likedId);
		wish.setAmount(amount);
		
//		System.out.println("wish_id: " + wish.getLiked_id());
//		System.out.println("name: " + wish.getItem().getTitle());
		
		supringService.updateLikedItem(wish);
		
		return "redirect:/wish/list";
		
	}

}
