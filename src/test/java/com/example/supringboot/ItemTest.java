package com.example.supringboot;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.supringboot.dao.mybatis.MybatisImageDao;
import com.example.supringboot.dao.mybatis.MybatisItemDao;
import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemTest {
	
	@Autowired(required=true)
	MybatisItemDao itemDao;
	
	@Autowired(required=true)
	MybatisImageDao imagedao;
	
//	@Test
	@Ignore
	void searchFood_test() {
		System.out.println("food search 테스트 시작");
		List<Food> f = new ArrayList<Food>();
		f = itemDao.searchFoodList("자몽");
		System.out.println("dao수행");
		for(int i = 0; i < f.size(); i++) {
			System.out.println(f.get(i).getName());
		}
	}
	
//	@Test
	@Ignore
	void insertItem_test() {
		System.out.println("insert 테스트 시작");
		Food f = new Food(1200,11, "블루베리치즈빙수", 685, "g", 680, 21, 0, 0, 104);
		System.out.println("푸드id: " + f.getFood_id());
		
		Item item = new Item();
		item.setUser_id(61);
		item.setFood(f);
		item.setItem_price(129000);
		item.setShip_price(2500);
		item.setTitle("쟁반짜장");
		item.setContent("홍콩반점 신상");
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		    Date endDate = (Date) dateFormat.parse("2021/06/12");
		    Date createDate = (Date) dateFormat.parse("2021/05/12");
		    Date modifyDate = new Date();
		    item.setEnd_dt(new java.sql.Timestamp(endDate.getTime()));
		    item.setCreated_dt(new java.sql.Timestamp(createDate.getTime()));
		    item.setModified_dt(new java.sql.Timestamp(modifyDate.getTime()));
		} catch(Exception e) { }
		item.setMin_quantity(30);
		item.setItem_status("진행중");
		item.setNumOfRegister(0);
		
		int rslt = itemDao.insertItem(item);
		System.out.println("insert결과: " + rslt);
	}
	
//	@Test
	@Ignore
	void updateItem_test() {
		System.out.println("insert 테스트 시작");
		Food f = new Food(1200,11, "블루베리치즈빙수", 685, "g", 680, 21, 0, 0, 104);
		System.out.println("푸드id: " + f.getFood_id());
		
		Item item = new Item();
		item.setItem_id(30);
		item.setUser_id(61);
		item.setFood(f);
		item.setItem_price(129000);
		item.setShip_price(2500);
		item.setTitle("쟁반짜장");
		item.setContent("집에서 즐기는 짜장면");
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		    Date endDate = (Date) dateFormat.parse("2021/06/12");
		    Date createDate = (Date) dateFormat.parse("2021/05/12");
		    Date modifyDate = new Date();
		    item.setEnd_dt(new java.sql.Timestamp(endDate.getTime()));
		    item.setCreated_dt(new java.sql.Timestamp(createDate.getTime()));
		    item.setModified_dt(new java.sql.Timestamp(modifyDate.getTime()));
		} catch(Exception e) { }
		item.setMin_quantity(30);
		item.setItem_status("진행중");
		item.setNumOfRegister(0);
		
		int rslt = itemDao.updateItem(item);
		System.out.println("update결과: " + rslt);
	}
	
//	@Test
	@Ignore
	void deleteItem_test() {
		System.out.println("delete 테스트 시작");
		int rslt = itemDao.deleteItem(23);
		System.out.println("delete결과: " + rslt);
	}
	
	@Test
//	@Ignore
	void progressItem_test() {
		System.out.println("progress 시작");
		int rslt = itemDao.progressItem(30);
		System.out.println("progress 결과: " + rslt);
	}
	
}
