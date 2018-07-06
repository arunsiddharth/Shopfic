<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seller Index</title>
</head>
<body>
	<%
		if(session.getAttribute("sid")==null){
			out.println("<a href='login.jsp'>LOGIN</a>");
		}
		else{
			out.println("Welcome "+session.getAttribute("firstname"));
		}
	%>
	<br/><a href="add">Add</a><br/>
	<a href="viewprofile">View Profile</a><br/>
	<a href="notification?view=oos">Notification</a><br/>
	<a href="index">Home</a><br/>
	<a href="logout">Logout</a><br/><br/>
	
	Your Products : <br/>
	<c:forEach items="${products}" var="product">
		${product.name}<br/>
		${product.cost}<br/>
		<a href="update?pid=${product.pid}">Update</a><br/>
		<a href="delete?pid=${product.pid}">Delete</a><br/>
	</c:forEach>
</body>
</html>