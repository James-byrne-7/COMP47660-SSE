<html>
	<head>
		<title>Login: Student Management System</title>
	</head>
<body>
	<center>
		<h1>Login Page</h1>
		<form action="login" method="post">
			<table>
				<tr>
					<td>UserName</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
			</table>
			<input type="submit" value="Login" />
		</form>
		<form action="register" method="get">
			<input type="submit" value="New User?" />
		</form>
	</center>
</body>
</html>