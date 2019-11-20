<%@ page import="classes.Basket" %>
<%@ page import="java.util.List" %>
<%@ page import="classes.BasketParagraphBooked" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 19.11.2019
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Ваши заказы</h1>
    <a href='/springMVC_war_exploded/user/logout'>Выйти</a>
    <a href='/springMVC_war_exploded/user'>Моя страница</a>
    <a href='/springMVC_war_exploded/basket/get'>Корзина</a>
    <a href="/springMVC_war_exploded/catalog">Каталог</a>
<%
    Map<String,Object> map = (Map<String,Object>) request.getAttribute("map");
    List<BasketParagraphBooked> ordersParagraphs = (List<BasketParagraphBooked>) map.get("ordersParagraphs");
    List<Basket>orders = (List<Basket>) map.get("orders");
    try {
        for (Basket o : orders) {
            out.println("<div style='border: 2px solid red'>");
            for (BasketParagraphBooked bp : ordersParagraphs) {
                if (bp.getBasketId()==o.getId())
                    out.println("<p>" + bp.getBook().getName() + " В количестве: "+ bp.getNumberOfBooks() + " </p>");
            }
            out.println("Общая сумма заказа: " + o.getSum() + "p. ");
            out.println("Дата заказа: " + o.getDateOfSale().toString());
            out.println("</div>");
        }
    }
    catch(NullPointerException e){
        out.println("Кажется, Вы ничего у нас не заказывали! Исправить это можно в <a href='/springMVC_war_exploded/basket/get'>Корзине</a>!");
    }
%>
</body>
</html>
