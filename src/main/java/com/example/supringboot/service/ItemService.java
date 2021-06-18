package com.example.supringboot.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;

import com.example.supringboot.domain.Category;
import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Item;

public interface ItemService {
	//공구식품 등록
	int insertItem(Item item);
	//공구식품 등록을 위한 Food테이블 서치
	
	List<Food> searchFoodList(String keyword);
	//현재 관리자 등록 공구목록
	List<Item> getItemListByAdmin(int user_id);
	//Food객체 가져오기
	Food getFood(int food_id);
	//공구식품 수정
	int updateItem(Item item, String fileChaged);
	//공구식품 삭제
	int deleteItem(int item_id);
	//공구진행률 조회
	int progressItem(int item_id);
	//유효성 검사
	Map<String, String> validateHandling(Errors errors);
	
	// item 마감일에 대한 스케쥴러 시작
	void startScheduler(int item_id, Date end_dt);
	// Item 최신글 3개 가져오기
	List<Item> getItemLatest3Rows();
	
	public ArrayList<Item> selectItemWithCategory(Item item);
	
	public ArrayList<Category> selectAllCategory();
	
	public Category getCategoryById(int cat_id);
	
	// 전체 공구식품 목록 가져옥;
	public ArrayList<Item> getItemList(Item item);
	ArrayList<Item> getItemListByKeyword(Item item);
	
	// 진행중인 공구식품 목록 가져오기
	ArrayList<Item> getGoingItem(Item item);
	ArrayList<Item> getGoingItemByKeyword(Item item);
			
	// 마감일 지난 공구식품 목록 가져오기
	ArrayList<Item> getEndItem(Item item);
	ArrayList<Item> getEndItemByKeyword(Item item);
		
	// 특정 공구식품 상세 보기
	Item getDetailItem(int item_id);
	
}
