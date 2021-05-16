package com.example.supringboot.dao;

import java.util.List;

import com.example.supringboot.domain.WishItem;

public interface WishDao {
	// 찜한 식품 확인
	public List<WishItem> getWishItem(int user_id);
	// 공구식품 찜하기
	public boolean likedItem(int user_id, int item_id);
	// 공구식품 찜하기 취소
	public boolean cancelLikedItem(int user_id, int item_id);
}
