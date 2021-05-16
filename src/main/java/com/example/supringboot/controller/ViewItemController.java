package com.example.supringboot.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.supringboot.domain.Item;
import com.example.supringboot.service.WishServiceImpl;

@Controller
public class ViewItemController {
	@Autowired
	private WishServiceImpl wishService;

	// 공구 식품 목록 보기
	@RequestMapping("/item/list")
	public ModelAndView itemList() {
		ModelAndView mav = new ModelAndView("/Item/itemList");

		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Item> allList = wishService.getAllItem();
		List<Item> goingList = wishService.getGoingItem();
		List<Item> endList = wishService.getEndItem();

		map.put("allItemList", allList);
		map.put("allItemCount", allList.size());
		
		map.put("goingItemList", goingList);
		map.put("goingItemCount", goingList.size());
		
		map.put("endItemList", endList);
		map.put("endItemCount", endList.size());

		mav.addObject("map", map);

		return mav;
	}
	
	// 특정 공구 식품 보기
	@RequestMapping("/item/detail")
	public String detailItem(@RequestParam int itemId, ModelMap model) {
		Item item = wishService.getDetailItem(itemId);
		model.put("detail", item);
		
		return "/Item/detailItem";
	}
	
}
