package com.example.supringboot.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supringboot.dao.ApplyDao;
import com.example.supringboot.domain.Order_reg;

@Service
@Transactional
public class ApplyServiceImpl implements ApplyService {
	
	@Autowired
	private ApplyDao orderDao;
	
	@Override
	public boolean applyItem(Order_reg order) {
		return orderDao.applyItem(order);
	}

	@Override
	public boolean cancelItem(int order_reg_id, int user_id) {
		return orderDao.cancelItem(order_reg_id, user_id);
	}

	@Override
	public Order_reg getOrderById(int order_reg_id, int user_id) {
		return orderDao.getOnOrderById(order_reg_id, user_id);
	}

	@Override
	public boolean applyUpdate(Order_reg order) {
		return orderDao.applyUpdate(order);
	}
}
