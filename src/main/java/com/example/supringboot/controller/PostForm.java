package com.example.supringboot.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Category;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.domain.Image;

@SuppressWarnings("serial")
public class PostForm implements Serializable {
	private int post_id; // 기본키
	private Account user; // Post를 작성한 사용자 객체
	private Category category; // Post가 분류될 카테고리 객체
	@NotBlank(message = "{title.NotBlank}")
	private String title; // Post 제목
	@NotBlank(message = "{content.NotBlank}")
	private String content; // Post 내용
	@NotBlank(message = "{food_name.NotBlank}")
	private String food_name; // 거래 될 음식 이름
//	@NotNull(message = "{price.NotNull}")
//	@Min(value = 0, message="{price.NumberFormat}")
	private int price; // 거래 금액
	@NotBlank(message = "{trade_type.NotBlank}")
	private String trade_type; // 거래 타입 (교환, 거래) => (trade, payment)
	@NotNull(message = "{trade_status.NotNull}")
	private int trade_status; // 교환 상태 (1: 교환 또는 거래완료, 0: 교환 또는 거래가능)
	private Timestamp created_dt; // Post 게시일
	private Timestamp modified_dt; // Post 수정일
//	@NotNull(message = "{quantity.NotNull}")
//	@Min(value = 1, message="{quantity.NumberFormat}")
	private int quantity; // 교환 또는 거래 할 식품 개수 (소수점 단위는 사용 불가!! 0.5 이런 숫자 사용시 오류남.)
	@NotBlank(message = "{unit.NotBlank}")
	private String unit; // 교환 또는 거래 할 식품 단위
	private Timestamp exp_dt; // 식품 유통기한
	@NotNull(message = "{ship_type.NotNull}")
	private int ship_type; // 거래 방법 (1: 배송, 0: 직거래)
	private ArrayList<Image> images; // 식품 이미지
	private ArrayList<Comment> comments; // Post에 달린 댓글들
	/* post form에서만 사용하는 변수들 */
	@NotBlank(message = "{exp_dt_string.NotBlank}")
	@Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", message = "{exp_dt_string.pattern}")
	private String exp_dt_string; // 식품 유통기한 (from postForm) :  yyyy-MM-dd
//	private int user_id; // 사용x
	private MultipartFile file;
	private String str_price;
	private String str_quantity;
	private String fileChanged;
	
	public PostForm() {
		super();
	}

	public int getPost_id() {
		return post_id;
	}

	public String getStr_price() {
		return str_price;
	}

	public void setStr_price(String str_price) {
		this.str_price = str_price;
	}

	public String getStr_quantity() {
		return str_quantity;
	}

	public void setStr_quantity(String str_quantity) {
		this.str_quantity = str_quantity;
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

	public String getFileChanged() {
		return fileChanged;
	}

	public void setFileChanged(String fileChanged) {
		this.fileChanged = fileChanged;
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

	public String getExp_dt_string() {
		return exp_dt_string;
	}

	public void setExp_dt_string(String exp_dt_string) {
		this.exp_dt_string = exp_dt_string;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
