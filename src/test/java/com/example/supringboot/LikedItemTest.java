package com.example.supringboot;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.supringboot.dao.mybatis.MybatisItemDao;
import com.example.supringboot.domain.Account;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LikedItemTest {
	
	@Autowired(required=true)
	MybatisItemDao tmpDao;
	
//	@Test
	@Ignore
	public void selectAllItem_test() {
		List<Item> list = tmpDao.getAllItemList();
		
		for (Item i : list) {
			System.out.println("item_id: " + i.getItem_id());
			System.out.println("title: " + i.getTitle());
			System.out.println("content: " + i.getContent());
			System.out.println("end_dt: " + i.getEnd_dt());
		}
		System.out.println();
	}
	
//	@Test
	@Ignore
	public void selectOneItem_test() {
		Item item = tmpDao.getDetailItem(21);
		
		System.out.println("item_id: " + item.getItem_id());
		System.out.println("title: " + item.getTitle());
		System.out.println("content: " + item.getContent());
		System.out.println("end_dt: " + item.getEnd_dt());
	}
	
//	@Test
//	@Ignore
//	public void insertOrder_test() {
//		Order_reg order = new Order_reg();
//		
//		Item item = new Item();
//		item.setItem_id(30);
//		order.setItem(item);
//		
//		Account user = new Account();
//		user.setUser_id(61);
//		order.setUser(user);
//		
////		string to timestamp
//		try {
//		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		    Date parsedDate = (Date) dateFormat.parse("2023/05/20");
//		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//		    order.setOrd_reg_dt(timestamp);
//		} catch(Exception e) { //this generic but you can control another types of exception
//		    // look the origin of excption 
//		}
//		
//		order.setReceiver_name("minchae");
//		order.setShip_addr1("Seoul, South Korea");
//		order.setShip_addr2("Guuidong");
//		order.setShip_zip("12345");
//		order.setCard_type("visa");
//		order.setCard_num("1111-2222-3333-4444");
//		
////		string to timestamp, 일단 현재 시간 넣어서 테스트
//		try {
//		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		    Date parsedDate = (Date) dateFormat.parse("2023/05/20");
//		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//		    order.setCard_exp_dt(timestamp);
//		} catch(Exception e) { //this generic but you can control another types of exception
//		    // look the origin of excption 
//		}
//		
//		order.setQuantity(5);
//		order.setOrd_price(29700);
//		
//		boolean result = tmpDao.applyItem(order);
//		
//		if (result) {
//			System.out.println("공구 신청 완료");
//		} else {
//			System.out.println("공구 신청 실패");
//		}
//	}
	
//	@Test
////	@Ignore
//	public void deleteOrder_test() {
//		boolean result = tmpDao.cancelItem(21, 61);
//		
//		if (result) {
//			System.out.println("공구 취소 완료");
//		} else {
//			System.out.println("공구 취소 실패");
//		}
//	}
	
}
