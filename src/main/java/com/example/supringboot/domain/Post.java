package com.example.supringboot.domain;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Post {
	private int post_id; // 기본키
	private Account user; // Post를 작성한 사용자 객체
	private Category category; // Post가 분류될 카테고리 객체
	private String title; // Post 제목
	private String content; // Post 내용
	private String food_name; // 거래 될 음식 이름
	private int price; // 거래 금액
	private String trade_type; // 거래 타입 (교환, 거래) => (trade, payment)
	private int trade_status; // 교환 상태 (1: 교환 또는 거래완료, 0: 교환 또는 거래가능)
	private Timestamp created_dt; // Post 게시일
	private Timestamp modified_dt; // Post 수정일
	private int quantity; // 교환 또는 거래 할 식품 개수
	private String unit; // 교환 또는 거래 할 식품 단위
	private Timestamp exp_dt; // 식품 유통기한
	private int ship_type; // 거래 방법 (1: 배송, 0: 직거래)
	private ArrayList<Image> images; // 식품 이미지
	private ArrayList<Comment> comments; // Post에 달린 댓글들
	
	public Post() {
		super();
	}
	
	public Post(int post_id, Account user, Category category, String title, String content, String food_name, int price,
			String trade_type, int trade_status, Timestamp created_dt, Timestamp modified_dt, int quantity, String unit,
			Timestamp exp_dt, int ship_type, ArrayList<Image> images, ArrayList<Comment> comments) {
		super();
		this.post_id = post_id;
		this.user = user;
		this.category = category;
		this.title = title;
		this.content = content;
		this.food_name = food_name;
		this.price = price;
		this.trade_type = trade_type;
		this.trade_status = trade_status;
		this.created_dt = created_dt;
		this.modified_dt = modified_dt;
		this.quantity = quantity;
		this.unit = unit;
		this.exp_dt = exp_dt;
		this.ship_type = ship_type;
		this.images = images;
		this.comments = comments;
	}
	
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public Account getUser() {
		return user;
	}
	public void setUser(Account user) {
		this.user = user;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public int getTrade_status() {
		return trade_status;
	}
	public void setTrade_status(int trade_status) {
		this.trade_status = trade_status;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Timestamp getExp_dt() {
		return exp_dt;
	}
	public void setExp_dt(Timestamp exp_dt) {
		this.exp_dt = exp_dt;
	}
	public int getShip_type() {
		return ship_type;
	}
	public void setShip_type(int ship_type) {
		this.ship_type = ship_type;
	}
	public ArrayList<Image> getImages() {
		return images;
	}
	public void setImages(ArrayList<Image> images) {
		this.images = images;
	}
	public ArrayList<Comment> getComments() {
		return comments;
	}
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
}
