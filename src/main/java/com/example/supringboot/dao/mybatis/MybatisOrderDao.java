package com.example.supringboot.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.supringboot.dao.OrderDao;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.mybatis.mapper.ItemMapper;
import com.example.supringboot.mybatis.mapper.OrderMapper;

@Repository
public class MybatisOrderDao implements OrderDao {
	
	@Autowired
	private OrderMapper orderMapper;
	
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
		return orderMapper.getOnOrderById(order_reg_id, user_id);
	}
}
