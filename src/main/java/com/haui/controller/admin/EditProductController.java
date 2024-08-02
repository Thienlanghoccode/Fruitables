package com.haui.controller.admin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;

import com.haui.entity.Category;
import com.haui.entity.Product;
import com.haui.service.CategoryService;
import com.haui.service.ProductService;
import com.haui.service.Impl.CategoryServiceImpl;
import com.haui.service.Impl.ProductServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/editproduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class EditProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	ProductService productService = new ProductServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("pId");
		Product product = productService.get(Integer.parseInt(id));
		List<Category> cateList = categoryService.getAll();
		req.setAttribute("categories", cateList);
		req.setAttribute("product", product);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/editproduct.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Product product = new Product();
		DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
		JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(req);
			for (FileItem item : items) {
	            if (item.getFieldName().equals("id")) {
	                product.setId(Integer.parseInt(item.getString()));
	            } else if (item.getFieldName().equals("name")) {
	                product.setName(item.getString(StandardCharsets.UTF_8));
	            } else if (item.getFieldName().equals("category")) {
	                Category category = categoryService.get(Integer.parseInt(item.getString()));
	                product.setCategory(category);
	            } else if (item.getFieldName().equals("description")) {
	                product.setDes(item.getString(StandardCharsets.UTF_8));
	            } else if (item.getFieldName().equals("price")) {
	                product.setPrice(Long.parseLong(item.getString()));
	            } else if (item.getFieldName().equals("image")) {
	                if (item.getSize() > 0) { // Nếu có tệp được tải lên
	                    final String uploadDir = getServletContext().getRealPath("/templates/user/img");
	                    String originalFileName = item.getName();
//	                    int index = originalFileName.lastIndexOf(".");
//	                    String ext = originalFileName.substring(index + 1); // Lấy phần mở rộng của file
//	                    String fileName = System.currentTimeMillis() + "." + ext; // Đổi tên file để tránh trùng lặp
	                    Path filePath = Paths.get(uploadDir, originalFileName);

	                    // Ghi tệp vào đường dẫn filePath
	                    try (InputStream input = item.getInputStream()) {
	                        Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
	                        
	                    }
	                    final String dir = "C:\\Users\\ASUS\\Documents\\LT_Java_Nangcao_IT6020\\BTL_QL_BanHang\\VegetableStoreManager\\src\\main\\webapp\\templates\\user\\img";
                        filePath = Paths.get(dir, originalFileName);
	                    try (InputStream input = item.getInputStream()) {
	                        Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
	                    }

	                    product.setImage(originalFileName); // Lưu đường dẫn tương đối vào đối tượng Product
	                } else {
	                    product.setImage(null); // Nếu không có file được tải lên, đặt image trong Product là null
	                }
	            }
	        }
			
	        productService.edit(product);
	        List<Product> products = productService.getAll();
	        req.setAttribute("products", products);
			resp.sendRedirect(req.getContextPath() + "/admin/productlist");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
