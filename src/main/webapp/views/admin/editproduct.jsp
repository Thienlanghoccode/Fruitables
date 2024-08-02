<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/admin/headlink.jsp"%>
<meta charset="UTF-8">
<title>EDIT PRODUCT</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
</head>
<body>
	<%@ include file="/common/admin/header.jsp"%>

	<div class="container tm-mt-big tm-mb-big">
		<div class="row">
			<div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
				<div class="tm-bg-primary-dark tm-block tm-block-h-auto">
					<div class="row">
						<div class="col-12">
							<h2 class="tm-block-title d-inline-block">Chỉnh sửa sản phẩm</h2>
						</div>
					</div>
					<form action="" method="post" enctype="multipart/form-data"
						class="tm-edit-product-form" accept-charset="UTF-8">
						<input type="hidden" name="id"
							value="${product.id }" />
						<div class="row tm-edit-product-row">
							<div class="col-xl-6 col-lg-6 col-md-12">
								<div class="form-group mb-3">
									<label for="name">Tên Sản Phẩm</label> <input id="name"
										value="${product.name}" name="name" type="text"
										class="form-control" spellcheck="false" required />
								</div>
								<div class="form-group mb-3">
									<label for="price">Giá Bán</label> <input name="price"
										value="${product.price}" type="number"
										class="form-control" required />
								</div>
								<div class="form-group mb-3">
									<label for="category">Danh Mục</label> <select
										class="custom-select tm-select-accounts" id="category"
										name="category">
										<c:forEach items="${categories}" var="category">
											<option value="${category.id}"
												${category.id == product.category.id ? 'selected="selected"' : ''}>${category.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group mb-3">
									<label for="description">Mô Tả</label>
									<textarea spellcheck="false" class="form-control validate" rows="3" name="description" required>${product.des }</textarea>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
								<div class="tm-product-img-dummy mx-auto"
									style="width: 100%; height: 300px; position: relative;">
									<img class="tm-product-img-dummy mx-auto"
										src="${pageContext.request.contextPath}/templates/user/img/${product.image}"
										style="width: 100%; height: 100%; object-fit: cover;" />
								</div>
								<div class="custom-file mt-3 mb-3">
									<input id="fileInput" type="file" name="image"
										style="display: none;" /> <input type="button"
										class="btn btn-primary btn-block mx-auto"
										value="TẢI HÌNH ẢNH LÊN"
										onclick="document.getElementById('fileInput').click();" />
								</div>
							</div>
							<div class="col-12">
								<button type="submit"
									class="btn btn-primary btn-block text-uppercase">CHỈNH
									SỬA SẢN PHẨM</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/common/admin/footer.jsp"%>
	<%@ include file="/common/admin/lastBodyScript.jsp"%>

</body>
</html>