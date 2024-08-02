<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/admin/headlink.jsp"%>
<meta charset="UTF-8">
<title>Category</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
</head>
<body>
	<%@ include file="/common/admin/header.jsp"%>

	<div class="container tm-mt-big tm-mb-big">
		<div class="row">
			<div class="col-12 mx-auto tm-login-col">
				<div class="tm-bg-primary-dark tm-block tm-block-h-auto">
					<div class="row">
						<div class="col-8 text-center">
							<h2 class="tm-block-title mb-4">DANH MỤC HIỆN CÓ</h2>
						</div>
						<div class="col-12 text-right">
							<button style="margin-bottom: 20px" type="button" class="btn btn-primary btn-block text-uppercase"
								data-toggle="modal" data-target="#addCategoryModal">Thêm danh mục</button>
						</div>
					</div>
					<div>		
					</div>
					<div class="row mt-2">
						<div class="col-12">
							<form
								action="${pageContext.request.contextPath}/admin/editcategory"
								method="post" class="tm-login-form">
								<div class="form-group">
									<label for="username">CHỌN DANH MỤC</label> <select
										id="category-select" class="custom-select" name="Id">
										<c:forEach items="${categoryList}" var="category">
											<option value="${category.id}">${category.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group mt-3">
									<label for="categoryname">Tên danh mục</label> <input
										name="categoryname" type="text" class="form-control validate"
										id="categoryname" />
								</div>
								<div class="form-group mt-4">
									<button type="submit"
										class="btn btn-primary btn-block text-uppercase">Chỉnh
										sửa</button>
								</div>
								<button type="button"
									class="mt-5 btn btn-primary btn-block text-uppercase"
									onclick="deleteSelectedCategory()">Xóa !</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="addCategoryModal" tabindex="-1" role="dialog" aria-labelledby="addCategoryModalLabel" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="addCategoryModalLabel">Thêm danh mục mới</h5>
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <div class="modal-body">
	                <form action="${pageContext.request.contextPath}/admin/addcategory" method="post">
	                    <div class="form-group">
	                        <label for="new-category-name" style="color: #000">Tên danh mục mới</label>
	                        <input name="newCategoryName" type="text" class="form-control btn btn-light validate" id="new-category-name" required />
	                    </div>
	                    <button type="submit" class="btn btn-success btn-block text-uppercase">Thêm</button>
	                </form>
	            </div>
	        </div>
	    </div>
	</div>
	

	<%@ include file="/common/admin/footer.jsp"%>
	<%@ include file="/common/admin/lastBodyScript.jsp"%>

	<script>
		$(document).ready(function() {
			$('#category-select').change(function() {
				var categoryId = $(this).val();
				if (categoryId != '0') {
					$.ajax({
						url : '/VegetableStoreManager/admin/category',
						type : 'POST',
						data : {
							Id : categoryId
						},
						success : function(response) {
							// Xử lý và cập nhật thông tin tài khoản ở đây mà không cần tải lại trang
							$('#id').val(response.id);
							$('#categoryname').val(response.name);
						},
						error : function() {
							alert("Opps, please try again!");
						}
					});
				} else {
					alert("Vui lòng chọn tài khoản hợp lệ!");
				}
			});
		});

		function deleteSelectedCategory() {
			var categoryId = document.getElementById('category-select').value;
			if (categoryId != null) {
				$.ajax({
					url : '/VegetableStoreManager/admin/deletecategory?Id='
							+ categoryId,
					type : 'POST',
					success : function() {
						window.location.reload();
					},
					error : function() {
						alert("Không thể xóa !");
					}
				});
			} else {
				alert("Hãy chọn danh mục cần xóa");
			}
		}
	</script>

</body>
</html>
