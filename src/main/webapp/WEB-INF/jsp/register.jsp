<html>
<head>
    <link rel="stylesheet" href="portal.css">
    <title>Register: Student Management System</title>
</head>
<body>

<div class="main" align="center">
    <p class="title">Register Student</p>
    <c:if test="${not empty errorMessage}"><div style="color:red; font-weight: bold; margin: 0;">${errorMessage}</div></c:if>
    <form action="/register" method="post">
        <input class="field" type="text" placeholder="Username [REQUIRED]" name="username" />
        <input class="field" type="password" placeholder="Password [REQUIRED]" name="password" />
        <input class="field" type="text" placeholder="Name" name="name" />
        <input class="field" type="text" placeholder="Surname" name="surname" />
        <input class="field" type="text" placeholder="Sex: 'M' or 'F' [REQUIRED]" name="sex" />
        <input class="field" type="text" placeholder="Nationality [REQUIRED]" name="nationality" />
        <input class="field" type="text" placeholder="Student ID" name="student_id" />
        <input class="field" type="text" placeholder="Address" name="address" />
        <input class="field" type="text" placeholder="Phone No." name="phone_number" />
        <input class="field" type="text" placeholder="Email Address" name="email_address" />
        <input class="submit" type="submit" value="Register">
        <p><a href="/login" class="register">Already Registered?</a></p>
    </form>
</div>
</body>

</html>
