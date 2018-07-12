<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="seller_header.jsp"%>
<div class="span13">
    <ul class="breadcrumb">
    <li><a href="seller_index">Home</a> <span class="divider">/</span></li>
    <li><a href="#">Add Product</a></li>
    </ul>	
	
	
	<h3> Add Product</h3>	
	<div class="well">
	<form class="form-horizontal" action="add" method="post" enctype="multipart/form-data">
		<div class="control-group">
			<label class="control-label" for="inputFname1">Product Name <sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="inputFname1" name="name" required placeholder="Name">
			</div>
		 </div>
		 
		 <div class="control-group">
			<label class="control-label" for="inputLnam">Price <sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="inputLnam" placeholder="Product MRP" name="price" required>
			</div>
		 </div>
		 
		<div class="control-group">
		<label class="control-label" for="inputLnam">Discount <sup>*</sup></label>
		<div class="controls">
		  <input type="text" id="inputLnam" name="discount" required placeholder="Discount">
		</div>
	  </div>	  
	  
	<div class="control-group">
		<label class="control-label" for="inputLnam">Stock <sup>*</sup></label>
		<div class="controls">
		  <input type="number" min=1 id="inputLnam" name="stock" required placeholder="Stock">
		</div>
	  </div>	  
	  
	  <div class="control-group">
		<label class="control-label" for="inputLnam">Brand <sup>*</sup></label>
		<div class="controls">
		  <input type="text" id="inputLnam" name="brand" required placeholder="Brand">
		</div>
	  </div>
	  
		<div class="control-group">
		<label class="control-label">Version/Model No. <sup>*</sup></label>
		<div class="controls">
		  <input type="text" name="version" required placeholder="Version"/>
		</div>
	  </div>

		<div class="control-group">
			<label class="control-label" for="inputLnam">Short Description<sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="inputLnam" name="short_description" required placeholder="Short Description"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="inputLnam">Features<sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="inputLnam" name="features" required placeholder="Features"/> <span>Details </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputLnam">Images<sup>*</sup></label>
			<div class="controls">
			  <input type="file" id="inputLnam" name="images" required placeholder="Image" multiple/> 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputLnam">Category<sup>*</sup></label>
			<div class="controls"><select name="category" required>
					<c:forEach var="categories" items="${list}">
						<option value="${categories.key}">${categories.key}</option>
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
  					                	<option value="${subcategory.key}">${subcategory.key}</option>
  					   				</c:if>
  					   			</c:forEach>
					   </c:forEach>
					   </select>
			</div>
		</div>
		
	<p><sup>*</sup>Required field	</p>
	
	<div class="control-group">
			<div class="controls">
				<input class="btn btn-large btn-success" type="submit" value="Add Product" />
			</div>
		</div>		
	</form>
</div>

</div>
<%@include file="seller_footer.jsp" %>