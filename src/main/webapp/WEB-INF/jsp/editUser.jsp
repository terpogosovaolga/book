<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 10.11.2019
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EDIT USER</title>
</head>
<body>
<form:form method="post" modelAttribute="user" commandName="user">
    <br>
    <form:input placeholder="email" type="text" path="email"/><br>
    <form:input path="name" type="text" placeholder="name"/><br>
    <form:input path="fullName" type="text" placeholder="surname"/><br>
    <form:input path="password" type="password" placeholder="password"/><br>
    <form:button type="submit">Редактировать</form:button>
</form:form>
</body>
</html>
