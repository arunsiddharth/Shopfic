<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOGIN</title>
</head>
<body>
	<form action="login" method="post">
		E-mail : <input type="text" name="email"/><br/> 
		Password : <input type="password" name="password"/><br/>
		<input type="submit" value="LOGIN"/><br/>
	</form>
	${val}
	${e.message}
</body>
</html>