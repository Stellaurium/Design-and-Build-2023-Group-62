package testWeb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testWeb.vo.*;
import testWeb.dao.UserDAO;
import testWeb.dao.impl.*;
import testWeb.db.DBConnect;

public class UserLoginServlet extends HttpServlet {
	
	public static String aa,bb,cc;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException{
		}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
		    throws IOException, ServletException{
		
	
		    UserInfo userinfo = new UserInfo();
			 userinfo.setUsername(req.getParameter("username"));
			 userinfo.setPassword(req.getParameter("password"));
			 
			 UserDAO dao = new UserDAOImpl();   
		     int flag = 0;
		      
		     try {
					flag = dao.queryByUserInfo(userinfo);
					  
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} 
			 if(flag == 1){ 
				 try {
					aa=dao.loginrobotid(userinfo);
					bb=dao.returnuser(userinfo);
					 cc=dao.returnpassword(userinfo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					  
				 HttpSession session=req.getSession();   
		         session.setAttribute("username", userinfo.getUsername());
		         
		         res.sendRedirect("./welcome.jsp");
		        } else {   
		         res.sendRedirect("./error.jsp");
		        }
		 }
			
			
			

}
