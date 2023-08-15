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
    <article>
        <div class="aboutUs">
            <p class="welcome">Your profile</p>
            <hr>
            <form:form action="/updateUser" method="post" modelAttribute="user">
        <div class="infoProfile">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <form:errors path="firstName" class="required profileError "/>
            <p class="info">First Name <span class="personal"><form:input path="firstName" class="personalInfo"/></span></p>
            <form:errors path="lastName" class="required profileError "/>
            <p class="info">Last Name <span class="personal"><form:input path="lastName" class="personalInfo"/></span></p>
            <p class="info">Email <span class="personal"><form:input disabled="true" path="email" class="personalInfo"/></span></p>
            <form:errors path="city" class="required profileError "/>
            <p class="info">City/Town/Village <span class="personal"><form:input path="city" class="personalInfo"/></span> </p>
            <form:errors path="address" class="required profileError "/>
            <p class="info">Address <span class="personal"><form:input path="address" class="personalInfo"/></span></p>
            <form:errors path="userDescription" class="required profileError "/>
            <p class="info">Description <span class="personal"><form:input path="userDescription" class="personalInfo"/></span></p>
            <form:errors path="job" class="required profileError "/>
            <p class="info">Job <span class="personal"><form:input path="job" class="personalInfo"/></span></p>
            <p class="info">Orders <span class="personal"><form:input disabled="true" path="orders" class="personalInfo"/></span></p>
            <form:errors path="password" class="required profileError "/>
            <p class="info">New Password <span class="personal"><form:password path="password" class="personalInfo"/></span></p>
            <div class="save"><input type="submit" value="Submit" style="
    width: 100%;
    height: inherit;
    background-color: transparent;
    border-radius: 0px;
    border: 0px;
    font: inherit;
    color: inherit;
    cursor: pointer"
            />
            <br>
            <br>
        </div>
            </form:form>
        </div>
    </article>



</section>


<footer>
</footer>


</body>
</html>