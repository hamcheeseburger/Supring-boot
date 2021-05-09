package com.example.supringboot.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.supringboot.dao.ItemDao;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.mybatis.mapper.ItemMapper;

@Repository
public class MybatisItemDao implements ItemDao {

	@Autowired
	private ItemMapper itemMapper;

	@Override
	public boolean likedItem(int user_id, int item_id) {
		int result = itemMapper.likedItem(user_id, item_id);
		
		if (result == 1) {
			return true;
		} else {
			return false;	
		}
	}

	@Override
	public List<Item> getAllItemList() {
		return itemMapper.getAllItem();
	}

	@Override
	public Item getDetailItem(int item_id) {
		return itemMapper.getOneItemById(item_id);
	}

	@Override
	public boolean applyItem(Order_reg order) {
		int result = itemMapper.applyItem(order);
		
		if (result == 1) {
			return true;
		} else {
			return false;	
		}
	}

	@Override
	public boolean cancelItem(int order_reg_id, int user_id) {
		int result = itemMapper.cancelItem(order_reg_id, user_id);
		
		if (result == 1) {
			return true;
		} else {
			return false;	
		}
	}
	
	

}
