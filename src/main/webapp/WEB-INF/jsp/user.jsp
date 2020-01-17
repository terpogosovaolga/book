<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="models.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Натусик
  Date: 22.10.2019
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Книжный магазин - Пользователь</title>
</head>
<body>
    <p>USER</p>
    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var= "isUSer"/>
    <ul class='menu'>
        <li class='memberOfMenu' id="this"><a href="<c:url value=''/>">Главная</a></li>
        <li class='memberOfMenu'><a href="<c:url value='//catalog'/>">Каталог</a></li>
        <li class='memberOfMenu'><a href="<c:url value='//basket/get'/>">Корзина</a></li>
        <li class='memberOfMenu'><a href="<c:url value='/basket/orders'/>">Мои заказы</a></li>
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
    <c:if test= "${not empty param.error}">
    <font size= "2" color= "red"><b>Неправильный логин или пароль</b></font>
    </c:if>

    <%

            Long message= (Long) request.getAttribute("message");

            try {
                if (!message.equals(null)) {
                    //**************************ПОСЛЕ РЕГИСТРАЦИИ
                    out.println(message);
                    if (message != null) {
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
