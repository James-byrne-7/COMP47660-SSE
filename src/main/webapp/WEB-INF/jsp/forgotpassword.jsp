
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Forgot Password: Student Management System</title>

    <link rel="stylesheet" type="text/css" href="${contextPath}/portal.css">

</head>

<body>

<div class="main" align="center">
    <p class="title">Forgot Password</p>

    <form modelAttribute="email" method="POST">
        <form:input class="field" name="email" path="email" type="text" placeholder="Your Email Address" autofocus="true"/>
        <input class="submit" type="submit" value="Request Password Reset">
    </form>
    <p><a href="${contextPath}/login" class="register">Return to login</a></p>
</div>

</body>
</html>