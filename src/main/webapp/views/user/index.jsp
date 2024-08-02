<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/common/headlink.jsp"%>
<meta charset="utf-8">
<title>Fruitables - Vegetable Website</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<!-- Include Cart -->
	<%@ include file="cart.jsp"%>


	<!-- Hero Start || Banner-->
	<div class="container-fluid py-12 mb-12 hero-header">
		<div class="container py-12">
			<div class="row g-12 align-items-center">
				<div class="col-md-12 col-lg-12">
					<div id="carouselId" class="carousel slide position-relative"
						data-bs-ride="carousel">
						<div class="carousel-inner" role="listbox">
							<div class="carousel-item active rounded">
								<img src="<c:url value='templates/user/img/slider_1.jpg' />"
									class="img-fluid w-100 h-100 rounded" alt="Second slide">
							</div>
							<div class="carousel-item rounded">
								<img src="<c:url value='templates/user/img/slider_2.jpg' />"
									class="img-fluid w-100 h-100 rounded" alt="Second slide">
							</div>
							<div class="carousel-item rounded">
								<img src="<c:url value='templates/user/img/slider_3.jpg' />"
									class="img-fluid w-100 h-100 rounded" alt="Second slide">
							</div>
						</div>
						<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselId" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselId" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Hero End -->

	<!-- Featurs Section Start -->
	<div class="container-fluid featurs py-5">
		<div class="container py-5">
			<div class="row g-4">
				<div class="col-md-6 col-lg-3">
					<div class="featurs-item text-center rounded bg-light p-4">
						<div
							class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
							<i class="fas fa-car-side fa-3x text-white"></i>
						</div>
						<div class="featurs-content text-center">
							<h5>Miễn Phí Giao Hàng</h5>
							<p class="mb-0">Những Đơn Hàng Từ 999.999 VND</p>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-lg-3">
					<div class="featurs-item text-center rounded bg-light p-4">
						<div
							class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
							<i class="fas fa-user-shield fa-3x text-white"></i>
						</div>
						<div class="featurs-content text-center">
							<h5>Thanh Toán Bảo Mật</h5>
							<p class="mb-0">100% Bảo Mật Khi Thanh Toán</p>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-lg-3">
					<div class="featurs-item text-center rounded bg-light p-4">
						<div
							class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
							<i class="fas fa-exchange-alt fa-3x text-white"></i>
						</div>
						<div class="featurs-content text-center">
							<h5>30 Ngày Hoàn Trả</h5>
							<p class="mb-0">Đảm Bảo Hoàn Tiền 30 Ngày</p>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-lg-3">
					<div class="featurs-item text-center rounded bg-light p-4">
						<div
							class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
							<i class="fa fa-phone-alt fa-3x text-white"></i>
						</div>
						<div class="featurs-content text-center">
							<h5>Hỗ Trợ 24/7</h5>
							<p class="mb-0">Hỗ Trợ Nhanh Chóng, Mọi Lúc</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Featurs Section End -->

	<div class="container-fluid fruite py-5">
		<div class="container py-5">
			<div class="tab-class text-center">
				<div class="row g-4">
					<div class="col-lg-4 text-start">
						<h1>SẢN PHẨM NỔI BẬT</h1>
					</div>
				</div>
				<div class="tab-content">
					<div id="tab-1" class="tab-pane fade show p-0 active">
						<div class="row g-4">
							<div class="col-lg-12">
								<div class="row g-4">
									<c:forEach items="${products }" var="product" begin="0" end="11">
										<c:url value="/templates/user/img/${product.image }"
											var="imgUrl"></c:url>
										<div class="col-md-6 col-lg-4 col-xl-3 ">
											<div class="rounded position-relative fruite-item"
												role="button">
												<div class="fruite-img">
													<a
														href="<c:url value='product?action=detail&id=${product.id}'/>">
														<img src="${imgUrl }" class="w-100" height="244.8"
														class="rounded-top" alt="${imgUrl }">
													</a>
												</div>
												<div
													class="text-white bg-secondary px-3 py-1 rounded position-absolute"
													style="top: 10px; left: 10px;">
													<a
														href="<c:url value='product?action=detail&id=${product.id}'/>"
														style="color: #FFFFFF;"> ${product.category.name } </a>
												</div>
												<div
													class="p-4 border border-secondary border-top-0 rounded-bottom">
													<a
														href="<c:url value='product?action=detail&id=${product.id}'/>"
														style="color: #747D88;">
														<h4>${product.name }</h4>
														<p>${product.des}</p>
													</a>
													<div class="d-flex justify-content-between flex-lg-wrap">
														<p class="text-dark fs-5 fw-bold mb-0">
															<a
																href="<c:url value='product?action=detail&id=${product.id}'/>"
																style="color: black;"> ${product.price}$ </a>
														</p>
														<a
															href="<c:url value='product?action=add&pId=${product.id}&quantity=1'/>"
															class="btn border border-secondary rounded-pill px-3 text-primary"><i
															class="fa fa-shopping-bag me-2 text-primary"></i> Thêm
															Hàng</a>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bestsaler Product Start -->
	<div class="container-fluid py-5">
		<div class="container py-5">
			<div class="text-center mx-auto mb-5" style="max-width: 700px;">
				<h1 class="display-4"
					style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif !important">SẢN
					PHẨM BÁN CHẠY</h1>
				<p>Latin words, combined with a handful of model sentence
					structures, to generate Lorem Ipsum which looks reasonable.</p>
			</div>
			<div class="row g-4">
				<c:forEach items="${products }" var="product" begin="0" end="5">
					<div class="col-lg-6 col-xl-4">

						<div class="p-4 rounded bg-light">
							<div class="row align-items-center">
								<div class="col-6">
									<a
										href="<c:url value='product?action=detail&id=${product.id}'/>">
										<img
										src="<c:url value="/templates/user/img/${product.image }" />"
										class="img-fluid rounded-circle w-100" alt="">
									</a>
								</div>
								<div class="col-6">
									<a
										href="<c:url value='product?action=detail&id=${product.id}'/>"
										class="h5">${product.name }</a>
									<div class="d-flex my-3">
										<i class="fas fa-star text-primary"></i> <i
											class="fas fa-star text-primary"></i> <i
											class="fas fa-star text-primary"></i> <i
											class="fas fa-star text-primary"></i> <i class="fas fa-star"></i>
									</div>
									<h4 class="mb-3">${product.price }$</h4>
									<a
										href="<c:url value='product?action=add&pId=${product.id}&quantity=1'/>"
										class="btn border border-secondary rounded-pill px-3 text-primary"><i
										class="fa fa-shopping-bag me-2 text-primary"></i> Thêm Hàng</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<c:forEach items="${products }" var="product" begin="6" end="9">
					<div class="col-md-6 col-lg-6 col-xl-3">
						<div class="text-center">
							<a href="<c:url value='product?action=detail&id=${product.id}'/>">
								<img
								src="<c:url value="/templates/user/img/${product.image }" />"
								class="img-fluid img-fluid rounded w-100" alt="" style="height: 244.8px">
							</a>
							<div class="py-4">
								<a
									href="<c:url value='product?action=detail&id=${product.id}'/>"
									class="h5">${product.name }</a>
								<div class="d-flex my-3 justify-content-center">
									<i class="fas fa-star text-primary"></i> <i
										class="fas fa-star text-primary"></i> <i
										class="fas fa-star text-primary"></i> <i
										class="fas fa-star text-primary"></i> <i class="fas fa-star"></i>
								</div>
								<h4 class="mb-3">${product.price }$</h4>
								<a
									href="<c:url value='product?action=add&pId=${product.id}&quantity=1'/>"
									class="btn border border-secondary rounded-pill px-3 text-primary"><i
									class="fa fa-shopping-bag me-2 text-primary"></i> Thêm Hàng</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	</div>
	<!-- Bestsaler Product End -->

	<!-- Fact Start -->
	<div class="container-fluid py-5">
		<div class="container">
			<div class="bg-light p-5 rounded">
				<div class="row g-4 justify-content-center">
					<div class="col-md-6 col-lg-6 col-xl-3">
						<div class="counter bg-white rounded p-5">
							<i class="fa fa-users text-secondary"></i>
							<h4
								style="font-family: 'Times New Roman', Times, serif !important;">Khách
								Hàng Hài Lòng</h4>
							<h1
								style="font-family: 'Times New Roman', Times, serif !important;">1963</h1>
						</div>
					</div>
					<div class="col-md-6 col-lg-6 col-xl-3">
						<div class="counter bg-white rounded p-5">
							<i class="fa fa-users text-secondary"></i>
							<h4
								style="font-family: 'Times New Roman', Times, serif !important;">Chất
								Lượng Dịch Vụ</h4>
							<h1
								style="font-family: 'Times New Roman', Times, serif !important;">99%</h1>
						</div>
					</div>
					<div class="col-md-6 col-lg-6 col-xl-3">
						<div class="counter bg-white rounded p-5">
							<i class="fa fa-users text-secondary"></i>
							<h4
								style="font-family: 'Times New Roman', Times, serif !important;">Chứng
								Nhận Chất Lượng</h4>
							<h1
								style="font-family: 'Times New Roman', Times, serif !important;">33</h1>
						</div>
					</div>
					<div class="col-md-6 col-lg-6 col-xl-3">
						<div class="counter bg-white rounded p-5">
							<i class="fa fa-users text-secondary"></i>
							<h4
								style="font-family: 'Times New Roman', Times, serif !important;">Sản
								Phẩm Hiện Có</h4>
							<h1
								style="font-family: 'Times New Roman', Times, serif !important;">789</h1>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Fact Start -->

	<!-- Tastimonial Start -->
	<div class="container-fluid testimonial py-5">
		<div class="container py-5">
			<div class="testimonial-header text-center">
				<h1 class="display-5 mb-5 text-dark"
					style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif !important;">Khách
					Hàng Nói Về Chúng Tôi !</h1>
			</div>
			<div class="owl-carousel testimonial-carousel">
				<div class="testimonial-item img-border-radius bg-light rounded p-4">
					<div class="position-relative">
						<i
							class="fa fa-quote-right fa-2x text-secondary position-absolute"
							style="bottom: 30px; right: 0;"></i>
						<div class="mb-4 pb-4 border-bottom border-secondary">
							<p class="mb-0">Nho sữa ở Online Food rất ngon và đậm vị sữa,
								đặc biệt là loại cherry xách tay ăn rất ngon. Nhân viên tư vấn
								rất nhiệt tình !</p>
						</div>
						<div class="d-flex align-items-center flex-nowrap">
							<div class="bg-secondary rounded">
								<img
									src="<c:url value='templates/user/img/testimonial-1.jpg' />"
									class="img-fluid rounded" style="width: 100px; height: 100px;"
									alt="">
							</div>
							<div class="ms-4 d-block">
								<h4 class="text-dark">Anh Nguyễn Văn A</h4>
								<p class="m-0 pb-3">Profession</p>
								<div class="d-flex pe-5">
									<i class="fas fa-star text-primary"></i> <i
										class="fas fa-star text-primary"></i> <i
										class="fas fa-star text-primary"></i> <i
										class="fas fa-star text-primary"></i> <i class="fas fa-star"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="testimonial-item img-border-radius bg-light rounded p-4">
					<div class="position-relative">
						<i
							class="fa fa-quote-right fa-2x text-secondary position-absolute"
							style="bottom: 30px; right: 0;"></i>
						<div class="mb-4 pb-4 border-bottom border-secondary">
							<p class="mb-0">Nho sữa ở Online Food rất ngon và đậm vị sữa,
								đặc biệt là loại cherry xách tay ăn rất ngon. Nhân viên tư vấn
								rất nhiệt tình !</p>
						</div>
						<div class="d-flex align-items-center flex-nowrap">
							<div class="bg-secondary rounded">
								<img
									src="<c:url value='templates/user/img/testimonial-1.jpg' />"
									class="img-fluid rounded" style="width: 100px; height: 100px;"
									alt="">
							</div>
							<div class="ms-4 d-block">
								<h4 class="text-dark">Chị Nguyễn Thị B</h4>
								<p class="m-0 pb-3">Profession</p>
								<div class="d-flex pe-5">
									<i class="fas fa-star text-primary"></i> <i
										class="fas fa-star text-primary"></i> <i
										class="fas fa-star text-primary"></i> <i
										class="fas fa-star text-primary"></i> <i
										class="fas fa-star text-primary"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="testimonial-item img-border-radius bg-light rounded p-4">
					<div class="position-relative">
						<i
							class="fa fa-quote-right fa-2x text-secondary position-absolute"
							style="bottom: 30px; right: 0;"></i>
						<div class="mb-4 pb-4 border-bottom border-secondary">
							<p class="mb-0">Nho sữa ở Online Food rất ngon và đậm vị sữa,
								đặc biệt là loại cherry xách tay ăn rất ngon. Nhân viên tư vấn
								rất nhiệt tình !</p>
						</div>
						<div class="d-flex align-items-center flex-nowrap">
							<div class="bg-secondary rounded">
								<img
									src="<c:url value ='templates/user/img/testimonial-1.jpg' />"
									class="img-fluid rounded" style="width: 100px; height: 100px;"
									alt="">
							</div>
							<div class="ms-4 d-block">
								<h4 class="text-dark">Anh Lê Văn C</h4>
								<p class="m-0 pb-3">Profession</p>
								<div class="d-flex pe-5">
									<i class="fas fa-star text-primary"></i> <i
										class="fas fa-star text-primary"></i> <i
										class="fas fa-star text-primary"></i> <i
										class="fas fa-star text-primary"></i> <i
										class="fas fa-star text-primary"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Tastimonial End -->

	<%@ include file="/common/footer.jsp"%>
	<%@ include file="/common/lastBodyScript.jsp"%>
</body>
</html>