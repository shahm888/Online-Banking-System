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
	<h2> Hello ${CustomerItems.getName()}!! We Welcome you!!!</h2>
	<ul>
  		<li><a href="#AccountSummary" class="active">Account Summary</a></li>
  		<li><a href="deposit.jsp">Deposit</a></li>
  		<li><a href="transfer.jsp">Transfer</a></li>
  		<li><a href="transSummary.jsp"> Transfer Summary</a>
  		<li><a href="logout.jsp"> Logout</a>
	</ul>
	<div id="AccountSummary">
		<h1 align="Center"> Account Details: </h1>
		<table cellpadding= 15% align= "center">
		<tr> <td> Account Number :</td><td>${CustomerItems.getCust_acc_no()}</td> </tr>
		<tr> <td> Name :</td><td>${ CustomerItems.getName()} </td> </tr>
		<tr> <td> Balance: </td> <td> ${actBalance.getBalance()}</td></tr>
		<tr> <td> Address :</td><td>${CustomerItems.getAddress()}</td> </tr>
		<tr> <td> Email :</td><td>${CustomerItems.getEmail()}</td> </tr>
		<tr> <td> Phone Number :</td><td>${CustomerItems.getPh_num()}</td> </tr>
		<tr> <td> SSN :</td><td>${CustomerItems.getSSN()}</td> </tr>
		<tr> <td> Date Of Birth :</td><td> ${CustomerItems.getDob()}</td> </tr>
		
		</table>
	</div>
</body>
</html>