<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Reset Password: Student Management System</title>

    <link rel="stylesheet" type="text/css" href="${contextPath}/portal.css">
    <script src="password_strength.js"></script>

</head>

<body>

<div class="main" align="center">
    <p class="title">Reset Password</p>

    <form:form method="POST" modelAttribute="user" onsubmit="return checkPasswordMatches()">
        <form:hidden path="email"/>
        <form:input class="field" path="password" type="password" placeholder="New Password" onkeyup="CheckPasswordStrength(this.value)"/>
        <form:errors path="password"/>
        <input class="field" type="password" placeholder="Confirm Password" id="confirmPassword" onkeyup="checkPasswordMatches()" />
        <div>
            <input type="checkbox" onclick="toggleShowPassword()" id="show"><label for="show">Show Password</label>
            <span  id="passwordStrength"></span>
            <span  id="passwordsMatch"></span>
        </div>

        <input class="submit" type="submit" value="Reset Password">

    </form:form>

    <script>
        function toggleShowPassword() {
            let x = document.getElementById("password");
            let y = document.getElementById("confirmPassword");
            if (x.type === "password" && y.type === "password") {
                x.type = "text";
                y.type = "text";
            } else {
                x.type = "password";
                y.type = "password";
            }
        }
        function checkPasswordMatches() {
            let x = document.getElementById("password");
            let y = document.getElementById("confirmPassword");
            if (x.value === y.value) {
                document.getElementById("passwordsMatch").innerHTML = '';
                return true;
            } else {
                document.getElementById("passwordsMatch").innerHTML = 'Passwords do not Match';
                return false;
            }
        }
    </script>
</div>


</body>
</html>