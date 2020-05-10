<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" href="portal.css">
    <script src="/password_strength.js"></script>
    <title>Register: Student Management System</title>
</head>
<body>

<div class="main" align="center">
    <p class="title">Register Student</p>
    <c:if test="${not empty errorMessage}"><div style="color:red; font-weight: bold; margin: 0;">${errorMessage}</div></c:if>
    <form:form action="/register" method="post" modelAttribute="newUser" onsubmit="return checkPasswordMatches()">
        <form:input class="field" type="text" placeholder="Username" path="username"/>
        <form:errors path="username"></form:errors>
        <form:input class="field" type="password" placeholder="Password" path="password" id="password" onkeyup="CheckPasswordStrength(this.value)"/>
        <form:errors path="password"></form:errors>
        <input class="field" type="password" placeholder="Confirm Password" id="confirmPassword" onkeyup="checkPasswordMatches()" />
        <div>
            <input type="checkbox" onclick="toggleShowPassword()" id="show"><label for="show">Show Password</label>
            <span  id="passwordStrength"></span>
            <span  id="passwordsMatch"></span>
        </div>
        <form:input class="field" type="text" placeholder="Name" path="firstname" />
        <form:input class="field" type="text" placeholder="Surname" path="lastname" />
        <div class="field">
            <form:radiobutton path="sex" Value="M"/>Male
            <form:radiobutton path="sex" Value="F"/>Female
        </div>
        <form:errors path="sex"></form:errors>
<%--        <form:input class="field" type="text" placeholder="Student ID" path="student_id" />--%>
<%--        <form:input class="field" type="text" placeholder="Address" path="address" />--%>
<%--        <form:input class="field" type="text" placeholder="Phone No." path="phone_number" />--%>
<%--        <form:input class="field" type="text" placeholder="Email Address" path="email_address" />--%>
        <form:select class="field" size="5" path="nationality">
            <OPTION selected disabled>Select Your Nationality:</OPTION>
            <OPTION Value="Irish">Irish</OPTION>
            <OPTION Value="British">British</OPTION>
            <OPTION Value="Spanish">Spanish</OPTION>
            <OPTION Value="Italian">Italian</OPTION>
            <OPTION Value="French">French</OPTION>
            <OPTION Value="German">German</OPTION>
            <OPTION Value="Portuguese">Portuguese</OPTION>
            <OPTION Value="Finnish">Finnish</OPTION>
            <OPTION Value="Swedish">Swedish</OPTION>
            <OPTION Value="American">American</OPTION>
        </form:select>
        <form:errors path="nationality"></form:errors>
        <input class="submit" type="submit" value="Register">
        <p><a href="/login" class="register">Already Registered?</a></p>
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
