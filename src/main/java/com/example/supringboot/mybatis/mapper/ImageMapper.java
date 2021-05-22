package com.example.supringboot.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.example.supringboot.domain.Image;

@Mapper
public interface ImageMapper {
//	다중 이미지 삽입은 추후 구현
	int insertImage(Image image) throws DataAccessException;
	int insertImageWithPost(Image image) throws DataAccessException;
	int insertImageWithItem(Image image) throws DataAccessException;
	int deleteImage(@Param("image_id") int image_id) throws DataAccessException;
	Image getImageByPostId(@Param("post_id") int post_id) throws DataAccessException;
	Image getImageByItemId(@Param("item_id") int item_id) throws DataAccessException;
}
