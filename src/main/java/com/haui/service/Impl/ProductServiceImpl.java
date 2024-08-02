package com.haui.service.Impl;

import java.io.File;
import java.util.List;

import com.haui.dao.ProductDao;
import com.haui.dao.impl.ProductDaoImpl;
import com.haui.entity.Product;
import com.haui.service.ProductService;

public class ProductServiceImpl implements ProductService {
	
	private ProductDao productDao = new ProductDaoImpl();

	@Override
	public void insert(Product product) {
		productDao.insert(product);
	}

	@Override
	public void edit(Product product) {
		Product oldProduct = productDao.get(product.getId());
		oldProduct.setName(product.getName());
		oldProduct.setPrice(product.getPrice());
		oldProduct.setCategory(product.getCategory());
		oldProduct.setDes(product.getDes());;
		if (product.getImage() != null) {
			// XOA ANH CU DI
			String fileName = oldProduct.getImage();
			final String dir = "C:\\Users\\ASUS\\Documents\\LT_Java_Nangcao_IT6020\\BTL_QL_BanHang\\VegetableStoreManager\\src\\main\\webapp\\templates\\user\\img";
			File file = new File(dir + "/" + fileName);
			if (file.exists()) {
				file.delete();
			}

			oldProduct.setImage(product.getImage());
		}

		productDao.edit(oldProduct);
	}

	@Override
	public void delete(int id) {
		productDao.delete(id);
	}

	@Override
	public Product get(int id) {
		return productDao.get(id);
	}

	@Override
	public List<Product> getAll() {
		return productDao.getAll();
	}

	@Override
	public List<Product> search(String username) {
		return productDao.search(username);
	}

	@Override
	public List<Product> seachByCategory(int cate_id) {
		return productDao.seachByCategory(cate_id);
	}

	@Override
	public List<Product> seachByName(String productName) {
		return productDao.seachByName(productName);
	}

	@Override
	public List<Product> getAllProducts(int pageNumber, int pageSize) {
		return productDao.getAllProducts(pageNumber, pageSize);
	}

	@Override
	public List<Product> seachByName(String productName, int pageNumber, int pageSize) {
		return productDao.seachByName(productName, pageNumber, pageSize);
	}
}
