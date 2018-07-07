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
		<li class="active">Registration</li>
    </ul>
	<h3> Registration</h3>	
	<div class="well">
	<form class="form-horizontal" action="register" method="POST">
		<h4>Your personal information</h4>
		<div class="control-group">
		<label class="control-label">Title <sup>*</sup></label>
		<div class="controls">
		<select class="span1" name="title" required>
  					<option value="Mr.">Mr.</option>
  					<option value="Miss.">Miss.</option>
  					<option value="Mrs.">Mrs.</option>
		</select>
		</div>
		
		</div>
		<div class="control-group">
			<label class="control-label" for="inputFname1">First name <sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="inputFname1" name="firstname" required placeholder="First Name">
			</div>
		 </div>
		 
		 <div class="control-group">
			<label class="control-label" for="inputLnam">Last name <sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="inputLnam" placeholder="Last Name" name="lastname">
			</div>
		 </div>
		 
		<div class="control-group">
		<label class="control-label" for="input_email">Email <sup>*</sup></label>
		<div class="controls">
		  <input type="text" id="input_email" name="email" required placeholder="Email">
		</div>
	  </div>	  
	  
	<div class="control-group">
		<label class="control-label" for="inputPassword1">Password <sup>*</sup></label>
		<div class="controls">
		  <input type="password" id="inputPassword1" name="password" required placeholder="Password">
		</div>
	  </div>	  
	  
	  <div class="control-group">
		<label class="control-label" for="inputPassword1">Confirm Password <sup>*</sup></label>
		<div class="controls">
		  <input type="password" id="inputPassword1" name="confirm_password" required placeholder="Re-enter Password">
		</div>
	  </div>
	  
		<div class="control-group">
		<label class="control-label">Date of Birth <sup>*</sup></label>
		<div class="controls">
		  <input type="date" name="dob" required/>
		</div>
	  </div>

	<div class="alert alert-block alert-error fade in">
		<button type="button" class="close" data-dismiss="alert">×</button> ${e.message}
	 </div>	

		<h4>Your address</h4>
		
		
		<div class="control-group">
			<label class="control-label" for="address">Address<sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="address" name="address.address1" required placeholder="Address"/> <span>Street address, P.O. box, company name, c/o</span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="address2">Address (Line 2)<sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="address2" name="address.address2" required placeholder="Address line 2"/> <span>Apartment, suite, unit, building, floor, etc.</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="city">City<sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="city" name="address.city" required placeholder="city"/> 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="state">State<sup>*</sup></label>
			<div class="controls"><input type="text" id="city" name="address.state" required placeholder="state"/> </div>
		</div>		
		<div class="control-group">
			<label class="control-label" for="postcode">Zip / Postal Code<sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="postcode" name="address.zip" required placeholder="Zip / Postal Code"/> 
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="country">Country<sup>*</sup></label>
			<div class="controls">
			<input type="text" id="postcode" name="address.country" required placeholder="Zip / Postal Code"/> 
			
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label" for="aditionalInfo">Additional information</label>
			<div class="controls">
			  <textarea name="additional_info" required id="aditionalInfo" cols="26" rows="3">Additional information</textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="phone">Home phone <sup>*</sup></label>
			<div class="controls">
			  <input type="text"  name="homephone" required id="phone" placeholder="phone"/> <span>You must register at least one phone number</span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="mobile">Mobile Phone </label>
			<div class="controls">
			  <input type="text"  name="mobilephone" required id="mobile" placeholder="Mobile Phone"/> 
			</div>
		</div>
		
		<h4>Seller Registration</h4>
		<div class="control-group">
			<label class="control-label" for="company">Seller?</label>
			<div class="controls">
			  <input type="radio" name="role" value="true"> Yes <input type="radio" name="role" value="false" checked> No<br>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="company">Id Proof</label>
			<div class="controls">
			  <input type="text" name="seller.id_proof" required/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="company">Image of Proof</label>
			<div class="controls">
			  <input type="text" name="seller.image_path" required/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="company">Company</label>
			<div class="controls">
			  <input type="text" id="company" name="seller.company" required placeholder="company"/>
			</div>
		</div>
		
		
	<p><sup>*</sup>Required field	</p>
	
	<div class="control-group">
			<div class="controls">
				<input class="btn btn-large btn-success" type="submit" value="Register" />
			</div>
		</div>		
	</form>
</div>

</div>
	
	
	
<%@include file="footer.jsp" %>	