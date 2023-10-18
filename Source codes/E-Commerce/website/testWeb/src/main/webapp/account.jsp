<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>!YOUR ACCOUNT!</title>
</head>
<body>
<br></br>
<%String username = (String)session.getAttribute("uusername"); %>
    <h4>Your username :  <font color=red><%= username %></font>
    <br></br>
    <%String username1 = (String)session.getAttribute("upassword"); %>
    Your password :   <font color=red><%= username1 %></font>
    <br></br>
    <%String username2 = (String)session.getAttribute("urobotid"); %>
    Your robot ID :   <font color=red><%= username2 %></font><h5>PS : Robot ID is automatically assigned and cannot be changed.</h5></h4>
    
<br></br>
<input type="button" onclick="window.location.href='welcome.jsp';" value="return homepage">
</body>
</html>