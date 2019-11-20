<%@ page import="classes.BasketParagraph" %>
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

<form:form modelAttribute="basketParagraph" method="post" commandName="basketParagraph">
    <label>Количество: </label>
    <form:input id="number" path="numberOfBooks" type="number" step="1" min="1" max="100" value="30"/>
    <br>
    <label>Стоимость: </label>
    <form:input id="sum" path="sum" type="text" value="${basketParagraph.sum}" readonly="true"/>
    <form:button type="submit">Добавить в корзину</form:button>
</form:form>
<script type="text/javascript">
    number.oninput = function() {
        sum.value = ${basketParagraph.sum} * number.value;
    };
</script>
</body>
</html>
