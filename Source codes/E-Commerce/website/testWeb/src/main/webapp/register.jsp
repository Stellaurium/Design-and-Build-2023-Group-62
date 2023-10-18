<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>!REGISTER!</title>
</head>
<body>

<br></br>
	<form action="./RegisterServlet" method="post">			
   		<table width="1000" border="0" align="center">
   			<tr>
   			<tr><td align="center"><i><font size=5>
        <b>REGISTER</b> 
</font></i></td></tr>
   				<br></br><td align="center">username<input type="text" name="username">
   				
   					
   				</td>
   			<tr>
   			<tr>
   				<td align="center">password
   				
   					<input type="password" name="password">
   				</td>
   			</tr>
   			<tr>
    			<td colspan="2" align="center">
    				<input type="submit" value="register">
    				<a href="login.jsp">cancel</a></td>
    				
    			</td>
    		</tr>
    		<tr>
    		<td align="center">
    		
    		</td></tr>
    		
   		</table>
   		
   		
   	</form>


</body>
</html>