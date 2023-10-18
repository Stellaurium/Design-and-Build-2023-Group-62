<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>!MANAGE ROBOT!</title>
</head>
<body>
<h4>Choose what you want!</h4>
<br></br>
<input type="button" onclick="window.location.href='robotadd.jsp';" value="Add"><br></br>
<input type="button" onclick="window.location.href='robotdel.jsp';" value="Delete"><br></br>
<input type="button" onclick="window.location.href='robotmod.jsp';" value="Modify"><br></br>

<form method="post" action="./RobotcheckServlet"> 
	
	<input type="SUBMIT" name="submit" value="Check robot"> 
</form>
<br></br><br></br>
<input type="button" onclick="window.location.href='welcome.jsp';" value="return homepage">

</body>
</html>