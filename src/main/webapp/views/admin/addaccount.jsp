<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/admin/headlink.jsp"%>
<meta charset="UTF-8">
<title>ACCOUNT</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
</head>
<body>
	<%@ include file="/common/admin/header.jsp"%>
	<div class="container mt-5">
		<form class="row tm-content-row" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/addaccount" method="post">
			<div class="tm-block-col tm-col-avatar">
				<div class="tm-bg-primary-dark tm-block tm-block-avatar">
					<h2 class="tm-block-title">ĐỔI ẢNH ĐẠI DIỆN</h2>
					<div class="tm-avatar-container">
						<img id="avatar-img"
							src="${pageContext.request.contextPath}/templates/admin/img/avatar.png"
							alt="Avatar" class="tm-avatar img-fluid mb-4" />
					</div>
					<div class="custom-file mt-3 mb-3">
						<input id="fileInput" type="file" name="image"
							style="display: none;" /> <input type="button"
							class="btn btn-primary btn-block mx-auto"
							value="TẢI HÌNH ẢNH LÊN"
							onclick="document.getElementById('fileInput').click();" />
					</div>
				</div>
			</div>
			<div class="tm-block-col tm-col-account-settings">
				<div class="tm-bg-primary-dark tm-block tm-block-settings">
					<h2 class="tm-block-title">QUẢN LÝ TÀI KHOẢN</h2>
					<div class="tm-signup-form row">
						<div class="form-group col-lg-6">
							<label for="name">Tên tài khoản</label> <input id="name"
								name="username" type="text" class="form-control validate" />
						</div>
						<div class="form-group col-lg-6">
							<label for="email">Email</label> <input id="email" name="email"
								type="text" class="form-control validate" />
						</div>
						<div class="form-group col-lg-6">
							<label for="password">Mật khẩu</label> <input id="password"
								name="password" type="password" class="form-control validate" />
						</div>
						<div class="form-group col-lg-6">
							<label for="role-select">Quyền truy cập</label> <select
								id="role-select" class="custom-select" name="isAdmin">
								<option value="0">User</option>
								<option value="1">Admin</option>
							</select>
						</div>
						<div class="form-group col-lg-12">
							<label for="status-select">Trạng thái</label> <select
								id="status-select" class="custom-select" name="isActive">
								<option value="0">Ngừng Hoạt Động</option>
								<option value="1">Còn Hoạt động</option>
							</select>
						</div>
						<div class="form-group col-lg-12">
							<label class="tm-hide-sm">&nbsp;</label>
							<button type="submit"
								class="btn btn-primary btn-block text-uppercase">THÊM TÀI KHOẢN</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	
		<%@ include file="/common/admin/footer.jsp"%>
		<%@ include file="/common/admin/lastBodyScript.jsp"%>
</body>
</html>
