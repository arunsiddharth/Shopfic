<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- cart object -->
<%@include file="header.jsp" %>
<%@include file="middle.jsp" %>
<c:if test="${fn:length(cart) lt 1}">
   <p>Please Buy Some Product First</p>
</c:if>
<c:if test="${fn:length(cart) gt 0}">
	<form action="buycart" method="post">
		Mode of Payment : 
		<select name="cod" required>
			<option value="true">Cash On Delivery</option>
			<option value="false">Online</option>
		</select>
		<input type="submit" value="Buy Now"><br/>
	</form>
</c:if>
<c:forEach items="${cart}" var="cart">
	Product : ${cart.name}<br/>
	Cost : ${cart.cost}<br/>
	Amount You Wish : ${cart.count}<br/>
	<c:if test="${cart.request==0}">
	<form action="updatecart" method="post">
		Update Form : pid<input type="text" name="pid" value="${cart.pid}"/>
		Count : <input type="text" name="count"/>
		<input type="submit" value="Update Count"><br/>
	</form>
	<a href="deletecartproduct?pid=${cart.pid}">Delete From Cart</a>
	</c:if>
	<hr/>
</c:forEach>
<%@include file="footer.jsp" %>