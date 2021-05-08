package com.example.supringboot;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;

import com.example.supringboot.dao.mybatis.MybatisAccountDao;
import com.example.supringboot.dao.mybatis.MybatisAdminDao;
import com.example.supringboot.dao.mybatis.MybatisImageDao;
import com.example.supringboot.dao.mybatis.MybatisPostDao;
import com.example.supringboot.dao.mybatis.MybatisSellTradeDao;
import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.domain.Image;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.domain.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PostAndCommentAndImageTest {

	@Autowired(required=true)
	MybatisPostDao postdao;
	
	@Autowired(required=true)
	MybatisImageDao imagedao;
	
	@Test
//	@Ignore
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
}
