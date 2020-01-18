<%@ page import="models.BasketParagraph" %>
<%@ page import="models.User" %>
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
                    out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/admin/addBook'>ДОБАВИТЬ КНИГУ</a></li>");
                    out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user/addAdmin'>ДОБАВИТЬ АДМИНИСТРАТОРА</a></li>");
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
