package com.haui.dao;

import java.util.List;

import com.haui.entity.Product;

public interface ProductDao {
	void insert(Product product);

	void edit(Product product);

	void delete(int id);

	Product get(int id);

	List<Product> getAll();
	
	List<Product> getAllProducts(int pageNumber, int pageSize);

	List<Product> search(String username);
	
	List<Product> seachByCategory(int cate_id);
	
	List<Product> seachByName(String productName);
	
	List<Product> seachByName(String productName, int pageNumber, int pageSize);
}
