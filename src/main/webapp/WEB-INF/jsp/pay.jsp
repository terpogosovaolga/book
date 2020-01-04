<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Map" %>
<%@ page import="classes.Basket" %>
<%@ page import="classes.BasketParagraphBooked" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 18.11.2019
  Time: 19:55
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
            out.println("<a href='/springMVC_war_exploded/'>Главная</a>");
            out.println("<a href='/springMVC_war_exploded/catalog'>Каталог</a>");
            out.println("<p>Вы покупаете: </p>");
            Basket basket = (Basket) session.getAttribute("basket");
            out.println("<p>Сумма:" + basket.getSum() +" рублей </p><br></br>");
            out.println("<p>Для оплаты введите информацию о своей карте.</p>");
           /* out.println("<form:form modelAttribute='card' method='post' commandName='card'>");
            out.println("<form:input path='numberOfCard' type='text' placeholder='Номер карты' /><br>");
            out.println("<form:input path='name' type='text' placeholder='Имя владельца карты'/><br>");
            out.println("<form:input path='cvc' type='text' placeholder='CVC-код на обратной стороне' /><br>");
            out.println("<form:button type='submit'>Купить</form:button>");
            out.println("</form:form>");*/
        }
    }
    /* pattern='[0-9]{16}' required='true'
    pattern='[A-Za-z\s]' required='true'
    pattern='[0-9]{3}' required='true'*/
    catch(NullPointerException np)
    {

        out.println("<a href='/springMVC_war_exploded/user/login'>Войти</a>");
        out.println("<a href='/springMVC_war_exploded/user/register'>Зарегистрироваться</a>");
        out.println("<br>");
        out.println("<p>Вам нужно <a href='/springMVC_war_exploded/user/register'>Зарегистрироваться</a> или <a href='/springMVC_war_exploded/user/login'>Войти</a>, чтобы совершить покупку :с</p>");
    }

%>

<p>Для оплаты введите информацию о своей карте.</p>
<form:form modelAttribute='card' method='post' commandName='card' required='true'>
<form:input path='numberOfCard' type='text' placeholder='Номер карты'  pattern='[0-9]{16}'required='true'/><br>
<form:input path='name' type='text' placeholder='Имя владельца карты' pattern='[A-Za-z\s]'  pattern='[0-9]{3}' required='true'/><br>
<form:input path='cvc' type='text' placeholder='CVC-код на обратной стороне' /><br>
<form:button type='submit'>Купить</form:button>
</form:form>

</body>
</html>
