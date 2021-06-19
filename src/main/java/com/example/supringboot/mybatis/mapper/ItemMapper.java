package com.example.supringboot.mybatis.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Category;
import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Item;

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
	public ArrayList<Item> getAllItem(Item item) throws DataAccessException;
	public ArrayList<Item> getAllItemByKeyword(Item item) throws DataAccessException;
	
	// 진행중인 공구식품 목록 조회
	public ArrayList<Item> getGoingItemList(Item item) throws DataAccessException;
	public ArrayList<Item> getGoingItemListByKeyword(Item item) throws DataAccessException;
	
	// 성공 또는 실패한 공구식품 목록 조회 -> 마감일 지난 공구식품
	public ArrayList<Item> getEndItemList(Item item) throws DataAccessException;
	public ArrayList<Item> getEndItemListByKeyword(Item item) throws DataAccessException;
	
	// 특정 공구식품 정보조회
	public Item getOneItemById(@Param("item_id") int item_id) throws DataAccessException;
	
	// 마감일 만료시 아이템 상태 변경
	public int updateItemStatus(@Param("item_id") int item_id, @Param("item_status") String item_status) throws DataAccessException;

	public List<Item> getItemLatest3Rows() throws DataAccessException;

	public ArrayList<Item> searchItemFromMain(String keyword) throws DataAccessException;
	
	public ArrayList<Item> selectItemWithCategory(Item item) throws DataAccessException;
	
	public ArrayList<Category> selectAllCategory() throws DataAccessException;
	
	public Category getCategoryById(int cat_id) throws DataAccessException;

	public int selectItemTotalCount(Item item) throws DataAccessException;

	public int selectItemCatCount(int cat_id) throws DataAccessException;

	public int selectGoingItemCount(Item item) throws DataAccessException;
	
	public int selectEndItemCount(Item item) throws DataAccessException;
	
	public int searchItemTotalCount(Item item)  throws DataAccessException;

	public ArrayList<Item> selectTop4Item() throws DataAccessException;
}
