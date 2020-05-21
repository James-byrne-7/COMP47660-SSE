<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="portal.css">
    <title>Fee Payment System</title>
</head>
<body onload='document.login.username.focus();'>
<div class="main" align="center">
    <p class="title">Fee Payment</p>
    <c:if test="${not empty successMessage}"><div style="color:red; font-weight: bold; margin: 0;">${successMessage}</div></c:if>
    <form name ="fees" action="/fees" method="post">
        <p>YOU OWE : ${amount}</p>
        <input class="field" name="payment" type="text" placeholder="Amount you wish to pay">
        <input class="submit" type="submit" value="Process Payment">
        <p><a href="/home" class="register">Return to Home</a></p>
    </form>
</div>
</body>

</html>