package com.example.supringboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Account;

@Mapper
public interface AccountMapper {
	Account getAccountByLoginId(String login_id) throws DataAccessException;
}
