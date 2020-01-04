<%@ page import="classes.BasketParagraph" %>
<%@ page import="classes.User" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 15.11.2019
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            background-color: #e9e8fa;
        }
        .memberOfMenu {
            display: inline-block;
            width: 24%;
            font-color: black;
            font-size: 20px;
            align: center;
            margin-top: 10px;
            margin-bottom: 10px;
            height: 100%;

        }
        .memberOfMenu:hover {
            background: #1b1899;
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
        }
        .form {
            padding: 5px;
        }
        .form input{
            width: 400px;
            height: 35px;
            font-size: 15px;

        }
        .form button {
            background-color: #4845ff;
            font-color: white;
            font-size: 20px;
            padding: 5px;
        }
        .form button:hover {
            background-color: #1b1899;
        }
    </style>
</head>
<body>
<ul class='menu'>
    <%
        User user = (User) session.getAttribute("user");
        try {
            if (!user.equals(null)) {
                out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user/logout'>Выйти</a></li>");
                out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user'>Моя страница</a></li>");
                out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/basket/orders'>Мои заказы</a><li>");
                if (user.getAccessCode()==2) // ЕСЛИ ЭТО АДМИН
                {
                    out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/admin/addBook'>ДОБАВИТЬ КНИГУ</a></li>");
                    out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user/addAdmin'>ДОБАВИТЬ АДМИНИСТРАТОРА</a></li>");
                }
            }
        }
        catch(NullPointerException np)
        {
            User anon = (User) session.getAttribute("anonId");
            out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user/login'>Войти</a></li>");
            out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user/register'>Зарегистрироваться</a></li>");
        }
    %>

    <li class='memberOfMenu'><a href="/springMVC_war_exploded/catalog">Каталог</a></li>
    <li class='memberOfMenu'><a href='/springMVC_war_exploded/basket/orders'>Мои заказы</a><li>
</ul>
<div class="main_form">
    <p class="mainForm_header">Войдите</p>
    <div class="main_form_inner">
        <form:form modelAttribute="basketParagraph" method="post" commandName="basketParagraph" class="form">
            <label>Количество: </label>
            <form:input id="number" path="numberOfBooks" type="number" step="1" min="1" max="100" value="1"/>
            <br>
            <label>Стоимость: </label>
            <form:input id="sum" path="sum" type="text" value="${basketParagraph.sum}" readonly="true"/>
            <form:button type="submit">Добавить в корзину</form:button>
        </form:form>
    </div>
</div>
<script type="text/javascript">
    number.oninput = function() {
        sum.value = ${basketParagraph.sum} * number.value;
    };
</script>
</body>
</html>
