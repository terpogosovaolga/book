<%@ page import="classes.Book" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Title</title>
    <h1>Главная страница</h1>
    <h3>Здесь будут подборки популярных/рекомендованных/новых и т.д. книг</h3>
    <h3>ПОПУЛЯРНЫЕ</h3>
    <%
        List<Book> books=(List<Book>) request.getAttribute("popularBooks");
        out.println("<p>"+ books.size() + "</p>");
      //  if(books.size()!=0)
       // {
            for (Book b : books) {
                out.println("<div>");
                out.println("<p>'"+ b.getName() + "' " + b.getAuthorSureName() + "</p>");
                out.println("<p>" + b.getNumberOfWatching() + " просмотров!</p>");
                out.println("</div>");
        //    }
       // }
    %>
    <form:form method="get" value="">
        <input type="submit" path=""></input>
    </form:form>
</head>
<body>

</body>
</html>
