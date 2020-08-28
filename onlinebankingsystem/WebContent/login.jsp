<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Banking System</title>
</head>
<body>
<form action="loginRegister" method ="post">
	<table  style="background-color: skyblue; margin-left: 20px; margin-left: 20px;" align="center">
		<tr>
			<td> 
				<h3 style="color: red;">${message}</h3>
				<h3 style="color: green;">${successMessage} </h3>
			</td>
		</tr>
		<tr align="center">
			<td> <h3 style="color: Red; margin-left: 20px;">Login Page</h3> </td>
		</tr>
		<tr>
			<td>Username: </td> <td> <input type="text" name="name"></td> 
		</tr>
		<tr>
			<td>Password: </td> <td> <input type="password" name="password"></td> 
		</tr>
		<tr>
			<td> <input type="submit" name="submit" value="login"></td> 
			<td> <a href="register.jsp">Create account </a></td> 
		</tr>
	</table>
</form>

</body>
</html>