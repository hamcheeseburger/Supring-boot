package com.example.supringboot.domain;

import java.io.Serializable;
//
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Pattern;

@SuppressWarnings("serial")
public class Account implements Serializable {

	private int user_id; // 기본키
	private String login_id; // 로그인 아이디
	private String password; // 비밀번호
	private String email; // 이메일 주소
	private String name; // 사용자 이름
	private String addr1; // 주소1 (서울특별시 ㅁㅁ구)
	private String addr2; // 주소2 (ㅇㅇ동)
	private String zip; // 우편번호
	private String phone; // 핸드폰 번호
	private boolean admin; // 관리자 여부 (true: 관리자, false: 일반 사용자)
	
	public Account() {
		super();
	}
	
	public Account(int user_id, String login_id, String password, String email, String name, String addr1, String addr2,
			String zip, String phone, boolean admin) {
		super();
		this.user_id = user_id;
		this.login_id = login_id;
		this.password = password;
		this.email = email;
		this.name = name;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zip = zip;
		this.phone = phone;
		this.admin = admin;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
