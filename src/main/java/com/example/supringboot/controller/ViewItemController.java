package com.example.supringboot.controller;

import java.util.ArrayList;

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

@Controller
public class ViewItemController {	
	@Autowired
	private ItemService itemService;
	
	@ModelAttribute("categoryList")
	public ArrayList<Category> categoryList() {
		return itemService.selectAllCategory();
	}
	
	// 공구 식품 목록 보기
	@RequestMapping("/item/list")
	public ModelAndView itemList(@RequestParam(required=false, defaultValue="-1", name="cat_id") Integer cat_id,
			@RequestParam(required=false, defaultValue="going", name="status") String status,
			@ModelAttribute("params") Item item, Model model) {
		System.out.println("cat_id : " + cat_id);
		
		ModelAndView mav = new ModelAndView("/Item/itemList");
		
		if(cat_id == -1) {
			ArrayList<Item> itemList = null;
			
			if (status.equals("all")) {
				itemList = itemService.getItemList(item);
			}
			else if (status.equals("going")) {
				itemList = itemService.getGoingItem(item);
			}
			else if (status.equals("end")) {
				itemList = itemService.getEndItem(item);
			}
			
			mav.addObject("status", status);
			mav.addObject("itemList", itemList);
			
			if (itemList == null) {
				mav.addObject("itemSize", 0);
			}
			
		}else {
			item.setCat_id(cat_id);
			ArrayList<Item> catItemList = itemService.selectItemWithCategory(item);
			Category cat = itemService.getCategoryById(cat_id);
			
			mav.addObject("catName", cat.getCat_name());
			mav.addObject("catItemList", catItemList);
			
			if (catItemList == null) {
				mav.addObject("catSize", 0);
			}
		}
		return mav;
	}
	
	// 특정 공구 식품 보기
	@RequestMapping("/item/detail")
	public String detailItem(@RequestParam int itemId, ModelMap model) {
		Item item = itemService.getDetailItem(itemId);
		System.out.println("detail food name: " + item.getFood().getName());
		
		Category cat = itemService.getCategoryById(item.getFood().getCat_id());
		
		model.put("detail", item);
		model.put("category", cat);
		
		return "/Item/detailItem";
	}
	
}
