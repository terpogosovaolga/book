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
    <title>Title</title>



</head>
<body>
<%
    try {
        if (!session.getAttribute("user").equals(null)) {
            out.println("<a href='/springMVC_war_exploded/user/logout'>Выйти</a>");
            out.println("<a href='/springMVC_war_exploded/user'>Моя страница</a>");
            out.println("<a href='/springMVC_war_exploded/basket/orders'>Мои заказы</a>");
        }
    }
    catch(NullPointerException np)
    {

        out.println("<a href='/springMVC_war_exploded/user/login'>Войти</a>");
        out.println("<a href='/springMVC_war_exploded/user/register'>Зарегистрироваться</a>");
    }
%>
<a href="/springMVC_war_exploded/basket/get">Корзина</a>
<a href="/springMVC_war_exploded">Главная</a>
<a href="/springMVC_war_exploded/catalog">Каталог</a>
    <%
        Book book = (Book) request.getAttribute("result");
        out.println("<div>");
        try {
            if (!session.getAttribute("user").equals(null)) {
                User user = (User) session.getAttribute("user");
                if (user.getAccessCode()==2);
                    out.println("<a href='/springMVC_war_exploded/editBook/"+ book.getId() +"'>Редактировать книгу</a>");
            }
        }
        catch(NullPointerException np)
        {
        }
        out.println("<h3>"+book.getName() + " " + book.getAuthorName() + " " + book.getAuthorSureName() + "</h3>");
        out.println("<p>Страниц:" + book.getCountOfPages()  + "</p>");
        out.println("<p>Цена:" + book.getCout()  + "</p>");
        out.println("<p>" + book.getDescription()  + "</p>");
        out.println("<p><a href='/springMVC_war_exploded/basket/add/"+ book.getId() +"'>Добавить в корзину</a></p>");
    %>

</body>
</html>
