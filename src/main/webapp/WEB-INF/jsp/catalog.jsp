<%@ page import="classes.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 22.10.2019
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<a href='/springMVC_war_exploded/'>Главная</a>
    <%
    try {
        if (!session.getAttribute("user").equals(null)) {

            out.println("<a href='/springMVC_war_exploded/user/logout'>Выйти</a>");
            out.println("<a href='/springMVC_war_exploded/user'>Моя страница/a>");
            out.println("<a href='/springMVC_war_exploded/basket/orders'>Мои заказы</a>");
        }
    }
    catch(NullPointerException np)
    {
        out.println("<a href='/springMVC_war_exploded/user/login'>Войти</a>");
        out.println("<a href='/springMVC_war_exploded/user/register'>Зарегистрироваться</a>");
    }

        out.println("<a href='/springMVC_war_exploded/basket/get'>Корзина</a>");
        List<Book> books = (List<Book>) request.getAttribute("result");
        for (Book b:books)
        {
            out.println("<div style='border: 1px solid black'>");
            out.println("<b>'"+ b.getName() + "' " + b.getAuthorSureName() + "</b>");
            out.println("<p>" + b.getCout() + " рублей</p>");
            out.println("<a href='book/"+b.getId() + "'> Перейти на страницу книги </a>");
            out.println("</div>");
        }
    %>
</body>
</html>
