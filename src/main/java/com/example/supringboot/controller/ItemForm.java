package com.example.supringboot.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.web.multipart.MultipartFile;

import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Image;
import com.example.supringboot.domain.Item;

/**
 * @author yoo77
 *
 */
@SuppressWarnings("serial")
public class ItemForm implements Serializable{
	private int item_id; // 기본키
	private Food food; // 등록할 식품 객체
	private int food_id;
	private int user_id; // 게시한 관리자 기본키
	
	@NotBlank(message = "게시글 제목은 필수 입력 값입니다.")
	private String title; // 게시글 제목
	
	@NotBlank(message = "게시글 내용은 필수 입력 값입니다.")
	private String content; // 게시글 내용
	
	@NotBlank(message = "공구상품 만료일은 필수 입력 값입니다.")
	@Pattern(regexp="^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])",
	message="yyyy-MM-dd 형식으로 작성해야 합니다.")
	private String end_dt; // 공동구매 만료일
	private String end_dt_time;
	private int min_quantity; // 공동구매 최소수량(목표수량)
	
	@Pattern(regexp="^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])",
	message="yyyy-MM-dd 형식으로 작성해야 합니다.")
	@NotBlank(message = "공구상품 시작일은 필수 입력 값입니다.")
	private String created_dt; // 공동구매 시작일
	private String created_dt_time;
	private String modified_dt; // 내용 수정일
	private String item_status; // 공동구매 상태 (진행중: ongoing, 실패:fail, 성공:success)
	
	private int item_price; // 식품 가격
	private int ship_price; // 배송 가격
	
	private MultipartFile file; //이미지파일 
	ArrayList<Image> images; // 식품 이미지들
	private int numOfRegister; // 신청수
	
	private String fileChanged;
	
	//금액 입력 필드
	@NotBlank(message = "배송가격은 필수 입력 값입니다.")
	@PositiveOrZero(message = "배송 가격은 0이상의 양수 값입니다.")
	private String str_ship_price; //배송비
	
	@NotBlank(message = "상품가격은 필수 입력 값입니다.")
	@PositiveOrZero(message = "상품 가격은 0이상의 양수 값입니다.")
	private String str_item_price; //상품가격
	
	@NotBlank(message = "상품의 최소수량은 필수 입력 값입니다.")
	@PositiveOrZero(message = "수량은 0이상의 양수 값입니다.")
	private String str_min_quantity; //목표수량
	
	public ItemForm() {
		super();
	}
	
	public ItemForm(int item_id, Food food, int user_id, int price, int ship_price, String title, String content,
			String end_dt, int min_quantity, String created_dt, String modified_dt, String item_status,
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
	
	//
	public String getStr_ship_price() {		return str_ship_price;	}
	public void setStr_ship_price(String str_ship_price) {	this.str_ship_price = str_ship_price;	}

	public String getStr_item_price() {	return str_item_price;	}
	public void setStr_item_price(String str_item_price) {	this.str_item_price = str_item_price;	}

	public String getStr_min_quantity() {	return str_min_quantity;	}
	public void setStr_min_quantity(String str_min_quantity) {	this.str_min_quantity = str_min_quantity;	}
	
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
	public int getFood_id() {
		return food_id;
	}
	public void setFood_id(int food_id) {
		this.food_id = food_id;
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
	public String getEnd_dt() {
		return end_dt;
	}
	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}
	public int getMin_quantity() {
		return min_quantity;
	}
	public void setMin_quantity(int min_quantity) {
		this.min_quantity = min_quantity;
	}
	public String getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(String created_dt) {
		this.created_dt = created_dt;
	}
	public String getModified_dt() {
		return modified_dt;
	}
	public void setModified_dt(String modified_dt) {
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getEnd_dt_time() {
		return end_dt_time;
	}

	public void setEnd_dt_time(String end_dt_time) {
		this.end_dt_time = end_dt_time;
	}

	public String getCreated_dt_time() {
		return created_dt_time;
	}

	public void setCreated_dt_time(String created_dt_time) {
		this.created_dt_time = created_dt_time;
	}

	public String getFileChanged() {
		return fileChanged;
	}

	public void setFileChanged(String fileChanged) {
		this.fileChanged = fileChanged;
	}

}
