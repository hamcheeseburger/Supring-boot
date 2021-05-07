package com.example.supringboot.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

	@Override
	public void insertAccount(Account account) {
		// TODO Auto-generated method stub
		accountMapper.insertAccount(account);
		
	}

	@Override
	public boolean loginCheck(String login_id, String password) {
		// TODO Auto-generated method stub
		Account acc = accountMapper.accountLoginCheck(login_id, password);
		if(acc == null) {
			return false;
		}
		
		return true;
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
	
	
}