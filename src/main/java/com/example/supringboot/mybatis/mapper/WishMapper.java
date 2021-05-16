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
	public int likedItem(@Param("user_id") int user_id, @Param("item_id") int item_id) throws DataAccessException;

	// 공구식품 찜하기 취소
	public int cancelLikedItem(@Param("user_id") int user_id, @Param("item_id") int item_id) throws DataAccessException;
}
