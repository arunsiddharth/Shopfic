<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="seller_header.jsp" %>

<div class="span13">
    <ul class="breadcrumb">
    <li><a href="seller_index">Home</a> <span class="divider">/</span></li>
    <li><a href="#">Products</a> <span class="divider">/</span></li>
    </ul>	
<h3>Your Products</h3>
<hr class="soft"/>
<div class="tab-pane" id="listView">	
<c:forEach items="${products}" var="product">	
		<div class="row">	  
			<div class="span12">		
				Name : ${product.name}<br/>
				Cost : ${product.cost}
				<button class="btn btn-medium btn-primary pull-right"><a href="update?pid=${product.pid}">Update</a> <i class=" icon-shopping-cart"></i></button>
				<button class="btn btn-medium btn-primary pull-right"><a href="delete?pid=${product.pid}">Delete</a><i class=" icon-shopping-cart"></i></button>
			</div>
		</div>
		<hr class="soft"/>
</c:forEach>
</div>


	</div>
<%@include file="seller_footer.jsp" %>
