<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<form action="register" method="POST">
		Title : <select name="title" required>
  					<option value="Mr.">Mr.</option>
  					<option value="Miss.">Miss.</option>
  					<option value="Mrs.">Mrs.</option>
				</select>
		First Name : <input type="text" name="firstname" required/><br/>
		Last Name : <input type="text" name="lastname"/><br/>
		E-mail : <input type="text" name="email" required/><br/> 
		Password : <input type="password" name="password" required/><br/>
		Confirm Password : <input type="password" name="confirm_password" required/><br/>
		Date of Birth : <input type="date" name="dob" required/><br/>
	    Address :
	    	Address 1 : <input type="text" name="address.address1" required/><br/>
	    	Address 2 : <input type="text" name="address.address2" required/><br/>
	    	City : <input type="text" name="address.city" required/><br/>
	    	State : <input type="text" name="address.state" required/><br/>
	    	Country : <input type="text" name="address.country" required/><br/>
	    	ZIP : <input type="text" name="address.zip" required/><br/>
	    	Home Phone : <input type="text" name="homephone" required/><br/>
	    	Mobile Phone : <input type="text" name="mobilephone"/><br/>
	    	Additional Information : <input type="text" name="additional_info" required/><br/>
	    	Seller :<input type="radio" name="role" value="true"> Yes <input type="radio" name="role" value="false" checked> No<br>
	    	ID PROOF : <input type="text" name="seller.id_proof" required/><br/><!-- UPLOAD IMAGE -->
	    	Image Upload : <input type="text" name="seller.image_path" required/><br/>
	    	Company :<input type="text" name="seller.company" required/><br/>
		<input type="submit" value="REGISTER"/><br/>
	</form>
	${e.message}
</body>
</html>