package com.example.supringboot.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.domain.WishItem;
import com.example.supringboot.service.WishService;

@Controller
public class WishController {
	
	@Autowired
	private WishService wishService;
	
	// 공구식품 찜하기
	@RequestMapping("/wish/add")
	public String insertWishItem(HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		// 로그인된 사용자가 아닐 경우 로그인 페이지로 이동시키기
		if (userSession == null) {
			return "redirect:/account/signOnForm";
		}
		else {
			int userId = userSession.getAccount().getUser_id();
			int amount = Integer.parseInt(request.getParameter("amount"));
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			System.out.println("item_id: " + itemId);
			
			boolean isWishItem = wishService.isWishItem(userId, itemId);
			
			// 찜하기 목록에 추가하려는 식품이 있을 경우
			if (isWishItem) {
				wishService.updateQuantity(userId, itemId, amount);
			} else {
				// 없을 경우 새롭게 추가
				wishService.likeItem(userId, itemId, amount);
			}
			
			return "redirect:/wish/list";
		}
		
	}
	
	// 찜한 식품 목록 보기
	@RequestMapping("/wish/list")
	public ModelAndView wishList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("Wish/wishList");
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		// 로그인하지 않았을 경우 어떻게 처리할지 생각해야함
		
		int userId = userSession.getAccount().getUser_id();
		System.out.println("user_id: " + userId);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<WishItem> list = wishService.getLikedItem(userId);
		// 찜한 식품 전체 금액
		int totalPrice = wishService.totalMoney(userId);
		
		if (list.isEmpty()) {
			System.out.println("비어있음");
		} else {
			System.out.println("식품 이름: " + list.get(0).getItem().getFood().getName());	
			System.out.println("totalPrice: " + totalPrice);
		}
		
		map.put("wishList", list);
		map.put("wishListCount", list.size());
		map.put("totalPrice", totalPrice);
		
		mav.addObject("user", userSession);
		mav.addObject("map", map);
		
		return mav;
		
	}
	
	// 찜한 식품 목록에서 삭제하기
	@RequestMapping("/wish/remove")
	public String deleteWishList(@RequestParam int likedId) {
		wishService.cancelLikedItem(likedId);
		return "redirect:/wish/list";
	}
	
	// 공구 식품 상세 페이지에서 삭제하기 -> 찜하기 버튼 다시 누르면 삭제
	@RequestMapping("/wish/detail/remove")
	public String deleteItemDetail(@RequestParam int likedId, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int userId = userSession.getAccount().getUser_id();
		
		wishService.cancelDetailLikedItem(userId, likedId);
		return "redirect:/item/detail"; // 삭제 후 공구 식품 상세페이지로 이동
	}
	
	// 찜한 목록 다 삭제하기
	@RequestMapping("/wish/remove/list")
	public String deleteAllWishList(HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int userId = userSession.getAccount().getUser_id();
		wishService.deleteAllLikedItem(userId);
		
		return "redirect:/wish/list";
	}
	
	// 찜한 식품 수량 수정하기
	@RequestMapping("/wish/update")
	public String updateWishItem(HttpServletRequest request) {	
		int likedId = Integer.parseInt(request.getParameter("likedId"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		WishItem wish = wishService.getOneWishItem(likedId);
		wish.setAmount(amount);
		
//		System.out.println("wish_id: " + wish.getLiked_id());
//		System.out.println("name: " + wish.getItem().getTitle());
		
		wishService.updateLikedItem(wish);
		
		return "redirect:/wish/list";
		
	}

}
