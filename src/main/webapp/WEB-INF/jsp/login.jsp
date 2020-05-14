<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Login: Student Management System</title>

    <link rel="stylesheet" type="text/css" href="${contextPath}/portal.css">

</head>

<body>

<div class="main" align="center">
    <div class="title">Sign in</div>

    <c:if test="${not empty loginMessage}"><div style="color:red; font-weight: bold; margin: 0;">${loginMessage}</div></c:if>
    <form action="${contextPath}/login" method="POST">
        <input class="field" name="username" type="text" placeholder="Username" autofocus="true">
        <input class="field" name="password" type="password" placeholder="Password">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <input class="submit" type="submit" value="Sign in">
    </form>
    <p><a href="${contextPath}/register" class="register">Register New Student</a></p>
    <p><a href="${contextPath}/forgotpassword" class="register">Forgot Password?</a></p>
</div>
</body>

</html>