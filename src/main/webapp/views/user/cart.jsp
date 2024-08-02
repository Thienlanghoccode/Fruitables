<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade come-from-modal right" id="cartModal"
	tabindex="-1" aria-labelledby="cartModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-right modal-lg">
		<div class="modal-content">
			<c:set var="sumTotal" value="0" />
			<div class="modal-header">
				<h5 class="modal-title" id="cartModalLabel">Shopping Cart</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<c:choose>
				<c:when test="${not empty sessionScope.cart }">
					<div class="modal-body">
						<div class="container">
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
										<c:forEach items="${sessionScope.cart}" var="map">
											<c:set var="quantity" value="${map.value.quantity }" />
											<c:set var="price" value="${map.value.product.price }" />
											<c:set var="total" value="${quantity * price}" />
											<c:set var="sumTotal" value="${sumTotal + total}" />
											<tr id="productID_'${map.value.product.id }'">
												<td><img
													src="<c:url value='templates/user/img/${map.value.product.image }' />"
													class="img-fluid rounded-circle"
													style="width: 80px; height: 80px" alt="" /></td>
												<td>
													<p class="mb-0">${map.value.product.name }</p>
												</td>
												<td>
													<p class="mb-0">${price }$</p>
												</td>
												<td class="text-center">
													<div class="d-flex justify-content-center">
														<div class="input-group quantity" style="width: 100px">
															<input type="text"
																class="form-control form-control-sm text-center border-0"
																value="${quantity }" />
														</div>
													</div>
												</td>
												<td>
													<p class="mb-0">${total }$</p>
												</td>
												<td>
													<button class="btn btn-sm rounded-circle bg-light border"
														onclick="deleteCart('${map.value.product.id}')">
														<i class="fa fa-times text-danger"></i>
													</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="text-end mt-3 modal-header d-flex justify-content-end">
						<h5 class="modal-title" id="cartModalLabel"
							style="font-family:; font-family: 'Font Awesome 5 Brands'; margin-right: 30%; font-weight: bold;">TỔNG
							TIỀN: ${sumTotal } $</h5>
						<button class="btn btn-secondary" style="margin: 0px 20px;"
						 onclick="window.location.href='http://localhost:8080/VegetableStoreManager/checkout?action=views'">
							Xem Đơn Hàng</button>
						<button class="btn btn-primary"
						onclick="window.location.href='http://localhost:8080/VegetableStoreManager/order'"
						>Thanh Toán</button>
					</div>
				</c:when>
				<c:otherwise>
					<div class="text-end mt-3 modal-header d-flex justify-content-end">
						<button class="btn btn-primary" data-bs-dismiss="modal">TIẾP TỤC MUA SẮM</button>
					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
</div>

<script>
	function deleteCart(productId) {
		$.ajax({
			url : '/VegetableStoreManager/cart?action=delete&pID=' + productId,
			success : function(data) {
				// Xử lý khi xóa thành công
				$('#productID_' + productId).remove();
				window.location.reload();
			},
			error : function() {
				// Xử lý khi có lỗi
				alert("Oops! Try again.");
			}
		});
	}
</script>