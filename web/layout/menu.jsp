<%-- 
    Document   : menu
    Created on : Jan 6, 2022, 1:14:27 AM
    Author     : Khuong Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <header id="topnav" class="navigation sticky">
        <div class="container">

            <div>
                <a class="logo" href="index.jsp">
                    <img src="assets/images/bg/fpt.png" height="50" class="l-light" alt="">
                    <img src="assets/images/bg/fpt.png" class="l-dark" height="50" alt="">
                </a>
            </div>

            <div class="menu-extras">
                <div class="menu-item">
                    <a class="navbar-toggle" id="isToggle" onclick="toggleMenu()">
                        <div class="lines">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>
                    </a>
                </div>
            </div>

            <ul class="dropdowns list-inline mb-0">
                <li class="list-inline-item mb-0 ms-1">
                    <div class="dropdown dropdown-primary">
                        <c:if test="${sessionScope.user != null}">
                            <c:if test="${sessionScope.user.avatar_url != 'default'}">
                                <button type="button" class="btn btn-pills btn-soft-primary dropdown-toggle p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <img src="${sessionScope.user.avatar_url}" class="avatar avatar-ex-small rounded-circle" alt="">
                                </button>
                            </c:if>
                            <c:if test="${sessionScope.user.avatar_url == 'default'}">
                                <button type="button" class="btn btn-pills btn-soft-primary dropdown-toggle p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <img src="assets/images/avata.png" class="avatar avatar-ex-small rounded-circle" alt="">
                                </button>
                            </c:if>
                        </c:if>

                        <c:if test="${sessionScope.user == null}">
                            <div class="d-flex justify-content-between">
                                <button class="btn btn-primary flex-grow-1 me-1 p-1" onclick="window.location.href = 'login.jsp'">Login</button>
                                <button class="btn btn-primary flex-grow-1 ms-1 p-1" onclick="window.location.href = 'register.jsp'">Sign Up</button>
                            </div>
                        </c:if>

                        <div class="dropdown-menu dd-menu dropdown-menu-end bg-white shadow border-0 mt-3 py-3" style="min-width: 200px;">
                            <c:if test="${sessionScope.user != null}">
                                <a class="dropdown-item d-flex align-items-center text-" href="#">
                                    <c:if test="${sessionScope.user.avatar_url != 'default'}">
                                        <img src="${sessionScope.user.avatar_url}" class="avatar avatar-md-sm rounded-circle border shadow" alt="">
                                    </c:if>
                                    <c:if test="${sessionScope.user.avatar_url == 'default'}">
                                        <img src="assets/images/avata.png" class="avatar avatar-md-sm rounded-circle border shadow" alt="">
                                    </c:if>
                                    <div class="flex-1 ms-2">
                                        <span class="d-block mb-1">${sessionScope.user.name}</span>
                                    </div>
                                </a>
                            </c:if>
                            <div class="dropdown-divider border-top"></div>
                            <c:if test="${sessionScope.user != null}">
                                <a class="dropdown-item text-" href="profile.jsp"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Tài khoản của tôi</a>
                                    </c:if>
                                    <c:if test="${sessionScope.user != null}">
                                <a class="dropdown-item text-" href="#"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Thay đổi mật khẩu</a>
                                    </c:if>
                                    <c:if test="${sessionScope.user != null}">
                                <a class="dropdown-item text-" href="logout"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Đăng xuất</a>
                                    </c:if>
                                    <c:if test="${sessionScope.user == null}">
                                <a class="dropdown-item text-" href="login.jsp"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Đăng Nhập</a>
                                    </c:if>
                                    <c:if test="${sessionScope.user != null}">
                                        <c:if test="${sessionScope.user.roleID == 1}">
                                    <a class="dropdown-item text-" href="dashboard?action=default"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Quản lý</a>
                                        </c:if>
                                        <c:if test="${sessionScope.user.roleID == 5}">
                                    <a class="dropdown-item text-" href="doctormanage?action=all"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Quản lý</a>
                                        </c:if>
                                        <c:if test="${sessionScope.user.roleID == 4}">
                                    <a class="dropdown-item text-" href="appointmentmanage?action=all"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Quản lý</a>
                                        </c:if>
                                    </c:if>
                        </div>
                    </div>
                </li>

            </ul>

            <div id="navigation" style="margin-left: 300px">

                <ul class="navigation-menu nav-left nav-light"  >
                    <li class="has-submenu">
                        <a href="javascript:void(0)" class="sub-menu-item">Club <span class="dropdown-indicator"></span></a>
                        <ul class="submenu">
                            <li class="has-submenu parent-menu-item">
                            <li><a href="#" class="sub-menu-item">Music club</a></li>
                    </li>
                    <li><a href="#" class="sub-menu-item">Sport Club</a></li>
                    <li><a href="#" class="sub-menu-item">Bussiness club</a></li>
                    <li><a href="#" class="sub-menu-item">English Club</a></li>
                    <li><a href="#" class="sub-menu-item">Street Workout Club</a></li>
                    <li><a href="#" class="sub-menu-item">Hebe Club</a></li>
                    <li><a href="#" class="sub-menu-item"> Basketball Club</a></li>
                    <li><a href="#" class="sub-menu-item">Chess Club</a></li>
                    <li><a href="#" class="sub-menu-item">Gymnastic Club</a></li>
                    <li><a href=#" class="sub-menu-item"> Debate Club</a></li>
                </ul>
                </li>
                <li><a href="#" class="sub-menu-item">Blog</a></li>

                <c:if test="${sessionScope.user != null}">
                    <li><a href="#" class="sub-menu-item">Register Club</a></li>
                    </c:if>

                <c:if test="${sessionScope.user == null}">
                    <li><a href="login.jsp" class="sub-menu-item">Register Club</a></li>
                    </c:if>
                <li><a href="contact.jsp" class="sub-menu-item">About us</a></li>
                </ul>

            </div>
        </div>
    </header>
</html>

