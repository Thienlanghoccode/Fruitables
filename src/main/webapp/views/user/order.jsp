<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/headlink.jsp"%>
<meta charset="UTF-8">
<title>Order</title>
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
		<h1 class="text-center text-white display-6">Giỏ Hàng</h1>
		<ol class="breadcrumb justify-content-center mb-0">
			<li class="breadcrumb-item"><a href="./index.html">Trang chủ</a></li>
			<li class="breadcrumb-item active text-white">Kiểm Tra Giỏ Hàng</li>
		</ol>
	</div>
	<!-- Single Page Header End -->

	<!-- Checkout Page Start -->
	<div class="container-fluid py-5">
		<div class="container py-5">
			<h1 class="mb-4">Thông Tin Nhận Hàng</h1>
			<form action="#">
				<div class="row g-5">
					<div class="col-md-12 col-lg-5 col-xl-5">
						<div class="form-item">
							<label class="form-label my-3">Họ Tên<sup></sup></label> <input
								type="text" class="form-control" />
						</div>
						<div class="form-item">
							<label class="form-label my-3">Email <sup></sup></label> <input
								type="text" class="form-control" placeholder="(Tùy Chọn)" />
						</div>
						<div class="form-item">
							<label class="form-label my-3">Số Điện Thoại<sup></sup></label> <input
								type="text" class="form-control" />
						</div>
						<div class="form-item">
							<label class="form-label my-3">Địa Chỉ<sup>*</sup></label> <input
								type="text" class="form-control" />
						</div>
						<div class="form-check my-3">
							<input type="checkbox" class="form-check-input" id="Account-1"
								name="Accounts" value="Accounts" /> <label
								class="form-check-label" for="Account-1">Thanh Toán Khi
								Đặt Hàng </label>
						</div>
						<hr />
						<div class="form-item">
							<textarea name="text" class="form-control" spellcheck="false"
								cols="30" rows="4" placeholder="Ghi Chú (Tùy Chọn)"></textarea>
						</div>
					</div>
					<div class="col-md-12 col-lg-7 col-xl-7">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">Sản Phẩm</th>
										<th scope="col">Tên Sản Phẩm</th>
										<th scope="col">Đơn Giá</th>
										<th scope="col">Số Lượng</th>
										<th scope="col">Tổng Tiền</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${sessionScope.cart }" var="map">
										<c:set var="quantity" value="${map.value.quantity }" />
										<c:set var="price" value="${map.value.product.price }" />
										<c:set var="total" value="${quantity * price}" />
										<tr>
											<th scope="row">
												<div class="d-flex align-items-center mt-2">
													<img
														src="<c:url value='templates/user/img/${map.value.product.image }' />"
														class="img-fluid rounded-circle"
														style="width: 90px; height: 90px" alt="" />
												</div>
											</th>
											<td class="py-5">${map.value.product.name}</td>
											<td class="py-5">$ ${price }</td>
											<td class="py-5">${quantity }</td>
											<td class="py-5">$ ${total }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div
							class="row g-4 text-center align-items-center justify-content-center pt-4">
							<button type="button"
								class="btn border-secondary py-3 px-4 text-uppercase w-100 text-primary"
								onclick="window.location.href='http://localhost:8080/VegetableStoreManager/finalOder'">
								Đặt Hàng</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- Checkout Page End -->


	<%@ include file="/common/footer.jsp"%>
	<%@ include file="/common/lastBodyScript.jsp"%>
</body>
</html>