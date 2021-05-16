package com.example.supringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.supringboot.dao.mybatis.MybatisWishDao;
import com.example.supringboot.domain.WishItem;

public class WishServiceImpl implements WishService {
	
	@Autowired
	private MybatisWishDao wishDao;
	
	@Override
	public List<WishItem> getLikedItem(int user_id) {
		return wishDao.getWishItem(user_id);
	}

	@Override
	public boolean likeItem(WishItem wish) {
		return wishDao.likedItem(wish);
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
	public boolean updateQuantity(WishItem wish) {
		return wishDao.updateQuantity(wish);
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
}
