<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
+
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
        <div class="regZone">
            <span><font>Register</font></span>
            <form:form action="register" method="post" modelAttribute="userRegister" cssStyle="margin-bottom: 20px">
                <form:input path="userDescriptionDto.firstName" class="fname" placeholder="First Name" id="FN"/>
                <form:input path="userDescriptionDto.lastName" class="lname" placeholder="Last Name" id="LN"/>
                <form:errors path="userDescriptionDto.firstName" class="required requiredfn" id="req1"/>
                <form:errors path="userDescriptionDto.lastName" class="required requiredln" id="req2"/>

                <form:input path="userDescriptionDto.email" placeholder="E-mail Address" class="email" id="email"/>
                <form:errors path="userDescriptionDto.email" class="required requiredfn " id="req3"/>

                <form:input path="username" placeholder="Username" class="username" id="username"/>
                <form:errors path="username" class="required requiredfn " id="req4"/>
                <br>
                <form:password path="password" placeholder="Password" class="password" id="pass1"/>
                <form:errors path="password" class="required requiredfn " id="req5"/>
                <br>
                <form:password path="repeatPassword" placeholder="Repeat your password" class="password" id="pass2"/>
                <form:errors path="repeatPassword" class="required requiredfn " id="req6"/>
                <br>

                <form:input path="userDescriptionDto.city" class="fname place" placeholder="City/Town/Village"/>
                <form:input path="userDescriptionDto.address" class="lname place" placeholder="Address"/>
                <form:errors path="userDescriptionDto.city" class="required requiredfn" id="req1"/>
                <form:errors path="userDescriptionDto.address" class="required requiredln" id="req2"/>
                <br>
                <input type="checkbox" required class="term_button" style="width: 15px; height: 15px; margin-left: 0px;margin-top: 28px"/><span
                    class="agree_term">I agree to the terms and conditions and the privacy policy</span>
                <br>
                <input type="submit" class="create_acc" value="Create Account"/>

            </form:form>
        </div>
    </article>
    <article>

    </article>

</section>


<footer>
</footer>


</body>
</html>