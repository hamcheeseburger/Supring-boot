package com.example.supringboot.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Item;

@Mapper
public interface ItemMapper {
	//공구식품 등록
	public int insertItem(Item item) throws DataAccessException;
	
	//공구식품 등록을 위한 Food테이블 서치
	public List<Food> searchFoodList(@Param("keyword") String keyword) throws DataAccessException;
	
	//공구식품 수정
	public int updateItem(Item item) throws DataAccessException;
	
	//공구식품 삭제
	public int deleteItem(@Param("item_id") int item_id) throws DataAccessException;
	
	//공구진행률 조회
	public int progressItem(@Param("item_id") int item_id);
}
