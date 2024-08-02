<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-xl">
	<div class="container h-100">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/admin">
			<h1 class="tm-site-title mb-0">QUẢN LÝ SẢN PHẨM</h1>
		</a>
		<button class="navbar-toggler ml-auto mr-0" type="button"
			data-toggle="collapse" data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<i class="fas fa-bars tm-nav-icon"></i>
		</button>
	
		<c:set var="currentURL" value="${pageContext.request.requestURI}" />
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mx-auto h-100">
				<li class="nav-item"><a class="nav-link ${currentURL.contains('index.jsp') ? 'active' : ''}" href="${pageContext.request.contextPath}/admin"> <i
						class="fas fa-tachometer-alt"></i> Trang chủ <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link ${currentURL.contains('product-list.jsp')
				|| currentURL.contains('addproduct.jsp') || currentURL.contains('editproduct.jsp')
				 ? 'active' : ''}" href="${pageContext.request.contextPath}/admin/productlist">
						<i class="fas fa-shopping-cart"></i> Sản Phẩm
				</a></li>
				<li class="nav-item"><a class="nav-link ${currentURL.contains('category.jsp') ? 'active' : ''}" href="${pageContext.request.contextPath}/admin/category">
						<i class="far fa-file-alt"></i> Danh Mục
				</a></li>

				<li class="nav-item"><a class="nav-link ${currentURL.contains('account.jsp') || currentURL.contains('editaccount.jsp') ? 'active' : ''}" href="${pageContext.request.contextPath}/admin/account">
						<i class="far fa-user"></i> Tài Khoản
				</a></li>
			</ul>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link d-block"
					href="${pageContext.request.contextPath}/logout">Chào mừng: ${sessionScope.currentUser.username } , <b>Thoát !</b>
				</a></li>
			</ul>
		</div>
	</div>

</nav>