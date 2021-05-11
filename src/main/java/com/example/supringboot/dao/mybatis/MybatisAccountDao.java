package com.example.supringboot.dao.mybatis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.supringboot.mybatis.mapper.AccountMapper;
import com.example.supringboot.dao.AccountDao;
import com.example.supringboot.domain.Account;

import com.example.supringboot.domain.Comment;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.domain.Post;
import com.example.supringboot.domain.Item;


@Repository
public class MybatisAccountDao implements AccountDao {
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public Account getAccountByLoginId(String login_id) {
		// TODO Auto-generated method stub
		return accountMapper.getAccountByLoginId(login_id);
	}

	@Override
	public void insertAccount(Account account) {
		// TODO Auto-generated method stub
		accountMapper.insertAccount(account);
		
	}

	@Override
	public Account getAccount(String login_id, String password) {
		// TODO Auto-generated method stub
		return accountMapper.getAccount(login_id, password);
	}

	@Override
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		int result = accountMapper.updateAccount(account);
		
		if(result == 1) {
			return true;
		}
		
		return false;
	}

	@Override
	public ArrayList<Order_reg> selectMyOrderRegs(int user_id) {
		// TODO Auto-generated method stub
		return accountMapper.selectOrderRegWithUserId(user_id);
	}

	@Override
	public ArrayList<Order_reg> selectMyOrders(int user_id) {
		// TODO Auto-generated method stub
		return accountMapper.selectOrderWithUserId(user_id);
	}

	@Override
	public ArrayList<Post> selectMyPosts(int user_id) {
		// TODO Auto-generated method stub
		return accountMapper.selectPostsWithUserId(user_id);
	}

	@Override
	public ArrayList<Comment> selectMyComments(int user_id) {
		// TODO Auto-generated method stub
		return accountMapper.selectCommentsWithUserId(user_id);
	}

	// 찜한 식품 확인
	@Override
	public List<Item> getWishItem(int user_id) {
		List<Item> wishList = new ArrayList<Item>();
		wishList = accountMapper.getWishItem(user_id);
		
		if (wishList.isEmpty()) {
			return Collections.emptyList();
		}
		else {
			return wishList;
		}
	}
	
	
}