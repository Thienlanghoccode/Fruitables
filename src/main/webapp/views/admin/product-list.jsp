<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/admin/headlink.jsp"%>
<meta charset="UTF-8">
<title>Fruitables - Product Admin</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
</head>
<body>
	<%@ include file="/common/admin/header.jsp"%>
	<div class="col-12 tm-block-col">
		<div
			class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
			<h2 class="tm-block-title">DANH SÁCH SẢN PHẨM</h2>
			<table
				class="table table table-hover tm-table-small tm-product-table">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">TÊN</th>
						<th scope="col">ĐƠN GIÁ</th>
						<th scope="col">DANH MỤC</th>
						<th scope="col">HÌNH ẢNH</th>
						<th scope="col">MÔ TẢ</th>
						<th scope="col">&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${products}" var="product">
						<c:url value="/templates/user/img/${product.image}" var="imgUrl"></c:url>
						<tr>
							<th scope="row" class="tm-product-name" data-product-id="${product.id}"><b># ${product.id }</b></th>
							<td class="tm-product-name" data-product-id="${product.id}">${product.name }</td>
							<td class="tm-product-name" data-product-id="${product.id}"><b>${product.price }</b></td>
							<td class="tm-product-name" data-product-id="${product.id}"> <b style="text-transform: uppercase;">${product.category.name }</b></td>
							<td class="tm-product-name" data-product-id="${product.id}"><img src="${imgUrl }" width="175" height="150"
								class="rounded-top" alt="${imgUrl }"></td>
							<td class="tm-product-name" data-product-id="${product.id}">${product.des }</td>
							<td><a href="${pageContext.request.contextPath}/admin/deleteproduct?pId=${product.id}" class="tm-product-delete-link"> <i
									class="far fa-trash-alt tm-product-delete-icon"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<a href="${pageContext.request.contextPath}/admin/addproduct"
						class="btn btn-primary btn-block text-uppercase mb-3">THÊM SẢN
						PHẨM MỚI</a>
				</tfoot>
			</table>
		</div>
	</div>


	<%@ include file="/common/admin/footer.jsp"%>
	<%@ include file="/common/admin/lastBodyScript.jsp"%>

	<script>
    $(function () {
        $(".tm-product-name").on("click", function (e) {
            e.preventDefault();
            var productId = $(this).data("product-id"); // Lấy giá trị từ thuộc tính data-product-id
            
            // Chuyển hướng đến trang chỉnh sửa sản phẩm với pId được chèn vào URL
            window.location.href = '/VegetableStoreManager/admin/editproduct?pId=' + productId;
        });
    });
	</script>
</body>
</html>