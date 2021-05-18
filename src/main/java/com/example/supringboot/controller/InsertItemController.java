package com.example.supringboot.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Image;
import com.example.supringboot.domain.Item;
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
	
	@GetMapping("/item/registerItem")
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
	
	@PostMapping("/item/registerItem")
	public String insertFood(@RequestParam("food")int food_id,
			@RequestParam("title") String title,
			@RequestParam("price") int price,
			@RequestParam("ship_price") int ship_price,
			@RequestParam("created_dt") String created_dt,
			@RequestParam("end_dt") String end_dt,
			@RequestParam("minQuantity") int minQuantity,
			@RequestParam("content") String content,
			@RequestParam("images") MultipartFile images,
			ModelMap model) {
		System.out.println(food_id);
		Food food = itemService.getFood(food_id);
		Timestamp create_time = null, end_time = null, modify_time = null;
		//공구 시작,마감 날짜 Timestamp로 변환
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		    Date createdParsedDate = (Date) dateFormat.parse(created_dt);
		    Date endParseDate = (Date) dateFormat.parse(end_dt);
		    Date modifyParseDate = new Date();
		    create_time = new java.sql.Timestamp(createdParsedDate.getTime());
		    end_time = new java.sql.Timestamp(endParseDate.getTime());
		    modify_time = new java.sql.Timestamp(modifyParseDate.getTime());
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		
		//이미지 처리
		ArrayList<Image> imageList = new ArrayList<Image>();
		if (images != null) {
			MultipartFile imageFile = images;
			try {
				byte[] imageContentBytes = imageFile.getBytes();
				Image image = new Image(); 
				image.setImage(imageContentBytes);
				
				//ArrayList<Image> imageList = new ArrayList<Image>();
				imageList.add(image);
				//item.setImages(imageList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//수정필요: 이미지 처리, 세번째 현재 유저아이디 가져오기(admin계정)
		//ArrayList<Image> images = new ArrayList<>();
		Item item = new Item(0, food, 41, price, ship_price, title, content, end_time, minQuantity, create_time, 
				modify_time, "ongoing", imageList, 0);
		itemService.insertItem(item);
		System.out.println("디비등록완료");
		model.put("itemInfo", item);
		model.put("foodInfo", food);
		return "/Item/updateItemForm";
	}
	
}
