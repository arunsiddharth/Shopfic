<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="seller_header.jsp"%>
<div class="span13">
    <ul class="breadcrumb">
    <li><a href="seller_index">Home</a> <span class="divider">/</span></li>
    <li><a href="#">Update Product</a></li>
    </ul>	
	
	<h3>Update Product</h3>	
	<div class="well">
	<form class="form-horizontal" action="update" method="post" enctype="multipart/form-data">
		<input type="text" name="pid" value="${product.pid}" style="display:none"/><br/>
		<div class="control-group">
			<label class="control-label" for="inputFname1">Product Name <sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="inputFname1" name="name" required value="${product.name}">
			</div>
		 </div>
		 
		 <div class="control-group">
			<label class="control-label" for="inputLnam">Price <sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="inputLnam"  value="${product.price}" name="price" required>
			</div>
		 </div>
		 
		<div class="control-group">
		<label class="control-label" for="inputLnam">Discount <sup>*</sup></label>
		<div class="controls">
		  <input type="text" id="inputLnam" name="discount" required  value="${product.discount}">
		</div>
	  </div>	  
	  
	<div class="control-group">
		<label class="control-label" for="inputLnam">Stock <sup>*</sup></label>
		<div class="controls">
		  <input type="number" min=1 id="inputLnam" name="stock" required  value="${product.stock}">
		</div>
	  </div>	  
	  
	  <div class="control-group">
		<label class="control-label" for="inputLnam">Brand <sup>*</sup></label>
		<div class="controls">
		  <input type="text" id="inputLnam" name="brand" required  value="${product.brand}">
		</div>
	  </div>
	  
		<div class="control-group">
		<label class="control-label">Version/Model No. <sup>*</sup></label>
		<div class="controls">
		  <input type="text" name="version" required  value="${product.version}"/>
		</div>
	  </div>

		<div class="control-group">
			<label class="control-label" for="inputLnam">Short Description<sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="inputLnam" name="short_description" required  value="${product.short_description}"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="inputLnam">Features<sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="inputLnam" name="features" required  value="${product.features}"/> <span>Details </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputLnam">Category<sup>*</sup></label>
			<div class="controls"><select name="category" required>
					<c:forEach var="categories" items="${list}">
						<c:if test="${categories.key==product.category}">
						<option value="${categories.key}" selected>${categories.key}</option>
						</c:if>
						<c:if test="${categories.key!=product.category}">
						<option value="${categories.key}">${categories.key}</option>
						</c:if>
					</c:forEach>
				   </select></div>
		</div>		
		<div class="control-group">
			<label class="control-label" for="inputLnam">Sub Category<sup>*</sup></label>
			<div class="controls">
			  <select name="subcategory" required>
					   <c:forEach var="categories" items="${list}">
  								<c:forEach var="subcategory" items="${categories.value}">
  					                <c:if test = "${subcategory.key != 'cid'}">
  					                	<c:if test="${subcategory.key==product.subcategory}">
											<option value="${subcategory.key}" selected>${subcategory.key}</option>
  					   					</c:if>
										<c:if test="${subcategory.key!=product.subcategory}">
											<option value="${subcategory.key}">${subcategory.key}</option>
  					   					</c:if>
  					                	</c:if>
  					   			</c:forEach>
					   </c:forEach>
					   </select>
			</div>
		</div>
		
	<p><sup>*</sup>Required field	</p>
	
	<div class="control-group">
			<div class="controls">
				<input class="btn btn-large btn-success" type="submit" value="Update Product" />
			</div>
		</div>		
	</form>
</div>

</div>
<%@include file="seller_footer.jsp" %>