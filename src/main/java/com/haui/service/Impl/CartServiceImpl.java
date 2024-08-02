package com.haui.service.Impl;

import java.util.List;

import com.haui.dao.CartDao;
import com.haui.dao.impl.CartDaoImpl;
import com.haui.entity.Cart;
import com.haui.service.CartService;

public class CartServiceImpl implements CartService {
	
	private CartDao cartDao = new CartDaoImpl();
	
	@Override
	public void insert(Cart cart) {
		cartDao.insert(cart);
	}

	@Override
	public void edit(Cart newCart) {
		Cart oldCart = cartDao.get(newCart.getId());

		oldCart.setBuyDate(newCart.getBuyDate());
		oldCart.setBuyer(newCart.getBuyer());
		
		
		cartDao.edit(oldCart);
	}

	@Override
	public void delete(int id) {
		cartDao.delete(id);
	}

	@Override
	public Cart get(int id) {
		return cartDao.get(id);
	}

	@Override
	public List<Cart> getAll() {
		return cartDao.getAll();
	}

	@Override
	public List<Cart> search(String keyword) {
		return cartDao.search(keyword);
	}

}
