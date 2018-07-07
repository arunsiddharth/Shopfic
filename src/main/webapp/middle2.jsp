<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Show Product list -->
	<div id="mainBody">
	<div class="container">
	<div class="row">
<!-- Sidebar ================================================== -->
	<div id="sidebar" class="span3">
		<div class="well well-small"><a id="myCart" href="product_summary.html"><img src="<c:url value="/resources/themes/images/ico-cart.png"/>" alt="cart">View Cart  <span class="badge badge-warning pull-right">$155.00</span></a></div>
		<ul id="sideManu" class="nav nav-tabs nav-stacked">

<c:forEach var="category" items="${productlist}">
  <li class="subMenu open"><a href="category?category=${category.key}">${category.key}</a>
  <ul style="display:none">
  		<li><a href="category?category=${category.key}"><i class="icon-chevron-right"></i>All</a></a>
  <c:forEach var="subcategory" items="${category.value}">
  	<c:if test = "${subcategory.key != 'cid' && subcategory.key != 'count'}">
  		<li><a href="subcategory?category=${category.key}&subcategory=${subcategory.key}"><i class="icon-chevron-right"></i>${subcategory.key}[<c:out value="${subcategory.value}"/>]</a>
  	</c:if>
  </c:forEach>
  </ul>
  </li>
</c:forEach>
	
		<br/>
			  <div class="thumbnail">
				<img src="<c:url value="/resources/themes/images/payment_methods.png"/>" title="Bootshop Payment Methods" alt="Payments Methods">
				<div class="caption">
				  <h5>Payment Methods</h5>
				</div>
			  </div>
	</div>

<br/>
