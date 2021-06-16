package com.example.supringboot.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Item;
import com.example.supringboot.service.ItemService;

@Controller
public class UpdateItemController {
	
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
		System.out.println("콘텐츠: " + item.getContent());
		System.out.println("제목: " +item.getTitle());
		model.put("item", item);
		model.put("foodInfo", food);
		
		return "/Item/updateItemForm";
	}
	
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
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
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
	
	@RequestMapping("/item/deleteItem")
	public String deleteItem (@RequestParam(value="itemId") int item_id) {
		System.out.println("delete컨트롤러");
		itemService.deleteItem(item_id);
		return "redirect:/item/adminList";
	}
	
}
