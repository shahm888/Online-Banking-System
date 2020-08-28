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
  		<li><a href="welcome.jsp">Account Summary</a></li>
  		<li><a href="deposit.jsp" >Deposit</a></li>
  		<li><a href="transfer.jsp" >Transfer</a></li>
  		<li><a href="transSummary.jsp" class="active"> Transfer Summary</a>
  		<li><a href="logout.jsp"> Logout</a>
	</ul>
	<div id="transfer">
		<table border=1 cellpadding ="15%" align="Center">
			<tr>
				<td> ID </td>
				<td> Date/Time </td>
				<td> Amount </td>
				<td> Type </td>
				<td> Transfer to </td>
			</tr>
			<c:forEach items = "${transList}" var="curTrans">
				<tr>
					<td> ${curTrans.getTransId()} </td>
					<td> ${curTrans.getTransDate()} </td>
					<td> ${curTrans.getTransAmt()} </td>
					<td> ${curTrans.getTransType()} </td>
					<td> ${curTrans.getTransTo()} </td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>