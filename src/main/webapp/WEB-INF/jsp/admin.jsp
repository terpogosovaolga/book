<%@ page import="sun.invoke.empty.Empty" %>
<%@ page import="org.springframework.dao.EmptyResultDataAccessException" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 01.11.2019
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>Добавление книги</p>
    <%
        String message = (String) request.getAttribute("message");
        try {
            out.println(message);
        }
        catch(EmptyResultDataAccessException e){}
    %>
    <a href='/springMVC_war_exploded/user/logout'>Выйти</a>
    <a href='/springMVC_war_exploded/user'>Моя страница</a>
    <a href="/springMVC_war_exploded/catalog">Каталог</a>
    <div>
        <form:form modelAttribute="book" method="post" commandName="book">
            <label>Автор</label>
            <br>
            <label>Имя</label><form:input path="authorName" type="text" required="true"/>
            <br>
            <label>Отчество</label><form:input path="authorSecondName" type="text" />
            <br>
            <label>Фамилия</label><form:input path="authorSureName" type="text" required="true"/>
            <br>
            <label>Книга</label>
            <br>
            <label>Название</label><form:input path="name" type="text" required="true"/>
            <br>
            <label>Год написания</label><form:input path="yearOfWriting" type="number" required="true"/>
            <br>
            <label>Издательство</label><form:input path="publisher" type="text" required="true"/>
            <br>
            <label>Год издания</label><form:input path="yearOfPublishing" type="number" required="true"/>
            <br>
            <label>Язык оригинала</label><form:input path="originalLanguage" type="text" required="true"/>
            <br>
            <label>Язык перевода</label><form:input path="language" type="text"  />
            <br>
            <label>Переводчик</label><form:input path="translater" type="text"/>
            <br>
            <label>Цена</label><form:input path="cout" type="number" step="0.01" min="0" required="true"/>
            <br>
            <label>Количество</label><form:input path="count" type="number" required="true"/>
            <br>
            <label>Количество страниц</label><form:input path="countOfPages" type="number"/>
            <br>
            <label>Описание</label><form:textarea path="description" type="text"/>
            <br>
            <label>Жанр</label><form:input path="genre" type="text" required="true"/>
            <br>
            <form:button type="submit">Add</form:button>
        </form:form>
    </div>
</body>
</html>
