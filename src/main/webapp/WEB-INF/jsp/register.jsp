<html>
<head>
    <link rel="stylesheet" href="portal.css">
    <title>Register: Student Management System</title>
</head>
<body>

<div class="main" align="center">
    <p class="title">Register Student</p>
    <form action="register" method="post">
        <input class="field" type="text" placeholder="Name" name="name" />
        <input class="field" type="text" placeholder="Password" name="password" />
        <input class="field" type="text" placeholder="Surname" name="surname" />
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
