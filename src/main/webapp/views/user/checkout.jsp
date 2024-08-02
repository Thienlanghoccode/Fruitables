<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/headlink.jsp"%>
<meta charset="utf-8">
<title>Fruitables - CheckOut</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	
	<!-- Include Cart -->
	<%@ include file="cart.jsp"%>

	<!-- Single Page Header start -->
	<div class="container-fluid page-header py-5">
		<h1 class="text-center text-white display-6">Cart</h1>
		<ol class="breadcrumb justify-content-center mb-0">
			<li class="breadcrumb-item"><a href="index">Trang chủ</a></li>
			<li class="breadcrumb-item active text-white">Giỏ hàng</li>
		</ol>
	</div>
	<!-- Single Page Header End -->

	<!-- Cart Page Start -->
	<div class="container-fluid py-5">
		<c:set var="sumTotal" value="${0}" />
		<div class="container py-5">
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Sản Phẩm</th>
							<th scope="col">Tên Hàng</th>
							<th scope="col">Đơn Giá</th>
							<th scope="col">Số Lượng</th>
							<th scope="col">Tổng Tiền</th>
							<th scope="col">Xóa</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sessionScope.cart }" var="map">
							<c:set var="quantity" value="${map.value.quantity }" />
							<c:set var="price" value="${map.value.product.price }" />
							<c:set var="total" value="${quantity * price}" />
							<c:set var="sumTotal" value="${sumTotal + total}" />
							<tr>
								<th scope="row">
									<div class="d-flex align-items-center">
										<img
											src="<c:url value='templates/user/img/${map.value.product.image }' />"
											class="img-fluid me-5 rounded-circle"
											style="width: 80px; height: 80px;" alt="">
									</div>
								</th>
								<td>
									<p class="mb-0 mt-4">${map.value.product.name }</p>
								</td>
								<td>
									<p class="mb-0 mt-4">${price }$</p>
								</td>
								<td>
									<div class="input-group quantity mt-4" style="width: 100px;">
										<div class="input-group-btn">
											<button
												onclick="window.location.href='http://localhost:8080/VegetableStoreManager/checkout?action=add&pId=${map.value.product.id}&quantity=${quantity - 1}'"
												class="btn btn-sm btn-minus rounded-circle bg-light border">
												<i class="fa fa-minus"></i>
											</button>
										</div>
										<input type="text"
											class="form-control form-control-sm text-center border-0"
											value="${quantity }">
										<div class="input-group-btn">
											<button
												onclick="window.location.href='http://localhost:8080/VegetableStoreManager/checkout?action=add&pId=${map.value.product.id}&quantity=${quantity + 1}'"
												class="btn btn-sm btn-plus rounded-circle bg-light border">
												<i class="fa fa-plus"></i>
											</button>
										</div>
									</div>
								</td>
								<td>
									<p class="mb-0 mt-4">${total }$</p>
								</td>
								<td>
									<button class="btn btn-md rounded-circle bg-light border mt-4"
										onclick="deleteCart('${map.value.product.id}')">
										<i class="fa fa-times text-danger"></i>
									</button>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			<form action="<c:url value='applyCoupon'/>" method="get"">
				<div class="mt-5">
					<input type="text" name="couponCode"
						class="border-0 border-bottom rounded me-5 py-3 mb-4"
						placeholder="Mã Giảm Giá">
					<button
						class="btn border-secondary rounded-pill px-5 py-3 text-primary"
						type="submit">ÁP DỤNG</button>

				</div>
			</form>

			<div class="row g-4 justify-content-end">
				<div class="col-8"></div>
				<div class="col-sm-8 col-md-7 col-lg-6 col-xl-4">
					<div class="bg-light rounded">
						<div class="p-4">
							<h1 class="display-6 mb-4">
								THÔNG TIN <br> <span class="fw-normal">GIỎ HÀNG</span>
							</h1>
							<div class="d-flex justify-content-between mb-4">
								<h5 class="mb-0 me-4">Tổng tiền:</h5>
								<p class="mb-0">$ ${sumTotal }</p>
							</div>
							<div class="d-flex justify-content-between">
								<h5 class="mb-0 me-4">Giảm Giá</h5>
								<div class="">
									<c:choose>
										<c:when test="${not empty sessionScope.discount }">
											<p class="mb-0">Giảm giá: ${sessionScope.discount } %</p>
										</c:when>
										<c:otherwise>
											<p class="mb-0">Giảm giá: 0%</p>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div
							class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
							<h5 class="mb-0 ps-4 me-4">Tổng đơn:</h5>
							<p class="mb-0 pe-4">$ ${sumTotal - (sumTotal * sessionScope.discount / 100)}</p>
						</div>
						<button
							class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4"
							onclick="window.location.href='http://localhost:8080/VegetableStoreManager/order'">
							Hoàn Thành Kiểm Tra</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Cart Page End -->

	<%@ include file="/common/footer.jsp"%>
	<%@ include file="/common/lastBodyScript.jsp"%>
	
	<script>
	function deleteCart(productId) {
		$.ajax({
			type: 'GET', //'GET' nếu yêu cầu của bạn là GET
			url: '/VegetableStoreManager/checkout?action=delete&pID=' + productId,
			success: function(data) {
				// Xử lý khi xóa thành công
				window.location.href='http://localhost:8080/VegetableStoreManager/checkout?action=views';
			},
			error: function() {
				// Xử lý khi có lỗi
				alert("Oops! Try again.");
			}
		});
	}
</script>

</body>
</html>