<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
</head>
<body>
<form action="loginRegister" method="post">
	<table  style="background-color: lightgreen; margin-left: 20px; margin-left: 20px; align: center" >
		<tr>
			<td> <h3 style="color: Red; margin-left: 20px;"> Create Account </h3> </td>
		</tr>
		<tr>
			<td>User Name : </td> <td> <input type="text" name="username">  </td>
		</tr>
		
		<tr>
			<td>Password : </td> <td> <input type="password" name="password1"> </td> 
		</tr>
		<tr>
			<td>Re-Type Password : </td> <td> <input type="password" name="password2"></td> 
		</tr>
		<tr>
			<td>Name : </td> <td> <input type="text" name="name"></td> 
		</tr>
		<tr>
			<td>Address : </td> <td> <input type="text" name="address"></td> 
		</tr>
		<tr>
			<td>Phone Number : </td> <td> <input type="text" name="ph_num"></td> 
		</tr>
		<tr>
			<td>Email-id : </td> <td> <input type="text" name="email"></td> 
		</tr>
		<tr>
			<td>SSN : </td> <td> <input type="text"  name="SSN"></td> 
		</tr>
		<tr>
			<td>Date of Birth : </td> <td> <input type="text"  id="datepicker" name="dob"></td> 
		</tr>
		<tr>
			<td> <input type="submit" name="submit" value="Create"></td>  
		</tr>
		<tr style="color: red; align:center">
			<td >
			<h6> ${errorInUName}</h6>
			<h6> ${ErrInPswd}</h6>
			<h6> ${PswdNotMatch}</h6>
			</td>
		</tr>
	</table>
</form>
</body>
</html>