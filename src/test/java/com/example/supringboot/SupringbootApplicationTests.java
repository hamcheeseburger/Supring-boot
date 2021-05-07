package com.example.supringboot;

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
	
	@Test
	void Account_test() {
		Account acc = dao.getAccountByLoginId("y77hj");
		System.out.println(acc.getName());
//		System.out.println("TEST");
	}

}
