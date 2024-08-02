package com.haui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.haui.JDBCUtil.ConnectionPool;
import com.haui.JDBCUtil.ConnectionPoolImpl.ConnectionPoolImpl;
import com.haui.dao.ProductDao;
import com.haui.entity.Category;
import com.haui.entity.Product;
import com.haui.service.CategoryService;
import com.haui.service.Impl.CategoryServiceImpl;

public class ProductDaoImpl implements ProductDao {

	private Connection con;
	private ConnectionPool pool;
	CategoryService categoryService = new CategoryServiceImpl();

	public ProductDaoImpl() {
		// Xác định bộ quản lý kết nối
		this.pool = new ConnectionPoolImpl();

		// Xin kết nối để làm việc
		try {
			this.con = pool.getConnection("ProductDaoImpl");

			// Hủy chế độ auto commit
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Product product) {
		String sql = "INSERT INTO Product(name, price, image, cate_id, des) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, product.getName());
			ps.setLong(2, product.getPrice());
			ps.setString(3, product.getImage());
			ps.setInt(4, product.getCategory().getId());
			ps.setString(5, product.getDes());
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
	public void edit(Product product) {
		String sql = "UPDATE Product SET Product.name = ? , price = ?, image = ?,cate_id=?, [des]=?  WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, product.getName());
			ps.setDouble(2, product.getPrice());
			ps.setString(3, product.getImage());
			ps.setInt(4, product.getCategory().getId());
			ps.setString(5, product.getDes());
			ps.setInt(6, product.getId());
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
		String sql = "DELETE FROM Product WHERE id=?";
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
				e1.printStackTrace();
			}
		}
	}

	@Override
	public Product get(int id) {
		String sql = "SELECT product.id, product.name AS p_name, product.price, product.image,product.des, category.cate_name AS c_name, "
				+ "category.cate_id AS c_id " + "FROM product INNER JOIN category "
				+ "ON product.cate_id = category.cate_id WHERE product.id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category category = categoryService.get(rs.getInt("c_id"));

				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("p_name"));
				product.setPrice(rs.getLong("price"));
				product.setImage(rs.getString("image"));
				product.setDes(rs.getString("des"));
				product.setCategory(category);

				return product;
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
	public List<Product> getAll() {
		List<Product> productList = new ArrayList<Product>();
		String sql = "SELECT product.id, product.name AS p_name, product.price, product.image, product.des ,"
				+ " category.cate_name AS c_name, category.cate_id AS c_id  " + "FROM product INNER JOIN category "
				+ "ON product.cate_id = category.cate_id";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Category category = categoryService.get(rs.getInt("c_id"));
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("p_name"));
				product.setPrice(rs.getLong("price"));
				product.setImage(rs.getString("image"));
				product.setDes(rs.getString("des"));
				product.setCategory(category);

				product.setCategory(category);
				productList.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productList;
	}

	@Override
	public List<Product> search(String keyword) {
		List<Product> productList = new ArrayList<Product>();
		String sql = "SELECT * FROM user WHERE name LIKE ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product product = new Product();

				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getLong("price"));
				product.setImage(rs.getString("image"));
				product.setDes(rs.getString("des"));

				Category category = new Category();
				category.setId(rs.getInt("c_id"));
				category.setName(rs.getString("c_name"));

				product.setCategory(category);

				productList.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productList;
	}

	@Override
	public List<Product> seachByCategory(int cate_id) {
		List<Product> productList = new ArrayList<Product>();
		String sql = "SELECT product.id, product.name AS p_name, " + "product.price, product.image, product.des , "
				+ "category.cate_name AS c_name, category.cate_id AS c_id "
				+ " FROM Product , Category where product.cate_id = category.cate_id " + "and Category.cate_id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cate_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Category category = categoryService.get(rs.getInt("c_id"));
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("p_name"));
				product.setPrice(rs.getLong("price"));
				product.setImage(rs.getString("image"));
				product.setDes(rs.getString("des"));
				product.setCategory(category);

				product.setCategory(category);
				productList.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productList;
	}

	@Override
	public List<Product> seachByName(String productName) {
		List<Product> productList = new ArrayList<Product>();
		String sql = "SELECT product.id, product.name AS p_name, " + "product.price, product.image, product.des , "
				+ "category.cate_name AS c_name, category.cate_id AS c_id " + " FROM Product , Category   "
				+ "where product.cate_id = category.cate_id and Product.name like ? ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + productName + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Category category = categoryService.get(rs.getInt("c_id"));
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("p_name"));
				product.setPrice(rs.getLong("price"));
				product.setImage(rs.getString("image"));
				product.setDes(rs.getString("des"));
				product.setCategory(category);

				product.setCategory(category);
				productList.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productList;
	}

	@Override
	public List<Product> getAllProducts(int pageNumber, int pageSize) {
		List<Product> products = new ArrayList<Product>();
		String sql = "SELECT product.id, product.name AS p_name, product.price, product.image, product.des, " +
                "category.cate_name AS c_name, category.cate_id AS c_id " +
                "FROM product " +
                "INNER JOIN category ON product.cate_id = category.cate_id " +
                "ORDER BY product.id " +
                "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			int offset = (pageNumber - 1) * pageSize;
			ps.setInt(1, offset);
			ps.setInt(2,pageSize);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Category category = categoryService.get(rs.getInt("c_id"));
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("p_name"));
				product.setPrice(rs.getLong("price"));
				product.setImage(rs.getString("image"));
				product.setDes(rs.getString("des"));
				product.setCategory(category);

				product.setCategory(category);
				products.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return products;
	}

	@Override
	public List<Product> seachByName(String productName, int pageNumber, int pageSize) {
		List<Product> products = new ArrayList<>();
        String sql = "SELECT product.id, product.name AS p_name, product.price, product.image, product.des, " +
                     "category.cate_name AS c_name, category.cate_id AS c_id " +
                     "FROM product " +
                     "INNER JOIN category ON product.cate_id = category.cate_id " +
                     "WHERE product.name LIKE ? " +
                     "ORDER BY product.id " +
                     "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            int offset = (pageNumber - 1) * pageSize;
            ps.setString(1, "%" + productName + "%");
            ps.setInt(2, offset);
            ps.setInt(3, pageSize);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category(rs.getInt("c_id"), rs.getString("c_name"));
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("p_name"));
                product.setPrice(rs.getLong("price"));
                product.setImage(rs.getString("image"));
                product.setDes(rs.getString("des"));
                product.setCategory(category);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return products;
	}

}
