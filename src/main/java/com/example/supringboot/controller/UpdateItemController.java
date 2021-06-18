package com.example.supringboot.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Image;
import com.example.supringboot.domain.Item;
import com.example.supringboot.service.ItemFormValidator;
import com.example.supringboot.service.ItemService;

@Controller
@SessionAttributes({"item"})
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
		
		String end_dt = item.getEnd_dt().toString();
		String [] splited = end_dt.split(" ");
		
		String created_dt = item.getCreated_dt().toString();
		String [] created_split = created_dt.split(" ");
		
		ItemForm itemForm = new ItemForm();
		itemForm.setItem_id(itemId);
		itemForm.setTitle(item.getTitle());
		itemForm.setContent(item.getContent());
		itemForm.setStr_item_price(String.valueOf(item.getItem_price()));
		itemForm.setStr_ship_price(String.valueOf(item.getShip_price()));
		
		itemForm.setCreated_dt(created_split[0]);
		itemForm.setCreated_dt_time(created_split[1]);
		itemForm.setEnd_dt(splited[0]);
		itemForm.setEnd_dt_time(splited[1]);
		
		itemForm.setStr_min_quantity(String.valueOf(item.getMin_quantity()));
		itemForm.setImages(item.getImages());
		itemForm.setFood(food);
		
		logger.info("이미지 : " +  itemForm.getImages().get(0).getImage_id());
		
		model.put("item", itemForm);
//		model.put("foodInfo", food);
		
		return "/Item/updateItemForm";
	}

	
	
	//DB수정
	@RequestMapping("/item/updateItem")
	public String updateItem(@Valid @ModelAttribute("item") ItemForm itemForm, BindingResult errors,
			ModelMap model) throws Exception {
				
		String end_dt_time = itemForm.getEnd_dt_time();
		
		if(end_dt_time.equals("")) {
			end_dt_time = "00:00";
		}
		
		Timestamp end_time = null;
		Date endParseDate = null;
		
		//공구 날짜 Timestamp 변환
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		    Date createdParsedDate = (Date) dateFormat.parse(itemForm.getCreated_dt() + " " +itemForm.getCreated_dt_time());
		    endParseDate = (Date) dateFormat.parse(itemForm.getEnd_dt() + " " + end_dt_time);
		    
		    if(createdParsedDate.after(endParseDate)) {
		    	errors.rejectValue("end_dt", "timeError", "마감일이 시작일보다 빠릅니다.");
		    }
		    	   
		    end_time = new java.sql.Timestamp(endParseDate.getTime());
		} catch(Exception e) { }
		
		if(errors.hasErrors()) {
			logger.info("오류개수 : " + errors.getErrorCount());
			return "/Item/updateItemForm";
		}
		
		Item item = new Item();
		item.setItem_id(itemForm.getItem_id());
		item.setTitle(itemForm.getTitle());
		item.setItem_price(Integer.valueOf(itemForm.getStr_item_price()));
		item.setShip_price(Integer.valueOf(itemForm.getStr_ship_price()));
		item.setEnd_dt(end_time);
		item.setMin_quantity(Integer.valueOf(itemForm.getStr_min_quantity()));
		item.setContent(itemForm.getContent());
		
		ArrayList<Image> imageList = new ArrayList<Image>();
		if (itemForm.getFile() != null) {
			MultipartFile imageFile = itemForm.getFile();
			System.out.println("이미지 받아오기");
			try {
				byte[] imageContentBytes = imageFile.getBytes();
				Image image = new Image(); 
				image.setImage(imageContentBytes);
				
				if(itemForm.getImages().size() != 0) {
					image.setImage_id(itemForm.getImages().get(0).getImage_id());
					image.setItem_id(itemForm.getItem_id());
				}
				imageList.add(image);
				
				item.setImages(imageList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			item.setImages(null);
		}
		
		
		//logger.info("imageId : " + item.getImages().get(0).getImage_id());
		String fileChanged = itemForm.getFileChanged();
		itemService.updateItem(item, fileChanged);
		
		
		
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
