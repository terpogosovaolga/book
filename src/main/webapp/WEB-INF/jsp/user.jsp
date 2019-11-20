<%@ page import="classes.User" %><%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 22.10.2019
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>USER</p>
    <a href='/springMVC_war_exploded/'>Главная</a>
    <a href="/springMVC_war_exploded/catalog">Каталог</a>
    <%

            String message= (String) request.getAttribute("message");

            try {
                if (!message.equals(null)) {
                    //**************************ПОСЛЕ РЕГИСТРАЦИИ
                    out.println(message);
                    if (message == "Пользователь с таким email уже зарегистрирован") {
                        out.println("<p>Попробуйте <a href = '/springMVC_war_exploded/user/login'>войти</a> или <a href='/springMVC_war_exploded/user/register'>Зарегистрироваться</a> на другой email  </p>");
                    } else {
                        out.println("<p> Вам осталось только <a  href='/springMVC_war_exploded/user/login'>войти </a>, чтобы пользоваться своей новой учетной записью </p>");
                    }
                }
            }
            catch(NullPointerException ex) {
                //***********************ПОСЛЕ ВХОДА
                try {
                    if (!session.getAttribute("user").equals(null)) {
                        User user = (User) session.getAttribute("user");
                        out.println("<a href='/springMVC_war_exploded/user/logout'>Выйти</a>");
                        out.println("<p>Привет! Вас зовут " + user.getName() + " " + user.getFullName() + "!</p>");
                        out.println("<a href='/springMVC_war_exploded/user/editUser'>Изменить профиль</a>");
                        out.println("<a href='/springMVC_war_exploded/basket/get'>Корзина</a>");

                    }
                }
                catch(NullPointerException np)
                {
                    out.println("<p>"+ (String)request.getAttribute("error")+"</p>");
                    out.println("<p>Попробуйте еще раз <a href='/springMVC_war_exploded/user/login'>войти </a>или <a href='/springMVC_war_exploded/user/register'>зарегистрируйтесь</a></p>");
                }

            }


    %>
</body>
</html>
