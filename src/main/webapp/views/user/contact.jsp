<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/headlink.jsp"%>
<meta charset="UTF-8">
<title>Contact</title>
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
		<h1 class="text-center text-white display-6">Liên hệ</h1>
		<ol class="breadcrumb justify-content-center mb-0">
			<li class="breadcrumb-item"><a href="index">Trang chủ</a></li>
			<li class="breadcrumb-item active text-white">Liên hệ</li>
		</ol>
	</div>
	<!-- Single Page Header End -->

	<!-- Contact Start -->
	<div class="container-fluid contact py-5">
		<div class="container py-5">
			<div class="p-5 bg-light rounded">
				<div class="row g-4">
					<div class="col-12">
						<div class="text-center mx-auto" style="max-width: 700px;">
							<h1 class="text-primary">THÔNG TIN LIÊN HỆ</h1>
							<p class="mb-4">Hệ thống trái cây nhập khẩu, đặc biệt trái
								cây xách tay trực tiếp từ Mỹ, Úc, Nhật, Hàn Quốc, Newzealand,
								Đài Loan...như Cherry, Nho sữa, giỏ trái cây và đầy đủ các mặt
								hàng trái cây nhập khẩu đáp ứng mọi yêu cầu khác hàng. Chất
								lượng chuẩn 100%, giá cả phải chăng</p>
						</div>
					</div>
					<div class="col-lg-12">
						<div class="h-100 rounded">
							<iframe class="rounded w-100" style="height: 400px;"
								src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3723.4931418971605!2d105.73604840948063!3d21.052957326620575!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x313454f82ed2dd9b%3A0x9574da0aaf7d298f!2zTmcuIDEzMiDEkC4gQ-G6p3UgRGnhu4VuLCBOZ3V5w6puIFjDoSwgTWluaCBLaGFpLCBU4burIExpw6ptLCBIw6AgTuG7mWksIFZp4buHdCBOYW0!5e0!3m2!1svi!2s!4v1719732184233!5m2!1svi!2s"
								loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
						</div>
					</div>
					<div class="col-lg-7">
						<form action="" class="">
							<input type="text" class="w-100 form-control border-0 py-3 mb-4"
								placeholder="Tên Của Bạn"> <input type="email"
								class="w-100 form-control border-0 py-3 mb-4"
								placeholder="Email Của Bạn">
							<textarea class="w-100 form-control border-0 mb-4" rows="5"
								cols="10" placeholder="Lời Nhắn Của Bạn"></textarea>
							<button
								class="w-100 btn form-control border-secondary py-3 bg-white text-primary "
								type="submit">GỬI ĐI</button>
						</form>
					</div>
					<div class="col-lg-5">
						<div class="d-flex p-4 rounded mb-4 bg-white">
							<i class="fas fa-map-marker-alt fa-2x text-primary me-4"></i>
							<div>
								<h4>Địa Chỉ</h4>
								<p class="mb-2">132, Cầu Diễn, Bắc Từ Liêm, Hà Nội</p>
							</div>
						</div>
						<div class="d-flex p-4 rounded mb-4 bg-white">
							<i class="fas fa-envelope fa-2x text-primary me-4"></i>
							<div>
								<h4>Email</h4>
								<p class="mb-2">Onlinefoodshoping@gmai.com</p>
							</div>
						</div>
						<div class="d-flex p-4 rounded bg-white">
							<i class="fa fa-phone-alt fa-2x text-primary me-4"></i>
							<div>
								<h4>Điện Thoại</h4>
								<p class="mb-2">(+012) 3456 7890</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Contact End -->


	<%@ include file="/common/footer.jsp"%>
	<%@ include file="/common/lastBodyScript.jsp"%>
</body>
</html>