package com.example.supringboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supringboot.dao.mybatis.MybatisItemDao;
import com.example.supringboot.dao.mybatis.MybatisApplyDao;
import com.example.supringboot.dao.mybatis.MybatisWishDao;
import com.example.supringboot.domain.Item;
import com.example.supringboot.domain.Order_reg;
import com.example.supringboot.domain.WishItem;

@Service
@Transactional
public class WishServiceImpl implements WishService {
	
	@Autowired
	private MybatisWishDao wishDao;
	
	@Autowired
	private MybatisItemDao itemDao;
	
	@Autowired
	private MybatisApplyDao orderDao;
	
	@Override
	public List<WishItem> getLikedItem(int user_id) {
		return wishDao.getWishItem(user_id);
	}

	@Override
	public boolean likeItem(int user_id, int item_id, int amount) {
		return wishDao.likedItem(user_id, item_id, amount);
	}

	@Override
	public boolean cancelLikedItem(int liked_id) {
		return wishDao.cancelLikedItem(liked_id);
	}
	
	@Override
	public boolean cancelDetailLikedItem(int user_id, int item_id) {
		return wishDao.cancelDetailLikedItem(user_id, item_id);
	}

	@Override
	public boolean deleteAllLikedItem(int user_id) {
		return wishDao.deleteAllLikedItem(user_id);
	}

	@Override
	public boolean updateLikedItem(WishItem wish) {
		return wishDao.updateLikedItem(wish);
	}

	@Override
	public boolean updateQuantity(int user_id, int item_id, int amount) {
		return wishDao.updateQuantity(user_id, item_id, amount);
	}

	@Override
	public int totalMoney(int user_id) {
		return wishDao.totalMoney(user_id);
	}

	@Override
	public boolean isWishItem(int user_id, int item_id) {
		return wishDao.isWishItem(user_id, item_id);
	}

	@Override
	public WishItem getOneWishItem(int liked_id) {
		return wishDao.getOneWishItem(liked_id);
	}

	
	// 공구식품 관련 코드 -> 임시로 여기에 작성
	@Override
	public List<Item> getAllItem() {
		return itemDao.getAllItemList();
	}

	@Override
	public List<Item> getGoingItem() {
		return itemDao.getGoingItemList();
	}

	@Override
	public List<Item> getEndItem() {
		return itemDao.getEndItemList();
	}
	
	@Override
	public Item getDetailItem(int item_id) {
		return itemDao.getDetailItem(item_id);
	}

	// 공구 신청 관련 코드 -> 임시로 여기에 작성
	@Override
	public boolean applyItem(Order_reg order) {
		return orderDao.applyItem(order);
	}

	@Override
	public boolean cancelItem(int order_reg_id, int user_id) {
		return orderDao.cancelItem(order_reg_id, user_id);
	}

	@Override
	public Order_reg getOrderById(int order_reg_id, int user_id) {
		return orderDao.getOnOrderById(order_reg_id, user_id);
	}
}
