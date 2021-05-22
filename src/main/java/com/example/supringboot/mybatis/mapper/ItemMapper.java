package com.example.supringboot.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;

@Mapper
public interface ItemMapper {
	
	//공구식품 등록
	public int insertItem(Item item) throws DataAccessException;
	
	//공구식품 등록을 위한 Food테이블 서치
	public List<Food> searchFoodList(@Param("keyword") String keyword) throws DataAccessException;
	
	//Food객체 가져오기
	public Food getFood(@Param("food_id") int food_id) throws DataAccessException;
	
	//특정관리자 공구목록 가져오기
	public List<Item> getItemListByAdmin(@Param("user_id") int user_id) throws DataAccessException;
	
	//공구식품 수정
	public int updateItem(Item item) throws DataAccessException;
	
	//공구식품 삭제
	public int deleteItem(@Param("item_id") int item_id) throws DataAccessException;
	
	//공구진행률 조회
	public int progressItem(@Param("item_id") int item_id);
	
	// 공구식품 목록 조회
	public List<Item> getAllItem() throws DataAccessException;
	// 진행중인 공구식품 목록 조회
	public List<Item> getGoingItemList() throws DataAccessException;
	// 성공 또는 실패한 공구식품 목록 조회 -> 마감일 지난 공구식품?
	public List<Item> getEndItemList() throws DataAccessException;
	// 특정 공구식품 정보조회
	public Item getOneItemById(@Param("item_id") int item_id) throws DataAccessException;
	// 공구 신청
	public int applyItem(Order_reg order) throws DataAccessException;
	// 공구 신청 취소
	public int cancelItem(@Param("order_reg_id") int order_reg_id, @Param("user_id") int user_id) throws DataAccessException;
}
