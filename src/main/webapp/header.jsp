<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    
    <meta charset="utf-8">
    <title>Bootshop online Shopping cart</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
<!-- Bootstrap style --> 
    <link id="callCss" rel="stylesheet" href="<c:url value="/resources/themes/bootshop/bootstrap.min.css"/>" media="screen"/>
    <link href="<c:url value="/resources/themes/css/base.css"/>" rel="stylesheet" media="screen"/>
<!-- Bootstrap style responsive -->	
	<link href="<c:url value="/resources/themes/css/bootstrap-responsive.min.css"/>" rel="stylesheet"/>
	<link href="<c:url value="/resources/themes/css/font-awesome.css"/>" rel="stylesheet" type="text/css">
<!-- Google-code-prettify -->	
	<link href="<c:url value="/resources/themes/js/google-code-prettify/prettify.css"/>" rel="stylesheet"/>
<!-- fav and touch icons -->
    <link rel="shortcut icon" href="<c:url value="/resources/themes/images/ico/favicon.ico"/>">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value="/resources/themes/images/ico/apple-touch-icon-144-precomposed.png"/>">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<c:url value="/resources/themes/images/ico/apple-touch-icon-114-precomposed.png"/>">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<c:url value="/resources/themes/images/ico/apple-touch-icon-72-precomposed.png"/>">
    <link rel="apple-touch-icon-precomposed" href="<c:url value="/resources/themes/images/ico/apple-touch-icon-57-precomposed.png"/>">
	<style type="text/css" id="enject"></style>
     
</head>
      
<body>
	
<div id="header">
<div class="container">
<div id="welcomeLine" class="row">
	<div class="span6">Welcome!<strong>
<%
      if(session.getAttribute("uid")==null)out.print("User");
      else out.print(""+session.getAttribute("firstname"));
%>
    </strong></div>
	
	
	<div class="span6">
	<div class="pull-right">
<%
		if(session.getAttribute("uid")==null){
			out.print("<a href='login'>LOGIN</a>  ");
			out.print("<a href='register'>REGISTER</a>");
		}
		else{
			out.print("<div class='pull-right'><a href='viewcart?view=fresh'><span class='btn btn-mini btn-primary'><i class='icon-shopping-cart icon-white'></i> View your cart </span> </a></div>");
			out.print("<a href='logout'>LOGOUT</a>");
		}
%>
	</div>
	</div>
</div>


<!-- Navbar ================================================== -->

<div id="logoArea" class="navbar">
<a id="smallScreen" data-target="#topMenu" data-toggle="collapse" class="btn btn-navbar">
	<span class="icon-bar"></span>
	<span class="icon-bar"></span>
	<span class="icon-bar"></span>
</a>
  <div class="navbar-inner">
    <a class="brand" href="index.html"><img src="<c:url value="/resources/themes/images/logo.png"/>" alt="Bootsshop"/></a>
	<form class="form-inline navbar-search" method="get" action="search" >
		<input id="srchFld" class="srchTxt" type="text" name="pattern"/>
		  <button type="submit" id="submitButton" class="btn btn-primary">Go</button>
    </form>
    <ul id="topMenu" class="nav pull-right">
	 <li class=""><a href="special_offer.jsp">Specials Offer</a></li>
	 <li class=""><a href="delivery.html">Delivery</a></li>
	 <li class=""><a href="contact.html">Contact</a></li>
    </ul>
  </div>
</div>

</div>
</div>
<!-- Header End====================================================================== -->    