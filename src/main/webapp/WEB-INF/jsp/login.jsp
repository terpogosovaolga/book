<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 03.11.2019
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

        <a href='/springMVC_war_exploded/'>Главная</a>
        <a href='/springMVC_war_exploded/catalog'>Каталог</a>

        <p>LOGIN</p>
        <div>
        <form:form modelAttribute="user" method="post" commandName="user">
            <form:input path="email" type="text" placeholder="email" />
            <br>
            <form:input path="password" type="password" placeholder="password" />
            <br>
            <form:button type="submit">Login</form:button>
        </form:form>
    </div>
</body>
</html>


