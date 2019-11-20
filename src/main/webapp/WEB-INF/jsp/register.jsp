<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 06.11.2019
  Time: 8:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<a href='/springMVC_war_exploded/'>Главная</a>
<a href="/springMVC_war_exploded/catalog">Каталог</a>
    <form:form method="post" modelAttribute="user">
        <form:input path="name" type="text" placeholder="name"/>
        <form:input path="fullName" type="text" placeholder="surname"/>
        <form:input placeholder="email" type="text" path="email"/>
        <form:input path="password" type="password" placeholder="password"/>
        <form:button type="submit">Register</form:button>
    </form:form>
</body>
</html>
