<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>!HOMEPAGE!</title>
</head>
<body>
   <%String username = (String)session.getAttribute("username"); %>
    <h4>Welcome   <font color=red>  <%= username %>  </font> ,  choose what you want</h4><br></br>
    <form method="post" action="./AccountServlet"> 
	
	<input type="SUBMIT" name="submit" value="Check Account">
		
</form>
<br> 
<input type="button" onclick="window.location.href='robotIndex.jsp';" value="Manage Robot"><br>
	<br>
<form method="post" action="./ExploServlet"> 
	<input type="SUBMIT" name="submit" value="Record & Achievement"> 
</form>	<br></br><br></br><br></br>
<input type="button" onclick="window.location.href='login.jsp';" value="Log Out"><br>
  </body>
</body>
</html>