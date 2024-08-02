<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Spinner Start -->
<div id="spinner"
	class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
	<div class="spinner-grow text-primary" role="status"></div>
</div>
<!-- Spinner End -->


<!-- Navbar start -->
<div class="container-fluid fixed-top">
	<div class="container topbar bg-primary d-none d-lg-block">
		<div class="d-flex justify-content-between">
			<div class="top-info ps-2">
				<small class="me-3"><i
					class="fas fa-map-marker-alt me-2 text-secondary"></i> <a
					href="contact" class="text-white">132, Cầu Diễn, Bắc Từ Liêm,
						Hà Nội</a></small> <small class="me-3"><i
					class="fas fa-envelope me-2 text-secondary"></i><a href="contact"
					class="text-white">Onlinefoodshoping@gmai.com</a></small>
			</div>
			<div class="top-link pe-2">
				<a href="#" class="text-white"><small class="text-white mx-2">Privacy
						Policy</small>/</a> <a href="#" class="text-white"><small
					class="text-white mx-2">Terms of Use</small>/</a> <a href="#"
					class="text-white"><small class="text-white ms-2">Sales
						and Refunds</small></a>
			</div>
		</div>
	</div>
	<div class="container px-0">
		<nav class="navbar navbar-light bg-white navbar-expand-xl">
			<a href="${pageContext.request.contextPath}/index" class="navbar-brand">
				<h1 class="text-primary display-6">Fruitables</h1>
			</a>
			<button class="navbar-toggler py-2 px-3" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
				<span class="fa fa-bars text-primary"></span>
			</button>
			<div class="collapse navbar-collapse bg-white" id="navbarCollapse">
				<div class="navbar-nav mx-auto">
					<a href="${pageContext.request.contextPath}/index" class="nav-item nav-link active">Trang chủ</a> <a
						href="${pageContext.request.contextPath}/shop" class="nav-item nav-link">Shop</a> <a
						href="${pageContext.request.contextPath}/introduce" class="nav-item nav-link">Giới thiệu</a>
					<div class="nav-item dropdown">
						<a href="#" class="nav-link dropdown-toggle"
							data-bs-toggle="dropdown">Sản Phẩm</a>
						<div class="dropdown-menu m-0 bg-secondary rounded-0">
							<a href="cart.html" class="dropdown-item">Cart</a> <a
								href="chackout.html" class="dropdown-item">Chackout</a> <a
								href="testimonial.html" class="dropdown-item">Testimonial</a> <a
								href="404.html" class="dropdown-item">404 Page</a>
						</div>
					</div>
					<a href="contact" class="nav-item nav-link">Liên hệ</a>
				</div>
				<div class="d-flex m-3 position-relative me-0 my-auto" >
					<div class="input-group w-100 my-auto d-flex position-relative" style="margin-right: 10px">
						<input type="search" class="me-0 my-auto p-3 form-control"
							placeholder="Nhập từ cần tìm"
							style="padding-right: 3rem; border-radius: 10px; z-index: 0;">
						<button
							class="border-0 rounded-circle bg-white position-absolute end-0 top-50 translate-middle-y me-2"
							style="z-index: 1; right: 10px; transform: translateY(-50%);">
							<i class="fas fa-search text-primary"></i>
						</button>
					</div>
				</div>


				<c:set var="totalQuantity" value="0" />
				<c:if test="${not empty sessionScope.cart}">
					<c:forEach var="entry" items="${sessionScope.cart}">
						<c:set var="totalQuantity"
							value="${totalQuantity + entry.value.quantity}" />
					</c:forEach>
				</c:if>
				<button class="position-relative me-4 my-auto bg-white border-0"
					data-bs-toggle="modal" data-bs-target="#cartModal">
					<i class="fa fa-shopping-bag fa-2x text-primary"></i> <span
						class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
						style="top: -5px; left: 15px; height: 20px; min-width: 20px">${totalQuantity}</span>
				</button>
				<div class="nav-item dropdown position-relative me-4 my-auto">
					<a href="#" class="nav-link " data-bs-toggle="dropdown"><i
						class="fas fa-user fa-2x"></i></a>
					<div class="dropdown-menu m-0 bg-secondary rounded-0">
						<c:choose>
							<c:when test="${not empty sessionScope.currentUser}">
								<a href="<c:url value='account' />" class="dropdown-item">${sessionScope.currentUser.username}</a>
								<a href="<c:url value='logout' />" class="dropdown-item">Đăng
									xuất</a>
							</c:when>
							<c:otherwise>
								<a href="<c:url value='login' />" class="dropdown-item">Đăng
									nhập</a>
								<a href="<c:url value='register' />" class="dropdown-item">Đăng
									ký</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
	</div>
	</nav>
</div>
</div>
<!-- Navbar End -->