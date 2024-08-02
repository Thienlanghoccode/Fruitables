<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/headlink.jsp"%>
<%@ include file="/common/loginregisterlink.jsp"%>
<meta charset="UTF-8">
<title>Register</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
</head>
<body>
	<%@ include file="/common/header.jsp" %>
	
	
	<div class="container-fluid py-12 mb-12 hero-header">
            <div class="container py-12">
                <div class="row g-12 align-items-center">
                    <div class="main">

                        <!-- Sign up form -->
                        <section class="signup">
                            <div class="container">
                                <div class="signup-content d-flex">
                                    <div class="signup-form">
                                        <h2 class="form-title">ĐĂNG KÝ TÀI KHOẢN</h2>
                                        <form method="POST" class="register-form" id="register-form">
                                            <div class="form-group">
                                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                                <input type="text" name="username" id="username" placeholder="Tên Tài Khoản"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                                <input type="email" name="email" id="email" placeholder="Email Của Bạn"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                                <input type="password" name="password" id="password" placeholder="Nhập Mật Khẩu"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                                <input type="password" name="re_pass" id="re_pass" placeholder="Xác Nhận Mật Khẩu"/>
                                            </div>
                                            <div class="form-group form-button">
                                                <input type="submit" name="signup" id="signup" class="form-submit" value="Đăng Ký" /> 
                                            </div>
                                        </form>
                                    </div>
                                    <div class="signup-image">
                                        <figure><img src="<c:url value='templates/login_register/images/signup-image.png' />" alt="sing up image" ></figure>
                                        <a href="<c:url value ='login'/>" class="signup-image-link">TÔI ĐÃ CÓ TÀI KHOẢN ?</a>
                                    </div>
                                </div>
                            </div>
                        </section>
                </div>
            </div>
        </div>

	
	
	<%@ include file="/common/footer.jsp" %>
	<%@ include file="/common/lastBodyScript.jsp" %>
	<script src="<c:url value='templates/login_register/vendor/jquery/jquery.min.js' />"></script>
</body>
</html>