<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="seller_header.jsp"%>
<div class="span13">
    <ul class="breadcrumb">
    <li><a href="seller_index">Home</a> <span class="divider">/</span></li>
    <li><a href="#">Notifications</a></li>
    </ul>	
<h3>
<c:if test="${fn:length(notification) lt 1}">
		No Notifications
</c:if>
</h3>
<hr class="soft"/>
<div class="tab-pane" id="listView">	
<c:forEach items="${notification}" var="note">	
		<div class="row">	  
			<div class="span12">		
		Product Name : ${note.name}<br/>
		Product Cost : ${note.cost}<br/>
		Product Stock : ${note.stock}<br/>
		<c:if test="${oos!='true'}">
		Purchase Mode : <c:if test="${note.cod=='1'}">COD</c:if><c:if test="${note.cod=='0'}">Online</c:if><br/>
		Amount Requested : ${note.count}<br/>
		Date of Order : ${note.date_request}<br/>
		Name : ${note.user.firstname}&nbsp;${note.user.lastname}<br/>
		Homephone : ${note.user.homephone}<br/>
		Address : ${note.user.address.address1}&nbsp;${note.user.address.address2}<br/>
		City : ${note.user.address.city}<br/>
		State : ${note.user.address.state}<br/>
		Country : ${note.user.address.country}<br/>
		ZIP : ${note.user.address.zip}
		
		</c:if>
		<hr class="soft"/>
			</div>
		</div>
</c:forEach>
</div>
<c:if test="${marker && fn:length(notification) gt 0}"><button class="btn btn-medium btn-primary pull-right"><a href="productsmark">MARK</a></button></c:if>
</div>
<%@include file="seller_footer.jsp" %>