package com.example.supringboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Account;

@Mapper
public interface AccountMapper {
	Account getAccountByLoginId(String login_id) throws DataAccessException;
	void insertAccount(Account account) throws DataAccessException;
	Account accountLoginCheck(@Param("login_id") String login_id, @Param("password") String password) throws DataAccessException;
	int updateAccount(Account account) throws DataAccessException;

}
