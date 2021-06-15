package com.example.supringboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.supringboot.domain.Category;
import com.example.supringboot.domain.Item;
import com.example.supringboot.service.ItemService;
import com.example.supringboot.service.WishServiceImpl;

@Controller
public class ViewItemController {
	@Autowired
	private WishServiceImpl wishService;
	
	@Autowired
	private ItemService itemService;
	
	@ModelAttribute("categoryList")
	public ArrayList<Category> categoryList() {
		return itemService.selectAllCategory();
	}
	
	// 공구 식품 목록 보기
	@RequestMapping("/item/list")
	public ModelAndView itemList(@RequestParam(required=false, defaultValue="-1") Integer cat_id,
			@ModelAttribute("params") Item item, Model model) {
		System.out.println("cat_id : " + cat_id);
		
		ModelAndView mav = new ModelAndView("/Item/itemList");
		
		if(cat_id == -1) {
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			List<Item> allList = wishService.getAllItem();
//			List<Item> goingList = wishService.getGoingItem();
//			List<Item> endList = wishService.getEndItem();
		
//			map.put("allItemList", allList);
//			map.put("allItemCount", allList.size());
		
//			map.put("goingItemList", goingList);
//			map.put("goingItemCount", goingList.size());
		
//			map.put("endItemList", endList);
//			map.put("endItemCount", endList.size());

//			mav.addObject("map", map);
			
			ArrayList<Item> itemList = itemService.getItemList(item);
			mav.addObject("itemList", itemList);
		}else {
			item.setCat_id(cat_id);
			ArrayList<Item> catItemList = itemService.selectItemWithCategory(item);
			Category cat = itemService.getCategoryById(cat_id);
			
			mav.addObject("catName", cat.getCat_name());
			mav.addObject("catItemList", catItemList);
		}
		return mav;
	}
	
	// 특정 공구 식품 보기
	@RequestMapping("/item/detail")
	public String detailItem(@RequestParam int itemId, ModelMap model) {
		Item item = wishService.getDetailItem(itemId);
		System.out.println("detail food name: " + item.getFood().getName());
		
		Category cat = itemService.getCategoryById(item.getFood().getCat_id());
		
		model.put("detail", item);
		model.put("category", cat);
		
		return "/Item/detailItem";
	}
	
}
