package com.haui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.haui.JDBCUtil.ConnectionPool;
import com.haui.JDBCUtil.ConnectionPoolImpl.ConnectionPoolImpl;
import com.haui.dao.CategoryDao;
import com.haui.entity.Category;

public class CategoryDaoImpl implements CategoryDao {
	
	private Connection con;
	private ConnectionPool pool;

	public CategoryDaoImpl() {
		// Xác định bộ quản lý kết nối
		this.pool = new ConnectionPoolImpl();

		// Xin kết nối để làm việc
		try {
			this.con = pool.getConnection("CategoryDaoImpl");

			// Hủy chế độ auto commit
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Category category) {
		String sql = "INSERT INTO category(cate_name) VALUES (?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, category.getName());
			ps.executeUpdate();
			this.con.commit();
		} catch (SQLException e) {
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
	public void edit(Category category) {
		String sql = "UPDATE category SET cate_name = ? WHERE cate_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, category.getName());
			ps.setInt(2, category.getId());
			ps.executeUpdate();
			this.con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM category WHERE cate_id = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			this.con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public Category get(int id) {
		String sql = "SELECT * FROM category WHERE cate_id = ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Category category = new Category();

				category.setId(rs.getInt("cate_id"));
				category.setName(rs.getString("cate_name"));

				return category;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Category get(String name) {
		String sql = "SELECT * FROM Category WHERE cate_name = ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Category category = new Category();

				category.setId(rs.getInt("cate_id"));
				category.setName(rs.getString("cate_name"));

				return category;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> getAll() {
		List<Category> categories = new ArrayList<Category>();
		String sql = "SELECT * FROM Category";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Category category = new Category();

				category.setId(rs.getInt("cate_id"));
				category.setName(rs.getString("cate_name"));

				categories.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}

	@Override
	public List<Category> search(String username) {
		List<Category> categories = new ArrayList<Category>();
		String sql = "SELECT * FROM category WHERE name LIKE ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + username + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Category category = new Category();

				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));

				categories.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}
}
