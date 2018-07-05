<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp" %>
<form action="search" method="get">
	<input type="text" name="pattern"/>
	<input type="submit" value="Search"/>
</form><br/>
<c:forEach var="category" items="${productlist}">
  <a href="category?category=${category.key}">${category.key}</a><br/>
  <c:forEach var="subcategory" items="${category.value}">
  	<c:if test = "${subcategory.key != 'cid' && subcategory.key != 'count'}">
  		<a href="subcategory?category=${category.key}&subcategory=${subcategory.key}">${subcategory.key}</a> &nbsp;
  		<c:out value="${subcategory.value}"/><br/>
  	</c:if>
  </c:forEach>
  <br/>
</c:forEach>
<br/>
<c:if test="${fn:length(products) lt 1}">
   <p>Sorry No Product Found</p>
</c:if>
<c:forEach items="${products}" var="product">
		${product.name}<br/>
		${product.cost}<br/>
		<a href="viewproduct?pid=${product.pid}">View Product</a><br/>
</c:forEach>
<%@include file="footer.jsp" %>