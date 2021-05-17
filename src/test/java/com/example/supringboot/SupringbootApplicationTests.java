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
import com.example.supringboot.dao.mybatis.MybatisItemDao;
import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Comment;
import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.domain.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SupringbootApplicationTests {
	
	@Autowired(required=true)
	MybatisAccountDao dao;
	
	@Autowired(required=true)
	MybatisAdminDao adDao;
	
//	@Test
	@Ignore
	void selectAccount_test() {
		Account acc = dao.getAccountByLoginId("y77hj");
		assertEquals(acc.getName(),"유현지");
		System.out.println("getAccountByLoginIdTest - " + acc.getName());
	}
	
//	@Test
	@Ignore
	void insertAccount_test() {
		Account account = new Account();
		account.setLogin_id("aaa");
		account.setPassword("1234aa5");
		account.setName("Hyeonji Yoo");
		account.setEmail("ahah@gmail.com");
		account.setAddr1("Seoul, South Korea");
		account.setAddr2("Guuidong");
		account.setZip("12345");
		account.setPhone("010-2222-1111");
		account.setAdmin(false);
		
		dao.insertAccount(account);
	}
	
//	@Test
	@Ignore
	void loginCheck_test() {
		Account account = dao.getAccount("y77hj", "1234");
		if(account == null) {
			
		}
	}
	
	@Test
//	@Ignore
	void updateAccount_test() {
		Account account = new Account();
		account.setUser_id(61);
		account.setPassword("12345");
		account.setName("유현지");
		account.setEmail("ahah@gmail.com");
		account.setAddr1("Seoul, South Korea");
		account.setAddr2("Guuidong");
		account.setZip("12345");
		account.setPhone("010-2222-1111");
		account.setAdmin(false);
		
		boolean result = dao.updateAccount(account);
		assertEquals(result, true);
	}
	
	@Test
//	@Ignore
	void selectOrderReg_test() {
		ArrayList<Order_reg> reg = dao.selectMyOrderRegs(41);
		for(Order_reg oReg : reg) {
			System.out.println("ITEM_ID : " + oReg.getItem().getItem_id());
			System.out.println("ORDER_REG_ID : " + oReg.getOrder_reg_id());
			System.out.println("ORDER_REG_DT : " + oReg.getOrd_reg_dt());
			System.out.println("ITEM_TITLE : " + oReg.getItem().getTitle());
			System.out.println("ITEM_TITLE : " + oReg.getItem().getFood().getName());
		}
	}
	
//	@Test
	@Ignore
	void selectOrder_test() {
		ArrayList<Order_reg> reg = dao.selectMyOrders(61);
		for(Order_reg oReg : reg) {
			System.out.println("ITEM_ID : " + oReg.getItem().getItem_id());
			System.out.println("ORDER_REG_ID : " + oReg.getOrder_reg_id());
			System.out.println("ORDER_REG_DT : " + oReg.getOrd_reg_dt());
		}
	}
	
//	@Test
	@Ignore
	void selectPosts_test() {
		ArrayList<Post> posts = dao.selectMyPosts(61);
		for(Post p: posts) {
			System.out.println("POST_ID : " + p.getPost_id());
			System.out.println("POST_TITLE : " + p.getTitle());
			System.out.println("POST_ACCOUNT_NAME : " + p.getUser().getName());
		}
	}
	
//	@Test
	@Ignore
	void selectMyComments_test() {
		ArrayList<Comment> commts = dao.selectMyComments(41);
		for(Comment c : commts) {
			System.out.println("COMMT_ID : " + c.getComment_id());
			System.out.println("COMMT_CONTENT : " + c.getContent());
			System.out.println("COMMT_POST_TITLE : " + c.getPost().getTitle());
		}
	}
	
//	@Test
	@Ignore
	void selectAdminItems_test() {
		ArrayList<Item> items = adDao.selectAdminItems(41);
		for(Item i : items) {
			System.out.println("ITEM_ID : " + i.getItem_id());
			System.out.println("ITEM_STATUS : " + i.getItem_status());
			System.out.println("ITEM_NUMOFREGISTER : " + i.getNumOfRegister());
		}
	}
	
//	@Test
	@Ignore
	void selectAdminOrderRegs_test() {
		ArrayList<Order_reg> regs = adDao.selectItemOrderRegs(1);
		for(Order_reg r: regs) {
			System.out.println("REG_ID : " +  r.getOrder_reg_id());
			System.out.println("REG_ACCOUNT_NAME : " + r.getUser().getName());
			System.out.println("REG_RECEIVER : " + r.getReceiver_name());
			System.out.println("REG_ADD : " + r.getShip_addr1() + ", " + r.getShip_addr2());
			System.out.println("REG_CARD_NUM : " + r.getCard_num());
		}
	}
	

}
