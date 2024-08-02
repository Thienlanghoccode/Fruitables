package com.haui.service.Impl;

import java.io.File;
import java.util.List;

import com.haui.dao.UserDao;
import com.haui.dao.impl.UserDaoImpl;
import com.haui.entity.User;
import com.haui.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl();

	@Override
	public void insert(User user) {
		userDao.insert(user);
		
	}

	@Override
	public void edit(User user) {
		User oldUser = userDao.get(user.getId());

		oldUser.setEmail(user.getEmail());
		oldUser.setUsername(user.getUsername());
		oldUser.setPassword(user.getPassword());
		oldUser.setAdmin(user.isAdmin());
		oldUser.setActive(user.isActive());
		if (user.getAvatar() != null) {
			// XOA ANH CU DI
			String fileName = oldUser.getAvatar();
			final String dir = "C:\\Users\\ASUS\\Documents\\LT_Java_Nangcao_IT6020\\BTL_QL_BanHang\\VegetableStoreManager\\Upload";
			File file = new File(dir + "/" + fileName);
			if (file.exists()) {
				file.delete();
			}
			// THEM ANH MOI
			oldUser.setAvatar(user.getAvatar());
		}

		userDao.edit(oldUser);
		
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public User get(String username) {
		return userDao.get(username);
	}

	@Override
	public User get(int id) {
		return userDao.get(id);
	}

	@Override
	public User login(String username, String password) {
		User user = userDao.get(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public User register(String email, String username, String password) {
		if (userDao.checkExistUsername(username)) {
			return null;
		}
		User user = new User(email, username, password);
		userDao.insert(user);
		return user;
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public List<User> search(String keyword) {
		return userDao.search(keyword);
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

}
