package com.haui.dao;

import java.util.List;

import com.haui.entity.Category;

public interface CategoryDao {
	void insert(Category category);

	void edit(Category category);

	void delete(int id);

	Category get(int id);
	
	Category get(String name);

	List<Category> getAll();

	List<Category> search(String username);
}
