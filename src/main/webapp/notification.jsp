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
	<a href="notification?view=oos">Out Of Stock</a>
	<a href="notification?view=pr">New Requests</a>
	<a href="notification?view=prp">Request Pending</a>
	<a href="notification?view=prf">Request Completed</a>
	<c:if test="${fn:length(notification) lt 1}">
		No Notifications
	</c:if>
	<c:forEach items="${notification}" var="note">
		Product Name : ${notification.name}<br/>
		Product Cost : ${notification.cost}<br/>
		Product Stock : ${notification.stock}<br/>
		<c:if test="${oos!='true'}">
		Purchase Mode : ${notification.cod}<br/>
		Amount Requested : ${notification.count}<br/>
		Date of Order : ${notification.date_request}<br/>
		Name : ${notification.user.firstname}&nbsp;${notification.user.lastname}<br/>
		Homephone : ${notification.user.homephone}<br/>
		Address : ${notification.user.address.address1}&nbsp;${notification.user.address.address2}<br/>
		City : ${notification.user.address.city}<br/>
		State : ${notification.user.address.state}<br/>
		Country : ${notification.user.address.country}<br/>
		ZIP : ${notification.user.address.zip}<hr/>
		</c:if>
	</c:forEach>
</body>
</html>