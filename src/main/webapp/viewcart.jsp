<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- cart object -->
<%@include file="header.jsp" %>
<%@include file="middle2.jsp" %>
<div class="span9">
    <ul class="breadcrumb">
    <li><a href="index.html">Home</a> <span class="divider">/</span></li>
    <li class="active">Cart</li>
    
    </ul>	
	<div class="row">	  
			
			<div class="span6">
				
				<c:if test="${fn:length(cart) lt 1}">
   					<h3>Please Buy Some Product First</h3>
				</c:if>
				
<c:if test="${view=='new'}">
	<form class="form-horizontal" action="buycart" method="post">
		<div class="control-group">
					<label class="control-label"><span>Mode of Payment :</span></label>
					<div class="controls">
					<select name="cod" required>
						<option value="true" selected>Cash On Delivery</option>
						<option value="false">Online</option>
					</select>
					 <button type="submit" class="btn btn-medium btn-primary pull-right"> Buy Now <i class=" icon-shopping-cart"></i></button>
					</div>
		</div> 
	</form>
</c:if>
<hr class="soft">
<div class="tab-pane" id="listView">	
<c:forEach items="${cart}" var="carto">	
		<div class="row">	  
			<div class="span8">		
				Product : <a href="viewproduct?pid=${product.pid}">${carto.name}</a><br/>
				Cost : ${carto.cost}<br/>
				Amount You Wish : ${carto.count}<br/>
				Mode of Payment : <c:if test="${carto.cod==1}">COD</c:if><c:if test="${carto.cod==0}">Online</c:if><br/>
				<c:if test="${carto.request==1}">
				Dispatched : <c:if test="${carto.mark==1}">Yes</c:if><c:if test="${carto.mark==0}">No</c:if><br/>
				Brought : <c:if test="${carto.brought==1}">Yes</c:if><c:if test="${carto.brought==0}">No, Arriving Soon!</c:if>
				</c:if>
				<c:if test="${carto.request==0}">
	<form action="updatecart" method="post">
		Update Form : <input type="text" name="pid" value="${carto.pid}" style="display:none"/>
		Count : <input type="number" min=0 max=50 name="count"/>
		<button type="submit" class="btn btn-medium btn-primary"> Update <i class=" icon-shopping-cart"></i></button>
		<button class="btn btn-medium btn-primary"><a href="deletecartproduct?pid=${carto.pid}">Delete From Cart</a> <i class=" icon-shopping-cart"></i></button>
	</form>
				</c:if>
			</div>
		</div>
		<hr class="soft"/>
</c:forEach>
</div>
	</div>
	</div>
<%@include file="footer.jsp" %>