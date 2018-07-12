<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp" %>
<div id="mainBody">
	<div class="container">
	<div class="row">
	<div class="span9">
    <ul class="breadcrumb">
		<li><a href="index.html">Home</a> <span class="divider">/</span></li>
		<li class="active">Login</li>
    </ul>
	<h3> Login</h3>	
	<div class="well">
	<form class="form-horizontal" action="login" method="POST">
		<div class="control-group">
		<label class="control-label">E-Mail <sup>*</sup></label>
		<div class="controls">
		<input type="text" id="input_email" name="email" required placeholder="Email">
		</div>
		<br/>
		<div class="control-group">
		<label class="control-label">Password <sup>*</sup></label>
		<div class="controls">
		<input type="password" id="inputPassword1" name="password" required placeholder="Password">
		</div>
		<br/>
		</div>
		<div class="alert alert-block alert-error fade in">
		<button type="button" class="close" data-dismiss="alert">×</button> ${e.message}
	 </div>	
	<div class="control-group">
			<div class="controls">
				<input class="btn btn-large btn-success" type="submit" value="Login" />
			</div>
		</div>		
	</form>
</div>

</div>
	
<%@include file="footer.jsp" %>	