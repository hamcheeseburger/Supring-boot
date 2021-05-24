package com.example.supringboot.domain;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Comment extends Common implements Serializable {
	private int comment_id; // 기본키
	private Post post; // 댓글이 달린 게시글 객체
	private Account user; // 댓글을 게시한 사용자 객체
	private String content; // 댓글 내용
	private Timestamp created_dt; // 댓글 게시일
	private Timestamp modified_dt; // 댓글 수정일
	private int secret; // 비밀댓글 여부 (1: 비밀댓글, 0: 공개댓글)
	
//	paging으로 인해 user name을 바로 저장하는 필드 필요
	private String name;
	private String str_modified_dt;
	private String str_created_dt;
//	댓글 삽입시 사용할 post_id
	private int post_id;
	
	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public Comment() {
		super();
	}
	
	public Comment(int comment_id, Post post, Account user, String content, Timestamp created_dt, Timestamp modified_dt,
			int secret) {
		super();
		this.comment_id = comment_id;
		this.post = post;
		this.user = user;
		this.content = content;
		this.created_dt = created_dt;
		this.modified_dt = modified_dt;
		this.secret = secret;
	}
	
	public String getStr_modified_dt() {
		return str_modified_dt;
	}

	public void setStr_modified_dt(String str_modified_dt) {
		this.str_modified_dt = str_modified_dt;
	}

	public String getStr_created_dt() {
		return str_created_dt;
	}

	public void setStr_created_dt(String str_created_dt) {
		this.str_created_dt = str_created_dt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public Account getUser() {
		return user;
	}
	public void setUser(Account user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public int getSecret() {
		return secret;
	}
	public void setSecret(int secret) {
		this.secret = secret;
	}
	
	
}
