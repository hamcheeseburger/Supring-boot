package com.example.supringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supringboot.dao.ItemDao;
import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Item;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemDao itemDao;
	
	@Override
	public int insertItem(Item item) {
		return itemDao.insertItem(item);
	}

	@Override
	public List<Food> searchFoodList(String keyword) {
		return itemDao.searchFoodList(keyword);
	}
	
	@Override
	public Food getFood(int food_id) {
		return itemDao.getFood(food_id);
	}

	@Override
	public int updateItem(Item item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteItem(int item_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int progressItem(int item_id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
