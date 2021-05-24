package com.example.supringboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Order_reg;

@Mapper
public interface ApplyMapper {
	// 공구 신청
	public int applyItem(Order_reg order) throws DataAccessException;
	// 공구 신청 취소
	public int cancelItem(@Param("order_reg_id") int order_reg_id, @Param("user_id") int user_id) throws DataAccessException;
	// 특정 공구 신청 내역 가져오기
	public Order_reg getOneOrderById(@Param("order_reg_id") int order_reg_id, @Param("user_id") int user_id) throws DataAccessException;
}
