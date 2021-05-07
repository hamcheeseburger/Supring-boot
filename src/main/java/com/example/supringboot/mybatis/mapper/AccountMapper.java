package com.example.supringboot.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Item;

@Mapper
public interface AccountMapper {
	Account getAccountByLoginId(String login_id) throws DataAccessException;
	void insertAccount(Account account) throws DataAccessException;
	Account accountLoginCheck(@Param("login_id") String login_id, @Param("password") String password) throws DataAccessException;
	int updateAccount(Account account) throws DataAccessException;
	
	// 찜한 식품 확인
	List<Item> getWishItem(int user_id) throws DataAccessException;

}
