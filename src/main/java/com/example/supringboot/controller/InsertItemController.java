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
	
	//?????? ???????????? ???????????? ????????????
	@RequestMapping("/item/adminList")
	public String adminList(HttpServletRequest request, ModelMap model) throws Exception{
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int user_id = userSession.getAccount().getUser_id();
		List<Item> itemList = itemService.getItemListByAdmin(user_id);
		model.put("itemList", itemList);
		return "/Item/admin_itemList";
	}
	
	//?????? ???????????????
	@GetMapping("/item/registerItem")
	public String searchFood(
			@RequestParam(value="keyword", required=false) String keyword, 
			ModelMap model, HttpServletRequest request) throws Exception {
		if(keyword == null) {
			System.out.println("????????? ?????????");
			model.put("itemForm", new ItemForm());
			return "/Item/registerItemForm";
		}
		else {
			System.out.println("????????? ?????? ???");
			try {
				ArrayList<Food> foodList = (ArrayList<Food>) this.itemService.searchFoodList(keyword);
				model.put("foodList", foodList);
				model.put("keyword", keyword);
				model.put("itemForm", new ItemForm());
				System.out.println("??????:" + foodList.get(0).getName());
				return "/Item/registerItemForm";
			}catch(Exception e) {
				model.put("noKeyword", "????????? ????????? ???????????? ????????????.");
				return "/Item/registerItemForm";
			}
		}
	}

	//Validation??????
	//?????? ??????-itemForm ??????
	@PostMapping("/item/registerItem")
	public String insertItem(HttpServletRequest request, 
			@RequestParam(value="keyword", required=false) String keyword,
			@Valid @ModelAttribute("itemForm") ItemForm itemForm,
			BindingResult errors, Model model) throws Exception{

		System.out.println("?????????: " + itemForm.getFood_id());
		System.out.println("??????:" + itemForm.getTitle());
		System.out.println("????????????: " + itemForm.getItem_price());
		System.out.println("?????????: " +itemForm.getShip_price());
		System.out.println("???????????????: " + itemForm.getCreated_dt());
		System.out.println("????????? ?????? : " + itemForm.getCreated_dt_time());
		System.out.println("???????????????: " + itemForm.getEnd_dt());
		System.out.println("????????? ?????? : " + itemForm.getEnd_dt_time());
		System.out.println("????????????: " + itemForm.getMin_quantity());
		System.out.println("??????: " + itemForm.getContent());
		
		String created_dt_time = itemForm.getCreated_dt_time();
		String end_dt_time = itemForm.getEnd_dt_time();
		
		if(created_dt_time.equals("")) {
			created_dt_time = "00:00";
		}
		if(end_dt_time.equals("")) {
			end_dt_time = "00:00";
		}
		
		Timestamp create_time = null, end_time = null;
		Date endParseDate = null;
		
		//?????? ?????? Timestamp ??????
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		    Date createdParsedDate = (Date) dateFormat.parse(itemForm.getCreated_dt() + " " +created_dt_time);
		    endParseDate = (Date) dateFormat.parse(itemForm.getEnd_dt() + " " + end_dt_time);
		    
		    if(createdParsedDate.after(endParseDate)) {
		    	errors.rejectValue("created_dt", "timeError", "???????????? ??????????????? ????????????.");
		    }
		    	   
		    create_time = new java.sql.Timestamp(createdParsedDate.getTime());
		    end_time = new java.sql.Timestamp(endParseDate.getTime());
		} catch(Exception e) { }
		
		
		Food food = itemService.getFood(itemForm.getFood_id());
		itemForm.setFood(food);
		
		//?????? ?????? ?????? ???
		if(errors.hasErrors()) {
			System.out.println("?????? ?????? ??????");
			System.out.println("?????? ????????????????" + errors.toString() +", " + errors.getFieldErrorCount());
			model.addAttribute("itemForm", itemForm);
			model.addAttribute("keyword", keyword);
			model.addAttribute("foodname", food.getName());
			model.addAttribute("foodId", food.getFood_id());
			
			Map<String, String> validatorResult = itemService.validateHandling(errors);
			for(String key: validatorResult.keySet())
				model.addAttribute(key, validatorResult.get(key));
			return "Item/registerItemForm";
		}
		
		//?????? ?????? ?????????
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int user_id = userSession.getAccount().getUser_id();
		
		//????????? ??????
		System.out.println("????????? ?????? ??????");
		ArrayList<Image> imageList = new ArrayList<Image>();
		if (itemForm.getFile() != null) {
			MultipartFile imageFile = itemForm.getFile();
			System.out.println("????????? ????????????");
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
				create_time, null, "ongoing", imageList, 0);
		item.setCreated_dt(item.getCreated_dt());
		
		if(item.getImages() != null)
			item.setImages(imageList);
		
		itemService.insertItem(item);
		itemService.startScheduler(item.getItem_id(), endParseDate);
		
		return "redirect:/admin/itemList"; //admin_List??? ??????????????? ??????
	}
	
}
