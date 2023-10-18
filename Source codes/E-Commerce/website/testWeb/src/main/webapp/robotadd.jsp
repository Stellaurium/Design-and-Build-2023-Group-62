<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>!ADD ROBOT!</title>
</head>
<body>

	<form action="./RobotaddServlet" method="post">			
   		<table width="500" border="0" align="center">
   			<tr>
   				<td align="center">robot'name<br></br><br></br></td>
   				<td align="center">
   					<input type="text" name="robotname">
   					<br></br><br></br>
   				</td>
   			</tr>
   			
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
   			 
                   
            
   			<tr>
   			
    			<td colspan="2" align="center">
    				<input type="submit" value="Add robot">
    				<a href="welcome.jsp">取消</a>
    				
    			</td>
    		</tr>
   		</table>
   	</form>

</body>
</html>