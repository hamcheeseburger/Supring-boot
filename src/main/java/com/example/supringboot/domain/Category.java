package com.example.supringboot.domain;

public class Category {
	private int cat_id; // 기본키
	private String cat_name; // 카테고리 명
	
	
	public Category() {
		super();
	}
	
	public Category(int cat_id, String cat_name) {
		super();
		this.cat_id = cat_id;
		this.cat_name = cat_name;
	}
	
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}	
}
