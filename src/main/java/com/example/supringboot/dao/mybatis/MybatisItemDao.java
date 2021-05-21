package com.example.supringboot.dao.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.supringboot.dao.ItemDao;
import com.example.supringboot.domain.Food;
import com.example.supringboot.domain.Image;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;
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
		if(item.getImages()!=null) {
			ArrayList<Image> images = item.getImages();
			for(Image img: images) {
				img.setItem_id(item.getItem_id());
				imageMapper.insertImage(img);
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
	public List<Item> getAllItemList() {
		return itemMapper.getAllItem();
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


	
}
