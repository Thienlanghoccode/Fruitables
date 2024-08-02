package com.haui.service.Impl;

import java.util.List;

import com.haui.dao.CartDetailsDao;
import com.haui.dao.impl.CartDetailsDaoImpl;
import com.haui.dto.CartDetails;
import com.haui.service.CartDetailsService;

public class CartDetailsServiceImpl implements CartDetailsService{

	CartDetailsDao dao = new CartDetailsDaoImpl();
	@Override
	public List<CartDetails> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

}
