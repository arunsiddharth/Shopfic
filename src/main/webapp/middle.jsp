<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Show Product list -->
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
