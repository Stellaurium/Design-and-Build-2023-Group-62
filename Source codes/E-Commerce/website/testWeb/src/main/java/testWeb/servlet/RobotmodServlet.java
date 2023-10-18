package testWeb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testWeb.dao.impl.RobotDAOImpl;
import testWeb.db.DBConnect;

/**
 * Servlet implementation class RobotmodServlet
 */
@WebServlet("/RobotmodServlet")
public class RobotmodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 RobotDAOImpl dao =new RobotDAOImpl();
	      
	        if(dao.checknull()) {
	     		String nrobotname=request.getParameter("nrobotname");
	    		String nrobotpicture=request.getParameter("robottype");
	    		
	    		DBConnect connect=new DBConnect();
	    		Connection c3=connect.getConnection();
	    		String sql = "update robotinfo set robotName = ? ,robotpicture = ? where robotid = ?";

				
	    		PreparedStatement ps;
				
	    		
	    		try {
					ps = c3.prepareStatement(sql);
					ps.setString(3, UserLoginServlet.aa);
				ps.setString(1,nrobotname );
				ps.setString(2,nrobotpicture );
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
				out0.print("<h3>Modify successfully!</h3><br>");
				out0.print("Automatically return to the home page after 3 seconds");
				out0.println("  </BODY>");
				out0.println("</HTML>");
				out0.flush();
				out0.close();
	    		

	        }
	     	else {
	     		
	     		
				PrintWriter out0=response.getWriter();
				out0.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
				out0.println("<HTML>");
				out0.println("  <HEAD><meta http-equiv=\"refresh\" content=\"3;url=welcome.jsp\"></HEAD>");
				out0.println("  <BODY>");
				out0.print("<h3>You don't have a robot yet! </h3><br>");
				out0.print("Automatically return to the home page after 3 seconds");
				out0.println("  </BODY>");
				out0.println("</HTML>");
				out0.flush();
				out0.close();
	     		
	     		
	     		
	     	}
		
	}

}
