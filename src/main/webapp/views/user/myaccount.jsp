<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Fruitables - My Account</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
<!-- Customized Bootstrap Stylesheet -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<c:url value='templates/user/account_css/index.css' />"
	rel="stylesheet">
</head>
<body>
	<div class="container light-style flex-grow-1 container-p-y">
		<h4 class="font-weight-bold py-3 mb-4">Cài Đặt Tài Khoản</h4>
		<form action="updateAccountServlet" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="id"
				value="${sessionScope.currentUser.id }" />

			<div class="card overflow-hidden">
				<div class="row no-gutters row-bordered row-border-light">
					<div class="col-md-3 pt-0">
						<div class="list-group list-group-flush account-settings-links">
							<a class="list-group-item list-group-item-action active"
								data-toggle="list" href="#account-general">THÔNG TIN TÀI
								KHOẢN</a>
						</div>
					</div>
					<div class="col-md-9">
						<div class="tab-content">
							<div class="tab-pane fade active show" id="account-general">
								<div class="card-body media align-items-center">
									<img
										src="<c:url value='${sessionScope.currentUser.avatar != null ? sessionScope.currentUser.avatar : "https://bootdey.com/img/Content/avatar/avatar1.png"}' />"
										alt class="d-block ui-w-80">
									<div class="media-body ml-4">
										<label class="btn btn-outline-primary"> Tải ảnh lên <input
											type="file" name="avatar" class="account-settings-fileinput">
										</label> &nbsp;
										<div class="text-light small mt-1">Ảnh kích thước không
											quá 3MB</div>
									</div>
								</div>
								<hr class="border-light m-0">
								<div class="card-body">
									<div class="form-group">
										<label class="form-label">Tài khoản</label> <input type="text"
											class="form-control mb-1" name="username"
											value="${sessionScope.currentUser.username }">
									</div>
									<div class="form-group">
										<label class="form-label">Email</label> <input type="text"
											class="form-control" name="email"
											value="${sessionScope.currentUser.email }">
									</div>
									<div class="form-group">
										<label class="form-label">Mật khẩu</label> <input
											type="password" class="form-control" name="password"
											value="${sessionScope.currentUser.password }">
									</div>
									<div class="form-group">
										<label class="form-label">Company</label> <input type="text"
											readonly class="form-control"
											value="http://VegatebleStoreManager.com">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="text-right mt-3">
				<button type="submit" class="btn btn-primary">Lưu thông tin</button>
				&nbsp;
				<button type="button" class="btn btn-default"
					onclick="window.location.href='http://localhost:8080/VegetableStoreManager/redirect.jsp'">Hủy
					bỏ</button>
			</div>
		</form>

	</div>
	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>