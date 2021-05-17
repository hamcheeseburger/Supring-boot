package com.example.supringboot.dao.mybatis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.supringboot.dao.WishDao;
import com.example.supringboot.domain.WishItem;
import com.example.supringboot.mybatis.mapper.WishMapper;

@Repository
public class MybatisWishDao implements WishDao {

	@Autowired
	private WishMapper wishMapper;

	@Override
	public boolean likedItem(int user_id, int item_id, int amount) {
		int result = wishMapper.likedItem(user_id, item_id, amount);

		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean cancelLikedItem(int liked_id) {
		int result = wishMapper.cancelLikedItem(liked_id);

		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean cancelDetailLikedItem(int user_id, int item_id) {
		int result = wishMapper.cancelDetailLikedItem(user_id, item_id);

		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	// 찜한 식품 확인
	@Override
	public List<WishItem> getWishItem(int user_id) {
		List<WishItem> wishList = new ArrayList<WishItem>();
		wishList = wishMapper.getWishItem(user_id);

		if (wishList.isEmpty()) {
			return Collections.emptyList();
		} else {
			return wishList;
		}
	}

	@Override
	public boolean deleteAllLikedItem(int user_id) {
		int result = wishMapper.deleteAllLikedItem(user_id);
		
		if (result == 1) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public boolean updateLikedItem(WishItem wish) {
		int result = wishMapper.updateLikedItem(wish);
		
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateQuantity(int user_id, int item_id, int amount) {
		int result = wishMapper.updateQuantity(user_id, item_id, amount);
		
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int totalMoney(int user_id) {
		return wishMapper.totalMoney(user_id);
	}

	@Override
	public boolean isWishItem(int user_id, int item_id) {
		int result = wishMapper.isWishItem(user_id, item_id);
		
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public WishItem getOneWishItem(int liked_id) {
		return wishMapper.getOneWishItem(liked_id);
	}
}
