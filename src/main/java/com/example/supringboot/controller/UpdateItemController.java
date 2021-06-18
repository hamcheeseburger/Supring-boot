package com.example.supringboot.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Item;
import com.example.supringboot.service.ItemFormValidator;
import com.example.supringboot.service.ItemService;

@Controller
public class UpdateItemController {
	private static final Logger logger = LoggerFactory.getLogger(UpdateItemController.class);
	@Autowired
	private ItemService itemService;
	
	public void setItemService(ItemService itemservice) {
		this.itemService = itemservice;
	}
	
	//공구 수정페이지로 이동
	@RequestMapping("/item/updateItemForm")
	public String updateItemForm(@RequestParam int itemId, ModelMap model) {
		System.out.println("공구 수정 페이지");
		Item item = itemService.getDetailItem(itemId);
		Food food = itemService.getFood(item.getFood().getFood_id());
		model.put("item", item);
		model.put("foodInfo", food);
		
		return "/Item/updateItemForm";
	}

	/*
	//DB수정
	@PostMapping("/item/updateItem")
	public String updateItem(HttpServletRequest request,
			@RequestParam(value="itemId") int item_id,
			@Valid @ModelAttribute("itemForm") ItemForm itemForm,
			BindingResult errors, Model model) throws Exception{
		
		System.out.println("공구 수정");
		System.out.println("param으로 받은 상품id: " + item_id);
		System.out.println("상품id: " + itemForm.getItem_id());
		System.out.println("제목:" + itemForm.getTitle());
		System.out.println("상품금액: " + itemForm.getItem_price());
		System.out.println("배송비: " +itemForm.getShip_price());
		System.out.println("공구시작일: " + itemForm.getCreated_dt());
		System.out.println("공구마감일: " + itemForm.getEnd_dt());
		System.out.println("목표수량: " + itemForm.getMin_quantity());
		System.out.println("상세: " + itemForm.getContent());
		
		Item item = itemService.getDetailItem(item_id);
		itemForm.setImages(item.getImages());
//		itemForm.setItem_price(Integer.parseInt(itemForm.getStr_item_price()));
//		itemForm.setShip_price(Integer.parseInt(itemForm.getStr_ship_price()));
//		itemForm.setMin_quantity(Integer.parseInt(itemForm.getStr_min_quantity()));
		itemForm.setItem_id(item_id);
		
		//검증 오류 발생 시
		if(errors.hasErrors()) {
			System.out.println("검증오류 발생");
			System.out.println("오류종류??" + errors.toString() +", " + errors.getFieldErrorCount());
			Food food = itemService.getFood(item.getFood().getFood_id());
			model.addAttribute("item", itemForm);
			model.addAttribute("foodInfo", food);
			
			return "Item/updateItemForm";
		}
		
		Timestamp create_time = null, end_time = null, modify_time = null;
		//공구 날짜 Timestamp 변환
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    Date createdParsedDate = (Date) dateFormat.parse(itemForm.getCreated_dt());
		    Date endParseDate = (Date) dateFormat.parse(itemForm.getEnd_dt());
		    Date modifyParseDate = new Date();
		    create_time = new java.sql.Timestamp(createdParsedDate.getTime());
		    end_time = new java.sql.Timestamp(endParseDate.getTime());
		    modify_time = new java.sql.Timestamp(modifyParseDate.getTime());
		} catch(Exception e) { }
		
		item.setTitle(itemForm.getTitle());
		//item.setItem_price(Integer.parseInt( itemForm.getStr_item_price()) );
		item.setItem_price(itemForm.getItem_price());
		//item.setShip_price(Integer.parseInt( itemForm.getStr_ship_price()) );
		item.setShip_price(itemForm.getShip_price());
		item.setCreated_dt(create_time);
		item.setEnd_dt(end_time);
		//item.setMin_quantity(Integer.parseInt( itemForm.getStr_min_quantity()) );
		item.setMin_quantity(itemForm.getMin_quantity());
		item.setModified_dt(modify_time);
		item.setContent(itemForm.getContent());
		
		System.out.println("최종");
		System.out.println("상품id: " + item.getItem_id());
		System.out.println("제목:" + item.getTitle());
		System.out.println("상품금액: " + item.getItem_price());
		System.out.println("배송비: " +item.getShip_price());
		System.out.println("공구시작일: " + item.getCreated_dt());
		System.out.println("공구마감일: " + item.getEnd_dt());
		System.out.println("목표수량: " + item.getMin_quantity());
		System.out.println("상세: " + item.getContent());
		
		itemService.updateItem(item);
		
		return "redirect:/item/adminList";
	}
	*/
	
	/*
	//DB수정
	@RequestMapping("/item/updateItem")
	public String updateItem( @RequestParam(value="itemId") int item_id,
			@RequestParam("title") String title,
			@RequestParam("item_price") int price,
			@RequestParam("ship_price") int ship_price,
			@RequestParam("created_dt") String created_dt,
			@RequestParam("end_dt") String end_dt,
			@RequestParam("min_quantity") int minQuantity,
			@RequestParam("content") String content,
			ModelMap model, BindingResult errors) throws Exception {
		System.out.println("공구 수정 시작, 넘어온 id값: " + item_id);
		
		System.out.println( "," + title + "," + price + "," + ship_price +","
				+ created_dt + "," + created_dt + "," + end_dt + "," + minQuantity + "," + content);
		
		Item item = itemService.getDetailItem(item_id);
		System.out.println("itemID: " + item.getItem_id());
		System.out.println("itemTitle: " + item.getTitle());
		System.out.println("itemPrice: " + item.getItem_price());
		System.out.println("itemContent: " + item.getContent());
		
		ItemForm itemForm = new ItemForm();
		Food food = itemService.getFood(item.getFood().getFood_id());
		itemForm.setTitle(title);
		itemForm.setItem_price(price);
//		itemForm.setStr_item_price(String.valueOf(price));
		itemForm.setShip_price(ship_price);
		itemForm.setCreated_dt(created_dt);
		itemForm.setEnd_dt(end_dt);
		itemForm.setMin_quantity(minQuantity);
		itemForm.setContent(content);
		new ItemFormValidator().validate(itemForm, errors);
		if(errors.hasErrors()) {
			model.put("item", itemForm);
			model.put("foodInfo", food);
		}
		
		Timestamp create_time = null, end_time = null, modify_time = null;
		//공구 날짜 Timestamp 변환
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    Date createdParsedDate = (Date) dateFormat.parse(created_dt);
		    Date endParseDate = (Date) dateFormat.parse(end_dt);
		    Date modifyParseDate = new Date();
		    create_time = new java.sql.Timestamp(createdParsedDate.getTime());
		    end_time = new java.sql.Timestamp(endParseDate.getTime());
		    modify_time = new java.sql.Timestamp(modifyParseDate.getTime());
		} catch(Exception e) { }
		
		item.setTitle(title);
		item.setItem_price(price);
		item.setShip_price(ship_price);
		item.setCreated_dt(create_time);
		item.setEnd_dt(end_time);
		item.setMin_quantity(minQuantity);
		item.setModified_dt(modify_time);
		item.setContent(content);
		System.out.println("itemID: " + item.getItem_id());
		System.out.println("itemTitle: " + item.getTitle());
		System.out.println("itemPrice: " + item.getItem_price());
		System.out.println("itemContent: " + item.getContent());
		itemService.updateItem(item);
		
		return "redirect:/item/adminList";
	}
	*/
	
	
	//DB수정
	@RequestMapping("/item/updateItem")
	public String updateItem( @RequestParam(value="itemId") int item_id,
			@RequestParam("title") String title,
			@RequestParam("item_price") int price,
			@RequestParam("ship_price") int ship_price,
			@RequestParam("created_dt") String created_dt,
			@RequestParam("end_dt") String end_dt,
			@RequestParam("min_quantity") int minQuantity,
			@RequestParam("content") String content,
			//@RequestParam("images") MultipartFile file,
			ModelMap model) throws Exception {
		System.out.println("공구 수정 시작, 넘어온 id값: " + item_id);
		
		System.out.println( "," + title + "," + price + "," + ship_price +","
				+ created_dt + "," + created_dt + "," + end_dt + "," + minQuantity + "," + content);
		
		Item item = itemService.getDetailItem(item_id);
		System.out.println("itemID: " + item.getItem_id());
		System.out.println("itemTitle: " + item.getTitle());
		System.out.println("itemPrice: " + item.getItem_price());
		System.out.println("itemContent: " + item.getContent());
		
		Timestamp create_time = null, end_time = null, modify_time = null;
		//공구 날짜 Timestamp 변환
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    Date createdParsedDate = (Date) dateFormat.parse(created_dt);
		    Date endParseDate = (Date) dateFormat.parse(end_dt);
		    Date modifyParseDate = new Date();
		    create_time = new java.sql.Timestamp(createdParsedDate.getTime());
		    end_time = new java.sql.Timestamp(endParseDate.getTime());
		    modify_time = new java.sql.Timestamp(modifyParseDate.getTime());
		} catch(Exception e) { }
		
		item.setTitle(title);
		item.setItem_price(price);
		item.setShip_price(ship_price);
		item.setCreated_dt(create_time);
		item.setEnd_dt(end_time);
		item.setMin_quantity(minQuantity);
		item.setModified_dt(modify_time);
		item.setContent(content);
		System.out.println("itemID: " + item.getItem_id());
		System.out.println("itemTitle: " + item.getTitle());
		System.out.println("itemPrice: " + item.getItem_price());
		System.out.println("itemContent: " + item.getContent());
		itemService.updateItem(item);
		
		return "redirect:/admin/itemList";
	}
	
	
	@RequestMapping("/item/deleteItem")
	public String deleteItem (@RequestParam(value="itemId") int item_id, RedirectAttributes model) {
		System.out.println("delete컨트롤러");
		int result = itemService.deleteItem(item_id);
		logger.info("삭제결과 : " + result);
		if(result == 0) {
			model.addAttribute("deleteException", 0);
		}
		
		return "redirect:/admin/itemList";
	}
	
}
