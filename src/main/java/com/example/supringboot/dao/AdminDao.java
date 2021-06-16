package com.example.supringboot.dao;

import java.util.ArrayList;

import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;

public interface AdminDao {
	// 관리자가 등록한 Item 목록
	public ArrayList<Item> selectAdminItems(int user_id);
	// 공동구매 신청서 및 신청자 목록
	public ArrayList<Order_reg> selectItemOrderRegs(int item_id);
	
	
	public ArrayList<Item> getOngoingItemListByAdmin(int user_id);
	
	public ArrayList<Item> getExpiredItemListByAdmin(int user_id);
}
