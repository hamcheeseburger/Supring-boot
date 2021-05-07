package com.example.supringboot.domain;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Comment implements Serializable {
	private int comment_id; // 기본키
	private Post post; // 댓글이 달린 게시글 객체
	private Account user; // 댓글을 게시한 사용자 객체
	private String content; // 댓글 내용
	private Timestamp created_dt; // 댓글 게시일
	private Timestamp modified_dt; // 댓글 수정일
	private int secret; // 비밀댓글 여부 (1: 비밀댓글, 0: 공개댓글)
	
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
