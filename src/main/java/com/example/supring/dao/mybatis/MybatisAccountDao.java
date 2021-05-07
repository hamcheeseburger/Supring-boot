package com.example.supring.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.supringboot.mybatis.mapper.AccountMapper;
import com.example.supringboot.dao.AccountDao;
import com.example.supringboot.domain.Account;

@Repository
public class MybatisAccountDao implements AccountDao {
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public Account getAccountByLoginId(String login_id) {
		// TODO Auto-generated method stub
		return accountMapper.getAccountByLoginId(login_id);
	}
	
	
}