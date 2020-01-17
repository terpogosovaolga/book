<%@ page import="models.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
        <title>Книжный Магазин - Главная</title>
        <meta charset="utf-8">
        <link href="img/favicon.ico" rel="shortcut icon"/>
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans:300,300i,400,400i,700,700i" rel="stylesheet">
        <!-- Stylesheets -->
        <style><%@include file="/css/bootstrap.min.css"%></style>
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
    <body>
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
                                        <a href="<c:url value='/user/login'/>">Войти</a> или <a href="#">Зарегистрироваться</a>
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
        <!--<section class="hero-section">
             <div class="hero-slider owl-carousel">
                 <div class="hs-item set-bg" data-setbg="C:\Users\olyao\IdeaProjects\myata.jpg">
                      <div class="container">
                            <div class="row">
                                <div class="col-xl-6 col-lg-7 text-white">
                                    <span>Новинка</span>
                                    <h2>Мятная сказка</h2>
                                    <p>Бестселлер российской современной литературы</p>
                                    <a href="#" class="site-btn sb-line">Просмотр</a>
                                    <a href="#" class="site-btn sb-white">Добавить в корзину</a>
                                </div>
                            </div>
                            <div class="offer-card text-white">
                                <span>всего</span>
                                <h2>490Р</h2>
                                <p>КУПИТЬ СЕЙЧАС</p>
                            </div>
                       </div>
                 </div>
                 <div class="hs-item set-bg" data-setbg="C:\Users\olyao\IdeaProjects\running.jpg">
                       <div class="container">
                            <div class="row">
                                 <div class="col-xl-6 col-lg-7 text-white">
                                     <span>Новинка</span>
                                     <h2>Бегущий за ветром</h2>
                                     <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Quis ipsum sus-pendisse ultrices gravida. Risus commodo viverra maecenas accumsan lacus vel facilisis. </p>
                                     <a href="#" class="site-btn sb-line">Просмотр</a>
                                     <a href="#" class="site-btn sb-white">Добавить в корзину</a>
                                 </div>
                            </div>
                            <div class="offer-card text-white">
                                <span>всего</span>
                                <h2>490Р</h2>
                                <p>КУПИТЬ СЕЙЧАС</p>
                            </div>
                       </div>
                 </div>
             </div>
             <div class="container">
                 <div class="slide-num-holder" id="snh-1"></div>
             </div>
         </section>-->
         <section class="top-letest-product-section">
                            <div class="container">
                                <div class="section-title">
                                    <h2>САМЫЕ ПОПУЛЯРНЫЕ КНИГИ</h2>
                                </div>
                                <div class="product-slider owl-carousel">
                                <%Map<String,Object> models = (Map<String, Object>) request.getAttribute("models");
                                    List<Book> popBooks= (List<Book>) models.get("popularBooks");
                                    if(popBooks.size()!=0) {
                                        for (Book b : popBooks)
                                        {
                                        out.println("<div class='product-item'>");
                                            out.println("<div class='pi-pic'>\n" +
                                                "<span>" + b.getFullNameOfAuthor() + "</span>\n" +
                                                "<h2>" + b.getName() + "</h2>\n" +
                                                "<p>" +b.getDescription()+ "</p>\n"+
                                            "</div>\n" +
                                            "<div class='pi-links'>\n" +
                                                "<a href='<c:url value='basket/add/"+b.getId()+"'/>' class='add-card'><i class='flaticon-bag'></i><span>Добавить в корзину</span></a>\n" +
                                                "<a href='<c:url value='basket/add/"+b.getId()+"'/>' class='wishlist-btn'><i class='flaticon-heart'></i></a>\n" +
                                            "</div>\n" +
                                            "<div class='pi-text'>\n" +
                                                "<h6>"+b.getCout()+"</h6>\n" +
                                            "</div>\n"+
                                        "</div>\n");
                                        }
                                    }%>
                                 </div>
                            </div>
         </section>
         <section class="top-letest-product-section">
                            <div class="container">
                            <div class="section-title">
                            <h2>НОВОЕ ПОСТУПЛЕНИЕ</h2>
                            </div>
                            <div class="product-slider owl-carousel">
                            <%
                                List<Book> newArrivals = (List<Book>) models.get("newArrivals");

                                if(newArrivals.size()!=0) {
                                    for (Book b : newArrivals)
                                    {

                                        out.println("<div class='product-item'>");
                                        out.println("<div class='pi-pic'>\n" +
                                                "<span>" + b.getFullNameOfAuthor() + "</span>\n" +
                                                "<h2>" + b.getName() + "</h2>\n" +
                                                "<p>" +b.getDescription()+ "</p>\n"+
                                                "</div>\n" +
                                                "<div class='pi-links'>\n" +
                                                "<a href='<c:url value='basket/add/"+b.getId()+"'/>' class='add-card'><i class='flaticon-bag'></i><span>Добавить в корзину</span></a>\n" +
                                                "<a href='<c:url value='basket/add/"+b.getId()+"'/>' class='wishlist-btn'><i class='flaticon-heart'></i></a>\n" +
                                                "</div>\n" +
                                                "<div class='pi-text'>\n" +
                                                "<h6>"+b.getCout()+"</h6>\n" +
                                                "</div>\n"+
                                                "</div>\n");
                                    }
                                }%>
                            </div></div></section>
                            <section class="top-letest-product-section">
                            <div class="container">
                            <div class="section-title">
                                <h2>ЛУЧШИЕ ШЕДЕВРЫ ПОЭЗИИ</h2>
                            </div>
                            <div class="product-slider owl-carousel">
                            <%
                                List<Book> poems = (List<Book>) models.get("poems");

                                if(poems.size()!=0) {
                                    for (Book b : poems)
                                    {

                                        out.println("<div class='product-item'>");
                                        out.println("<div class='pi-pic'>\n" +
                                                "<span>" + b.getFullNameOfAuthor() + "</span>\n" +
                                                "<h2>" + b.getName() + "</h2>\n" +
                                                "<p>" +b.getDescription()+ "</p>\n"+
                                                "</div>\n" +
                                                "<div class='pi-links'>\n" +
                                                "<a href='<c:url value='basket/add/"+b.getId()+"'/>' class='add-card'><i class='flaticon-bag'></i><span>Добавить в корзину</span></a>\n" +
                                                "<a href='<c:url value='basket/add/"+b.getId()+"'/>' class='wishlist-btn'><i class='flaticon-heart'></i></a>\n" +
                                                "</div>\n" +
                                                "<div class='pi-text'>\n" +
                                                "<h6>"+b.getCout()+"</h6>\n" +
                                                "</div>\n"+
                                                "</div>\n");
                                    }
                                }%>
                            </div></div></section>
                            <section class="top-letest-product-section">
                            <div class="container">
                            <div class="section-title">
                            <h2>ЛУЧШИЕ РОМАНЫ</h2>
                            </div>
                            <div class="product-slider owl-carousel">
                            <%
                                List<Book> novels = (List<Book>) models.get("novels");

                                if(novels.size()!=0) {
                                    for (Book b : novels)
                                    {
                                        out.println("<div class='product-item'>");
                                        out.println("<div class='pi-pic'>\n" +
                                                "<span>" + b.getFullNameOfAuthor() + "</span>\n" +
                                                "<h2>" + b.getName() + "</h2>\n" +
                                                "<p>" +b.getDescription()+ "</p>\n"+
                                                "</div>\n" +
                                                "<div class='pi-links'>\n" +
                                                "<a href='<c:url value='basket/add/"+b.getId()+"'/>' class='add-card'><i class='flaticon-bag'></i><span>Добавить в корзину</span></a>\n" +
                                                "<a href='<c:url value='basket/add/"+b.getId()+"'/>' class='wishlist-btn'><i class='flaticon-heart'></i></a>\n" +
                                                "</div>\n" +
                                                "<div class='pi-text'>\n" +
                                                "<h6>"+b.getCout()+"</h6>\n" +
                                                "</div>\n"+
                                                "</div>\n");
                                    }
                                }%>
                            </div></div></section>
                            <section class="top-letest-product-section">
                            <div class="container">
                            <div class="section-title">
                            <h2>МЕНЬШЕ 200 СТРАНИЦ</h2>
                            </div>
                            <div class="product-slider owl-carousel">
                            <%
                                List<Book> smalls = (List<Book>) models.get("small");
                                if(smalls.size()!=0) {
                                    for (Book b : smalls)
                                    {

                                        out.println("<div class='product-item'");
                                        out.println("<div class='pi-pic'>\n" +
                                                "<span>" + b.getFullNameOfAuthor() + "</span>\n" +
                                                "<h2>" + b.getName() + "</h2>\n" +
                                                "<p>" +b.getDescription()+ "</p>\n"+
                                                "</div>\n" +
                                                "<div class='pi-links'>\n" +
                                                "<a href='<c:url value='basket/add/"+b.getId()+"'/>' class='add-card'><i class='flaticon-bag'></i><span>Добавить в корзину</span></a>\n" +
                                                "<a href='<c:url value='basket/add/"+b.getId()+"'/>' class='wishlist-btn'><i class='flaticon-heart'></i></a>\n" +
                                                "</div>\n" +
                                                "<div class='pi-text'>\n" +
                                                "<h6>"+b.getCout()+"</h6>\n" +
                                                "</div>\n"+
                                                "</div>\n");
                                    }
                                }%>
                            </div></div></section>
                            <section class="top-letest-product-section">
                            <div class="container">
                            <div class="section-title">
                            <h2>ЛУЧШИЕ ПРОИЗВЕДЕНИЯ ЛЬВА ТОЛСТОГО</h2>
                            </div>
                            <div class="product-slider owl-carousel">
                            <%
                                List<Book> tolstoy = (List<Book>) models.get("tolstoy");

                                if(tolstoy.size()!=0) {
                                    for (Book b : tolstoy)
                                    {

                                        out.println("<div class='product-item' onclick='click_book("+b.getId()+")'>");
                                        out.println("<div class='pi-pic'>\n" +
                                                "<span>" + b.getFullNameOfAuthor() + "</span>\n" +
                                                "<h2>" + b.getName() + "</h2>\n" +
                                                "<p>" +b.getDescription()+ "</p>\n"+
                                                "</div>\n" +
                                                "<div class='pi-links'>\n" +
                                                "<a href='<c:url value='basket/add/"+b.getId()+"'/>' class='add-card'><i class='flaticon-bag'></i><span>Добавить в корзину</span></a>\n" +
                                                "<a href='<c:url value='basket/add/"+b.getId()+"'/>' class='wishlist-btn'><i class='flaticon-heart'></i></a>\n" +
                                                "</div>\n" +
                                                "<div class='pi-text'>\n" +
                                                "<h6>"+b.getCout()+"</h6>\n" +
                                                "</div>\n"+
                                                "</div>\n");
                                    }
                                }%>
                            </div></div></section>
</body>
</html>