package com.haui.service;

import java.util.List;

import com.haui.entity.User;

public interface UserService {

	void insert(User user);

	void edit(User user);

	void delete(int id);

	User get(String username);

	User get(int id);

	User login(String username, String password);

	User register(String email, String username, String password);

	List<User> getAll();

	List<User> search(String keyword);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

}
