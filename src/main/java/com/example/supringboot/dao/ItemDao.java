package com.example.supringboot.dao;

import java.util.List;

import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;

public interface ItemDao {
	// 공구식품 찜하기
	public boolean likedItem(int user_id, int item_id);
	// 공구식품 목록 조회
	public List<Item> getAllItemList();
	// 특정 공구식품 정보조회
	public Item getDetailItem(int item_id);
	// 공구 신청
	public boolean applyItem(Order_reg order);
	// 공구 신청 취소
	public boolean cancelItem(int order_reg_id, int user_id);
}
