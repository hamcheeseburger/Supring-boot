package com.example.supringboot.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.supringboot.domain.Food;
import com.example.supringboot.service.ItemService;

@Controller
public class InsertItemController {
	
	@Autowired
	private ItemService itemService;
	public void setItemService(ItemService itemservice) {
		this.itemService = itemservice;
	}
	
	
//	@RequestMapping("/item/registerItem")
//	public String showForm() {
//		return "/Item/registerItemForm";
//	}
	
	@RequestMapping("/item/registerItem")
	public String searchFood(
			@RequestParam(value="keyword", required=false) String keyword, 
			ModelMap model) throws Exception {
		if(keyword == null) {
			System.out.println("키워드 없을때");
			return "/Item/registerItemForm";
		}
		else{
			System.out.println("키워드 있을 때");
			ArrayList<Food> foodList = (ArrayList<Food>) this.itemService.searchFoodList(keyword);
			model.put("foodList", foodList);
			System.out.println("확인:" + foodList.get(0).getName());
			return "/Item/registerItemForm";
		}
	}
	
}
