<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="models.Basket" %>
<%@ page import="java.util.List" %>
<%@ page import="models.BasketParagraphBooked" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 19.11.2019
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>>Книжный Магазин - Заказы</title>
    <style><%@include file="/css/bootstrap.min.css"%></style>
    <style><%@include file="/css/myStile.css"%></style>
    <style><%@include file="/css/font-awesome.min.css"%></style>
    <style><%@include file="/css/flaticon.css"%></style>
    <style><%@include file="/css/slicknav.min.css"%></style>
    <style><%@include file="/css/jquery-ui.min.css"%></style>
    <style><%@include file="/css/owl.carousel.min.css"%></style>
    <style><%@include file="/css/animate.css"%></style>
    <style><%@include file="/css/style.css"%></style>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body><security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var= "isUSer"/>
<security:authorize access="hasAnyRole('ROLE_ADMIN')" var= "isADmin"/>
<header class="header-section">
    <div class="header-top">
        <div class="container">
            <div class="row">
                <div class="col-lg-2 text-center text-lg-left">
                </div>
                <div class="col-xl-6 col-lg-5">
                    <form class="header-search-form" id='search' method='get' class="search">
                        <input type="search" class="searchString" id='valueOfSearch' onkeyup="editLinksSearch(this.value)" name="q" placeholder="Поиск в Книжном магазине...">
                    </form>
                </div>
                <div class="col-xl-4 col-lg-5">
                    <div class="user-panel">
                        <div class="up-item">
                            <c:if test="${not isUSer}">
                                <a href="<c:url value='/user/login'/>">Войти</a> или <a href="<c:url value='/user/register'/>">Зарегистрироваться</a>
                            </c:if>
                            <c:if test="${isUSer}">
                                <a href="<c:url value='/logout'/>">Выйти</a>
                            </c:if>
                        </div>
                        <div class="up-item">
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
                <li><a href="<c:url value='/basket/orders'/>">Мои заказы</a></li>
                <c:if test="${isADmin}">
                    <li><a href="<c:url value='//user'/>">Кабинет администратора</a></li>
                </c:if>
            </ul>
        </div>
    </nav>
</header>
<%
    Map<String,Object> map = (Map<String,Object>) request.getAttribute("map");
    List<BasketParagraphBooked> ordersParagraphs = (List<BasketParagraphBooked>) map.get("ordersParagraphs");
    List<Basket>orders = (List<Basket>) map.get("orders");
    try {
        for (Basket o : orders) {
            out.println("<div style='border: 2px solid red'>");
            for (BasketParagraphBooked bp : ordersParagraphs) {
                if (bp.getBasketId()==o.getId())
                    out.println("<p>" + bp.getBook().getName() + " В количестве: "+ bp.getNumberOfBooks() + " </p>");
            }
            out.println("Общая сумма заказа: " + o.getSum() + "p. ");
            out.println("Дата заказа: " + o.getDateOfSale().toString());
            out.println("</div>");
        }
    }
    catch(NullPointerException e){
        out.println("Кажется, Вы ничего у нас не заказывали! Исправить это можно в <a href='/springMVC_war_exploded/basket/get'>Корзине</a>!");
    }
%>
</body>
</html>
