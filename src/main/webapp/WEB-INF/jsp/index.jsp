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
    <body>
        <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var= "isUSer"/>
        <security:authorize access="hasAnyRole('ROLE_ADMIN')" var= "isADmin"/>
        <p>${auth.toString()}</p>
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
         <section class="top-letest-product-section">
                            <div class="container">
                                <div class="section-title">
                                    <h2>САМЫЕ ПОПУЛЯРНЫЕ КНИГИ</h2>
                                </div>
                                <%Map<String,Object> models = (Map<String, Object>) request.getAttribute("models");
                                    List<Book> popBooks= (List<Book>) models.get("popularBooks");
                                    if(popBooks.size()!=0) {
                                        for (Book b : popBooks)
                                        {
                                        out.println("<div class='product-item'>");
                                            out.println("<div class='pi-pic'>\n" +
                                                "<span>" + b.getFullNameOfAuthor() + "</span>\n" +
                                                "<h2 class='bookname'>" + b.getName() + "</h2>\n" +
                                                "<p>" +b.getDescription()+ "</p>\n"+
                                            "</div>\n" +
                                            "<div class='pi-links'>\n" +
                                                "<a class='addbasket' href='basket/add/"+b.getId()+"'/>Добавить в корзину</a>\n" +
                                                "<security:authorize access=\"hasRole('ROLE_ADMIN')\">" +
                                                    "</br><a class='addbasket' href='book/edit/"+b.getId()+"'/>Изменить книгу</a>\n" +
                                                    "</br><a class='addbasket' href='book/delete/"+b.getId()+"'/>Удалить книгу</a>\n" +
                                                "</security:authorize>" +
                                             "</div>\n" +
                                            "<div class='pi-text'>\n" +
                                                "<h6 class='price'>"+b.getCout()+"</h6>\n" +
                                            "</div>\n"+
                                        "</div>\n");
                                        }
                                    }%>
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
                                                "<h2 class='bookname'>" + b.getName() + "</h2>\n" +
                                                "<p>" +b.getDescription()+ "</p>\n"+
                                                "</div>\n" +
                                                "<div class='pi-links'>\n" +
                                                "<a class='addbasket' href='basket/add/"+b.getId()+"'/>Добавить в корзину</a>\n" +
                                                "</br><a class='addbasket' href='book/edit/"+b.getId()+"'/>Изменить книгу</a>\n" +
                                                "</br><a class='addbasket' href='book/delete/"+b.getId()+"'/>Удалить книгу</a>\n" +
                                                "</div>\n" +
                                                "<div class='pi-text'>\n" +
                                                "<h6 class='price'>"+b.getCout()+"</h6>\n" +
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
                                                "<h2 class='bookname'>" + b.getName() + "</h2>\n" +
                                                "<p>" +b.getDescription()+ "</p>\n"+
                                                "</div>\n" +
                                                "<div class='pi-links'>\n" +
                                                "<a class='addbasket' href='basket/add/"+b.getId()+"'/>Добавить в корзину</a>\n" +
                                                "</br><a class='addbasket' href='book/edit/"+b.getId()+"'/>Изменить книгу</a>\n" +
                                                "</br><a class='addbasket' href='book/delete/"+b.getId()+"'/>Удалить книгу</a>\n" +
                                                "</div>\n" +
                                                "<div class='pi-text'>\n" +
                                                "<h6 class='price'>"+b.getCout()+"</h6>\n" +
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
                                                "<h2 class='bookname'>" + b.getName() + "</h2>\n" +
                                                "<p>" +b.getDescription()+ "</p>\n"+
                                                "</div>\n" +
                                                "<div class='pi-links'>\n" +
                                                "<a class='addbasket' href='basket/add/"+b.getId()+"'/>Добавить в корзину</a>\n" +
                                                "</br><a class='addbasket' href='book/edit/"+b.getId()+"'/>Изменить книгу</a>\n" +
                                                "</br><a class='addbasket' href='book/delete/"+b.getId()+"'/>Удалить книгу</a>\n" +
                                                "</div>\n" +
                                                "<div class='pi-text'>\n" +
                                                "<h6 class='price'>"+b.getCout()+"</h6>\n" +
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
                                        out.println("<div class='product-item'>");
                                        out.println("<div class='pi-pic'>\n" +
                                                "<span>" + b.getFullNameOfAuthor() + "</span>\n" +
                                                "<h2 class='bookname'>" + b.getName() + "</h2>\n" +
                                                "<p>" +b.getDescription()+ "</p>\n"+
                                                "</div>\n" +
                                                "<div class='pi-links'>\n" +
                                                "<a class='addbasket' href='basket/add/"+b.getId()+"'/>Добавить в корзину</a>\n" +
                                                "</br><a class='addbasket' href='book/edit/"+b.getId()+"'/>Изменить книгу</a>\n" +
                                                "</br><a class='addbasket' href='book/delete/"+b.getId()+"'/>Удалить книгу</a>\n" +
                                                "</div>\n" +
                                                "<div class='pi-text'>\n" +
                                                "<h6 class='price'>"+b.getCout()+"</h6>\n" +
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
                                        out.println("<div class='product-item'>");
                                        out.println("<div class='pi-pic'>\n" +
                                                "<span>" + b.getFullNameOfAuthor() + "</span>\n" +
                                                "<h2 class='bookname'>" + b.getName() + "</h2>\n" +
                                                "<p>" +b.getDescription()+ "</p>\n"+
                                                "</div>\n" +
                                                "<div class='pi-links'>\n" +
                                                "<a class='addbasket' href='basket/add/"+b.getId()+"'/>Добавить в корзину</a>\n" +
                                                "</br><a class='addbasket' href='book/edit/"+b.getId()+"'/>Изменить книгу</a>\n" +
                                                "</br><a class='addbasket' href='book/delete/"+b.getId()+"'/>Удалить книгу</a>\n" +
                                                "</div>\n" +
                                                "<div class='pi-text'>\n" +
                                                "<h6 class='price'>"+b.getCout()+"</h6>\n" +
                                                "</div>\n"+
                                                "</div>\n");
                                    }
                                }%>
                            </div></div></section>

        <script>
            function editLinksSearch(searchvalue){
                var form = document.forms.search;
                form.action = "/springMVC_war_exploded/search/"+searchvalue;
            }
        </script>
</body>
</html>