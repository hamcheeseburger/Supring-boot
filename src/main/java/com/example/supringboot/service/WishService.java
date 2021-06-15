package com.example.supringboot.service;

import java.util.List;

import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.domain.WishItem;

public interface WishService {
	// 찜한 공구 식품 확인
	List<WishItem> getLikedItem(int user_id);

	// 공구식품 찜하기
	boolean likeItem(int user_id, int item_id, int amount);

	// 공구식품 찜하기 취소하기 -> 찜한 목록 페이지에서 취소
	boolean cancelLikedItem(int liked_id);
	
	// 공구식품 찜하기 취소하기 -> 식품 상세 페이지에서 취소
	boolean cancelDetailLikedItem(int user_id, int item_id);

	// 찜한 식품 모두 삭제(찜한 식품 목록 비우기)
	public boolean deleteAllLikedItem(int user_id);

	// 찜하기 수정 -> 찜하기 화면에서 식품 수량을 변경할때
	public boolean updateLikedItem(WishItem wish);

	// 찜한 식품 수량 변경 -> 식품 상세페이지에서 동일한 식품을 찜하기 했을때 사용
	public boolean updateQuantity(int user_id, int item_id, int amount);

	// 찜한 식품 전체 금액 가져오기
	public int totalMoney(int user_id);

	// 찜한 식품 목록에 동일한 식품이 있는지 확인
	public boolean isWishItem(int user_id, int item_id);
	
	// liked_id로 찜한 식품 가져오기
	public WishItem getOneWishItem(int liked_id);
	
	// 공구식품 관련 -> 일단 여기에 작성
	// 공구식품 목록 가져오기
//	List<Item> getAllItem();
	
	// 공구식품 목록 가져오기
	List<Item> getGoingItem();
		
	// 공구식품 목록 가져오기
	List<Item> getEndItem();
	
	// 특정 공구식품 상세 보기
	Item getDetailItem(int item_id);
	
	// 공구 신청 관련 -> 임시로 여기에 작성
	// 공구 신청하기
	boolean applyItem(Order_reg order);
	// 공구 신청 취소하기
	boolean cancelItem(int order_reg_id, int user_id);
	// 특정 공구 신청 내역 가져오기
	Order_reg getOrderById(int order_reg_id, int user_id);
	// 공구 신청 내역 수정하기
	boolean applyUpdate(Order_reg order);
}
