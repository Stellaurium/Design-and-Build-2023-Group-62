<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>!LOGIN!</title>
</head>
<body>
<i><font size=5>
        <b>LOGIN</b> 
</font></i>
<br></br>
<form method="post" action="./login"> 
	<h4>user name : </h4><input type="text" name="username"/>
	<h4>password : </h4><input type="text" name="password"/><br></br>
	<input type="SUBMIT" name="submit" value="login"> <br></br>
	</form>
	
	<input type="button" onclick="window.location.href='register.jsp';" value="register">
 

</body>
</html>