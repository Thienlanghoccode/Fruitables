package com.haui.service.Impl;

import java.util.List;

import com.haui.dao.CartItemDao;
import com.haui.dao.impl.CartItemDaoImpl;
import com.haui.entity.CartItem;
import com.haui.service.CartItemService;

public class CartItemServiceImpl implements CartItemService{

	private CartItemDao cartItemDao = new CartItemDaoImpl();
	
	@Override
	public void insert(CartItem cartItem) {
		cartItemDao.insert(cartItem);
	}

	@Override
	public void edit(CartItem newCartItem) {
		CartItem oldCartItem = cartItemDao.get(newCartItem.getId());
		oldCartItem.setCart(newCartItem.getCart());
		oldCartItem.setProduct(newCartItem.getProduct());
		oldCartItem.setQuantity(newCartItem.getQuantity());
		oldCartItem.setUnitPrice(newCartItem.getUnitPrice());
		
		cartItemDao.edit(oldCartItem);
	}

	@Override
	public void delete(String id) {
		cartItemDao.delete(id);
	}

	@Override
	public CartItem get(int id) {
		return cartItemDao.get(id);
	}

	@Override
	public List<CartItem> getAll() {
		return cartItemDao.getAll();
	}

	@Override
	public List<CartItem> search(String keyword) {
		return cartItemDao.search(keyword);
	}

}
