<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14.11.2022
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
  <title>Error</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta charset="UTF-8"/>
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/styles.css"/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/CSS/styles2.css"/>"/>
</head>
<body>


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
      <p class="welcome">No permission<br></p>
      <hr>
      <p>We are sorry, but you don't have permission to do that.</p>
      <p>You can log-in <b><a href="login">here with another account,</a></b></p>
      <p>or if you don't have an account, you can create one <b><a href="register">here.</a></b></p>
      <br></br>
      <p>If you have questions or problems, contact us. </p>
      <p>Learn more about us <b><a href="about">here.</a></b></p>
    </div>
  </article>
  <div class="main">
    <img src="<c:url value="/images/main.jpg"/>" alt="Img"/>
  </div>
</section>

<footer>
</footer>


</body>
</html>