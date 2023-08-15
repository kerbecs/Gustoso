<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <title>Gustoso</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/styles.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/styles2.css"/>"/>
</head>
<body class="scroll">


<nav>
    <div class="buttons">
        <a href="<c:url value="/"/>" class="phone ">Home</a>
        <a href="<c:url value="/aboutUs"/>" class="phone phoneButton">About Us</a>
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
    <div class="mainText">
        Juicy & Hoot
        <br>Food
        <br>
        <span>Best gastronomic expirience!</span>
        <a href="orderPage"><input type="button" value="Order Food" class="order"/></a>
    </div>


</section>


<footer>
</footer>


</body>
</html>