package com.example.supringboot.service;

import java.util.List;

import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Item;

public interface ItemService {
	//공구식품 등록
	int insertItem(Item item);
	//공구식품 등록을 위한 Food테이블 서치
	List<Food> searchFoodList(String keyword);
	//Food객체 가져오기
	Food getFood(int food_id);
	//공구식품 수정
	int updateItem(Item item);
	//공구식품 삭제
	int deleteItem(int item_id);
	//공구진행률 조회
	int progressItem(int item_id);
}
