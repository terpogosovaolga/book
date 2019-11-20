<%@ page import="classes.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="classes.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            try {
                if (!user.equals(null)) {

                    out.println("<a href='/springMVC_war_exploded/user/logout'>Выйти</a>");
                    out.println("<a href='/springMVC_war_exploded/user'>Моя страница</a>");
                    out.println("<a href='/springMVC_war_exploded/basket/orders'>Мои заказы</a>");
                    if (user.getAccessCode()==2) // ЕСЛИ ЭТО АДМИН
                    {
                        out.println("<a href='/springMVC_war_exploded/admin/addBook'>ДОБАВИТЬ КНИГУ</a>");
                        out.println("<a href='/springMVC_war_exploded/user/addAdmin'>ДОБАВИТЬ АДМИНИСТРАТОРА</a>");
                    }
                }
            }
            catch(NullPointerException np)
            {
                out.println("<a href='/springMVC_war_exploded/user/login'>Войти</a>");
                out.println("<a href='/springMVC_war_exploded/user/register'>Зарегистрироваться</a>");
            }
        %>
        <a href="/springMVC_war_exploded/basket/get">Корзина</a>
        <a href="/springMVC_war_exploded/catalog">Каталог</a>

        <h1>Главная страница</h1>
        <h3>Здесь будут подборки популярных/рекомендованных/новых и т.д. книг</h3>
        <div style="border: 2px solid red">ПОПУЛЯРНЫЕ
        <%
            Map<String,Object> models = (Map<String, Object>) request.getAttribute("models");
            List<Book> popBooks= (List<Book>) models.get("popularBooks");
            if(popBooks.size()!=0)
            {
                for (Book b : popBooks) {
                    out.println("<div style='border: 1px solid black'>");
                    out.println("<b>'"+ b.getName() + "' " + b.getAuthorSureName() + "</b>");
                    out.println("<p>" + b.getNumberOfWatching() + " просмотров!</p>");
                    out.println("<a href='/springMVC_war_exploded/book/"+b.getId() + "'> Перейти на страницу книги </a>");
                    try {
                        if (user.getAccessCode() == 2) {
                            out.println("<a href=''>Изменить книгу</a>");
                        }
                    }
                    catch(NullPointerException e){}
                    out.println("</div>");
                }
            }
        %>
        </div>
        <div style="border: 2px solid red">НОВОЕ ПОСТУПЛЕНИЕ
        <%
            List<Book> newBooks= (List<Book>) models.get("newArrivals");
            if(newBooks.size()!=0)
            {
                for (Book b : newBooks) {
                    out.println("<div style='border: 1px solid black'>");
                    out.println("<b>'"+ b.getName() + "' " + b.getAuthorSureName() + "</b>");
                    out.println("<form:button>Перейти на страницу книги</form:button>");
                    out.println("</div>");
                }
            }
        %>
        </div>
    </body>
</html>
