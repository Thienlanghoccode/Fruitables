package com.haui.service.Impl;

import java.util.List;

import com.haui.dao.CategoryDao;
import com.haui.dao.impl.CategoryDaoImpl;
import com.haui.entity.Category;
import com.haui.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	
	private CategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void edit(Category category) {
		Category oldCate = categoryDao.get(category.getId());
		oldCate.setName(category.getName());
		categoryDao.edit(oldCate);
	}

	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}

	@Override
	public Category get(int id) {
		return categoryDao.get(id);
	}

	@Override
	public Category get(String name) {
		return categoryDao.get(name);
	}

	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}

	@Override
	public List<Category> search(String username) {
		return categoryDao.search(username);
	}

}
