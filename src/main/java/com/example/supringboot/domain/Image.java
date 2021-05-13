package com.example.supringboot.domain;

import java.io.Serializable;
import java.util.Base64;
import java.util.Base64.Encoder;

@SuppressWarnings("serial")
public class Image implements Serializable {
	private int image_id; // Image 테이블 기본키
	private int post_id; // Post 테이블 기본키
	private int item_id; // Item 테이블 기본키
	// 주의사항 : Image객체 1개당 post_id or item_id를 갖는다. 둘 다 가질 순 없다.
	private byte[] image; // 이미지
	private String base64Image;
	
	public Image() {
		super();
	}
	
	public Image(int image_id, int post_id, int item_id, byte[] image) {
		super();
		this.image_id = image_id;
		this.post_id = post_id;
		this.item_id = item_id;
		this.image = image;
	}
	
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
}
