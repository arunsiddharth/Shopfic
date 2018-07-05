<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Product</title>
</head>
<body>
	<form action="update" method="post">
	    Product Id : <input type="text" name="pid" value="${product.pid}"/><br/>
		Name : <input type="text" name="name" value="${product.name}"/><br/>
		Price : <input type="text" name="price" value="${product.price}"/><br/>
		Discount : <input type="text" name="discount" value="${product.discount}"/><br/>
		Stock : <input type="text" name="stock" value="${product.stock}"/><br/>
		Category : <select name="category">
					<c:forEach var="categories" items="${list}">
						<c:if test="${categories.key==product.category}">
						<option value="${categories.key}" selected>${categories.key}</option>
						</c:if>
						<c:if test="${categories.key!=product.category}">
						<option value="${categories.key}">${categories.key}</option>
						</c:if>
					</c:forEach>
				   </select><br/>
		Sub Category : <select name="subcategory">
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
					   </select><br/>
		
		Brand : <input type="text" name="brand" value="${product.brand}"/><br/>
		Version / Model No. : <input type="text" name="version" value="${product.version}"/><br/>
		Short Description : <input type="text" name="short_description" value="${product.short_description}"/><br/>
		Features : <input type="text" name="features" value="${product.features}"/><br/>
		<input type="submit" value="Update Product"/>
	</form>
</body>
</html>