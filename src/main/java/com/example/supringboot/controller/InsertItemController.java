package com.example.supringboot.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Image;
import com.example.supringboot.domain.Item;
import com.example.supringboot.service.ItemFormValidator;
import com.example.supringboot.service.ItemService;
import com.example.supringboot.service.WishServiceImpl;

@Controller
public class InsertItemController {
	
	@Autowired
	private ItemService itemService;
	public void setItemService(ItemService itemservice) {
		this.itemService = itemservice;
	}
	
	//현재 로그인한 관리자의 공구목록
	@RequestMapping("/item/adminList")
	public String adminList(HttpServletRequest request, ModelMap model) throws Exception{
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int user_id = userSession.getAccount().getUser_id();
		List<Item> itemList = itemService.getItemListByAdmin(user_id);
		model.put("itemList", itemList);
		return "/Item/admin_itemList";
	}
	
	//공구 등록페이지
	@GetMapping("/item/registerItem")
	public String searchFood(
			@RequestParam(value="keyword", required=false) String keyword, 
			ModelMap model, HttpServletRequest request) throws Exception {
		if(keyword == null) {
			System.out.println("키워드 없을때");
			model.put("itemForm", new ItemForm());
			return "/Item/registerItemForm";
		}
		else {
			System.out.println("키워드 있을 때");
			try {
				ArrayList<Food> foodList = (ArrayList<Food>) this.itemService.searchFoodList(keyword);
				model.put("foodList", foodList);
				model.put("keyword", keyword);
				model.put("itemForm", new ItemForm());
				System.out.println("확인:" + foodList.get(0).getName());
				return "/Item/registerItemForm";
			}catch(Exception e) {
				model.put("noKeyword", "검색한 제품이 존재하지 않습니다.");
				return "/Item/registerItemForm";
			}
		}
	}

	//Validation구현
	//공구 등록-itemForm 사용
	@PostMapping("/item/registerItem")
	public String insertItem(HttpServletRequest request, 
			@RequestParam(value="keyword", required=false) String keyword,
			@Valid @ModelAttribute("itemForm") ItemForm itemForm,
			BindingResult errors, Model model) throws Exception{

		System.out.println("상품명: " + itemForm.getFood_id());
		System.out.println("제목:" + itemForm.getTitle());
		System.out.println("상품금액: " + itemForm.getItem_price());
		System.out.println("배송비: " +itemForm.getShip_price());
		System.out.println("공구시작일: " + itemForm.getCreated_dt());
		System.out.println("공구마감일: " + itemForm.getEnd_dt());
		System.out.println("목표수량: " + itemForm.getMin_quantity());
		System.out.println("상세: " + itemForm.getContent());
		
		Food food = itemService.getFood(itemForm.getFood_id());
		itemForm.setFood(food);
		
		//new ItemFormValidator().validate(itemForm, errors);
		
		//검증 오류 발생 시
		if(errors.hasErrors()) {
			System.out.println("검증 오류 발생");
			System.out.println("대체 무슨오류????" + errors.toString() +", " + errors.getFieldErrorCount());
			model.addAttribute("itemForm", itemForm);
			model.addAttribute("keyword", keyword);
			model.addAttribute("foodname", food.getName());
			model.addAttribute("foodId", food.getFood_id());
			
			Map<String, String> validatorResult = itemService.validateHandling(errors);
			for(String key: validatorResult.keySet())
				model.addAttribute(key, validatorResult.get(key));
			return "Item/registerItemForm";
		}
		
		//현재 세션 아이디
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int user_id = userSession.getAccount().getUser_id();
		
		Timestamp create_time = null, end_time = null, modify_time = null;
		Date endParseDate = null;
		
		//공구 날짜 Timestamp 변환
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    Date createdParsedDate = (Date) dateFormat.parse(itemForm.getCreated_dt());
		    endParseDate = (Date) dateFormat.parse(itemForm.getEnd_dt());
		    Date modifyParseDate = new Date();
		    create_time = new java.sql.Timestamp(createdParsedDate.getTime());
		    end_time = new java.sql.Timestamp(endParseDate.getTime());
		    modify_time = new java.sql.Timestamp(modifyParseDate.getTime());
		} catch(Exception e) { }
		
		//이미지 처리
		System.out.println("이미지 처리 시작");
		ArrayList<Image> imageList = new ArrayList<Image>();
		if (itemForm.getFile() != null) {
			MultipartFile imageFile = itemForm.getFile();
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
		
		Item item = new Item(0, food, user_id, Integer.parseInt( itemForm.getStr_item_price() ), Integer.parseInt( itemForm.getStr_ship_price() ),
				itemForm.getTitle(), itemForm.getContent(), end_time, Integer.parseInt(itemForm.getStr_min_quantity()),
				create_time, modify_time, "ongoing", imageList, 0);
		
		if(item.getImages() != null)
			item.setImages(imageList);
		
		itemService.insertItem(item);
		System.out.println("디비등록완료");
		
		System.out.println("insert item_id : " + item.getItem_id());
		itemService.startScheduler(item.getItem_id(), endParseDate);
		
		return "redirect:/admin/itemList"; //admin_List로 리다이렉트 하기
	}
	
}
