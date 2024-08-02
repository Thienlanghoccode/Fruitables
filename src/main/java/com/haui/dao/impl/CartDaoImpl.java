package com.haui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.haui.JDBCUtil.ConnectionPool;
import com.haui.JDBCUtil.ConnectionPoolImpl.ConnectionPoolImpl;
import com.haui.dao.CartDao;
import com.haui.entity.Cart;
import com.haui.entity.User;
import com.haui.service.UserService;
import com.haui.service.Impl.UserServiceImpl;

public class CartDaoImpl implements CartDao {
	
	private Connection con;
	private ConnectionPool pool;
	private UserService userService = new UserServiceImpl();

	public CartDaoImpl() {
		// Xác định bộ quản lý kết nối
		this.pool = new ConnectionPoolImpl();

		// Xin kết nối để làm việc
		try {
			this.con = pool.getConnection("CartDaoImpl");

			// Hủy chế độ auto commit
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Cart cart) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Cart(id,u_id, buyDate) VALUES (?,?,?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cart.getId());
			ps.setInt(2, cart.getBuyer().getId());
			java.util.Date utilDate = cart.getBuyDate(); 
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			ps.setDate(3, sqlDate);
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
	public void edit(Cart cart) {
		String sql = "UPDATE cart SET id_user = ?, buyDate = ? WHERE id = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getBuyer().getId());
			ps.setDate(2, (java.sql.Date) new Date(cart.getBuyDate().getTime()));
			ps.setString(3, cart.getId());
			ps.executeUpdate();
			this.con.commit();
		} catch (SQLException e) {
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
		String sql = "DELETE FROM cart WHERE id = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
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
	public Cart get(String name) {
		return null;
	}

	@Override
	public Cart get(int id) {
		String sql = "SELECT cart.id, cart.buyDate, User.email, user.username, user.id AS user_id "
				+ "FROM cart INNER JOIN user " + "ON cart.id_user = user.id WHERE cart.id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userService.get(rs.getInt("user_id"));
				Cart cart = new Cart();
				cart.setId(rs.getString("id"));
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setBuyer(user);

				return cart;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Cart> getAll() {
		List<Cart> cartList = new ArrayList<Cart>();
		String sql = "SELECT cart.id, cart.buyDate, User.email, user.username, user.id AS user_id "
				+ "FROM cart INNER JOIN user " + "ON cart.id_user = user.id";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userService.get(rs.getInt("user_id"));
				Cart cart = new Cart();
				cart.setId(rs.getString("id"));
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setBuyer(user);

				cartList.add(cart);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartList;
	}

	@Override
	public List<Cart> search(String name) {
		List<Cart> cartList = new ArrayList<Cart>();
		String sql = "SELECT cart.id, cart.buyDate, User.email, user.username, user.id AS user_id "
				+ "FROM cart INNER JOIN user " + "ON cart.id_user = user.id LIKE User.email = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userService.get(rs.getInt("user_id"));

				Cart cart = new Cart();
				cart.setId(rs.getString("id"));
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setBuyer(user);

				cartList.add(cart);

			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return cartList;
	}

}
