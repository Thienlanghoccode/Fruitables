package com.haui.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;

import com.haui.entity.User;
import com.haui.service.UserService;
import com.haui.service.Impl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/updateaccount")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UpdateAccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "Upload";
	UserService userService = new UserServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    User user = new User();
	    DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
	    JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

	    try {
	        List<FileItem> items = upload.parseRequest(req);
	        for (FileItem item : items) {
	            if (item.isFormField()) {
	                String fieldName = item.getFieldName();
	                String fieldValue = item.getString();
	                switch (fieldName) {
	                    case "id":
	                        user.setId(Integer.parseInt(fieldValue));
	                        break;
	                    case "username":
	                        user.setUsername(fieldValue);
	                        break;
	                    case "email":
	                        user.setEmail(fieldValue);
	                        break;
	                    case "password":
	                        user.setPassword(fieldValue);
	                        break;
	                    case "isAdmin":
	                    	user.setAdmin("1".equals(fieldValue));
	                        break;
	                    case "isActive":
	                        user.setActive("1".equals(fieldValue));
	                        break;
	                }
	            } else {
	            	// This is a file upload field
	                String originalFileName = item.getName();
	                if (originalFileName != null && !originalFileName.isEmpty()) {
	                    String uploadDir = getServletContext().getRealPath("") + File.separator + SAVE_DIR;
	                    Path filePath = Paths.get(uploadDir, originalFileName);
	                    try (InputStream input = item.getInputStream()) {
	                        // Kiểm tra và sao chép tệp nếu chưa tồn tại
	                        if (!Files.exists(filePath)) {
	                            Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
	                        }
	                        // Cập nhật avatar với đường dẫn tệp mới
	                        user.setAvatar(SAVE_DIR + "/" + originalFileName);
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                        // Xử lý ngoại lệ
	                    }
	                } else {
	                    // Nếu không có tệp tải lên, giữ nguyên avatar hiện tại của người dùng
	                    user.setAvatar(userService.get(user.getId()).getAvatar());
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle exception
	    }
	    userService.edit(user);
	    resp.sendRedirect(req.getContextPath() + "/admin/account");
	}

}
