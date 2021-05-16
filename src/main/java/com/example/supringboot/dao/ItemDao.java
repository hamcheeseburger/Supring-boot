package com.example.supringboot.dao;

import java.util.List;

import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;

public interface ItemDao {
	//공구식품 등록
	public int insertItem(Item item);
	//공구식품 등록을 위한 Food테이블 서치
	public List<Food> searchFoodList(String keyword);
	//공구식품 수정
	public int updateItem(Item item);
	//공구식품 삭제
	public int deleteItem(int item_id);
	//공구진행률 조회
	public int progressItem(int item_id);
	
	// 공구식품 목록 조회 -> 
	public List<Item> getAllItemList();
	// 진행중인 공구식품 목록 조회
	public List<Item> getGoingItemList();
	// 성공 또는 실패한 공구식품 목록 조회 -> 마감일 지난 공구식품?
	public List<Item> getEndItemList();
	// 특정 공구식품 정보조회
	public Item getDetailItem(int item_id);
	// 공구 신청
	public boolean applyItem(Order_reg order);
	// 공구 신청 취소
	public boolean cancelItem(int order_reg_id, int user_id);
}
