<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp" %>
<%@include file="middle.jsp" %>
<c:if test="${fn:length(products) lt 1}">
   <p>Sorry No Product Found</p>
</c:if>
<c:forEach items="${products}" var="product">
		${product.name}<br/>
		${product.cost}<br/>
		<a href="viewproduct?pid=${product.pid}">View Product</a><br/>
</c:forEach>
<%@include file="footer.jsp" %>