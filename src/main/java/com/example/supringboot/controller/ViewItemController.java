package com.example.supringboot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(ViewItemController.class);
	@Autowired
	private ItemService itemService;
	
	@ModelAttribute("categoryList")
	public ArrayList<Category> categoryList() {
		return itemService.selectAllCategory();
	}
	
	// 공구 식품 목록 보기 defaultValue="going",
	@RequestMapping("/item/list")
	public ModelAndView itemList(@RequestParam(required=false, defaultValue="-1", name="cat_id") Integer cat_id,
			@RequestParam(required=false, defaultValue="going", name="status") String status,
			@ModelAttribute("params") Item item, Model model) {
		
		if(cat_id != -1) {
			item.setCat_id(cat_id);
		}
		
		String keyword = item.getSearchKeyword();
		
		System.out.println("cat_id : " + cat_id);
		System.out.println("status : " + status);
		System.out.println("keyword : " + keyword);
		ModelAndView mav = new ModelAndView("/Item/itemList");
		
		//키워드 미사용 시
		if(keyword == null || keyword.equals("")) {
			System.out.println("같이먹어요 페이지 이동(키워드없음)");
			if(cat_id == -1) {
				ArrayList<Item> itemList = null;
			
				if (status.equals("all")) {
					itemList = itemService.getItemList(item);
				}
				else if (status.equals("going")) {
					itemList = itemService.getGoingItem(item);
				}
				else if (status.equals("end")) {
					System.out.println("실행되야함//");
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
		} else {
			status = item.getSearchType();
			//키워드로 검색 시
			System.out.println("같이먹어요 페이지 이동(키워드: " + keyword +")");
//			item.setItem_status(status);
			System.out.println("검색범위(status): " + item.getItem_status());
			
			logger.info("param : " + item.getSearchKeyword());
			logger.info("param : " + item.getSearchType());
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("keyword", keyword);
//			map.put("item", (Item)item);
					
			if(cat_id == -1) {
				ArrayList<Item> itemList = null;
			
				if (status.equals("all")) {
					System.out.println("전체 검색");
					itemList = itemService.getItemListByKeyword(item);
				}
				else if (status.equals("going")) {
					System.out.println("진행 중 검색");
					itemList = itemService.getGoingItemByKeyword(item);
					System.out.println("검색해온 itemList: " + itemList.size());
				}
				else if (status.equals("end")) {
					System.out.println("마감 검색");
					itemList = itemService.getEndItemByKeyword(item);
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
		}
		return mav;
	}
	
	// 특정 공구 식품 보기
	@RequestMapping("/item/detail")
	public String detailItem(@RequestParam int itemId, ModelMap model) {
		Item item = itemService.getDetailItem(itemId);
		Category cat = itemService.getCategoryById(item.getFood().getCat_id());
		
		model.put("detail", item);
		model.put("category", cat);
		
		return "/Item/detailItem";
	}
	
}
