
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.List"%>
<%@ page language="java" import="java.sql.*"%>
<%@ page language="java" import="testWeb.dao.impl.ExploDAOImpl"%>
<%@ page language="java" import="testWeb.db.DBConnect"%>
<%@ page language="java" import="testWeb.vo.ExploInfo"%>
<%@ page language="java" import="testWeb.servlet.ExploServlet"%>
<%@ page language="java" import="javax.servlet.http.HttpServletRequest"%>

<html>



<title>!RECORD & ACHIEVEMENT!</title></head>

<body>
<i><font size=5>
        <b>YOUR</b> <font size=10> Exploration History Record</font>
</font></i>
<%
List<ExploInfo> list=(List<ExploInfo>)request.getAttribute("list");


out.print("<table border='1' cellspacing='1'><tr>");
    
    out.print("<tr><th width='60'>number </th>"
            + "<th width='100'>robot ID</th>"
            + "<th width='200'>start time</th>"
            + "<th width='200'>end time</th>"
            + "<th width='300'>treasure obtained amount</th>"
           );
    int count = 0;
    for (ExploInfo record:list){
        out.print("<tr>");
        out.print("<td>" + (++count) + "</td>");
        out.print("<td>" + record.getid()+ "</td>");
        out.print("<td>" + record.gettime1() + "</td>");
        out.print("<td>" + record.gettime2() + "</td>");
        out.print("<td align='center'>" + record.getnum()+ "</td>");
        
        out.print("</tr>");	
	
}
    out.print("</table>");
%>

</body>
<body>
<i><font size=5>
        <b>YOUR</b> <font size=10> Speed Achievement</font>
</font></i>

<%

    String explo_totaltime=(String)request.getSession().getAttribute("s1");
    String each_time=(String)request.getSession().getAttribute("s2");
    String sort=(String)request.getSession().getAttribute("s3");
    String[] first=(String[])request.getSession().getAttribute("s4");

	%><P>Total duration: <font color=red><font size=5><%=explo_totaltime%></font></font>  minutes<br></p><%

	%><P>Speed：<font color=red><font size=5><%=each_time%></font></font>  treasures per minute<br></p><%

	%><P>Speed ranking: <font color=red><font size=5><%=sort%></font></font>  <br></p><%

	%><P>Robot ID with speed first player: <font color=red><font size=5><%=first[0]%></font></font>          His speed:  <font color=red><font size=5><%=first[1]%></font></font>  treasures per minute<br></p><%

%>

</body>


<body>
<i><font size=5>
        <b>YOUR</b> <font size=10> Treasure Achievement</font>
</font></i>
<%
String total_treasure=(String)request.getSession().getAttribute("s5");
String sort6=(String)request.getSession().getAttribute("s6");
String[] first6=(String[])request.getSession().getAttribute("s7");

	%><P>Total number of treasure：<font color=red><font size=5><%=total_treasure%></font></font>  <br></p><%
	%><P>Treasure ranking: <font color=red><font size=5><%=sort6%></font></font>  <br></p><%
	%><P>Robot ID with the most treasure player: <font color=red><font size=5><%=first6[0]%></font></font>          His treasure number:  <font color=red><font size=5><%=first6[1]%></font></font>  </p><%


%>
<br>
<input type="button" onclick="window.location.href='welcome.jsp';" value="return home page"><br>
</body>


</html>

