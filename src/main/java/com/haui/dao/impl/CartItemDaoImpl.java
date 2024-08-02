package com.haui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.haui.JDBCUtil.ConnectionPool;
import com.haui.JDBCUtil.ConnectionPoolImpl.ConnectionPoolImpl;
import com.haui.dao.CartItemDao;
import com.haui.dao.UserDao;
import com.haui.entity.Cart;
import com.haui.entity.CartItem;
import com.haui.entity.Product;
import com.haui.entity.User;
import com.haui.service.CartService;
import com.haui.service.ProductService;
import com.haui.service.Impl.CartServiceImpl;
import com.haui.service.Impl.ProductServiceImpl;

public class CartItemDaoImpl implements CartItemDao {
	
	private Connection con;
	private ConnectionPool pool;

	public CartItemDaoImpl() {
		// Xác định bộ quản lý kết nối
		this.pool = new ConnectionPoolImpl();

		// Xin kết nối để làm việc
		try {
			this.con = pool.getConnection("CartItemDaoImpl");

			// Hủy chế độ auto commit
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	CartService cartService = new CartServiceImpl();
	ProductService productService = new ProductServiceImpl();
	UserDao userDao = new UserDaoImpl();

	@Override
	public void insert(CartItem cartItem) {
		String sql = "INSERT INTO CartItem(id, cat_id, pro_id, quantity, unitPrice) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, cartItem.getId());
			ps.setString(2, cartItem.getCart().getId());
			ps.setInt(3, cartItem.getProduct().getId());
			ps.setInt(4, cartItem.getQuantity());
			ps.setLong(5, cartItem.getUnitPrice());

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
	public void edit(CartItem cartItem) {
		String sql = "UPDATE CartItem SET cat_id = ?, pro_id = ?, quantity = ?, unitPrice=? WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, cartItem.getCart().getId());
			ps.setInt(2, cartItem.getProduct().getId());
			ps.setInt(3, cartItem.getQuantity());
			ps.setLong(4, cartItem.getUnitPrice());
			ps.setString(5, cartItem.getId());
			
			
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
	public void delete(String id) {
		String sql = "DELETE FROM CartItem WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
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
	public CartItem get(String name) {
		return null;
	}

	@Override
	public CartItem get(int id) {
		String sql = "SELECT " + 
				"CartItem.id, " + 
				"CartItem.quantity, " + 
				"CartItem.unitPrice, " + 
				"cart.u_id, " + 
				"cart.buyDate, " + 
				"product.name, " + 
				"product.price " + 
				"FROM CartItem " + 
				"INNER JOIN Cart " + 
				"ON CartItem.cart_id = cart.id " + 
				"INNER JOIN Product " + 
				"ON CartItem.pro_id = Product.id " +
				"WHERE CartItem.id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userDao.get(rs.getInt("u_id"));
				
				Cart cart = new Cart();
				cart.setBuyer(user);
				cart.setBuyDate(rs.getDate("buyDate"));
				
				Product product = new Product();
				product.setName(rs.getString("name"));
				product.setPrice(rs.getLong("price"));
				
				CartItem cartItem = new CartItem();
				cartItem.setCart(cart);
				cartItem.setProduct(product);
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setUnitPrice(rs.getLong("unitPrice"));
				
				
				return cartItem;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CartItem> getAll() {
		List<CartItem> cartItemList = new ArrayList<CartItem>();
		String sql = "SELECT " + 
				"CartItem.id, " + 
				"CartItem.quantity, " + 
				"CartItem.unitPrice, " + 
				"cart.u_id, " + 
				"cart.buyDate, " + 
				"product.name, " + 
				"product.price " + 
				"FROM CartItem " + 
				"INNER JOIN Cart " + 
				"ON CartItem.cat_id = Cart.id " + 
				"INNER JOIN Product " + 
				"ON CartItem.pro_id = Product.id ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userDao.get(rs.getInt("u_id"));
				
				Cart cart = new Cart();
				cart.setBuyer(user);
				cart.setBuyDate(rs.getDate("buyDate"));
				
				Product product = new Product();
				product.setName(rs.getString("name"));
				product.setPrice(rs.getLong("price"));
				
				CartItem cartItem = new CartItem();
				cartItem.setId(rs.getString("id"));
				cartItem.setCart(cart);
				cartItem.setProduct(product);
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setUnitPrice(rs.getLong("unitPrice"));

				cartItemList.add(cartItem);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartItemList;
	}

	@Override
	public List<CartItem> search(String name) {
		return null;
	}
	
}
