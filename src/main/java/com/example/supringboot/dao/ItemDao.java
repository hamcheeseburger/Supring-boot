package com.example.supringboot.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.supringboot.domain.Category;
import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Item;


public interface ItemDao {
	//공구식품 등록
	public int insertItem(Item item);
	//공구식품 등록을 위한 Food테이블 서치
	public List<Food> searchFoodList(String keyword);
	//Food객체 가져오기
	public Food getFood(int food_id);
	//공구상품 목록 가져오기
	public List<Item> getItemListByAdmin(int user_id);
	//공구식품 수정
	public int updateItem(Item item);
	//공구식품 삭제
	public int deleteItem(int item_id);
	//공구진행률 조회
	public int progressItem(int item_id);
	
	// 공구식품 목록 조회 -> 
	public ArrayList<Item> getAllItemList(Item item);
	// 진행중인 공구식품 목록 조회
	public ArrayList<Item> getGoingItemList(Item item);
	// 성공 또는 실패한 공구식품 목록 조회 -> 마감일 지난 공구식품?
	public ArrayList<Item> getEndItemList(Item item);
	// 특정 공구식품 정보조회
	public Item getDetailItem(int item_id);
	
	// 마감일 만료시 아이템 상태 변경
	public boolean updateItemStatus(int item_id, String item_status);
	
	public List<Item> getItemLatest3Rows();
	
	public ArrayList<Item> searchItemFromMain(String keyword);
	
	public ArrayList<Item> selectItemWithCategory(Item item);
	
	public ArrayList<Category> selectAllCategory();
	
	public Category getCategoryById(int cat_id);
	
	public int selectItemTotalCount(Item item);
	
	public int selectItemCatCount(int cat_id);

}
