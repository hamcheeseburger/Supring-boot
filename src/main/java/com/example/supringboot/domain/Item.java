package com.example.supringboot.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

// 공동구매
@SuppressWarnings("serial")
public class Item implements Serializable {
	private int item_id; // 기본키
	private Food food; // 등록할 식품 객체
	private int user_id; // 게시한 관리자 기본키
	private String title; // 게시글 제목
	private String content; // 게시글 내용
	private Timestamp end_dt; // 공동구매 만료일
	private int min_quantity; // 공동구매 최소수량(목표수량)
	private Timestamp created_dt; // 공동구매 시작일
	private Timestamp modified_dt; // 내용 수정일
	private String item_status; // 공동구매 상태 (진행중: ongoing, 실패:failure, 성공:success)
	private int item_price; // 식품 가격
	private int ship_price; // 배송 가격
	ArrayList<Image> images; // 식품 이미지들
	private int numOfRegister; // 신청수
	
	public Item() {
		super();
	}
	
	public Item(int item_id, Food food, int user_id, int price, int ship_price, String title, String content,
			Timestamp end_dt, int min_quantity, Timestamp created_dt, Timestamp modified_dt, String item_status,
			ArrayList<Image> images, int numOfRegister) {
		super();
		this.item_id = item_id;
		this.food = food;
		this.user_id = user_id;
		this.item_price = price;
		this.ship_price = ship_price;
		this.title = title;
		this.content = content;
		this.end_dt = end_dt;
		this.min_quantity = min_quantity;
		this.created_dt = created_dt;
		this.modified_dt = modified_dt;
		this.item_status = item_status;
		this.images = images;
		this.numOfRegister = numOfRegister;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public int getItem_price() {
		return item_price;
	}

	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}

	public int getShip_price() {
		return ship_price;
	}
	public void setShip_price(int ship_price) {
		this.ship_price = ship_price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getEnd_dt() {
		return end_dt;
	}
	public void setEnd_dt(Timestamp end_dt) {
		this.end_dt = end_dt;
	}
	public int getMin_quantity() {
		return min_quantity;
	}
	public void setMin_quantity(int min_quantity) {
		this.min_quantity = min_quantity;
	}
	public Timestamp getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Timestamp created_dt) {
		this.created_dt = created_dt;
	}
	public Timestamp getModified_dt() {
		return modified_dt;
	}
	public void setModified_dt(Timestamp modified_dt) {
		this.modified_dt = modified_dt;
	}
	public String getItem_status() {
		return item_status;
	}
	public void setItem_status(String item_status) {
		this.item_status = item_status;
	}
	public ArrayList<Image> getImages() {
		return images;
	}
	public void setImages(ArrayList<Image> images) {
		this.images = images;
	}
	public int getNumOfRegister() {
		return numOfRegister;
	}
	public void setNumOfRegister(int numOfRegister) {
		this.numOfRegister = numOfRegister;
	}
}
