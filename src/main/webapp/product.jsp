<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp" %>
<%@include file="middle2.jsp" %>
<div class="span9">
    <ul class="breadcrumb">
    <li><a href="index.html">Home</a> <span class="divider">/</span></li>
    <li><a href="products.html">Products</a> <span class="divider">/</span></li>
    <li class="active">product Details</li>
    </ul>	
	<div class="row">	  
			<div id="gallery" class="span3">
            <a href="<c:url value="/resources/themes/images/products/${product.images[0]}"/>" title="Fujifilm FinePix S2950 Digital Camera">
				<img src="<c:url value="/resources/themes/images/products/${product.images[0]}"/>" style="width:100%" alt="No Image"/>
            </a>
			<div id="differentview" class="moreOptopm carousel slide">
                <div class="carousel-inner">
                  <div class="item active">
                   <c:forEach items="${product.images}" var="image">
                   <a href="<c:url value="/resources/themes/images/products/${image}"/>"> <img style="width:29%" src="<c:url value="/resources/themes/images/products/${image}"/>" alt=""/></a>
                   </c:forEach>
                  </div>
                  <div class="item">
                   <c:forEach items="${product.images}" var="image">
                   <a href="<c:url value="/resources/themes/images/products/${image}"/>"> <img style="width:29%" src="<c:url value="/resources/themes/images/products/${image}"/>" alt=""/></a>
                   </c:forEach>
                  </div>
                </div>
              
              </div>
			  
			 <div class="btn-toolbar">
			  <div class="btn-group">
				<span class="btn"><i class="icon-envelope"></i></span>
				<span class="btn" ><i class="icon-print"></i></span>
				<span class="btn" ><i class="icon-zoom-in"></i></span>
				<span class="btn" ><i class="icon-star"></i></span>
				<span class="btn" ><i class=" icon-thumbs-up"></i></span>
				<span class="btn" ><i class="icon-thumbs-down"></i></span>
			  </div>
			</div>
			</div>
			<div class="span6">
				<h3>${product.name}  </h3>
				<small>- Brand : ${product.brand}&nbsp;&nbsp;Version :  ${product.version}</small>
				<hr class="soft"/>
				<form class="form-horizontal qtyFrm" action="addcart" method="post">
				  <div class="control-group">
					<label class="control-label"><span>Price: Rs. ${product.cost}</span></label>
					<div class="controls">
					<input class = "pull-right" type="text" name="pid" value="${product.pid}" style="display:none"/>
					Quantity : <input type="number" min=1 max=100 name="count" class="span1" placeholder="Qty." required/>
					  <button type="submit" class="btn btn-medium btn-primary pull-right"> Add to cart <i class=" icon-shopping-cart"></i></button>
					</div>
				  </div>
				</form>
				
				<hr class="soft"/>
				
				<h4>${product.stock} items in stock</h4>
					<h5>Market Price : Rs. ${product.price}</h5>
					<h5>Discount : ${product.discount}</h5>
				<hr class="soft clr"/>
				<h4>Features </h4>			
				<p>
					${product.features}
				</p>
				<br class="clr"/>
			<hr class="soft"/>
			</div>
<h3>Ratings And Reviews</h3>
<c:set var="ratings" value="${ratings}"/>
<h4>Average Rating : ${ratings.rating} out of 5.0</h4>
<hr class="soft"/>
<h5>Comments : </h5>
<div class="tab-pane" id="listView">	
<c:forEach var="comment" items="${ratings.comments}">	
		<div class="row">	  
			<div class="span8">		
				Name : ${comment.name}<br/>
				Rating Given : ${comment.rating} out of 5
				<p>
				Comment : ${comment.comment}
				</p>
				Added : ${comment.date}
			</div>
		</div>
		<hr class="soft"/>
</c:forEach>
</div>



<h5>Your Rating And Comment:</h5>
<form class="form-horizontal qtyFrm" action="addrating" method="post">
				  <input type="text" name="pid" value="${ratings.pid}" style="display:none">
					
					<span>Rating : </span></label><input type="number" min=1 max=5 name="rating" class="span1"/>
					
					<span>Comment : </span></label><input type="text" name="comment"/>
					  <button type="submit" class="btn btn-medium btn-primary "> Submit <i class=" icon-shopping-cart"></i></button>
					
</form>

	</div>
	</div>
<%@include file="footer.jsp" %>