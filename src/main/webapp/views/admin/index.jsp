<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/admin/headlink.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<meta charset="UTF-8">
<title>Fruitables - Product Admin</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
</head>
<body>
	<%@ include file="/common/admin/header.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col">
				<p class="text-white mt-5 mb-5">
					Chào mừng, <b>${sessionScope.currentUser.username }</b>
				</p>
			</div>
		</div>
		<!-- row -->
		<div class="row tm-content-row">

			<div class="col-12 tm-block-col">
				<div
					class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
					<h2 class="tm-block-title">DANH SÁCH ĐƠN HÀNG</h2>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">MÃ ĐƠN HÀNG</th>
								<th scope="col">TRẠNG THÁI</th>
								<th scope="col">NGƯỜI ĐẶT</th>
								<th scope="col">EMAIL</th>
								<th scope="col">TỔNG TIỀN</th>
								<th scope="col">NGÀY ĐẶT</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${cartDetailList }" var="cartdetails">
								<tr>
									<th scope="row" height="20px" width="170"><b>${cartdetails.id }</b></th>
									<td>
										<div class="tm-status-circle pending"></div>Đang chờ
									</td>
									<td><b>${cartdetails.buyer }</b></td>
									<td><b>${cartdetails.email }</b></td>
									<td><b>${cartdetails.sumToTal }</b></td>
									<td><fmt:formatDate value="${cartdetails.orderDate}" pattern="dd-MM-yyyy" /></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/common/admin/footer.jsp"%>
	<%@ include file="/common/admin/lastBodyScript.jsp"%>
</body>
</html>