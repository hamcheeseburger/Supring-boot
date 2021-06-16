package com.example.supringboot.mybatis.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;

@Mapper
public interface AdminMapper {
	// 관리자가 등록한 Item 목록
	ArrayList<Item> selectItemsWithAdminId(@Param("user_id") int user_id) throws DataAccessException;
	// 공동구매 신청서 및 신청자 목록
	ArrayList<Order_reg> selectItemOrderRegs(@Param("item_id") int item_id) throws DataAccessException;

	public ArrayList<Item> getOngoingItemListByAdmin(int user_id) throws DataAccessException;
	
	public ArrayList<Item> getExpiredItemListByAdmin(int user_id) throws DataAccessException;
}
