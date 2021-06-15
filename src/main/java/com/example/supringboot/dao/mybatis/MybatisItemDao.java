package com.example.supringboot.dao.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.supringboot.dao.ItemDao;
import com.example.supringboot.domain.Category;
import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Image;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.mybatis.mapper.AdminMapper;
import com.example.supringboot.mybatis.mapper.ImageMapper;
import com.example.supringboot.mybatis.mapper.ItemMapper;

@Repository
public class MybatisItemDao implements ItemDao {

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ImageMapper imageMapper;

	@Override
	public int insertItem(Item item) {
		int rslt = itemMapper.insertItem(item);
		
		if(rslt != 0 && item.getImages() != null) {
			ArrayList<Image> images = item.getImages();
			for(Image img: images) {
				img.setItem_id(item.getItem_id());
				imageMapper.insertImageWithItem(img);
				System.out.println("db에 이미지 등록 완료 ");
			}
		}
		return rslt;
	}

	@Override
	public List<Food> searchFoodList(String keyword) {
		return itemMapper.searchFoodList(keyword);
	}

	@Override
	public int updateItem(Item item) {
		return itemMapper.updateItem(item);
	}

	@Override
	public int deleteItem(int item_id) {
		return itemMapper.deleteItem(item_id);
	}

	@Override
	public int progressItem(int item_id) {
		return itemMapper.progressItem(item_id);
	}

	@Override
	public ArrayList<Item> getAllItemList(Item item) {
		return itemMapper.getAllItem(item);
	}

	@Override
	public Item getDetailItem(int item_id) {
		return itemMapper.getOneItemById(item_id);
	}

	@Override
	public List<Item> getGoingItemList() {
		return itemMapper.getGoingItemList();
	}

	@Override
	public List<Item> getEndItemList() {
		return itemMapper.getEndItemList();
	}

	@Override
	public Food getFood(int food_id) {
		return itemMapper.getFood(food_id);
	}

	@Override
	public List<Item> getItemListByAdmin(int user_id) {
		return itemMapper.getItemListByAdmin(user_id);
	}

	@Override
	public boolean updateItemStatus(int item_id, String item_status) {
		// TODO Auto-generated method stub
		int result = itemMapper.updateItemStatus(item_id, item_status);
		
		System.out.println(result);
		
		if(result == 1) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<Item> getItemLatest3Rows() {
		// TODO Auto-generated method stub
		return itemMapper.getItemLatest3Rows();
	}

	@Override
	public ArrayList<Item> searchItemFromMain(String keyword) {
		// TODO Auto-generated method stub
		return itemMapper.searchItemFromMain(keyword);
	}

	@Override
	public ArrayList<Item> selectItemWithCategory(Item item) {
		// TODO Auto-generated method stub
		return itemMapper.selectItemWithCategory(item);
	}

	@Override
	public ArrayList<Category> selectAllCategory() {
		// TODO Auto-generated method stub
		return itemMapper.selectAllCategory();
	}

	@Override
	public Category getCategoryById(int cat_id) {
		return itemMapper.getCategoryById(cat_id);
	}

	@Override
	public int selectItemTotalCount(Item item) {
		// TODO Auto-generated method stub
		return itemMapper.selectItemTotalCount(item);
	}

	@Override
	public int selectItemCatCount(int cat_id) {
		// TODO Auto-generated method stub
		return itemMapper.selectItemCatCount(cat_id);
	}
	
}
