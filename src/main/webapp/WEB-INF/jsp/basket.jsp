<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Map" %>
<%@ page import="classes.Basket" %>
<%@ page import="classes.BasketParagraph" %>
<%@ page import="java.util.List" %>
<%@ page import="classes.BasketParagraphBooked" %><%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 22.10.2019
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BASKET</title>

</head>
<body>
    <p>BASKET</p>
    <%
        try {
    if (!session.getAttribute("user").equals(null)) {
        out.println("<a href='/springMVC_war_exploded/user/logout'>Выйти</a>");
        out.println("<a href='/springMVC_war_exploded/user'>Моя страница</a>");
    }
    }
    catch(NullPointerException np)
    {

    out.println("<a href='/springMVC_war_exploded/user/login'>Войти</a>");
    out.println("<a href='/springMVC_war_exploded/user/register'>Зарегистрироваться</a>");
    out.println("<a href='/springMVC_war_exploded/basket/orders'>Мои заказы</a>");
    }
    %>
    <a href='/springMVC_war_exploded/'>Главная</a>
    <a href='/springMVC_war_exploded/catalog'>Каталог</a>
<%
    Map<String,Object> map = (Map<String, Object>) request.getAttribute("allBasket");
    Basket basket = (Basket) map.get("basket");
    List<BasketParagraphBooked> bp = (List<BasketParagraphBooked>) map.get("basketParagraphs");
    String message = (String) map.get("message");

    try{
        for (BasketParagraphBooked b : bp) {
            out.println("<div>");
            out.println("<p>" + b.getBook().getName() + " " + b.getBook().getAuthorSureName() + "</p>");
            out.println("<p>Цена:  </p><input id='price' type='number' value='" +b.getBook().getCout()+ "'/> р.");
            out.println("<form:form>");
            out.println("<p>Количество: </p><button id='minus'>-</button><input id='numberOfBooks' type='number' value='"+b.getNumberOfBooks()+"'/><button id='plus'>+</button>");
            out.println("<button id='apply'>Применить</button>");
            out.println("</form:form>");
            /* ЧТО-ТО С ЭТИМ СДЕЛАТЬ !!!!!!*/
          // out.println("<a href=''>Удалить книгу из корзины</a>");
        }
        out.println("<p>Сумма всей корзины: </p><input type='number' id='sum' value='"+basket.getSum()+"'/>руублей ");
        out.println("<a href='/springMVC_war_exploded/basket/buy'>Купить Все!</a>");
    }
    catch(NullPointerException e) {
        out.println("<p>"+ message + "</p>");
        out.println("<p>Чтобы исправить это, перейдите на <a href='/springMVC_war_exploded'>главную </a> страницу или в <a href='/springMVC_war_exploded/catalog'>каталог</a></p>");
    }
%>

<script type="text/javascript">
    minus.onclick = function() {

        if (parseInt(numberOfBooks.value, 10)>1) {
            numberOfBooks.value = parseInt(numberOfBooks.value, 10) - 1;
            sum.value=numberOfBooks.value * price.value;
        }
    };
    plus.onclick = function() {
         numberOfBooks.value=parseInt(numberOfBooks.value, 10)+1;
        sum.value=numberOfBooks.value * price.value;
    }
    numberOfBooks.oninput = function() {
        sum.value=numberOfBooks.value * price.value;
    }
    apply.onclick = function() {

    }

</script>
</body>
</html>
