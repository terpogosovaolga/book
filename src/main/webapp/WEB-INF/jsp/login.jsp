<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %><%@ taglib prefix="c" uri=""%>
<%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 03.11.2019
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Книжный магазин - Вход</title>
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

        .main_form {
            background-color: #fcbbf6;
            border: indianred 2px solid;
            border-radius: 10px;
            width: 450px;
            height: 300px;
            padding: 10px;
            align: center;
            position:absolute;
            top:50%;
            left:50%;
            margin-top: -150px;
            margin-left: -225.5px;
            align: center;
            text-align: center;
        }
        .mainForm_header {
            align: center;
            color: black;
            font-size: 30px;
            font-family: 'Cormorant Unicase', serif;
        }
        .form {
            padding: 5px;
        }
        .form input{
            width: 400px;
            height: 35px;
            font-size: 15px;
            margin: 10px;
            font-family: 'Montserrat', sans-serif;

        }
        .form button {
            background-color: #4845ff;
            color: white;
            font-size: 20px;
            padding: 10px;
            font-family: 'Playfair Display SC', serif;
        }
        .form button:hover {
            background-color: #1b1899;
        }

    </style>
</head>
<body>
    <ul class="menu">
        <li class='memberOfMenu'><a href='/springMVC_war_exploded/'>Главная</a></li>
        <li class='memberOfMenu'><a href='/springMVC_war_exploded/catalog'>Каталог</a></li>
        <li class='memberOfMenu'><a href='/springMVC_war_exploded/user/register'>Зарегистрироваться</a></li>
    </ul>
    <c:if test= "${not empty param.error}">
        <font size= "2" color= "red"><b>Неправильный логин или пароль</b></font>
    </c:if>

    <div class="main_form">
        <p class="mainForm_header">Войдите</p>
        <div class="main_form_inner">
            <form:form modelAttribute="user" method="post" commandName="user" class="form">
                <form:input path="email" name="j_username" type="email" placeholder="email" />
                <br>
                <form:input path="password" name="j_password" type="password" placeholder="password" />
                <br>
                <form:button type="submit">Login</form:button>
            </form:form>
        </div>
    </div>
</body>
</html>


