<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page session="true" %>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/portal.css">
		<title>Login: Student Management System</title>
	</head>
	<body>
		<div class="main" align="center">
			<p class="title">Sign in</p>
			<c:if test="${not empty loginMessage}"><div style="color:red; font-weight: bold; margin: 0;">${loginMessage}</div></c:if>
			<form name ="login" action="/login" method="post">
				<input class="field" name="username" type="text" placeholder="Username" autofocus="true">
				<input class="field" name="password" type="password" placeholder="Password">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

				<input class="submit" type="submit" value="Sign in">

				<p><a href="/register" class="register">Register New Student</a></p>
			</form>
		</div>
	</body>

</html>