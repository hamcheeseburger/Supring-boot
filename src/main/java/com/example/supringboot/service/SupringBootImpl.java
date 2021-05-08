package com.example.supringboot.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supringboot.dao.mybatis.MybatisAccountDao;
import com.example.supringboot.dao.mybatis.MybatisAdminDao;
import com.example.supringboot.domain.Account;

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
}
