package com.example.supringboot;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;

import com.example.supringboot.dao.mybatis.MybatisAccountDao;
import com.example.supringboot.dao.mybatis.MybatisAdminDao;
import com.example.supringboot.dao.mybatis.MybatisCommentDao;
import com.example.supringboot.dao.mybatis.MybatisImageDao;
import com.example.supringboot.dao.mybatis.MybatisPostDao;
import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.domain.Image;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.domain.Post;
import com.example.supringboot.mybatis.mapper.PostMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PostAndCommentAndImageTest {

	@Autowired(required=true)
	MybatisPostDao postdao;
	
	@Autowired(required=true)
	MybatisImageDao imagedao;
	
	@Autowired(required=true)
	MybatisCommentDao commentdao;
	
//	@Test
	@Ignore
	void getOnePostById_test() {
		int post_id = 1;
		Post post = postdao.detailPost(post_id);
		System.out.println(post.getPost_id());
	}
	
//	@Test
	@Ignore
	void getImageByPostId_test() {
		int post_id = 1;
		Image image =  imagedao.selectImgage(post_id);
		byte[] image_content = image.getImage();
	}
	
//	@Test
	@Ignore
	void searchAndgetCountPost() {
		String key = "당근";
		int count = postdao.getPostCount();
		ArrayList<Post> results = postdao.searchPostList(key);
	}
	
//	@Test
	@Ignore
	void insertComment() {
		Comment comment = new Comment();
		comment.setContent("저 살래요!");
		
		Post post = new Post();
		post.setPost_id(3);
		
		Account user = new Account();
		user.setUser_id(41);
		
		comment.setPost(post);
		comment.setUser(user);
		
		int result = commentdao.insertComment(comment);
	}
	
//	@Test
	@Ignore
	void updateComment() {
		Comment comment = new Comment();
		comment.setContent("불발되면 연락주세요");
		comment.setComment_id(7);
		
		int result = commentdao.updateComment(comment);
	}
	
	
//	@Test
	@Ignore
	void getAllPostList() {
		Post post = new Post();
		ArrayList<Post> list = postdao.getAllPostList(post);
		System.out.print(list.size());
	}
	
//	@Test
	@Ignore
	void insertPost() {
		Post post = new Post();
		post.setTitle("달걀 사실분");
		post.setContent("3천원에 달걀 15개입니다.");
		
		Account user = new Account();
		user.setUser_id(41);
		post.setUser(user);
		
//		string to timestamp
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		    Date parsedDate = (Date) dateFormat.parse("2023/05/20");
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    post.setExp_dt(timestamp);
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		
		post.setFood_name("달걀");
		post.setPrice(3000);
		post.setQuantity(15);
		post.setShip_type(0);
		post.setTrade_status(0);
		post.setTrade_type("payment");
		post.setUnit("개");
		
		int result = postdao.insertPost(post);
		
	}
	
//	@Test
	@Ignore
	void updatePost() {
//		try {
//		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		    Date parsedDate = (Date) dateFormat.parse("2023/05/20");
//		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//		    System.out.print(timestamp);
//		} catch(Exception e) { //this generic but you can control another types of exception
//		    // look the origin of excption 
//		}
			
		Post post = new Post();
		post.setTitle("달걀 사실분?!?!?!");
		post.setContent("3천원에 달걀 15개입니다.");
		
		Account user = new Account();
		user.setUser_id(41);
		post.setUser(user);
		
//		string to timestamp
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		    Date parsedDate = (Date) dateFormat.parse("2023/05/20");
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    post.setExp_dt(timestamp);
		} catch(Exception e) { //this generic but you can control another types of exception
		    e.printStackTrace();
		}
		
		post.setFood_name("달걀");
		post.setPrice(3000);
		post.setQuantity(15);
		post.setShip_type(0);
		post.setTrade_status(0);
		post.setTrade_type("payment");
		post.setUnit("개");
		
		post.setPost_id(8);
		
//		int result = postdao.updatePost(post);
		
	}
	
//	@Test
	@Ignore
	void deletePost() {
		int result = postdao.removePost(3);
	}
	
}
