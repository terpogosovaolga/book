<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="classes.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.security.Key" %>
<%@ page import="classes.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 22.10.2019
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Книжный магазин - Каталог</title>
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
    #this {
        background-color: #1b1899; !important;
    }
    #this:hover {
        background-color: #1a012e;
    }
    .memberOfMenu:hover {
    background: #1b1899;
    text-decoration: underline;
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


    .search {
        width: 100%;
        height: 40px;
        border: #1b1899;

    }
    .search input {
        display: inline-block;
        font-family: 'Montserrat', sans-serif;
    }
    .searchString {
        width: 80%;
        height: 40px;
        font-size: 25px;
        color: black;
    }
    .submit {
        background-color: #1b1899;
        color: white;
        width: 18%;
        height: 40px;
        font-family: 'Playfair Display SC', serif;
        font-size: 20px;
    }

    .submit:hover {
        background-color: #1a012e;
    }

    .searchParams {
        width: 100%;
        width: 100%;
        display: flex;
        justify-content: space-between;
        padding-bottom: 10px;
       /* padding-top: 10px;*/
    }
    .searchParams_component {
        display: inline-block;
        padding: 10px;

    }

    .category {
        color: black;
        font-size: 18px;
        font-family: 'Playfair Display SC', serif;
    }
    .searchParams_component_a {
        display: inline-block;
        background-color: #615fd4;
        padding: 5px;
        border-radius: 2px;
        border: #1a012e 1px solid;
        font-family: 'Montserrat', sans-serif;
    }

    .searchParams_component_a:hover {
        background-color: #1b1899;
        text-decoration: underline;

    }

    .searchParams_component_a a {
        text-decoration: none;
        color: white;

    }
    .book {
        width: 40%;
        display: inline-block;
        margin: 20px;
        padding: 20px;
        background-color: #ffe3fc;
        border-radius: 10px;
        border: indianred solid 3px;
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

    .book a {
        display: inline-block;
        color: #1a012e;
        text-decoration: none;
        align: center;
        }
    .book label {
        color: black;
        font-size: 20px;
        font-weight: bold;
        padding: 3px;
        font-family: 'Open Sans Condensed', serif;
    }
        .attr {
            display: inline-block;
            color: black;
            font-size: 17px;
            font-family: 'Open Sans Condensed', sans-serif;
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
        .desc {
            font-family: 'Open Sans Condensed', sans-serif;
            font-size: 20px;
            color: black;
        }

        .pastParams {
            background-color: #615fd4;
            color: black;
            padding: 3px;
            display: inline-block;
            border-radius: 2px;
            border: 1px solid #4845ff;
        }
        .pastParams a {
            text-decoration: none;
            color: black;
        }
    </style>
</head>
<body>
<ul class='menu'>

    <li class='memberOfMenu'><a href="/springMVC_war_exploded">Главная</a></li>
    <li class='memberOfMenu' id="this"><a href="/springMVC_war_exploded/catalog">Каталог</a></li>
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
                out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user'>Моя страница</a></li>");
                out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user/logout'>Выйти</a></li>");
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

<div class="searchParams">
    <div class="searchParams_component">
        <p class="category">Жанр</p>
        <div class="searchParams_component_a"><a href="/springMVC_war_exploded/search/genre/novel">романы</a></div>
        <div class="searchParams_component_a"><a href="/springMVC_war_exploded/search/genre/poem">поэзия и стихи</a></div>
        <div class="searchParams_component_a"><a href="/springMVC_war_exploded/search/genre/detective">детективы</a></div>
        <div class="searchParams_component_a"><a href="/springMVC_war_exploded/search/genre/tale">сказки</a></div>
    </div>
    <div class="searchParams_component">
        <p class="category">Издательство</p>
        <div class="searchParams_component_a"><a href="/springMVC_war_exploded/search/publisher/eksmo">Эксмо</a></div>
        <div class="searchParams_component_a"><a href="/springMVC_war_exploded/search/publisher/azbuka">Азбука</a></div>
        <div class="searchParams_component_a"><a href="/springMVC_war_exploded/search/publisher/prosveshenie">Просвещение</a></div>
        <div class="searchParams_component_a"><a href="/springMVC_war_exploded/search/publisher/communizm">Коммунизм</a></div>
    </div>
    <div class="searchParams_component">
        <p class="category">Язык</p>
        <div class="searchParams_component_a"><a href="/springMVC_war_exploded/search/language/russian">Русский</a></div>
        <div class="searchParams_component_a"><a href="/springMVC_war_exploded/search/language/english">Английский</a></div>
        <div class="searchParams_component_a"><a href="/springMVC_war_exploded/search/language/untranslated">Без перевода</a></div>
    </div>
    <div class="searchParams_component">
        <p class="category">Цена</p>
        <div class="searchParams_component_a"><a href="/springMVC_war_exploded/search/price/300">Не дороже 300 рублей</a></div>
        <div class="searchParams_component_a"><a href="/springMVC_war_exploded/search/price/700">Не дороже 700 рублей</a></div>
        <div class="searchParams_component_a"><a href="/springMVC_war_exploded/search/price/1000">Не дороже 1000 рублей</a></div>
    </div>
</div>


    <%
        List<Book> books = (List<Book>) request.getAttribute("result");
        for (Book b:books)
        {
            out.println("<a href='book/"+b.getId() + "'><div class='book'>");
                out.println("<p class='booksAuthor'>"+b.getFullNameOfAuthor()+"</p>");
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
            try {
                if (user.getAccessCode() == 2) {
                    out.println("<a class='editBook' href='/springMVC_war_exploded/admin/editBook/"+b.getId()+"'>Изменить книгу</a>");
                }
            }
            catch(NullPointerException e){}
            out.println("</div>");
        }
    %>

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
