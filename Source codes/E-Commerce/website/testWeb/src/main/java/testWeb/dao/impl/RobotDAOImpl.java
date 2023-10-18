package testWeb.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import testWeb.dao.RobotDAO;
import testWeb.db.DBConnect;
import testWeb.servlet.UserLoginServlet;
import testWeb.vo.ExploInfo;
import testWeb.vo.RobotInfo;

public class RobotDAOImpl implements RobotDAO {
	
	public String rid1=UserLoginServlet.aa;


	@Override
	public List<RobotInfo> returnrobot() {
		
		
		String query = "SELECT * FROM robotinfo WHERE robotID='";
	    PreparedStatement pstmt = null ;   
	    DBConnect dbc = null;   
	    List<RobotInfo> list1=new ArrayList<>();
	    // 下面是针对数据库的具体操作   
	    try{   
	    	
	        // 连接数据库   
	        dbc = new DBConnect() ;   
	        pstmt = dbc.getConnection().prepareStatement(query+rid1+"'") ; 
	         
	        // 进行数据库查询操作   
	        ResultSet rs = pstmt.executeQuery();
	        
	        while(rs.next()){  
	            // 查询出内容
	        	
	        	RobotInfo record1=new RobotInfo();
	            
	            record1.setrobotid(rs.getString("robotid"));
	            record1.setrobotname(rs.getString("robotName"));
	            record1.setrobotpic(rs.getString("robotpicture"));
	           
				list1.add(record1);
				
	            
	        } 
	       
	        rs.close() ; 
	        pstmt.close() ;   
	    }catch (SQLException e){   
	        System.out.println(e.getMessage());   
	    }finally{   
	        // 关闭数据库连接   
	        dbc.close() ;   
	    }   
		return list1;

}


	@Override
	public boolean checknull() {
		String query = "SELECT * FROM robotinfo WHERE robotID='";
		boolean b1=false;
	    PreparedStatement pstmt = null ;   
	    DBConnect dbc = null;   
	    List<RobotInfo> list1=new ArrayList<>();
	    // 下面是针对数据库的具体操作   
	    try{   
	    	
	        // 连接数据库   
	        dbc = new DBConnect() ;   
	        pstmt = dbc.getConnection().prepareStatement(query+rid1+"'") ; 
	         
	        // 进行数据库查询操作   
	        ResultSet rs = pstmt.executeQuery();
	        
	        if(rs.next()){  
	            // 查询出内容
	        	b1=true;
	            
	        } 
	       
	        rs.close() ; 
	        pstmt.close() ;   
	    }catch (SQLException e){   
	        System.out.println(e.getMessage());   
	    }finally{   
	        // 关闭数据库连接   
	        dbc.close() ;   
	    }   
		return b1;

	}
}
