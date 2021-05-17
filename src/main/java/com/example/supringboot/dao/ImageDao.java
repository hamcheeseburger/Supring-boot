package com.example.supringboot.dao;

import com.example.supringboot.domain.Image;

public interface ImageDao {
//	추후 여러 이미지를 삽입하게 한다.
	public int insertImage(Image image);
	public int insertImageWithPost(Image image);
	public int deleteImage(int image_id);
	public Image selectImgage(int post_id);
	public Image selectImageByItemId(int item_id);
}
