<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Show Product list -->

	<div class="span9">
    <ul class="breadcrumb">
		<li><a href="index.html">Home</a> <span class="divider">/</span></li>
		<li class="active">Products Name</li>
    </ul>
    <h3>
    <c:if test="${fn:length(products) lt 1}">
   	<p>Sorry No Product Found</p>
	</c:if>
	<c:if test="${fn:length(products) gt 0}">
		Products List <small class="pull-right"> ${fn:length(products)} product available </small>
	<hr class="soft"/>
		<form class="form-horizontal span6">
		<div class="control-group">
		  <label class="control-label alignL">Sort By </label>
			<select>
              <option>Product name A - Z</option>
              <option>Product name Z - A</option>
              <option>Product Stock</option>
              <option>Price Lowest first</option>
            </select>
		</div>
	  </form>
	</c:if>
	</h3>	

<br class="clr"/>

<div class="tab-content">
	<div class="tab-pane  active" id="blockView">
	
		<ul class="thumbnails">
			<c:forEach items="${products}" var="product">
				<li class="span3">
			  	<div class="thumbnail">
					<a href="viewproduct?pid=${product.pid}"><img src="<c:url value="/resources/themes/images/products/${product.image_path}"/>" alt=""/></a>
					<div class="caption">
				  		<h5>${product.name}</h5><br/>
				  		<h5>${product.cost}</h5>
				  		<p> 
							${product.short_description}
				  		</p>
				   		<h4 style="text-align:center"> <a class="btn" href="viewproduct?pid=${product.pid}">View Product</a> <a class="btn btn-primary" href="#">&euro;${product.cost}</a></h4>
					</div>
				</div>
				</li>
			</c:forEach>
		  </ul>
		  
	</div>
</div>
<c:if test="${fn:length(products) gt 0}">

	<hr class="soft"/>
	<div class="pagination">
			<ul>
			<li><a href="#">&lsaquo;</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">...</a></li>
			<li><a href="#">&rsaquo;</a></li>
			</ul>
			</div>
			<br class="clr"/>
	</div>
</c:if>
	
	

