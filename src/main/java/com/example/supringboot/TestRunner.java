package com.example.supringboot;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.supringboot.dao.mybatis.MybatisAccountDao;
import com.example.supringboot.domain.Account;

@Component
public class TestRunner implements ApplicationRunner {
//	  @Autowired
//	  DataSource dataSource;
//	  
//	  @Autowired
//	  MybatisAccountDao dao;
//	  
	  @Override
	  public void run(ApplicationArguments args) throws Exception {
		  
////	      Connection connection = dataSource.getConnection();
////	      System.out.println("Url: " + connection.getMetaData().getURL());
//		  Account acc = dao.getAccountByLoginId("y77hj");
//		  System.out.println(acc.getPassword());
	  }
}
