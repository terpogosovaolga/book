<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page import="models.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.security.Key" %>
<%@ page import="models.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 22.10.2019
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
    <style><%@include file="/css/myStile.css"%></style>

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
                            <div class="shopping-card">
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
                <li><a href="<c:url value='/'/>">Главная</a></li>
                <li><a href="<c:url value='//catalog'/>">Каталог</a></li>
                <c:if test="${isUSer}">
                    <li><a href="<c:url value='//user'/>">Моя страница</a></li>
                </c:if>
                <li><a href="<c:url value='/basket/orders'/>">Мои заказы</a></li>
            </ul>
        </div>
    </nav>
</header>

<%
    Map<String, String> pastParams = (Map<String, String>) session.getAttribute("params");
    try {
        for (String k : pastParams.keySet()) {
            out.println("<div class='pastParams'><a href='/springMVC_war_exploded/deleteParam/"+k+"'>" + pastParams.get(k) +"</a></div>");
        }
    }
    catch(NullPointerException e){

    }
%>

<section class="top-letest-product-section">
    <div class="container">
        <ul class="product-filter-menu">
            <li><a href="<c:url value='/search/genre/novel'/>">РОМАНЫ</a></li>
            <li><a href="<c:url value='/search/genre/poem'/>">ПОЭЗИЯ</a></li>
            <li><a href="<c:url value='/search/genre/detective'/>">ДЕТЕКТИВЫ</a></li>
            <li><a href="<c:url value='/search/genre/tale'/>">СКАЗКИ</a></li>
            <li><a href="<c:url value='/search/cout/300'/>">НЕ ДОРОЖЕ 300</a></li>
            <li><a href="<c:url value='/search/cout/800'/>">НЕ ДОРОЖЕ 800</a></li>
        </ul>

<%
    List<Book> books = (List<Book>) request.getAttribute("result");
            if(books.size()!=0) {
                for (Book b : books)
                {
                    out.println("<div class='product-item'>");
                    out.println("<a href='book/"+b.getId()+"'><div class='pi-pic'>\n" +
                            "<span>" + b.getFullNameOfAuthor() + "</span>\n" +
                            "<h2 class='bookname'>" + b.getName() + "</h2>\n" +
                            "<p>" +b.getDescription()+ "</p>\n"+
                            "</div></a>\n" +
                            "<div class='attributes'>\n" +
                            "<p><b>Издательство:</b> "+ b.getPublisher() +", " +b.getYearOfPublishing()+ "</p>" +
                            "<p><b>Год написания:</b> "+b.getYearOfWriting()+"</p>" +
                            "<p><b>Жанр:</b> "+b.getGenre()+"</p>" +
                            "<p><b>Язык:</b> "+ b.getLanguage() +"</p>" +
                            "<p><b>Количество страниц:</b> "+ b.getCountOfPages() +"</p>\n" +
                            "<p></p>" +
                            "</div>\n"+
                            "<div class='pi-links'>\n" +
                            "<a class='addbasket' href='book/"+b.getId()+"'/>Перейти на страницу книги</a>\n" +
                            "</br><a class='addbasket' href='basket/add/"+b.getId()+"'/>Добавить в корзину</a>\n" +
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
</body>
<script>
    function editLinksSearch(searchvalue){
        var form = document.forms.search;
            form.action = "/springMVC_war_exploded/search/"+searchvalue;
    }

    /*function editLinksWithParams() {
        var form = document.forms.searchWithParams;
         ОБРАБОТКА КОЛ-ВА СТРАНИЦ
        var minNumberOfPages = 0;
        var maxNumberOfPages = -1;
            if (more700==true)
                maxNumberOfPages = -1;
            else if (less700==true)
                maxNumberOfPages = 700;
            else if (less300==true)
                maxNumberOfPages = 300;
            else if (less100==true)
                maxNumberOfPages = 100;

            if (less100 == true)
                minNumberOfPages = 0;
            else if (less300 == true)
                minNumberOfPages = 100;
            else if (less700 == true)
                minNumberOfPages = 300;
            else if (more700 == true)
                minNumberOfPages = 700;
        /* ОБРАБОТКА ЖАНРА
        var genres = [];

    }*/
</script>
</html>
