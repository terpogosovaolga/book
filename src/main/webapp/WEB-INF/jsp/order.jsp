<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="models.Basket" %>
<%@ page import="java.util.List" %>
<%@ page import="models.BasketParagraphBooked" %>
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
    <h1>Ваши заказы</h1><security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var= "isUSer"/>
    <ul class='menu'>
        <li class='memberOfMenu' id="this"><a href="<c:url value=''/>">Главная</a></li>
        <li class='memberOfMenu'><a href="<c:url value='//catalog'/>">Каталог</a></li>
        <li class='memberOfMenu'><a href="<c:url value='//basket/get'/>">Корзина</a></li>
        <li class='memberOfMenu' id="this"><a href="<c:url value='/basket/orders'/>">Мои заказы</a></li>
        <c:if test="${isUSer}">
            <li class='memberOfMenu'><a href="<c:url value='/logout'/>">Выйти</a></li>
            <li class='memberOfMenu'><a href='<c:url value='/user/editUser'/>'>Моя страница</a></li>
        </c:if>
        <!--<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                <li class='memberOfMenu'><a href='/springMVC_war_exploded/admin'>Администратор</a></li>
            </sec:authorize>-->
        <c:if test="${not isUSer}">
            <li class='memberOfMenu'><a href="<c:url value='/user/login'/>">Войти</a></li>
            <li class='memberOfMenu'><a href="<c:url value='/user/register'/>">Зарегистрироваться</a></li>
        </c:if>
    </ul>
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
