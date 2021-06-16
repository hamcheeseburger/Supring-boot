package com.example.supringboot.dao;

import com.example.supringboot.domain.Order_reg;

public interface ApplyDao {
	// 공구 신청
	public boolean applyItem(Order_reg order);
	// 공구 신청 취소
	public boolean cancelItem(int order_reg_id, int user_id);
	// 특정 공구 상세 내역 보기
	public Order_reg getOnOrderById(int order_reg_id, int user_id);
	// 공구 수정
	public boolean applyUpdate(Order_reg order);
	// 공구 신청 했는지 확인
	public boolean isApply(int user_id, int item_id);
}
