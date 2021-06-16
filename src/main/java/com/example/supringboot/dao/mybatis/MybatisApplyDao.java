package com.example.supringboot.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.supringboot.dao.ApplyDao;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.mybatis.mapper.ItemMapper;
import com.example.supringboot.mybatis.mapper.ApplyMapper;

@Repository
public class MybatisApplyDao implements ApplyDao {
	
	@Autowired
	private ApplyMapper orderMapper;
	
	@Override
	public boolean applyItem(Order_reg order) {
		int result = orderMapper.applyItem(order);
		
		if (result == 1) {
			return true;
		} else {
			return false;	
		}
	}

	@Override
	public boolean cancelItem(int order_reg_id, int user_id) {
		int result = orderMapper.cancelItem(order_reg_id, user_id);
		
		if (result == 1) {
			return true;
		} else {
			return false;	
		}
	}

	@Override
	public Order_reg getOnOrderById(int order_reg_id, int user_id) {
		return orderMapper.getOneOrderById(order_reg_id, user_id);
	}

	@Override
	public boolean applyUpdate(Order_reg order) {
		int result = orderMapper.applyUpdate(order);
		
		if (result == 1) {
			return true;
		} else {
			return false;	
		}
	}

	@Override
	public boolean isApply(int user_id, int item_id) {
		int result = orderMapper.isApply(user_id, item_id);
		
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}
}
