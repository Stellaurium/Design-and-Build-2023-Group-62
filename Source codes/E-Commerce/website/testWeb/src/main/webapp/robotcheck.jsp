<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.List"%>
<%@ page language="java" import="java.sql.*"%>
<%@ page language="java" import="testWeb.dao.impl.RobotDAOImpl"%>
<%@ page language="java" import="testWeb.db.DBConnect"%>
<%@ page language="java" import="testWeb.vo.RobotInfo"%>
<%@ page language="java" import="testWeb.servlet.RobotcheckServlet"%>
<%@ page language="java" import="javax.servlet.http.HttpServletRequest"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>!CHECK ROBOT!</title>
</head>
<body>
 
<%
List<RobotInfo> list=(List<RobotInfo>)request.getAttribute("robotlist");
out.print("<table border='1' cellspacing='1'><tr>");
    out.print("<caption>robot information</caption>");
    out.print("<tr><th width='60'>robotid </th>"
            + "<th width='100'>robotname</th>"
            + "<th width='200'>robotpicture</th>"
            
           );
    int count = 0;
    for (RobotInfo record:list){
        out.print("<tr>");
       
        out.print("<td align='center'>" + record.getrobotid()+ "</td>");
        out.print("<td align='center'>" + record.getrobotname() + "</td>");
        if(record.getrobotpic().equals("red")){
        out.print("<td align='center'><img src ='img/red.jpg' width='60px' height='60px'/></td>");
        }
        if(record.getrobotpic().equals("green")){
            out.print("<td align='center'><img src ='img/green.jpg' width='60px' height='60px'/></td>");
            }
        if(record.getrobotpic().equals("yellow")){
            out.print("<td align='center'><img src ='img/yellow.jpg' width='60px' height='60px'/></td>");
            }
        
        out.print("</tr>");	
        
	
}
    out.print("</table>");
    
%>

    

<a href="welcome.jsp">return home page</a>

</body>
</html>