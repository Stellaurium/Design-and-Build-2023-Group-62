<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Enter a new name and picture
<form action="./RobotmodServlet" method="post">			
   		<table width="500" border="0" align="center">
   			<tr>
   				<td align="right">new robot'name</td>
   				<td align="left">
   					<input type="text" name="nrobotname">
   				</td>
   			<tr>
   				<td align="center">robot'picture<br></br><br></br></td>
   				<td align="center">
   					<input type="radio" id="robotpic" name="robottype" value="red"> <label for="flat-radio-1" class="">red</label>
   					<input type="radio" id="robotpic" name="robottype" value="green"> <label for="flat-radio-1" class="">green</label>
            
            <input type="radio" id="robotpic" name="robottype" value="yellow"> <label for="flat-radio-1" class="">yellow
            <br></br><br></br>
   				</td>
   			</tr>
   			<br></br><br></br>
   			<tr>
   				
   				<td align="center">
   				<img src ="img/red.jpg" width="60px" height="60px" />
   				<br></br><br></br>	 
   				</td>
   				<td align="center">
   				<img src ="img/green.jpg" width="60px" height="60px" />
   				<br></br><br></br>	 
   				</td>
   				<td align="center">
   				<img src ="img/yellow.jpg" width="60px" height="60px" />
   				<br></br><br></br>	 
   				</td>
   			</tr>
   			</tr>
    			<td colspan="2" align="center">
    				<input type="submit" value="Modify robot">
    				<a href="welcome.jsp">取消</a>
    				
    			</td>
    		</tr>
   		</table>
   	</form>



</body>
</html>