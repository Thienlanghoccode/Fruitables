package com.haui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.haui.JDBCUtil.ConnectionPool;
import com.haui.JDBCUtil.ConnectionPoolImpl.ConnectionPoolImpl;
import com.haui.dao.CartDetailsDao;
import com.haui.dto.CartDetails;

public class CartDetailsDaoImpl implements CartDetailsDao {
	private Connection con;
	private ConnectionPool pool;

	public CartDetailsDaoImpl() {
		// Xác định bộ quản lý kết nối
		this.pool = new ConnectionPoolImpl();

		// Xin kết nối để làm việc
		try {
			this.con = pool.getConnection("CartDetailsImpl");

			// Hủy chế độ auto commit
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CartDetails> getAll() {
		List<CartDetails> cartDetailsList  = new ArrayList<>();
		String sql = "SELECT c.id AS id, u.username AS buyer, u.email AS email, " +
                "SUM(ci.quantity * ci.unitPrice) AS sumToTal, c.buyDate AS orderDate " +
                "FROM Cart c " +
                "JOIN [User] u ON c.u_id = u.id " +
                "JOIN CartItem ci ON c.id = ci.cat_id " +
                "JOIN Product p ON ci.pro_id = p.id " +
                "GROUP BY c.id, u.username, u.email, c.buyDate " +
                "ORDER BY c.buyDate DESC";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CartDetails cartDetails = new CartDetails();
                cartDetails.setId(rs.getString("id"));
                cartDetails.setBuyer(rs.getString("buyer"));
                cartDetails.setEmail(rs.getString("email"));
                cartDetails.setSumToTal(rs.getLong("sumToTal"));
                cartDetails.setOrderDate(rs.getDate("orderDate"));
                cartDetailsList.add(cartDetails);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return cartDetailsList ;
	}

}
