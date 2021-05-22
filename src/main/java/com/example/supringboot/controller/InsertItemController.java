package com.example.supringboot.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.example.supringboot.service.WishServiceImpl;

@Controller
public class InsertItemController {
	
	@Autowired
	private ItemService itemService;
	
	public void setItemService(ItemService itemservice) {
		this.itemService = itemservice;
	}
	
	@Autowired
	private WishServiceImpl wishService;
	
	//현재 로그인한 관리자의 공구목록
	@RequestMapping("/item/adminList")
	public String adminList(ModelMap model) throws Exception{
		int user_id = 41;	//현재 db에 있는 임의의admin계정 -> 추후 현재 로그인한 아이디로 변경
		List<Item> itemList = itemService.getItemListByAdmin(user_id);
		model.put("itemList", itemList);
		return "/Item/admin_itemList";
	}
	
	//공구 등록페이지
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
	
//	//공구 수정페이지로 이동
//	@RequestMapping("/item/updateItem")
//	public String updateItemForm(@RequestParam int itemId, ModelMap model) {
//		System.out.println("공구 수정 페이지");
//		Item item = wishService.getDetailItem(itemId);
//		Food food = itemService.getFood(item.getFood().getFood_id());
//		System.out.println("콘텐츠: " + item.getContent());
//		model.put("item", item);
//		model.put("foodInfo", food);
//		
//		return "/Item/updateItemForm";
//	}

	//공구 등록
	@PostMapping("/item/registerItem")
	public String insertItem(@RequestParam("food")int food_id,
			@RequestParam("title") String title,
			@RequestParam("price") int price,
			@RequestParam("ship_price") int ship_price,
			@RequestParam("created_dt") String created_dt,
			@RequestParam("end_dt") String end_dt,
			@RequestParam("minQuantity") int minQuantity,
			@RequestParam("content") String content,
			@RequestParam("images") MultipartFile file,
			ModelMap model) throws Exception {
		
		System.out.println(food_id);
		Food food = itemService.getFood(food_id);
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
		
		//이미지 처리
		System.out.println("이미지 처리 시작");
		ArrayList<Image> imageList = new ArrayList<Image>();
		if (file != null) {
			MultipartFile imageFile = file;
			System.out.println("이미지 받아오기");
			try {
				byte[] imageContentBytes = imageFile.getBytes();
				Image image = new Image(); 
				image.setImage(imageContentBytes);
				imageList.add(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//수정필요: 세번째 현재 유저아이디 가져오기(admin계정)
		Item item = new Item(0, food, 41, price, ship_price, title, content, end_time, minQuantity, create_time, 
				modify_time, "ongoing", imageList, 0);
		
		if(item.getImages() != null)
			item.setImages(imageList);
		
		itemService.insertItem(item);
		System.out.println("디비등록완료");
		return "redirect:/item/adminList"; //admin_List로 리다이렉트 하기
		//return "/Item/updateItemForm";
	}
	
}
