package testWeb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testWeb.db.DBConnect;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8") ;	
		String username=request.getParameter("username");
		
		String password=request.getParameter("password");
		//String newrid="r0004";
		
		DBConnect connect=new DBConnect();
		Connection c1=connect.getConnection();
		
		if(c1!=null) {
			//查重(用户名）
			String sql1="select * from  userinfo where username= ?";
			String sql2="select count(robotid) from  userinfo ";
			PreparedStatement pstmt;
			PreparedStatement pstmt2;
			try {
				
				pstmt = connect.getConnection().prepareStatement(sql1);
			    pstmt.setString(1,request.getParameter("username")) ;
			    ResultSet rs = pstmt.executeQuery();
			    if(rs.next()) {
			    	PrintWriter out0=response.getWriter();
					out0.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
					out0.println("<HTML>");
					out0.println("  <HEAD><meta http-equiv=\"refresh\" content=\"5;url=login.jsp\"></HEAD>");
					out0.println("  <BODY>");
					out0.print("<h3>Username had existed,please choose another or login in directly!</h3><br><br>");
					out0.print("(Automatically return to the<font color=red>  login page  </font>after 5 seconds after registering!)");
					out0.println("  </BODY>");
					out0.println("</HTML>");
					out0.flush();
					out0.close();
			    	
			    }
			    else {
			    	
			    	try {
			    		
			    		pstmt2 = connect.getConnection().prepareStatement(sql2);
					    ResultSet rs2 = pstmt2.executeQuery();
					    if(rs2.next()) {
					    	int robotIndex=Integer.parseInt(rs2.getString(1))+1;
					    	String newrid="r000"+String.valueOf(robotIndex);
					  	
					    String sql="insert into userinfo(username,password,robotid)"+"value(?,?,?)";
						PreparedStatement ps=c1.prepareStatement(sql);
						ps.setString(1, username);
						ps.setString(2, password);
						ps.setString(3, newrid);
						ps.executeUpdate();
						PrintWriter out=response.getWriter();
						out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
						out.println("<HTML>");
						out.println("  <HEAD><meta http-equiv=\"refresh\" content=\"5;url=login.jsp\"></HEAD>");
						out.println("  <BODY>");
						out.println("<h3>Registered successfully!</h3><br><br>");
						out.print("(Automatically return to the<font color=red>  login page  </font>after 5 seconds after registering!)");
						out.println("  </BODY>");
						out.println("</HTML>");
						out.flush();
						out.close();
				
						//response.setHeader("refresh", "3;url=login.jsp");
							
					    }
						}
				
					catch (Exception e) {
						e.printStackTrace();
					}
			    
			    }
			    
			    
			    
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		}else {
			System.out.println("没连上");
		}
		
	}}

