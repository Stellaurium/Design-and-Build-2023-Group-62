package testWeb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testWeb.dao.impl.ExploDAOImpl;
import testWeb.dao.impl.RobotDAOImpl;
import testWeb.vo.ExploInfo;
import testWeb.vo.RobotInfo;

/**
 * Servlet implementation class RobotcheckServlet
 */
@WebServlet("/RobotcheckServlet")
public class RobotcheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    RobotDAOImpl dao =new RobotDAOImpl();
      
        
        List<RobotInfo> robotlist=dao.returnrobot();
        
        request.setAttribute("robotlist", robotlist);
        
        if(dao.checknull()) {
     		request.getRequestDispatcher("robotcheck.jsp").forward(request, response); 
  	      response.sendRedirect("robotcheck.jsp");
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
