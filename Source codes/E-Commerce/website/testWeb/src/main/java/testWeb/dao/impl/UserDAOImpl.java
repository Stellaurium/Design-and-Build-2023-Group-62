package testWeb.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import testWeb.dao.UserDAO;
import testWeb.db.DBConnect;
import testWeb.vo.UserInfo;

public class UserDAOImpl implements UserDAO {

	public int queryByUserInfo(UserInfo userinfo) throws Exception {
		int flag = 0;
		String sql = "select * from userinfo where username=?";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;   
  
        // 下面是针对数据库的具体操作   
        try{   
            // 连接数据库   
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            pstmt.setString(1,userinfo.getUsername()) ;   
            // 进行数据库查询操作   
            ResultSet rs = pstmt.executeQuery();//rs是数据库里查出来的
            while(rs.next()){  
                // 查询出内容，将其与用户提交的内容对比 
                if(rs.getString("password").equals(userinfo.getPassword())){
                	flag = 1;
                } 
            }   
            rs.close() ; 
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            // 关闭数据库连接   
            dbc.close() ;   
        }   
		return flag;
	}

	@Override
	public String loginrobotid(UserInfo userinfo) throws Exception {
		String loginrobotid="0";
		String sql = "select * from userinfo where username=?";
		
        PreparedStatement pstmt = null ;  
      
        DBConnect dbc = null;   
  
        // 下面是针对数据库的具体操作   
        try{   
            // 连接数据库   
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            pstmt.setString(1,userinfo.getUsername()) ;
            
            // 进行数据库查询操作   
            ResultSet rs = pstmt.executeQuery();//rs是数据库里查出来的
            while(rs.next()){  
                // 查询出内容，将其与用户提交的内容对比 
                if(rs.getString("password").equals(userinfo.getPassword())){
                	
                	loginrobotid=rs.getString("robotid");
                	
                } 
            }   
            rs.close() ; 
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            // 关闭数据库连接   
            dbc.close() ;   
        }   
		return loginrobotid;
	}

	@Override
	public String returnuser(UserInfo userinfo) throws Exception {
		String uname="0";
		String sql = "select * from userinfo where username=?";
		
        PreparedStatement pstmt = null ;  
      
        DBConnect dbc = null;   
  
        // 下面是针对数据库的具体操作   
        try{   
            // 连接数据库   
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            pstmt.setString(1,userinfo.getUsername()) ;
            
            // 进行数据库查询操作   
            ResultSet rs = pstmt.executeQuery();//rs是数据库里查出来的
            while(rs.next()){  
                // 查询出内容，将其与用户提交的内容对比 
                if(rs.getString("password").equals(userinfo.getPassword())){
                	
                	uname=rs.getString("username");
                	
                } 
            }   
            rs.close() ; 
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            // 关闭数据库连接   
            dbc.close() ;   
        }   
		return uname;
	}

	
	@Override
	public String returnpassword(UserInfo userinfo) throws Exception {

		String upassword="0";
		String sql = "select * from userinfo where username=?";
		
        PreparedStatement pstmt = null ;  
      
        DBConnect dbc = null;   
  
        // 下面是针对数据库的具体操作   
        try{   
            // 连接数据库   
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            pstmt.setString(1,userinfo.getUsername()) ;
            
            // 进行数据库查询操作   
            ResultSet rs = pstmt.executeQuery();//rs是数据库里查出来的
            while(rs.next()){  
                // 查询出内容，将其与用户提交的内容对比 
                if(rs.getString("password").equals(userinfo.getPassword())){
                	
                	upassword=rs.getString("password");
                	
                } 
            }   
            rs.close() ; 
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            // 关闭数据库连接   
            dbc.close() ;   
        }   
		return upassword;
	}
	


		
	
}
