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
<form id='search' method='get' class="search">
    <p><input type="search" class="searchString" id='valueOfSearch' onkeyup="editLinksSearch(this.value)" name="q" placeholder="Знаете, какую книгу ищете? Введите ее название!"/>
        <input class='submit' type="submit" value="Найти"/></p>
</form>

<%
    Map<String, String> pastParams = (Map<String, String>) session.getAttribute("params");
    try {
        for (String k : pastParams.keySet()) {
            //out.println("<p>");
            out.println("<div class='pastParams'><a href='/springMVC_war_exploded/deleteParam/"+k+"'>" + pastParams.get(k) +"</a></div>");
           // out.println("</p>");
        }
    }
    catch(NullPointerException e){

    }
%>

<!--<div class="searchParams">
    <div class="searchParams_component">
        <p class="category">Жанр</p>
        <div class="searchParams_component_a"><a href="<c:url value='/search/genre/novel'/>">романы</a></div>
        <div class="searchParams_component_a"><a href="<c:url value='/search/genre/poem'/>">поэзия и стихи</a></div>
        <div class="searchParams_component_a"><a href="<c:url value='/search/genre/detective'/>">детективы</a></div>
        <div class="searchParams_component_a"><a href="<c:url value='/search/genre/tale'/>">сказки</a></div>
    </div>
    <div class="searchParams_component">
        <p class="category">Издательство</p>
        <div class="searchParams_component_a"><a href="<c:url value='/search/publisher/eksmo'/>">Эксмо</a></div>
        <div class="searchParams_component_a"><a href="<c:url value='/search/publisher/azbuka'/>">Азбука</a></div>
        <div class="searchParams_component_a"><a href="<c:url value='/search/publisher/prosveshenie'/>">Просвещение</a></div>
        <div class="searchParams_component_a"><a href="<c:url value='/search/publisher/communizm'/>">Коммунизм</a></div>
    </div>
    <div class="searchParams_component">
        <p class="category">Язык</p>
        <div class="searchParams_component_a"><a href="<c:url value='/search/language/russian'/>">Русский</a></div>
        <div class="searchParams_component_a"><a href="<c:url value='/search/language/english'/>">Английский</a></div>
        <div class="searchParams_component_a"><a href="<c:url value='/search/language/untranslated'/>">Без перевода</a></div>
    </div>
    <div class="searchParams_component">
        <p class="category">Цена</p>
        <div class="searchParams_component_a"><a href="<c:url value='/search/price/300'/>">Не дороже 300 рублей</a></div>
        <div class="searchParams_component_a"><a href="<c:url value='/search/price/700'/>">Не дороже 700 рублей</a></div>
        <div class="searchParams_component_a"><a href="<c:url value='/search/price/1000'/>">Не дороже 1000 рублей</a></div>
    </div>
</div>-->


    <%
        List<Book> books = (List<Book>) request.getAttribute("result");
        for (Book b:books) {
            out.println("<a href='book/" + b.getId() + "'><div class='book'>");
            out.println("<p class='booksAuthor'>" + b.getFullNameOfAuthor() + "</p>");
            out.println("<p class='bookName'> " + b.getName() + "</p>");
            out.println("<div class='attributes'>");
            out.println("<div class='one_attr'><label>Год написания: </label><span class='yearOfWriting attr'>" + b.getYearOfWriting() + "г. </span></div>");
            out.println("<div class='one_attr'><label>Издательство: </label><span class='publisher attr'>" + b.getPublisher() + "</span></div>");
            out.println("<div class='one_attr'><label>Год издательства: </label><span class='yearOfPublishing attr'>" + b.getYearOfPublishing() + " г.</span></div>");
            out.println("<div class='one_attr'><label>Цена: </label><span class='price attr'>" + b.getCout() + " Р.</span></div>");
            out.println("</div>");
            out.println("<div class='attributes'>");
            out.println("<p class='desc'>" + b.getDescription() + "</p>");
            out.println("</div>");
            out.println("</a>");
            out.println("<c:if test='${isUSer}'>");
            out.println("<a class='editBook' href='/springMVC_war_exploded/admin/editBook/" + b.getId() + "'>Изменить книгу</a>");
            out.println("</c:if>");
            out.println("</div>");
        }%>
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
