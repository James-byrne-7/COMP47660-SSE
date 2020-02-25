<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<link rel="stylesheet" href="portal.css">
		<title>Login: Student Management System</title>
	</head>
	<body onload='document.login.username.focus();'>
		<div class="main" align="center">
			<p class="title">Sign in</p>
			<c:if test="${not empty errorMessage}"><div style="color:red; font-weight: bold; margin: 0;">${errorMessage}</div></c:if>
			<form name ="login" action="login" method="post">
				<input class="field" name="username" type="text" placeholder="Username">
				<input class="field" name="password" type="password" placeholder="Password">
				<input class="submit" type="submit" value="Sign in">
				<p><a href="/register" class="register">Register New Student</a></p>
			</form>
		</div>
	</body>

</html>