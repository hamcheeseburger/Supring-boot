package com.example.supringboot;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements ApplicationRunner {
	  @Autowired
	  DataSource dataSource;

	  @Override
	  public void run(ApplicationArguments args) throws Exception {

	      Connection connection = dataSource.getConnection();
	      System.out.println("Url: " + connection.getMetaData().getURL());

	  }
}
