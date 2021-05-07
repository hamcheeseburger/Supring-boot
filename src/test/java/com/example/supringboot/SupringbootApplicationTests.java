package com.example.supringboot;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;

import com.example.supringboot.dao.mybatis.MybatisAccountDao;
import com.example.supringboot.domain.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SupringbootApplicationTests {
	
	@Autowired(required=true)
	MybatisAccountDao dao;
	
//	@Test
	@Ignore
	void selectAccount_test() {
		Account acc = dao.getAccountByLoginId("y77hj");
		assertEquals(acc.getName(),"유현지");
//		System.out.println("getAccountByLoginIdTest - " + acc.getName());
	}
	
	@Ignore
//	@Test
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
		boolean result = dao.loginCheck("y77hj", "1234");
		assertEquals(result, true);
	}
	
//	@Test
	@Ignore
	void updateAccount_test() {
		Account account = new Account();
		account.setUser_id(61);
		account.setLogin_id("aaa");
		account.setPassword("12345");
		account.setName("Hyeonji Yoo");
		account.setEmail("ahah@gmail.com");
		account.setAddr1("Seoul, South Korea");
		account.setAddr2("Guuidong");
		account.setZip("12345");
		account.setPhone("010-2222-1111");
		account.setAdmin(false);
		
		boolean result = dao.updateAccount(account);
		assertEquals(result, true);
	}
	
	
}
