<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mansishah.onlinebanking.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account </title>
<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li {
  float: left;
}

li a {
  display: inline-block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover {
  background-color: #228B22;
}

.active {
  background-color: red;
}
</style>

</head>
<body>
<form action="depositAmt" method="post">
	<h2> Hello ${CustomerItems.getName()}!! We Welcome you!!!</h2>
	<ul>
  		<li><a href="welcome.jsp">Account Summary</a></li>
  		<li><a href="deposit.jsp" class="active">Deposit</a></li>
  		<li><a href="transfer.jsp">Transfer</a></li>
  		<li><a href="transSummary.jsp"> Transfer Summary</a>
  		<li><a href="logout.jsp"> Logout</a>
	</ul>
	<div id="deposit">
		<table cellpadding ="15%" align="Center">
			<tr>
				<td> Deposit Amount : </td>
				<td> <input type ="number" name="depositAmt"> </td>
			</tr>
			<tr>
				<td colspan="2" align="center"> <input type="submit" name="submitDeposit" value="Deposit"> </td>
			</tr>
			<tr>
				<td> ${mesg} </td>
			</tr>		
		</table>
	</div>
</form>
</body>
</html>