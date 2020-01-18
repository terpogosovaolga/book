<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: olyao
  Date: 17.01.2020
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var= "isUSer"/>
<header class="header-section">
    <div class="header-top">
        <div class="container">
            <div class="row">
                <div class="col-lg-2 text-center text-lg-left">
                    <a href="<c:url value=''/>" class="site-logo">
                        <img src="C:\Users\olyao\IdeaProjects\logo.png" alt="">
                    </a>
                </div>
                <div class="col-xl-6 col-lg-5">
                    <form class="header-search-form">
                        <input type="text" placeholder="Поиск в Книжном магазине...">
                        <button><i class="flaticon-search"></i></button>
                    </form>
                </div>
                <div class="col-xl-4 col-lg-5">
                    <div class="user-panel">
                        <div class="up-item">
                            <i class="flaticon-profile"></i>
                            <c:if test="${not isUSer}">
                                <a href="<c:url value='/user/login'/>">Войти</a> или <a href="<c:url value='/user/register'/>">Зарегистрироваться</a>
                            </c:if>
                            <c:if test="${isUSer}">
                                <a href="<c:url value='/logout'/>">Выйти</a>
                            </c:if>
                        </div>
                        <div class="up-item">
                            <div class="shopping-card">
                                <i class="flaticon-bag"></i>
                                <span>0</span>
                            </div>
                            <a href="<c:url value='/basket'/>">Корзина</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <nav class="main-navbar">
        <div class="container">
            <ul class="main-menu">
                <li><a href="<c:url value=''/>">Главная</a></li>
                <li><a href="<c:url value='//catalog'/>">Каталог</a></li>
                <c:if test="${isUSer}">
                    <li><a href="<c:url value='//user'/>">Моя страница</a></li>
                </c:if>
                <li><a href="<c:url value='/basket/orders'/>">Мои заказы</a></li>
            </ul>
        </div>
    </nav>
</header>
</html>
