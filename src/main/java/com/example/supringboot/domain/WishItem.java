package com.example.supringboot.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WishItem implements Serializable {

	/* Private Fields */
	private int liked_id;
	private int user_id;
	private Item item;
	private int quantity;
	private int amount;
	private int totalPrice;

	/* JavaBeans Properties */

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getLiked_id() {
		return liked_id;
	}

	public void setLiked_id(int liked_id) {
		this.liked_id = liked_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public int getTotalPrice() {
		if (item != null) {
			totalPrice = item.getItem_price() * quantity;
			return totalPrice;
		} else {
			return 0;
		}
	}
	
	public void incrementQuantity() {
		quantity++;
	}

}
