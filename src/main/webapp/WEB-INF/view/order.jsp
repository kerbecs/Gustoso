<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
    <title>Gustoso</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/styles.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/styles2.css"/>"/>

        <script type="text/javascript" src="/resources/JS/Order.JS"></script>


</head>
<body class="scroll">

<nav>
    <div class="buttons">
        <a href="<c:url value="/"/>" class="phone ">Home</a>
        <a href="/images/about.html" class="phone phoneButton">About Us</a>
        <a href="<c:url value="/orderPage"/>" class="phone phoneButton">Order</a><img src="/images/buy.png" class="icon"/>
        <security:authorize access="!isAuthenticated()">
            <a href="<c:url value="/loginPage"/>" class="phone phoneButton">Log in</a>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <a href="<c:url value="/profile"/>" class="phone phoneButton">Profile</a>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <form:form action="logout" method="post" cssStyle="display: inline-block;">
                <a href="javascript:;" onclick="parentNode.submit()" class="phone phoneButton">Logout</a>
            </form:form>
        </security:authorize>
        <a href="" class="phone"><img src="/images/facebook.png" class="facebook"/></a>
        <a href="" class="phone"><img src="/images/instagram.png" class="instagram"/></a>
    </div>

</nav>


    <header>
        <a href="<c:url value="/"/>">
            <font>Gustoso</font>
            <img src="/images/logo.png"/>
        </a>
    </header>


<section>
    <article>
        <form:form method="post" action="makeOrder" modelAttribute="order">
        <div class="orderProduct">
            <p class="welcome">Order Food <br>Online</p>
                <c:forEach var="produce" items="${produces}">
            <p class="product">
                <span class="img"><img src="/images/${produce.image}"></span>
                <span class="description">
                    <b style="font-size: 20px;">${produce.name}</b>
                    <br>
                    <br>
                    <b>Ingredients:</b> ${produce.ingredients}
                    <br><b>Weight:</b> ${produce.weight}
                    <br><b>Price: ${produce.price}</b>
                </span>
                <span class="addButton">
                    <b>Add to list</b>
                    <br>

                    <form:checkbox path="orderedProduceList"  class="add" value="${produce.id}" onclick="check(this,'${produce.price}')"/>
                </span>
            </p>
                </c:forEach>
            
            <div class='totalDisplay'>Total producst: <span id='totalCount'></span></div>
            <br>
            <div class='totalDisplay'>Total to pay: <span id='totalPay'></span> $</div>
            <input type="submit" value="Sumbit">
            </form:form>
        </div>
    </article>


</section>


<footer>
</footer>


</body>
</html>