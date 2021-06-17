package com.example.supringboot.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.example.supringboot.dao.ItemDao;
import com.example.supringboot.domain.Category;
import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.PaginationInfo;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemDao itemDao;
	
	@Autowired		// applicationContext.xml에 정의된 scheduler 객체를 주입 받음
	private ThreadPoolTaskScheduler scheduler;
	
	@Override
	public int insertItem(Item item) {
		return itemDao.insertItem(item);
	}
	
	@Override
	@Cacheable(value="findFoodList", key="#keyword")
	public List<Food> searchFoodList(String keyword) {
		System.out.println("<<searchFoodList>>");
		return itemDao.searchFoodList(keyword);
	}
	
	@Override
	public Food getFood(int food_id) {
		return itemDao.getFood(food_id);
	}

	@Override
	public List<Item> getItemListByAdmin(int user_id) {
		return itemDao.getItemListByAdmin(user_id);
	}
	
	@Override
	public int updateItem(Item item) {
		return itemDao.updateItem(item);
	}

	@Override
	public int deleteItem(int item_id) {
		return itemDao.deleteItem(item_id);
	}

	@Override
	public int progressItem(int item_id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
	}

	@Override
	public void startScheduler(int item_id, Date end_dt) {
		// TODO Auto-generated method stub
		
		Runnable updateTableRunner = new Runnable() {	
			// anonymous class 정의
			@Override
			public void run() {   // 스케쥴러에 의해 미래의 특정 시점에 실행될 작업을 정의				
				Date curTime = new Date();
				// 실행 시점의 시각을 전달하여 그 시각 이전의 closing time 값을 갖는 event의 상태를 변경 
				Item item = itemDao.getDetailItem(item_id);
				Integer num = item.getNumOfRegister();
				System.out.println("numOfRegister : " + num);
				
				String item_status = null;
				if(item.getMin_quantity() > num) {
					item_status = "fail";
				} else {
					item_status = "success";
				}
				
				itemDao.updateItemStatus(item_id, item_status);
				
				System.out.println("updateTableRunner is executed at " + curTime);
			}
		};
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		System.out.println("end_dt : " + format.format(end_dt));
		
		// 스케줄 생성: closingTime에 updateTableRunner.run() 메소드 실행
		scheduler.schedule(updateTableRunner, end_dt);
		System.out.println("startScheduler 실행");
	}

	@Override
	public List<Item> getItemLatest3Rows() {
		// TODO Auto-generated method stub
		return itemDao.getItemLatest3Rows();
	}

	@Override
	public ArrayList<Item> selectItemWithCategory(Item item) {
		// TODO Auto-generated method stub
		System.out.println("[selectItemWithCategory] " + item.getItem_id());
		ArrayList<Item> itemList = null;
		int totalCount = itemDao.selectItemCatCount(item.getCat_id());
		
		PaginationInfo paginationInfo = new PaginationInfo(item);
		paginationInfo.setTotalRecordCount(totalCount);
		
		item.setPaginationInfo(paginationInfo);
		
		if(totalCount > 0) {
			itemList = itemDao.selectItemWithCategory(item);
		}
		
		return itemList;
	}

	@Override
	public ArrayList<Item> getItemList(Item item) {
		// TODO Auto-generated method stub
		System.out.println("[getItemList] " + item.getItem_id());
		ArrayList<Item> itemList = null;
		int totalCount = itemDao.selectItemTotalCount(item);
		
		PaginationInfo paginationInfo = new PaginationInfo(item);
		paginationInfo.setTotalRecordCount(totalCount);
		
		item.setPaginationInfo(paginationInfo);
		
		if(totalCount > 0) {
			itemList = itemDao.getAllItemList(item);
		}
		
		return itemList;
	}
	@Override
	public ArrayList<Category> selectAllCategory() {
		// TODO Auto-generated method stub
		return itemDao.selectAllCategory();
	}

	@Override
	public Category getCategoryById(int cat_id) {
		return itemDao.getCategoryById(cat_id);
	}

	@Override
	public ArrayList<Item> getGoingItem(Item item) {
		ArrayList<Item> itemList = null;
		int totalCount = itemDao.selectGoingItemCount(item);
		
		PaginationInfo paginationInfo = new PaginationInfo(item);
		paginationInfo.setTotalRecordCount(totalCount);
		
		item.setPaginationInfo(paginationInfo);
		
		if(totalCount > 0) {
			itemList = itemDao.getGoingItemList(item);
		}
		
		return itemList;
	}

	@Override
	public ArrayList<Item> getEndItem(Item item) {
		ArrayList<Item> itemList = null;
		int totalCount = itemDao.selectEndItemCount(item);
		
		PaginationInfo paginationInfo = new PaginationInfo(item);
		paginationInfo.setTotalRecordCount(totalCount);
		
		item.setPaginationInfo(paginationInfo);
		
		if(totalCount > 0) {
			itemList = itemDao.getEndItemList(item);
		}
		
		return itemList;
	}
	
	@Override
	public Item getDetailItem(int item_id) {
		return itemDao.getDetailItem(item_id);
	}

	//키워드 검색을 통한 공구식품 목록
	@Override
	public ArrayList<Item> getItemListByKeyword(String keyword) {
		return itemDao.getAllItemListByKeyword(keyword);
	}
	
	@Override
	public ArrayList<Item> getGoingItemByKeyword(Item item, String keyword) {
		return itemDao.getGoingItemListByKeyword(item, keyword);
	}

	@Override
	public ArrayList<Item> getEndItemByKeyword(String keyword) {
		return itemDao.getEndItemListByKeyword(keyword);
	}


	
}
