package com.example.supringboot.dao.mybatis;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.supringboot.dao.AdminDao;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.mybatis.mapper.AdminMapper;

@Repository
public class MybatisAdminDao implements AdminDao{
	
	@Autowired
	AdminMapper adminMapper;
	
	@Override
	public ArrayList<Item> selectAdminItems(int user_id) {
		// TODO Auto-generated method stub
		return adminMapper.selectItemsWithAdminId(user_id);
	}

	@Override
	public ArrayList<Order_reg> selectItemOrderRegs(int item_id) {
		// TODO Auto-generated method stub
		return adminMapper.selectItemOrderRegs(item_id);
	}
	
	@Override
	public ArrayList<Item> getOngoingItemListByAdmin(int user_id) {
		// TODO Auto-generated method stub
		return adminMapper.getOngoingItemListByAdmin(user_id);
	}

	@Override
	public ArrayList<Item> getExpiredItemListByAdmin(int user_id) {
		// TODO Auto-generated method stub
		return adminMapper.getExpiredItemListByAdmin(user_id);
	}
}
