package com.example.supringboot.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supringboot.dao.mybatis.MybatisAccountDao;
import com.example.supringboot.dao.mybatis.MybatisAdminDao;
import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.domain.Post;

@Service
@Transactional
public class SupringBootImpl implements SupringBootFacade{
	
	@Autowired
	private MybatisAccountDao accountDao;
	
	@Autowired
	private MybatisAdminDao adminDao;
	
	@Override
	public void insertAccount(Account account) {
		// TODO Auto-generated method stub
		accountDao.insertAccount(account);
	}
	
	
	@Override
	public Account getAccount(String login_id, String password) {
		// TODO Auto-generated method stub
		return accountDao.getAccount(login_id, password);
	}

	@Override
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		return accountDao.updateAccount(account);
		
	}

	@Override
	public HashMap<String, ArrayList<Order_reg>> getMyOrderList(int user_id) {
		// TODO Auto-generated method stub
		ArrayList<Order_reg> orderRegs = accountDao.selectMyOrderRegs(user_id);
		ArrayList<Order_reg> orders = accountDao.selectMyOrders(user_id);
		
		HashMap<String, ArrayList<Order_reg>> hashMap = new HashMap<String, ArrayList<Order_reg>>();
		hashMap.put("orderRegs", orderRegs);
		hashMap.put("orders", orders);
		
		return hashMap;
	}


	@Override
	public Account getAccountById(int user_id) {
		// TODO Auto-generated method stub
		return accountDao.getAccountById(user_id);
	}


	@Override
	public ArrayList<Post> getMyPostList(int user_id) {
		// TODO Auto-generated method stub
		return accountDao.selectMyPosts(user_id);
	}


	@Override
	public ArrayList<Comment> getMyCommentList(int user_id) {
		// TODO Auto-generated method stub
		return accountDao.selectMyComments(user_id);
	}
}
