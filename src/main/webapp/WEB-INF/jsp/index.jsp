<%@ page import="classes.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="classes.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <title>Книжный Магазин - Главная</title>
        <meta charset="utf-8">
        <link href="https://fonts.googleapis.com/css?family=Comfortaa|Cormorant+Unicase|Montserrat|Montserrat+Alternates|Open+Sans+Condensed:300|Playfair+Display+SC|Poiret+One&display=swap" rel="stylesheet">
        <style>
            body {
                background-color: #e9e8fa;
            }

            p:hover {
                text-decoration: underline;
            }
            .memberOfMenu {
                display: inline-block;
                width: 24%;
                color: black;
                font-size: 20px;
                font-family: 'Montserrat', sans-serif;
                align: center;
                margin-top: 10px;
                margin-bottom: 10px;
                height: 100%;
                text-align: center;

            }
            .memberOfMenu:hover {
                background: #1b1899;
                text-decoration: underline;
            }
            #this {
                background-color: #1b1899; !important;
            }
            #this:hover {
                background-color: #1a012e;
            }
            .menu {
                background-color: #615fd4;
                height: 100px;
                width: 100%;
                display: flex;
                justify-content: space-between;
            }
            .memberOfMenu a {
                color: white;
                text-decoration: none;
                align: center;
                font-family: 'Montserrat', sans-serif;
            }



            .select {
                display: inline-block;
                background-color: white;
                border: 2px solid #4845ff;
                border-radius: 10px;
                width: 40%;
                margin: 20px;

            }
            .select:hover{
                background-color: #fcbbf6;
                text-display: underline;
            }
            .select_header {
                font-size: 25px;
                margin-left: 10px;
                font-family: 'Cormorant Unicase', serif;
            }
            .select_generalInner {
                display: none;
            }

            .booksAuthor {
                font-size: 20px;
                color: black;
                font-family: 'Open Sans Condensed', sans-serif;
            }

            .bookName {
                font-size: 25px;
                color: #1a012e;
                font-family: 'Playfair Display SC', serif;
            }

            .select_innerDiv {
                background-color: #fcbbf6;
                margin: 10px;
                padding: 10px;
                border: #4845ff;
                border-radius: 5px;
            }
            .select_innerDiv:hover {
                background-color: indianred;
            }
            .select_innerDiv a {
                diplay: inline-block;
                color: #1a012e;
                text-decoration: none;
                align: center;
            }
            .select_innerDiv a:hover {
                color: black;
            }

            .select_button {
                background: #4845ff;
                padding: 7px;
                border-radius: 3px;
            }
            .select_button:hover {
                background-color: #1b1899;
            }

        </style>
    </head>
    <body>
        <security:authorize access= "hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var= "isUSer"/>
        <ul class='menu'>
            <li class='memberOfMenu' id="this"><a href="/springMVC_war_exploded">Главная</a></li>
            <li class='memberOfMenu'><a href="/springMVC_war_exploded/catalog">Каталог</a></li>
            <li class='memberOfMenu'><a href="/springMVC_war_exploded/basket/get">Корзина</a></li>
            <li class='memberOfMenu'><a href='/springMVC_war_exploded/basket/orders'>Мои заказы</a><li>
            <c:if test= "${not isUSer}">
                <c:if test= "${empty param.error}">
                    <li class='memberOfMenu'><a href='/springMVC_war_exploded/user/login'>Войти</a></li>
                    <li class='memberOfMenu'><a href='/springMVC_war_exploded/user/register'>Зарегистрироваться</a></li>
                </c:if>
            <security:authorize access = "hasAnyRole('ROLE_ADMIN')">
                <li class='memberOfMenu'><a href='/springMVC_war_exploded/admin/addBook'>ДОБАВИТЬ КНИГУ</a></li>
                <li class='memberOfMenu'><a href='/springMVC_war_exploded/user/addAdmin'>ДОБАВИТЬ АДМИНИСТРАТОРА</a></li>
            </security:authorize>
            <c:if test= "${isUSer}">
                    <li class='memberOfMenu'><a href='/springMVC_war_exploded/user'>Моя страница</a></li>
                <li class='memberOfMenu'><a href= "<c:url value= "/user/logout"/>">Выйти</a></li>
            </c:if>


           <%/*
                User user = (User) session.getAttribute("user");
                try {
                    if (!user.equals(null)) {
                        if (user.getAccessCode()==2) // ЕСЛИ ЭТО АДМИН
                        {
                            out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/admin/addBook'>ДОБАВИТЬ КНИГУ</a></li>");
                            out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user/addAdmin'>ДОБАВИТЬ АДМИНИСТРАТОРА</a></li>");
                        }
                        out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user/logout'>Выйти</a></li>");
                        out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user'>Моя страница</a></li>");

                    }
                }
                catch(NullPointerException np)
                {
                    User anon = (User) session.getAttribute("anonId");
                    out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user/login'>Войти</a></li>");
                    out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user/register'>Зарегистрироваться</a></li>");
                }
           */ %>



        </ul>
        <div class="select">
            <p class="select_header"  id="popular"  onclick="openSelection('popular_div')">Подборка самых популярных книг
            </p>
            <div class="select_generalInner" id="popular_div">
            <%
            Map<String,Object> models = (Map<String, Object>) request.getAttribute("models");
            List<Book> popBooks= (List<Book>) models.get("popularBooks");
            if(popBooks.size()!=0)
            {
                for (Book b : popBooks) {
                    out.println("<a href='/springMVC_war_exploded/book/"+b.getId() + "'><div class='select_innerDiv' >");
                    out.println("<p class='booksAuthor'>"+b.getFullNameOfAuthor()+"</p>");
                    out.println("<p class='bookName'> " + b.getName() + "</p>");
                    out.println("</a>");
                    out.println("<security:authorize access = 'hasAnyRole('ROLE_ADMIN')'>");
                        out.println("<a href='/springMVC_war_exploded/admin/editBook/"+b.getId()+"'>Изменить книгу</a>");
                    out.println("</security:authorize>");
                    out.println("</div>");
                }
            }

        %>
            </div>
        </div>
        <div class='select'>
            <p class="select_header" id="newBooks"  onclick="openSelection('new_div')">Новое поступление
            </p>
            <div class="select_generalInner" id="new_div">
            <%
            List<Book> newBooks= (List<Book>) models.get("newArrivals");
            if(newBooks.size()!=0)
            {
                for (Book b : popBooks) {
                    out.println("<a href='/springMVC_war_exploded/book/"+b.getId() + "'><div class='select_innerDiv' >");
                    out.println("<p class='booksAuthor'>"+b.getFullNameOfAuthor()+"</p>");
                    out.println("<p class='bookName'> " + b.getName() + "</p>");
                    out.println("</a>");
                    out.println("<security:authorize access = 'hasAnyRole('ROLE_ADMIN')'>");
                    out.println("<a href='/springMVC_war_exploded/admin/editBook/"+b.getId()+"'>Изменить книгу</a>");
                    out.println("</security:authorize>");
                    out.println("</div>");
                }
            }
        %>
            </div>
        </div>

        <div class="select">
            <p class="select_header"  id="poems"  onclick="openSelection('poems_div')">Полет рифмы: лучшие стихи и поэмы
            </p>
            <div class="select_generalInner" id="poems_div">
                <%
                    List<Book> poems_books = (List<Book>) models.get("poems");
                    if(popBooks.size()!=0)
                    {
                        for (Book b : poems_books) {
                            out.println("<a href='/springMVC_war_exploded/book/"+b.getId() + "'><div class='select_innerDiv' >");
                            out.println("<p class='booksAuthor'>"+b.getFullNameOfAuthor()+"</p>");
                            out.println("<p class='bookName'> " + b.getName() + "</p>");
                            out.println("</a>");
                            out.println("<security:authorize access = 'hasAnyRole('ROLE_ADMIN')'>");
                            out.println("<a href='/springMVC_war_exploded/admin/editBook/"+b.getId()+"'>Изменить книгу</a>");
                            out.println("</security:authorize>");
                            out.println("</div>");
                        }
                    }

                %>
            </div>
        </div>

        <div class="select">
            <p class="select_header"  id="novels"  onclick="openSelection('novels_div')">Слезы и драма: Романы всех времен
            </p>
            <div class="select_generalInner" id="novels_div">
                <%
                    List<Book> novels_books = (List<Book>) models.get("novels");
                    if(popBooks.size()!=0)
                    {
                        for (Book b : novels_books) {
                            out.println("<a href='/springMVC_war_exploded/book/"+b.getId() + "'><div class='select_innerDiv' >");
                            out.println("<p class='booksAuthor'>"+b.getFullNameOfAuthor()+"</p>");
                            out.println("<p class='bookName'> " + b.getName() + "</p>");
                            out.println("</a>");
                            out.println("<security:authorize access = 'hasAnyRole('ROLE_ADMIN')'>");
                            out.println("<a href='/springMVC_war_exploded/admin/editBook/"+b.getId()+"'>Изменить книгу</a>");
                            out.println("</security:authorize>");
                            out.println("</div>");
                        }
                    }

                %>
            </div>
        </div>
        <div class="select">
            <p class="select_header"  id="small"  onclick="openSelection('small_div')">Самые маленькие книги
            </p>
            <div class="select_generalInner" id="small_div">
                <%
                    List<Book> small_books = (List<Book>) models.get("small");
                    if(popBooks.size()!=0)
                    {
                        for (Book b : popBooks) {
                            out.println("<a href='/springMVC_war_exploded/book/"+b.getId() + "'><div class='select_innerDiv' >");
                            out.println("<p class='booksAuthor'>"+b.getFullNameOfAuthor()+"</p>");
                            out.println("<p class='bookName'> " + b.getName() + "</p>");
                            out.println("</a>");
                            out.println("<security:authorize access = 'hasAnyRole('ROLE_ADMIN')'>");
                            out.println("<a href='/springMVC_war_exploded/admin/editBook/"+b.getId()+"'>Изменить книгу</a>");
                            out.println("</security:authorize>");
                            out.println("</div>");
                        }
                    }

                %>
            </div>
        </div>

        <div class="select">
            <p class="select_header"  id="tolstoy"  onclick="openSelection('tolstoy_div')">Лев Толстой: золотые произведения
            </p>
            <div class="select_generalInner" id="tolstoy_div">
                <%
                    List<Book> tolstoy_books = (List<Book>) models.get("tolstoy");
                    if(popBooks.size()!=0)
                    {
                        for (Book b : tolstoy_books) {
                            out.println("<a href='/springMVC_war_exploded/book/"+b.getId() + "'><div class='select_innerDiv' >");
                            out.println("<p class='booksAuthor'>"+b.getFullNameOfAuthor()+"</p>");
                            out.println("<p class='bookName'> " + b.getName() + "</p>");
                            out.println("<p>" + b.getNumberOfWatching() + " просмотров!</p>");
                            out.println("</a>");
                            out.println("<security:authorize access = 'hasAnyRole('ROLE_ADMIN')'>");
                            out.println("<a href='/springMVC_war_exploded/admin/editBook/"+b.getId()+"'>Изменить книгу</a>");
                            out.println("</security:authorize>");
                            out.println("</div>");
                        }
                    }

                %>
            </div>
        </div>
    <script type="text/javascript">
        function openSelection(string) {
            var div = document.getElementById(string);
            if (div.style.display == "none") {
                div.style.display = 'block';
            }
            else {
                div.style.display = "none";
            }
        };
    </script>
    </body>
</html>
