package testWeb.servlet;

import java.io.IOException;
import testWeb.vo.UserInfo;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testWeb.dao.impl.ExploDAOImpl;
import testWeb.dao.impl.RobotDAOImpl;
import testWeb.dao.impl.UserDAOImpl;
import testWeb.db.DBConnect;
import testWeb.vo.RobotInfo;

/**
 * Servlet implementation class RobotaddServlet
 */
@WebServlet("/RobotaddServlet")
public class RobotaddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        RobotDAOImpl dao =new RobotDAOImpl();
      
        if(dao.checknull()) {
     		PrintWriter out0=response.getWriter();
			out0.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out0.println("<HTML>");
			out0.println("  <HEAD><meta http-equiv=\"refresh\" content=\"3;url=welcome.jsp\"></HEAD>");
			out0.println("  <BODY>");
			out0.print("<h3>1 account for 1 robot! You already have a robot.</h3><br>");
			out0.print("Automatically return to the home page after 3 seconds");
			out0.println("  </BODY>");
			out0.println("</HTML>");
			out0.flush();
			out0.close();
     	}
     	else {
     		
     		String robotname=request.getParameter("robotname");
    		String robotpicture=request.getParameter("robottype");
    		
    		
     		DBConnect connect=new DBConnect();
    		Connection c2=connect.getConnection();
     		String sql="insert into robotinfo(robotid,robotName,robotpicture)"+"value(?,?,?)";
			PreparedStatement ps;
			try {
				ps = c2.prepareStatement(sql);
				ps.setString(1, UserLoginServlet.aa);
			ps.setString(2,robotname );
			ps.setString(3, robotpicture);
			ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			PrintWriter out0=response.getWriter();
			out0.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out0.println("<HTML>");
			out0.println("  <HEAD><meta http-equiv=\"refresh\" content=\"3;url=welcome.jsp\"></HEAD>");
			out0.println("  <BODY>");
			out0.print("<h3>Add successfully!</h3><br>");
			out0.print("Automatically return to the home page after 3 seconds");
			out0.println("  </BODY>");
			out0.println("</HTML>");
			out0.flush();
			out0.close();
     		
     		
     		
     	}
		
		
	}
		
		
	

}
