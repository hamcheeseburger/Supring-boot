package com.example.supringboot.service;

import com.example.supringboot.domain.Order_reg;

public interface ApplyService {
	// 공구 신청하기
	boolean applyItem(Order_reg order);
	// 공구 신청 취소하기
	boolean cancelItem(int order_reg_id, int user_id);
	// 특정 공구 신청 내역 가져오기
	Order_reg getOrderById(int order_reg_id, int user_id);
	// 공구 신청 내역 수정하기
	boolean applyUpdate(Order_reg order);
}
