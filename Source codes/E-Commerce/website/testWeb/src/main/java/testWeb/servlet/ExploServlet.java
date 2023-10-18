package testWeb.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testWeb.db.DBConnect;
import testWeb.dao.impl.ExploDAOImpl;
import testWeb.vo.ExploInfo;

/**
 * Servlet implementation class ExploServlet
 */
@WebServlet("/ExploServlet")
public class ExploServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=utf8");
    	
    	 
     
       ExploDAOImpl dao =new ExploDAOImpl();
       dao.update();
       
       
       
        List<ExploInfo> list=dao.query0();
       
        request.setAttribute("list", list);
        
        request.getSession().setAttribute("s1",dao.query1() );
     	request.getSession().setAttribute("s2",dao.query2() );
     	request.getSession().setAttribute("s3",dao.query3() );
     	request.getSession().setAttribute("s4",dao.query4() );
     	request.getSession().setAttribute("s5",dao.query5() );
     	request.getSession().setAttribute("s6",dao.query6() );
     	request.getSession().setAttribute("s7",dao.query7() );
     	if(dao.query1()!=null) {
     		request.getRequestDispatcher("record1.jsp").forward(request, response); 
  	      response.sendRedirect("record1.jsp");
     	}
     	else {
     		PrintWriter out0=response.getWriter();
			out0.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out0.println("<HTML>");
			out0.println("  <HEAD><meta http-equiv=\"refresh\" content=\"3;url=welcome.jsp\"></HEAD>");
			out0.println("  <BODY>");
			out0.print("<h3>No record!</h3><br>");
			out0.print("Automatically return to the home page after 3 seconds");
			out0.println("  </BODY>");
			out0.println("</HTML>");
			out0.flush();
			out0.close();
     		
     	}
     
     		
         
    	}
    
    	
    
    }
        
     
    
 