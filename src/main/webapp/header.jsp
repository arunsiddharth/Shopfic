<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WELCOME</title>
</head>
<body>
	<%
		if(session.getAttribute("uid")==null){
			out.println("<a href='login.jsp'>LOGIN</a>");
		}
		else{
			out.println("Welcome"+session.getAttribute("firstname"));
		}
	%>
