<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <jsp:include page="layout/head.jsp"/>

    <body>
        <div class="back-to-home rounded d-none d-sm-block">
            <a href="index.jsp" class="btn btn-icon btn-primary"><i data-feather="home" class="icons"></i></a>
        </div>

        <!-- Hero Start -->
        <section class="bg-home d-flex bg-light align-items-center">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-md-8">
                        <div class="card login-page bg-white shadow mt-4 rounded border-0">
                            <div class="card-body">
                                <h4 class="text-center">Đăng nhập</h4> 
                                <%
                                    Cookie[] cookies = request.getCookies();
                                    String emailValue = "";
                                    String passwordValue = "";
                                    if (cookies != null) {
                                        for (Cookie cookie : cookies) {
                                            if (cookie.getName().equals("email")) {
                                                emailValue = cookie.getValue();
                                            } else if (cookie.getName().equals("password")) {
                                                passwordValue = cookie.getValue();
                                            }
                                        }
                                    }
                                %>
                                <form action="checklogin" method="POST" onSubmit="document.getElementById('rememberForm').submit();">
                                    <p style="color: red; align-content: center;">
                                        ${requestScope.loginError}
                                    </p>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Email <span class="text-danger">*</span></label>
                                                <input type="email" oninvalid="CheckEmail(this);" oninput="CheckEmail(this);" class="form-control" placeholder="Email" name="email" value="<%= emailValue %>" required="">
                                            </div>
                                        </div>

                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Mật khẩu <span class="text-danger">*</span></label>
                                                <input type="password" oninvalid="CheckPassword(this);" oninput="CheckPassword(this);" class="form-control" name="password" placeholder="Password" value="<%= passwordValue %>" required="">
                                            </div>
                                        </div>

                                        <div class="col-lg-12">
                                            <div class="d-flex justify-content-between">
                                                <div class="mb-3">
                                                    <div class="form-check">
                                                        <input class="form-check-input align-middle" type="checkbox" name="remember" id="remember-check">
                                                        <label class="form-check-label" for="remember-check">Lưu tài khoản</label>
                                                    </div>
                                                </div>
                                                <a href="recover.jsp" class="text-dark h6 mb-0">Quên mật khẩu ?</a>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 mb-0">
                                            <div class="d-grid">
                                                <button class="btn btn-primary" id="submit" type="submit">Đăng nhập</button>
                                            </div>
                                        </div>
                                        <div class="col-12 text-center">
                                            <p class="mb-0 mt-3"><small class="text-dark me-2">Chưa có tài khoản ?</small> <a href="register.jsp" class="text-dark fw-bold">Đăng ký</a></p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
        </section>

        <form id="rememberForm" action="remember" method="POST">
            <input type="hidden" name="email" value="${param.email}">
            <input type="hidden" name="password" value="${param.password}">
            <input type="hidden" name="remember" value="${param.remember}">
        </form>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>

    </body>

</html>