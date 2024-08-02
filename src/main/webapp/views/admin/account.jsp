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
		<div class="row tm-content-row">
			<div class="col-12 tm-block-col">
				<div class="tm-bg-primary-dark tm-block tm-block-h-auto">
					<div class="d-flex justify-content-between align-items-center">
				        <h2 class="tm-block-title">DANH SÁCH TÀI KHOẢN</h2>
				        <button class="btn btn-primary text-uppercase" onclick="window.location.href='/VegetableStoreManager/admin/addaccount'"
				        >THÊM TÀI KHOẢN</button>
    				</div>
					<p class="text-white">Tài khoản</p>
					<select id="account-select" class="custom-select">
						<option value="0">Chọn tài khoản</option>
						<c:forEach items="${userlist}" var="user">
							<option value="${user.id}">${user.username}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<form class="row tm-content-row" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/updateaccount" method="post">
		<input type="hidden" name="id" id="id" value="${user.id }" />
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
								class="btn btn-primary btn-block text-uppercase">CẬP
								NHẬT THÔNG TIN</button>
						</div>
						<div class="col-12">
							<button type="button" onclick="deleteAccount()"
								class="btn btn-primary btn-block text-uppercase">XÓA
								TÀI KHOẢN</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>


		<%@ include file="/common/admin/footer.jsp"%>
		<%@ include file="/common/admin/lastBodyScript.jsp"%>

		<script>
		$(document).ready(function() {
		    $('#account-select').change(function() {
		        var accountId = $(this).val();
		        if (accountId != '0') {
		            $.ajax({
		                url: '/VegetableStoreManager/admin/account',
		                type: 'POST',
		                data: {
		                    Id: accountId
		                },
		                success: function(response) {
		                    // Xử lý và cập nhật thông tin tài khoản ở đây mà không cần tải lại trang
		                    $('#id').val(response.id);
		                    $('#name').val(response.username);
		                    $('#email').val(response.email);
		                    $('#password').val(response.password);
		
		                    $('#role-select').find('option').removeAttr('selected');
		                    if (response.admin) {
		                        $('#role-select').find('option[value="1"]').attr('selected', 'selected');
		                    } else {
		                        $('#role-select').find('option[value="0"]').attr('selected', 'selected');
		                    }
		
		                    $('#status-select').find('option').removeAttr('selected');
		                    if (response.active) {
		                        $('#status-select').find('option[value="1"]').attr('selected', 'selected');
		                    } else {
		                        $('#status-select').find('option[value="0"]').attr('selected', 'selected');
		                    }
		                    if(response.avatar != null){
		                    	$('#avatar-img').attr('src','/VegetableStoreManager/' + response.avatar);
		                    }
		                    else {
		                    	$('#avatar-img').attr('src','/VegetableStoreManager/' + '/templates/admin/img/avatar.png');
		                    }

		                },
		                error: function() {
		                    alert("Opps, please try again!");
		                }
		            });
		        } else {
		            alert("Vui lòng chọn tài khoản hợp lệ!");
		        }
		    });
		});
		
		function deleteAccount() {
			var userID = document.getElementById('id').value;
		    if(userID != null && userID != '0'){
		    	$.ajax({
			        url: '/VegetableStoreManager/admin/deleteaccount?Id=' + userID,
			        type: 'POST',
			        success: function() {
			            window.location.reload();
			        },
			        error: function() { 
			            alert("Không thể xóa !");
			        }
			    });
		    }
		    else {
		    	alert("Hãy chọn tài khoản cần xóa");
		    }
		}
</script>
</body>
</html>
