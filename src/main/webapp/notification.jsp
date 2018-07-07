<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	<a href="index">Home</a><br/>
	<a href="logout">Logout</a><br/><br/>
	<a href="notification?view=oos">Out Of Stock</a>
	<a href="notification?view=pr">New Requests</a>
	<a href="notification?view=prp">Request Pending</a>
	<a href="notification?view=prf">Request Completed</a>
	<c:if test="${fn:length(notification) lt 1}">
		No Notifications
	</c:if>
	<c:forEach items="${notification}" var="note">
		Product Name : ${note.name}<br/>
		Product Cost : ${note.cost}<br/>
		Product Stock : ${note.stock}<br/>
		<c:if test="${oos!='true'}">
		Purchase Mode : <c:if test="${note.cod=='1'}">COD</c:if><c:if test="${note.cod=='0'}">Online</c:if><br/>
		Amount Requested : ${note.count}<br/>
		Date of Order : ${note.date_request}<br/>
		Name : ${note.user.firstname}&nbsp;${note.user.lastname}<br/>
		Homephone : ${note.user.homephone}<br/>
		Address : ${note.user.address.address1}&nbsp;${note.user.address.address2}<br/>
		City : ${note.user.address.city}<br/>
		State : ${note.user.address.state}<br/>
		Country : ${note.user.address.country}<br/>
		ZIP : ${note.user.address.zip}<hr/>
		</c:if>
	</c:forEach>
	<c:if test="${marker && fn:length(notification) gt 0}"><a href="productsmark">MARK</a></c:if>
</body>
</html>