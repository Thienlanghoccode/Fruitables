<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/headlink.jsp"%>
<meta charset="utf-8">
<title>Fruitables - Product Detail</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<!-- Single Page Header start -->
	<div class="container-fluid page-header py-5">
		<h1 class="text-center text-white display-6">${product.name }</h1>
		<ol class="breadcrumb justify-content-center mb-0">
			<li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
			<li class="breadcrumb-item"><a href="#">${product.category.name }</a></li>
			<li class="breadcrumb-item active text-white">${product.name }</li>
		</ol>
	</div>
	<!-- Single Page Header End -->


	<!-- Single Product Start -->
	<div class="container-fluid py-5 mt-5">
		<div class="container py-5">
			<div class="row g-4 mb-5">
				<div class="col-lg-8 col-xl-9">
					<div class="row g-4">
						<div class="col-lg-6">
							<div class="border rounded">
								<a href="#"> <img
									src="<c:url value='/templates/user/img/${product.image }' />"
									class="img-fluid rounded" alt="Image">
								</a>
							</div>
						</div>
						<div class="col-lg-6">
							<h4 class="fw-bold mb-3">${product.name }</h4>
							<p class="mb-3">Phân loại: ${product.category.name }</p>
							<h5 class="fw-bold mb-3">${product.price }$</h5>
							<div class="d-flex mb-4">
								<i class="fa fa-star text-secondary"></i> <i
									class="fa fa-star text-secondary"></i> <i
									class="fa fa-star text-secondary"></i> <i
									class="fa fa-star text-secondary"></i> <i class="fa fa-star"></i>
							</div>
							<p class="mb-4">${product.des }</p>
							<form action="<c:url value='product'/>" class="mb-4" method="get" >
							<input type="text" value="add" name="action" hidden="">
								<input type="text" value="${product.id }" name="pId" hidden="">
								
								<!-- quantity controller-->
								<div class="input-group quantity mb-5" style="width: 100px;">
									<div class="input-group-btn">
										<button type="button"
											class="btn btn-sm btn-minus rounded-circle bg-light border">
											<i class="fa fa-minus"></i>
										</button>
									</div>
									<input type="text"
										class="form-control form-control-sm text-center border-0"
										name ="quantity" value="1">
									<div class="input-group-btn">
										<button type="button"
											class="btn btn-sm btn-plus rounded-circle bg-light border">
											<i class="fa fa-plus"></i>
										</button>
									</div>
								</div>		
								<!-- submit -->
								<button type="submit"
									class="btn border border-secondary rounded-pill px-4 py-2 mb-4 text-primary">
									<i class="fa fa-shopping-bag me-2 text-primary"></i> THÊM VÀO GIỎ </button>
							</form>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-xl-3">
					<div class="row g-4 fruite">
						<div class="col-lg-12">
							<div class="position-relative">
								<img
									src="<c:url value='/templates/user/img/banner-fruits.jpg'/> "
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
			</div>


			<h1 class="fw-bold mb-0">Sản phẩm liên quan</h1>
			<div class="vesitable">

				<div class="owl-carousel vegetable-carousel justify-content-center">
					<c:forEach items="${relatedProducts }" var="relatedProduct">
						<div
							class="border border-primary rounded position-relative vesitable-item"
							role="button">
							<div class="vesitable-img">
								<a
									href="<c:url value='product?action=detail&id=${relatedProduct.id}'/>">
									<img
									src="<c:url value='templates/user/img/${relatedProduct.image }' />"
									class="rounded-top" alt="" width="347" height="243">>
								</a>
							</div>
							<div
								class="text-white bg-primary px-3 py-1 rounded position-absolute"
								style="top: 10px; right: 10px;">
								<a
									href="<c:url value='product?action=detail&id=${relatedProduct.id}'/>"
									style="color: #FFFFFF;"> ${relatedProduct.category.name } </a>
							</div>
							<div class="p-4 pb-0 rounded-bottom">
								<a
									href="<c:url value='product?action=detail&id=${relatedProduct.id}'/>"
									style="color: #747D88;">
									<h4>${relatedProduct.name }</h4>
									<p>${relatedProduct.des }</p>
								</a>
								<div class="d-flex justify-content-between flex-lg-wrap">
									<p class="text-dark fs-5 fw-bold">${relatedProduct.price }$</p>
									<a href="<c:url value='product?action=detail&id=${product.id}'/>"
										class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i
										class="fa fa-shopping-bag me-2 text-primary"></i> THÊM VÀO GIỎ</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!-- Single Product End -->



	<%@ include file="/common/footer.jsp"%>
	<%@ include file="/common/lastBodyScript.jsp"%>
</body>
</html>