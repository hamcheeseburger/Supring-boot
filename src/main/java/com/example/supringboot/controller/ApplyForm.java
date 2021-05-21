package com.example.supringboot.controller;

import java.io.Serializable;

import com.example.supringboot.domain.Order_reg;

public class ApplyForm  implements Serializable{
	
//	private final Order_reg order = new Order_reg();
	private Order_reg order = new Order_reg();
	private boolean shippingAddressRequired = false;
	private boolean shippingAddressProvided = false;

	public Order_reg getOrder() {
		return order;
	}

	public void setShippingAddressRequired(boolean shippingAddressRequired) {
		this.shippingAddressRequired = shippingAddressRequired;
	}

	public boolean isShippingAddressRequired() {
		return shippingAddressRequired;
	}

	public void setShippingAddressProvided(boolean shippingAddressProvided) {
		this.shippingAddressProvided = shippingAddressProvided;
	}

	public boolean didShippingAddressProvided() {
		return shippingAddressProvided;
	}
}
