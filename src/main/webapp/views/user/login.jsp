<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/headlink.jsp"%>
<%@ include file="/common/loginregisterlink.jsp"%>
<meta charset="UTF-8">
<title>Login</title>
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
                                    <div class="signup-image">
                                        <figure><img src="<c:url value='templates/login_register/images/singin-image.png' />" alt="sing in image" ></figure>
                                        <a href="<c:url value='register'/>" class="signup-image-link">CHƯA CÓ TÀI KHOẢN ?</a>
                                    </div>
                                    <div class="signup-form">
                                        <h2 class="form-title">ĐĂNG NHẬP</h2>
                                        <form method="POST" action="login" class="register-form" id="register-form">
                                            <div class="form-group">
                                                <label for="email"><i class="zmdi material-icons-name zmdi-account"></i></label>
                                                <input type="text" name="username" id="username" placeholder="Tài Khoản Của Bạn"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                                <input type="password" name="password" id="password" placeholder="Nhập Mật Khẩu"/>
                                            </div>
                                            <div class="form-group form-button">
                                                <input type="submit" name="signin" id="signin" class="form-submit" value="Đăng Nhập" /> 
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </section>
                	</div>
                </div>
            </div>
        </div>
	
	
	<%@ include file="/common/footer.jsp" %>
	<script src="<c:url value='templates/login_register/vendor/jquery/jquery.min.js' />"></script>
	<%@ include file="/common/lastBodyScript.jsp" %>
</body>
</html>