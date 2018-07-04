<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp" %>
<c:forEach var="category" items="${productlist}">
  Category: <c:out value="${category.key}"/>
  <c:forEach var="subcategory" items="${category.value}">
  	Subcategory : <c:out value="${subcategory.key}"/>&nbsp;
  	<c:out value="${subcategory.value}"/>
  	<br/>
  </c:forEach>
</c:forEach>
<%@include file="footer.jsp" %>