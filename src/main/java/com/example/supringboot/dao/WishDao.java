package com.example.supringboot.dao;

import java.util.List;

import com.example.supringboot.domain.WishItem;

public interface WishDao {
	// 찜한 식품 확인
	public List<WishItem> getWishItem(int user_id);

	// 공구식품 찜하기
	public boolean likedItem(int user_id, int item_id, int amount);

	// 공구식품 찜하기 취소 -> 찜하기 목록 페이지에서 취소
	public boolean cancelLikedItem(int liked_id);
	
	// 공구식품 찜하기 취소 -> 식품 상세 페이지에서 취소
	public boolean cancelDetailLikedItem(int user_id, int item_id);
	
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
	
	// 특정 찜한 식품 가져오기
	public WishItem getOneWishItem(int liked_id);
}
