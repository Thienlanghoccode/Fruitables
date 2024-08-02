<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<meta charset="utf-8">
<title>Fruitables - Vegetable Website</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
<%@ include file="/common/headlink.jsp"%>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<!-- Include Cart -->
	<%@ include file="cart.jsp"%>

	<!-- Single Page Header start -->
	<div class="container-fluid page-header py-5">
		<h1 class="text-center text-white display-6">Shop</h1>
		<ol class="breadcrumb justify-content-center mb-0">
			<li class="breadcrumb-item"><a href="index">Trang chủ</a></li>
			<li class="breadcrumb-item active text-white">Shop</li>
		</ol>
	</div>
	<!-- Single Page Header End -->


	<!-- Fruits Shop Start-->
	<div class="container-fluid fruite py-5">
		<div class="container py-5">
			<h1 class="mb-4">Fresh fruits shop</h1>
			<div class="row g-4">
				<div class="col-lg-12">
					<div class="row g-4">
						<div class="col-xl-3">
							<form action="searchByName" method="post">
								<div class="input-group w-100 mx-auto d-flex">
									<input type="text" class="form-control p-3" name="productName" 
										placeholder="Nhập từ cần tìm" aria-describedby="search-icon-1">
									<button type="submit" id="search-icon-1"
										class="input-group-text p-3">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</form>
						</div>
						<div class="col-6"></div>
						<div class="col-xl-3">
							<div
								class="bg-light ps-3 py-3 rounded d-flex justify-content-between mb-4">
								<label for="fruits">Default Sorting:</label> <select id="fruits"
									name="fruitlist" class="border-0 form-select-sm bg-light me-3"
									form="fruitform">
									<option value="volvo">Nothing</option>
									<option value="saab">Popularity</option>
									<option value="opel">Organic</option>
									<option value="audi">Fantastic</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row g-4">
						<div class="col-lg-3">
							<div class="row g-4">
								<div class="col-lg-12">
									<div class="mb-3">
										<h4>DANH MỤC</h4>
										<ul class="list-unstyled fruite-categorie">
											<c:forEach items="${categories }" var="category">
												<li>
													<div class="d-flex justify-content-between fruite-name">
														<a href="shop/search" style="text-transform: uppercase;">${category.name }</a>
													</div>
												</li>
											</c:forEach>

										</ul>
									</div>
								</div>
								<div class="col-lg-12">
									<div class="mb-3">
										<h4 class="mb-2">Price</h4>
										<input type="range" class="form-range w-100" id="rangeInput"
											name="rangeInput" min="0" max="500" value="0"
											oninput="amount.value=rangeInput.value">
										<output id="amount" name="amount" min-velue="0"
											max-value="500" for="rangeInput">0</output>
									</div>
								</div>
								<div class="col-lg-12">
									<div class="position-relative">
										<img
											src="<c:url value='/templates/user/img/banner-fruits.jpg' />"
											class="img-fluid w-100 rounded" alt="">
										<div class="position-absolute"
											style="top: 50%; right: 10px; transform: translateY(-50%);">
											<h3 class="text-secondary fw-bold">
												Fresh <br> Fruits <br> Banner
											</h3>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-9">
							<div class="row g-4 justify-content-center">
								<c:forEach items="${productSeachByCategory }" var="product">
									<div class="col-md-6 col-lg-6 col-xl-4">
										<div class="rounded position-relative fruite-item">
											<div class="fruite-img">
												<a
													href="${pageContext.request.contextPath}/product?action=detail&id=${product.id}">
													<img
													src="<c:url value='/templates/user/img/${product.image }' />"
													class="w-100" height="244.8" alt="">
												</a>

											</div>
											<div
												class="text-white bg-secondary px-3 py-1 rounded position-absolute"
												style="top: 10px; left: 10px;">${product.category.name }</div>
											<div
												class="p-4 border border-secondary border-top-0 rounded-bottom">
												<a
													href="${pageContext.request.contextPath}/product?action=detail&id=${product.id}">
													<h4>${product.name }</h4>
												</a> <a style="color: #747D88;"
													href="${pageContext.request.contextPath}/product?action=detail&id=${product.id}">
													<p>${product.des }</p>
												</a>
												<div class="d-flex justify-content-between flex-lg-wrap">
													<p class="text-dark fs-5 fw-bold mb-0">$
														${product.price }</p>
													<button onclick="addToCart('${product.id}')"
														class="btn border border-secondary rounded-pill px-3 text-primary">
														<i class="fa fa-shopping-bag me-2 text-primary"></i> Thêm
														hàng
													</button>
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
	<!-- Fruits Shop End-->

	<%@ include file="/common/footer.jsp"%>
	<%@ include file="/common/lastBodyScript.jsp"%>

	<script type="text/javascript">
		function addToCart(productID) {
			$.ajax({
				url : '/VegetableStoreManager/product?action=add&pId='
						+ productID + '&quantity=1',
				success : function(data) {
					window.location.href = '/VegetableStoreManager/shop';
				},
				error : function() {
					alert("Oops! Try again.");
				}
			});
		}
	</script>
</body>
</html>