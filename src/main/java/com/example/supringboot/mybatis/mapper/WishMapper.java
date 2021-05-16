package com.example.supringboot.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.WishItem;

@Mapper
public interface WishMapper {

	// 찜한 식품 확인
	List<WishItem> getWishItem(int user_id) throws DataAccessException;

	// 공구식품 찜하기
	public int likedItem(WishItem wish) throws DataAccessException;

	// 공구식품 찜하기 취소 -> 찜한 목록 페이지에서 취소
	public int cancelLikedItem(@Param("liked_id") int liked_id) throws DataAccessException;
	
	// 공구식품 찜하기 취소 -> 식품 상세 페이지에서 취소
	public int cancelDetailLikedItem(@Param("user_id") int user_id, @Param("item_id") int item_id) throws DataAccessException;

	// 찜한 식품 모두 삭제(찜한 식품 목록 비우기)
	public int deleteAllLikedItem(@Param("user_id") int user_id);

	// 찜하기 수정 -> 찜하기 화면에서 식품 수량을 변경할때
	public int updateLikedItem(WishItem wish);

	// 찜한 식품 수량 변경 -> 식품 상세페이지에서 동일한 식품을 찜하기 했을때 사용
	public int updateQuantity(WishItem wish);

	// 찜한 식품 전체 금액 가져오기
	public int totalMoney(@Param("user_id") int user_id);

	// 찜한 식품 목록에 동일한 식품이 있는지 확인
	public int isWishItem(@Param("user_id") int user_id, @Param("item_id") int item_id);
	
	// 특정 찜한 식품 가져오기
	public WishItem getOneWishItem(@Param("liked_id") int liked_id);
}
