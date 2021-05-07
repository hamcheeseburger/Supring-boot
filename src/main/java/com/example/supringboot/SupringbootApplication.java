package com.example.supringboot;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.example.supring.mybatis.mapper")
public class SupringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupringbootApplication.class, args);
	}
}
