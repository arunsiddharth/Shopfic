<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
</head>
<body>
	<form action="add" method="post">
		Name : <input type="text" name="name"/><br/>
		Price : <input type="text" name="price"/><br/>
		Discount : <input type="text" name="discount"/><br/>
		Stock : <input type="text" name="stock"/><br/>
		Category : <select name="category">
					<c:forEach var="categories" items="${list}">
						<option value="${categories.key}">${categories.key}</option>
					</c:forEach>
				   </select><br/>
		Sub Category : <select name="subcategory">
					   <c:forEach var="categories" items="${list}">
  								<c:forEach var="subcategory" items="${categories.value}">
  					                <c:if test = "${subcategory.key != 'cid'}">
  					                	<option value="${subcategory.key}">${subcategory.key}</option>
  					   				</c:if>
  					   			</c:forEach>
					   </c:forEach>
					   </select><br/>
		
		Brand : <input type="text" name="brand"/><br/>
		Version / Model No. : <input type="text" name="version"/><br/>
		Short Description : <input type="text" name="short_description"/><br/>
		Features : <input type="text" name="features"/><br/>
		Images : <input type="text" name="images"/><br/>
		<input type="submit" value="Add Product"/>
	</form>
</body>
</html>