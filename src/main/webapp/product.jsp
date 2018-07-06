<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp" %>
<%@include file="middle.jsp" %>
Product Details : <br/>
${product.name}<br/>
${product.price}<br/>
${product.discount}<br/>
${product.cost}<br/>
${product.stock}<br/>
${product.brand}<br/>
${product.version}<br/>
${product.short_description}<br/>
${product.features}<br/>
${product.image_path}<br/>
Add To Cart :
<form action="addcart" method="post">
	<input type="text" name="pid" value="${product.pid}"/><br/>
	<input type="number" min=0 name="count" value="${product.pid}"/><br/>
	<input type="submit" value="Add to Cart"/><br/>
</form>


Rating And Comments :
<form action="addrating" method="post">
	<input type="text" name="pid" value="${ratings.pid}">
	Rating : <input type="text" name="rating"/><br/>
	Comment : <input type="text" name="comment"/><br/>
	<input type="submit" value="Submit Rating"/><br/>
</form>
<c:set var="ratings" value="${ratings}"/>
Rating of this product is : ${ratings.rating}<br/>
<c:forEach var="comment" items="${ratings.comments}">
	Name : ${comment.name}<br/>
	Comment : ${comment.comment}<br/>
	Rating Given : ${comment.rating}<br/>
	Added : ${comment.date}<br/>
</c:forEach>
<%@include file="footer.jsp" %>