package com.example.supringboot.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Food implements Serializable {
	private int food_id; // food 테이블 기본키
	private int cat_id; // Category 테이블 기본키
	private String name; // 식품 이름
	private int serving_size; // 식품 1회 제공량
	private int serving_size_unit; // 식품 1회 제공량 단위
	private int calories; // 열량 수치
	private int protein; // 단백질 수치
	private int grease; // 지방 수치
	private int carbohydrate; // 탄수화물 수치
	private int sugars; // 당 수치
	
	public Food() {
		super();
	}
	
	public Food(int food_id, int cat_id, String name, int serving_size, int serving_size_unit, int calories,
			int protein, int grease, int carbohydrate, int sugars) {
		super();
		this.food_id = food_id;
		this.cat_id = cat_id;
		this.name = name;
		this.serving_size = serving_size;
		this.serving_size_unit = serving_size_unit;
		this.calories = calories;
		this.protein = protein;
		this.grease = grease;
		this.carbohydrate = carbohydrate;
		this.sugars = sugars;
	}
	public int getFood_id() {
		return food_id;
	}
	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getServing_size() {
		return serving_size;
	}
	public void setServing_size(int serving_size) {
		this.serving_size = serving_size;
	}
	public int getServing_size_unit() {
		return serving_size_unit;
	}
	public void setServing_size_unit(int serving_size_unit) {
		this.serving_size_unit = serving_size_unit;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public int getProtein() {
		return protein;
	}
	public void setProtein(int protein) {
		this.protein = protein;
	}
	public int getGrease() {
		return grease;
	}
	public void setGrease(int grease) {
		this.grease = grease;
	}
	public int getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(int carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public int getSugars() {
		return sugars;
	}
	public void setSugars(int sugars) {
		this.sugars = sugars;
	}
	
	
}
