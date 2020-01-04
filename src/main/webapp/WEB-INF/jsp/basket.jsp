<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Map" %>
<%@ page import="classes.Basket" %>
<%@ page import="classes.BasketParagraph" %>
<%@ page import="java.util.List" %>
<%@ page import="classes.BasketParagraphBooked" %>
<%@ page import="com.test.pluto.BookController" %>
<%@ page import="classes.User" %>
<%--
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
    <style>
        body {
            background-color: #e9e8fa;
        }
        .memberOfMenu {
            display: inline-block;
            width: 24%;
            font-color: black;
            font-size: 20px;
            align: center;
            margin-top: 10px;
            margin-bottom: 10px;
            height: 100%;
            text-align: center;

        }
        .memberOfMenu:hover {
            background: #1b1899;
        }
        .menu {
            background-color: #615fd4;
            height: 100px;
            width: 100%;
            display: flex;
            justify-content: space-between;
        }
        .memberOfMenu a {
            color: white;
            text-decoration: none;
            align: center;
        }

        .allBook {
            width: 50%;
            margin-left: auto;
            margin-right: auto;
        }
        .book {
            margin: 20px;
            padding: 20px;
            background-color: #ffe3fc;
            border-radius: 10px;
            border: indianred solid 3px;
            width: 90%;
        }

        .booksAuthor {
            font-size: 18px;
            color: black;
        }

        .bookName {
            font-size: 23px;
            color: #1a012e;
        }

        .book a {
            display: inline-block;
            color: #1a012e;
            text-decoration: none;
            align: center;
        }
        .book label {
            color: black;
            font-size: 18px;
            font-weight: bold;
            padding: 3px;
        }
        .one_attr {
            display: block;
            width: 30%;
        }
        .one_attr label {
            display: inline-block;
        }
        .attr {
            display: inline-block;
            font-style: italic;
            color: black;
            font-size: 13px;
            margin-left: auto;
        }
        .attributes{
            margin: 5px;
            padding: 5px;
            background-color: #b3b1fa;
        }
        .delete_button{
            background-color: #615fd4;
            border: #4845ff 2px solid;
            border-radius: 2px;
            color: black;
            font-size: 20px;
            margin-left: auto;
        }
        .delete_button:hover {
            background-color: #4845ff;
            border: #1b1899 2px solid;
        }

        .numberOfBooks {
            height: 30px;
            font-size: 20px;
        }
        .sum_p {
            color: #1a012e;
            font-weight: bold;

        }
        #sum {
            height: 30px;
            font-size: 25px;
        }
        .button {
            display: inline-block;
            padding: 5px;
            border-radius: 2px;
            font-size: 30px;
            margin: 5px;
        }
        .button a {
            text-decoration: none;

        }
        .buy {
            background-color: #1b1899;

        }
        .buy:hover {
            background-color: #1a012e ;

        }
        .buy a {
            color: white;
        }
        .clean {
            background-color: #fcbbf6;
            color: black;
            border: #1a012e solid 2px;
        }
        .clean:hover {
            background-color: indianred;
        }
        .clean a {
            color: black;
        }
        .form_number button {
            background-color: #1b1899;
            color: white;
            height: 30px;
            width: 30px;
        }
        .form_number button:hover {
            background-color: #1a012e;

        }

    </style>
</head>
<body>
<ul class='menu'>
    <%
        User user = (User) session.getAttribute("user");
        try {
            if (!user.equals(null)) {
                out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user/logout'>Выйти</a></li>");
                out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user'>Моя страница</a></li>");
                out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/basket/orders'>Мои заказы</a><li>");
                if (user.getAccessCode()==2) // ЕСЛИ ЭТО АДМИН
                {
                    out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/admin/addBook'>ДОБАВИТЬ КНИГУ</a></li>");
                    out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user/addAdmin'>ДОБАВИТЬ АДМИНИСТРАТОРА</a></li>");
                }
            }
        }
        catch(NullPointerException np)
        {
            User anon = (User) session.getAttribute("anonId");
            out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user/login'>Войти</a></li>");
            out.println("<li class='memberOfMenu'><a href='/springMVC_war_exploded/user/register'>Зарегистрироваться</a></li>");
        }
    %>

    <li class='memberOfMenu'><a href="/springMVC_war_exploded/catalog">Каталог</a></li>
    <li class='memberOfMenu'><a href='/springMVC_war_exploded/basket/orders'>Мои заказы</a><li>
</ul>
<%
    Map<String , Object> map = (Map<String , Object>) request.getAttribute("allBasket");
    Basket basket = (Basket) map.get("basket");
    List<BasketParagraphBooked> bp = (List<BasketParagraphBooked>) map.get("basketParagraphs");
    String message = (String) map.get("message");

    out.println("<div class='allBooks'>");
    try{
        for (BasketParagraphBooked b : bp) {
            out.println("<div class='book'>");
                out.println("<form id='delete"+b.getId()+"' class='delete' action='/springMVC_war_exploded/basket/delete/"+b.getId()+"' method='get' class='form_deleteBook' id='form_deleteBook"+b.getId()+"'>");
                out.println("<button  class='delete_button' onclick = 'SubmitDelete("+b.getId()+ ", "+ b.getNumberOfBooks() + b.getBook().getCout() +  ")' class = 'deleteBook' id='deleteBook"+b.getId()+"'>Удалить книгу из корзины</button>");
                out.println("</form>");
                out.println("<p class='booksAuthor'>"+b.getBook().getFullNameOfAuthor()+"</p>");
                out.println("<p class='bookName'> " + b.getBook().getName() + "</p>");
                out.println("<div class='attributes'>");
                    out.println("<div class='one_attr'><label>Год написания: </label><span class='yearOfWriting attr'>" + b.getBook().getYearOfWriting() + "г. </span></div>");
                    out.println("<div class='one_attr'><label>Издательство: </label><span class='publisher attr'>" + b.getBook().getPublisher() + "</span></div>");
                    out.println("<div class='one_attr'><label>Год издательства: </label><span class='yearOfPublishing attr'>" + b.getBook().getYearOfPublishing() + " г.</span></div>");
                    out.println("<div class='one_attr'><label>Цена: </label><span class='price attr'>" + b.getBook().getCout() + " Р.</span></div>");
                    out.println("<form class='form_number' action='/springMVC_war_exploded/basket/editNumber/"+b.getId()+"/' method='post'>");
                    out.println("<label class='number'>Количество: </label><button class='minus' onclick = 'minus("+b.getId()+", " +b.getBook().getCout()+")' id='minus" + b.getId()+"'>-</button><input class='numberOfBooks' id='numberOfBooks" + b.getId()+"' type='number' name = 'newNumber' value='"+b.getNumberOfBooks()+"' readonly = true/><button  onclick = 'plus("+b.getId()+", " +b.getBook().getCout()+")' class='plus' id='plus" + b.getId()+"'>+</button>");
                    out.println("</form:form>");
                out.println("</div>");
            out.print("</div>");
        }
        out.println("<p class='sum_p'>Сумма всей корзины: </p><input type='number' id='sum' value='"+basket.getSum()+"' readonly='true'/>рублей ");
        out.println("<div>");
            out.println("<div class='button buy'><a href='/springMVC_war_exploded/basket/buy'>Купить Все!</a></div>");
            out.println("<div class='button clean'><a href='/springMVC_war_exploded/basket/clean'>Очистить корзину</a></div>");
        out.println("</div>");
    }
    catch(NullPointerException e) {
        out.println("<p>"+ message + "</p>");
        out.println("<p>Чтобы исправить это, перейдите на <a href='/springMVC_war_exploded'>главную </a> страницу или в <a href='/springMVC_war_exploded/catalog'>каталог</a></p>");
    }
    out.println("<div>");
%>

<script type="text/javascript">
   /* minus.onclick = function() {

        if (parseInt(numberOfBooks.value, 10) > 1) {
            numberOfBooks.value = parseInt(numberOfBooks.value, 10) - 1;
            sum.value = numberOfBooks.value * price.value;

        }
    }

    plus.onclick = function() {
         numberOfBooks.value=parseInt(numberOfBooks.value, 10)+1;
        sum.value=numberOfBooks.value * price.value;
    }*/
    function minus(id, price) {
       string = "numberOfBooks" + id;
       element = document.getElementById(string);
       if (parseInt(element.value, 10) > 1 ) {
           element.value = parseInt(element.value, 10) - 1;
       }
       sum.value = sum.value-price;
   }
   function plus(id, price) {
       string = "numberOfBooks" + id;
       element = document.getElementById(string);
       element.value = parseInt(element.value, 10) + 1;
       sum.value = sum.value+price;
   }

    function SubmitDelete(id, number, price) {
       /* session.setAttribute("delete_Bp", b.getId());*/
        document.getElementById("sum").value=document.getElementById("sum").value-number*price;
        formId = "delete" + id;
        document.getElementById(formId).submit;
    }

</script>
</body>
</html>
