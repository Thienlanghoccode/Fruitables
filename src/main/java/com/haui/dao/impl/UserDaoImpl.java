package com.haui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.haui.JDBCUtil.ConnectionPool;
import com.haui.JDBCUtil.ConnectionPoolImpl.ConnectionPoolImpl;
import com.haui.dao.UserDao;
import com.haui.entity.User;

public class UserDaoImpl implements UserDao {

	private Connection con;
	private ConnectionPool pool;

	public UserDaoImpl() {
		// Xác định bộ quản lý kết nối
		this.pool = new ConnectionPoolImpl();

		// Xin kết nối để làm việc
		try {
			this.con = pool.getConnection("UserDaoImpl");

			// Hủy chế độ auto commit
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(User user) {
		boolean is_Admin = false, is_Active = true;
		String sql = "INSERT INTO [User](email, username, password, avatar, isAdmin, isActive) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getAvatar());	
			try {
				if(user.isAdmin()) {
					is_Admin = true;
				} else {
					is_Admin = false;
				}
			}
			catch (Exception e) {
				is_Admin = false;
			}
			ps.setBoolean(5, is_Admin);
			ps.setBoolean(6, is_Active);
			ps.executeUpdate();
			this.con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void edit(User user) {
		String sql = "UPDATE [User] SET email = ? , username = ?, password = ?, avatar = ?, isAdmin = ?, isActive = ? WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getAvatar());
			ps.setBoolean(5, user.isAdmin());
			ps.setBoolean(6, user.isActive());
			ps.setInt(7, user.getId());
			ps.executeUpdate();
			this.con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM [User] WHERE id = ?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			
			statement.executeUpdate();
			this.con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public User get(String username) {
	    String sql = "SELECT * FROM [User] WHERE username = ?";
	    
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, username);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                User user = new User();

	                user.setId(rs.getInt("id"));
	                user.setEmail(rs.getString("email"));
	                user.setUsername(rs.getString("username"));
	                user.setPassword(rs.getString("password"));
	                user.setAvatar(rs.getString("avatar"));
	                user.setAdmin(rs.getBoolean("isAdmin"));
	                user.setActive(rs.getBoolean("isActive"));
	                return user;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	            this.con.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    }
	    return null;
	}

	@Override
	public User get(int id) {
		String sql = "SELECT * FROM [User] WHERE id = ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setAdmin(rs.getBoolean("isAdmin"));
                user.setActive(rs.getBoolean("isActive"));
                return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM [User]";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setAdmin(rs.getBoolean("isAdmin"));
                user.setActive(rs.getBoolean("isActive"));

				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return userList;
	}

	@Override
	public List<User> search(String username) {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM [User] WHERE name LIKE ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + username + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setAdmin(rs.getBoolean("isAdmin"));
                user.setActive(rs.getBoolean("isActive"));

				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return userList;
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "select * from [user] where email = ?";
		
		try {
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, email);

			ResultSet resultSet = psmt.executeQuery();
			if (resultSet.next()) {
				duplicate = true;
			}
			psmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from [User] where username = ?";
		
		try {
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, username);

			ResultSet resultSet = psmt.executeQuery();
			if (resultSet.next()) {
				duplicate = true;
			}
			psmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return duplicate;
	}
}
