<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page import="classes.Book" %>
<%@ page import="classes.User" %><%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 22.10.2019
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>"${result.name}"</title>
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

        .book {
            width: 90%;
            display: inline-block;
            margin: 20px;
            padding: 20px;
            background-color: #ffe3fc;
            border-radius: 10px;
            border: indianred solid 3px;
        }

        .booksAuthor {
            font-size: 30px;
            color: black;
            font-family: 'Open Sans Condensed', sans-serif;
        }

        .bookName {
            font-size: 25px;
            color: #1a012e;
            font-family: 'Playfair Display SC', serif;
        }

        .book a {
            display: inline-block;
            color: #1a012e;
            text-decoration: none;
            align: center;
        }
        .book label {
            color: black;
            font-size: 23px;
            font-weight: bold;
            padding: 3px;
            font-family: 'Open Sans Condensed', serif;
        }
        .attr {
            display: inline-block;
            color: black;
            font-size: 20px;
            font-family: 'Open Sans Condensed', sans-serif;
        }
        .price {

        }
        .editBook {
            color: #1a012e;
            text-decoration: none;
        }
        .attributes{
            margin: 5px;
            padding: 15px;
            background-color: #b3b1fa;
            border-radius: 7px;
        }
        .attributes_price {
            background-color: #fcc5f7; !important;
        }
        .price {
            font-size: 27px; !important;
        }
        .desc {
            font-family: 'Open Sans Condensed', sans-serif;
            font-size: 20px;
            color: black;
        }
        .button {
            font-size: 20px;
            padding: 10px;
            font-family: 'Playfair Display SC', serif;
        }
        .addToBasket {
            background-color: #4845ff;
            color: white;

        }
        .editBook {
            background-color: #fcc5f7;
            border: #4845ff solid 2px;
        }
        .addToBasket:hover {
            background-color: #1b1899;
        }
        .editBook:hover {
            background-color: #4845ff;
            color: white;
        }
        .addToBasket_a {
            display: block;
            margin: 20px;
        }


    </style>
</head>
<body>
<ul class='menu'>
    <li class='memberOfMenu'><a href="/springMVC_war_exploded">Главная</a></li>
    <li class='memberOfMenu'><a href="/springMVC_war_exploded/catalog">Каталог</a></li>
    <li class='memberOfMenu'><a href="/springMVC_war_exploded/basket/get">Корзина</a></li>
    <li class='memberOfMenu'><a href='/springMVC_war_exploded/basket/orders'>Мои заказы</a><li>
    <%
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
    %>

</ul>
    <%
        Book book = (Book) request.getAttribute("result");



        out.println("<a href='book/"+book.getId() + "'><div class='book'>");
            out.println("<p class='booksAuthor'>"+book.getFullNameOfAuthor()+"</p>");
            out.println("<p class='bookName'> " + book.getName() + "</p>");
            out.println("<div class='attributes'>");
                out.println("<div class='one_attr'><label>Жанр: </label><span class='genre attr'>" + book.getGenre() + " </span></div>");
                out.println("<div class='one_attr'><label>Год написания: </label><span class='yearOfWriting attr'>" + book.getYearOfWriting() + "г. </span></div>");
                out.println("<div class='one_attr'><label>Издательство: </label><span class='publisher attr'>" + book.getPublisher() + "</span></div>");
                out.println("<div class='one_attr'><label>Год издательства: </label><span class='yearOfPublishing attr'>" + book.getYearOfPublishing() + " г.</span></div>");
                out.println("<div class='one_attr'><label>Язык оригинала: </label><span class='originalLanguage attr'>" + book.getOriginalLanguage() + "</span></div>");
                out.println("<div class='one_attr'><label>Язык издания: </label><span class='language attr'>" + book.getLanguage() + "</span></div>");
                if (!book.getLanguage().equals(book.getOriginalLanguage()))
                    out.println("<div class='one_attr'><label>Переводчик: </label><span class='translater attr'>" + book.getTranslater() + "</span></div>");
                out.println("<div class='one_attr'><label>Количетсво страниц: </label><span class='language attr'>" + book.getCountOfPages() + "</span></div>");
            out.println("</div>");
            out.println("<div class='attributes'>");
                out.println("<p class='desc'>" + book.getDescription() + "</p>");
            out.println("</div>");
            out.println("<div class='attributes_price attributes'>");
                out.println("<div class='one_attr'><span class='price attr'>" + book.getCout() + " Р.</span></div>");
            out.println("</div>");

        out.println("</div></a>");

        out.println("<a class='button_a' href='/springMVC_war_exploded/basket/add/"+ book.getId() +"'><button class='addToBasket button'>Добавить в корзину</button></a>");
        try {
            if (user.getAccessCode() == 2) {
                out.println("<a class='editBook_a ' href='/springMVC_war_exploded/admin/editBook/"+book.getId()+"'><button class='editBook button'>Изменить книгу</button></a>");
            }
        }
        catch(NullPointerException e){}
    %>

</body>
</html>
