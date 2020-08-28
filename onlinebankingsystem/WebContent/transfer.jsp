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
	<form action="transferAction" method="post">
	<h2> Hello ${CustomerItems.getName()}!! We Welcome you!!!</h2>
	<ul>
  		<li><a href="welcome.jsp">Account Summary</a></li>
  		<li><a href="deposit.jsp" >Deposit</a></li>
  		<li><a href="transfer.jsp" class="active">Transfer</a></li>
  		<li><a href="transSummary.jsp"> Transfer Summary</a>
  		<li><a href="logout.jsp"> Logout</a>
	</ul>
	<div id="transfer">
		<table cellpadding ="15%" align="Center">
			<tr>
				<td> Transfer Account : </td>
				<td> <input type ="number" name="transAct"> </td>
			</tr>
			<tr>
				<td> Transfer Amount : </td>
				<td> <input type ="number" name="transAmt"> </td>
			</tr>
			<tr>
				<td> Current Balance: : </td>
				<td> ${actBalance.getBalance()} </td>
			</tr>
			<tr>
				<td colspan="2" align="center"> <input type="submit" name="submitTrans" value="Transfer"> </td>		
			</tr>
			<tr>
				<td> 
					<h3 style="color: green"> ${transMsgS}</h3>
					<h3 style="color: red"> ${transMsgE}</h3>
					<h3 style="color: red"> ${AmtMsg}</h3>
					<h3 style="color: red"> ${AccMsg} </h3>
				</td>
			</tr>
		</table>
	</div>
	</form>
</body>
</html>