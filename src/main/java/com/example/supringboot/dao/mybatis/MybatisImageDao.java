package com.example.supringboot.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.supringboot.dao.ImageDao;
import com.example.supringboot.domain.Image;
import com.example.supringboot.mybatis.mapper.ImageMapper;

@Repository
public class MybatisImageDao implements ImageDao {
	@Autowired
	private ImageMapper imageMapper;

	@Override
	public int insertImage(Image image) {
		return imageMapper.insertImage(image);
	}

	@Override
	public int deleteImage(int image_id) {
		return imageMapper.deleteImage(image_id);
	}

	@Override
	public Image selectImgage(int post_id) {
		return imageMapper.getImageByPostId(post_id);
	}

	@Override
	public Image selectImageByItemId(int item_id) {
		return imageMapper.getImageByItemId(item_id);
	}
	
	
}
