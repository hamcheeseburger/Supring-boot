package com.example.supringboot.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("serial")
public class Order_reg implements Serializable {
	private int order_reg_id; // 기본키 (주문번호)
	private Item item; // 구매신청한 식품 객체
	private Account user; // 구매 신청한 사용자 객체 
	private Timestamp ord_reg_dt; // 구매 신청일
	private int quantity; // 구매 신청 수량
	private int ord_price; // 총 주문 금액
	private String receiver_name; // 수령인
	private String ship_addr1; // 배송주소1
	private String ship_addr2; // 배송주소2
	private String ship_zip; // 배송지 우편번호
	private String card_type; // 카드 타입 (visa, master)
	private String card_num; // 카드번호
	private Timestamp card_exp_dt; // 카드 만료기간
	
	public Order_reg() {
		super();
	}	
	
	public Order_reg(int order_reg_id, Item item, Account user, Timestamp ord_reg_dt, int quantity, int ord_price,
			String receiver_name, String ship_addr1, String ship_addr2, String card_type, String card_number,
			Timestamp card_exp_dt) {
		super();
		this.order_reg_id = order_reg_id;
		this.item = item;
		this.user = user;
		this.ord_reg_dt = ord_reg_dt;
		this.quantity = quantity;
		this.ord_price = ord_price;
		this.receiver_name = receiver_name;
		this.ship_addr1 = ship_addr1;
		this.ship_addr2 = ship_addr2;
		this.card_type = card_type;
		this.card_num = card_number;
		this.card_exp_dt = card_exp_dt;
	}
	
	public void initOrder(Account account, Item receive_item, int amount, int total) {
		user = account;
		item = receive_item;
		ord_reg_dt = new Timestamp(new Date().getTime());
		quantity = amount;
		ord_price = total;
		
		ship_addr1 = account.getAddr1();
	    ship_addr2 = account.getAddr2();
	    ship_zip = account.getZip();
	}
	
	public int getOrder_reg_id() {
		return order_reg_id;
	}
	public void setOrder_reg_id(int order_reg_id) {
		this.order_reg_id = order_reg_id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Account getUser() {
		return user;
	}
	public void setUser(Account user) {
		this.user = user;
	}
	public Timestamp getOrd_reg_dt() {
		return ord_reg_dt;
	}
	public void setOrd_reg_dt(Timestamp ord_reg_dt) {
		this.ord_reg_dt = ord_reg_dt;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getOrd_price() {
		return ord_price;
	}
	public void setOrd_price(int ord_price) {
		this.ord_price = ord_price;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getShip_addr1() {
		return ship_addr1;
	}
	public void setShip_addr1(String ship_addr1) {
		this.ship_addr1 = ship_addr1;
	}
	public String getShip_addr2() {
		return ship_addr2;
	}
	public void setShip_addr2(String ship_addr2) {
		this.ship_addr2 = ship_addr2;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getCard_num() {
		return card_num;
	}

	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}

	public Timestamp getCard_exp_dt() {
		return card_exp_dt;
	}
	public void setCard_exp_dt(Timestamp card_exp_dt) {
		this.card_exp_dt = card_exp_dt;
	}

	public String getShip_zip() {
		return ship_zip;
	}

	public void setShip_zip(String ship_zip) {
		this.ship_zip = ship_zip;
	}
	
	
}
