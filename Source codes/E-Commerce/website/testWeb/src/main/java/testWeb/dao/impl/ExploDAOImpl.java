package testWeb.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import testWeb.db.DBConnect;
import testWeb.servlet.UserLoginServlet;
import testWeb.dao.ExploDAO;
import testWeb.dao.UserDAO;
import testWeb.vo.ExploInfo;
import testWeb.servlet.*;
import java.lang.String;
public class ExploDAOImpl implements ExploDAO {
	
		
	
	
	public String rid=UserLoginServlet.aa;//登录的用户的机器人id,一对一

	@Override
	public List<ExploInfo> query0() //选项一：获取全部历史记录
	{
	
	String query0 = "SELECT * FROM explo_table WHERE robotID='";
    PreparedStatement pstmt = null ;   
    DBConnect dbc = null;   
    List<ExploInfo> list=new ArrayList<>();
    // 下面是针对数据库的具体操作   
    try{   
    	
        // 连接数据库   
        dbc = new DBConnect() ;   
        pstmt = dbc.getConnection().prepareStatement(query0+rid+"'") ; 
         
        // 进行数据库查询操作   
        ResultSet rs = pstmt.executeQuery();
        
        while(rs.next()){  
            // 查询出内容
        	
        	ExploInfo record=new ExploInfo();
            
            record.setid(rs.getString("robotId"));
            record.settime1(rs.getString("time_begin"));
            record.settime2(rs.getString("time_end"));
            record.setnum(rs.getString("treasure_num"));
			list.add(record);
			
            
        } 
       
        rs.close() ; 
        pstmt.close() ;   
    }catch (SQLException e){   
        System.out.println(e.getMessage());   
    }finally{   
        // 关闭数据库连接   
        dbc.close() ;   
    }   
	return list;
}
	
	


	@Override
	public String query1() //选项二：返回探险总时长1
    {
    	
    	String query1 ="SELECT TRUNCATE(SUM((time_end-time_begin)/100),2) As explo_totaltime FROM explo_table WHERE robotID='";
    	PreparedStatement pstmt = null ;   
        DBConnect dbc = null; 
        String explo_totaltime = null;
		try {
			dbc = new DBConnect() ;   
	        pstmt = dbc.getConnection().prepareStatement(query1+rid+"'") ; 
	     // 进行数据库查询操作   
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){  
	            // 查询出内容
	        	explo_totaltime= rs.getString("explo_totaltime");
	        }   
	        rs.close() ; 
	        pstmt.close() ;   
	    }catch (SQLException e){   
	        System.out.println(e.getMessage());   
	    }finally{   
	        // 关闭数据库连接   
	        dbc.close() ;   
	    }   
		return explo_totaltime;
	
		}
    

	@Override
	public String query2() //选项二：返回平均探险时长（速度 ）2
    {
    	
    	String query2 ="SELECT TRUNCATE(SUM(treasure_num)/SUM((time_end-time_begin)/100),2) AS each_time FROM explo_table WHERE robotID='";
    	PreparedStatement pstmt = null ;   
        DBConnect dbc = null; 
        String each_time = null;
		try {
			dbc = new DBConnect() ;   
	        pstmt = dbc.getConnection().prepareStatement(query2+rid+"'") ; 
	     // 进行数据库查询操作   
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){  
	            // 查询出内容
	        	each_time= rs.getString("each_time");
	        }   
	        rs.close() ; 
	        pstmt.close() ;   
	    }catch (SQLException e){   
	        System.out.println(e.getMessage());   
	    }finally{   
	        // 关闭数据库连接   
	        dbc.close() ;   
	    }   
		return each_time;
	
		}

	@Override
	public String query3() //选项二：选项二：返回速度排名3
    {
    	
    	String query3 ="SELECT c.sort FROM(select *from (select *, (select count(DISTINCT b.speed) + 1 from (SELECT robotID,SUM(treasure_num)/SUM((time_end-time_begin)/100) AS speed FROM explo_table GROUP BY robotID)b where b.speed > a.speed) sort from (SELECT robotID,SUM(treasure_num)/SUM((time_end-time_begin)/100) AS speed FROM explo_table GROUP BY robotID)a order by a.speed desc)t where t.sort <= 3)c WHERE robotID='";
    	PreparedStatement pstmt = null ;   
        DBConnect dbc = null; 
        String sort = null;
		try {
			dbc = new DBConnect() ;   
	        pstmt = dbc.getConnection().prepareStatement(query3+rid+"'") ; 
	     // 进行数据库查询操作   
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){  
	            // 查询出内容
	        	sort= rs.getString("sort");
	        }   
	        rs.close() ; 
	        pstmt.close() ;   
	    }catch (SQLException e){   
	        System.out.println(e.getMessage());   
	    }finally{   
	        // 关闭数据库连接   
	        dbc.close() ;   
	    }   
		return sort;
	
		}
    
    

	@Override
	public String[] query4() //选项二：返回第一名ID和成绩
    {
    	
    	String query4="SELECT robotID,TRUNCATE(speed,2)AS speed FROM(select *from (select *, (select count(DISTINCT b.speed) + 1 from (SELECT robotID,SUM(treasure_num)/SUM((time_end-time_begin)/100) AS speed FROM explo_table GROUP BY robotID ORDER BY speed DESC)b where b.speed > a.speed) sort from (SELECT robotID,SUM(treasure_num)/SUM((time_end-time_begin)/100) AS speed FROM explo_table GROUP BY robotID ORDER BY speed DESC) a order by a.speed desc) t where t.sort <= 3)c WHERE c.sort=1";
    	PreparedStatement pstmt = null ;   
        DBConnect dbc = null; 
        String[] first1 = {"a","a"};
		try {
			dbc = new DBConnect() ;   
	        pstmt = dbc.getConnection().prepareStatement(query4) ; 
	     // 进行数据库查询操作   
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){  
	            // 查询出内容
	        	first1[0]= rs.getString("robotID");
	    		first1[1]= rs.getString("speed");
	    		
	        }   
	        rs.close() ; 
	        pstmt.close() ;   
	    }catch (SQLException e){   
	        System.out.println(e.getMessage());   
	    }finally{   
	        // 关闭数据库连接   
	        dbc.close() ;   
	    }   
		return first1;
	
		}

	@Override
	public String query5() //选项三：返回宝藏数量5
    {
    	
    	String query5 ="SELECT SUM(treasure_num) AS total_treasure FROM explo_table WHERE robotID='";
    	PreparedStatement pstmt = null ;   
        DBConnect dbc = null; 
        String total_treasure = null;
		try {
			dbc = new DBConnect() ;   
	        pstmt = dbc.getConnection().prepareStatement(query5+rid+"'") ; 
	     // 进行数据库查询操作   
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){  
	            // 查询出内容
	        	total_treasure = rs.getString("total_treasure");
	        }   
	        rs.close() ; 
	        pstmt.close() ;   
	    }catch (SQLException e){   
	        System.out.println(e.getMessage());   
	    }finally{   
	        // 关闭数据库连接   
	        dbc.close() ;   
	    }   
		return total_treasure;
	
		}

	@Override
	public String query6() //选项三：返回宝藏数量排名
    {
    	
    	String query6 ="SELECT c.sort FROM( select * from (select *, (select count(DISTINCT b.treasureNum) + 1 from (SELECT robotID,SUM(treasure_num)AS treasureNum FROM explo_table GROUP BY robotID)b where b.treasureNum > a.treasureNum) sort from (SELECT robotID,SUM(treasure_num)AS treasureNum FROM explo_table GROUP BY robotID) a order by a.treasureNum desc) t where t.sort <= 3)c WHERE robotID='";
    	PreparedStatement pstmt = null ;   
        DBConnect dbc = null; 
        String sort1 = null;
		try {
			dbc = new DBConnect() ;   
	        pstmt = dbc.getConnection().prepareStatement(query6+rid+"'") ; 
	     // 进行数据库查询操作   
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){  
	            // 查询出内容
	        	sort1 = rs.getString("sort");
	        }   
	        rs.close() ; 
	        pstmt.close() ;   
	    }catch (SQLException e){   
	        System.out.println(e.getMessage());   
	    }finally{   
	        // 关闭数据库连接   
	        dbc.close() ;   
	    }   
		return sort1;
	
		}
    
    
    

	@Override
	public String[] query7() //选项三：返回第一名ID和成绩
    {
    	
    	String query7="SELECT robotid,treasureNum FROM( select * from (select *, (select count(DISTINCT b.treasureNum) + 1 from (SELECT robotID,SUM(treasure_num)AS treasureNum FROM explo_table GROUP BY robotID)b where b.treasureNum > a.treasureNum) sort from (SELECT robotID,SUM(treasure_num)AS treasureNum FROM explo_table GROUP BY robotID) a order by a.treasureNum desc) t where t.sort <= 3)c WHERE c.sort=1";
    	PreparedStatement pstmt = null ;   
        DBConnect dbc = null; 
        String[] first2 = {"a","a"};
		try {
			dbc = new DBConnect() ;   
	        pstmt = dbc.getConnection().prepareStatement(query7) ; 
	     // 进行数据库查询操作   
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){  
	            // 查询出内容

	    		first2[0]= rs.getString("robotID");
	    		first2[1]= rs.getString("treasureNum");
	    		
	        }   
	        rs.close() ; 
	        pstmt.close() ;   
	    }catch (SQLException e){   
	        System.out.println(e.getMessage());   
	    }finally{   
	        // 关闭数据库连接   
	        dbc.close() ;   
	    }   
		return first2;
	
		}
	
	
	public static void update() throws FileNotFoundException, IOException {
		
		
		List<String> list = new ArrayList<String>(); 
		List<String> list1 = new ArrayList<String>(); 
	    String line = null;
		
		
		 String pathname = "C:\\Users\\lenovo\\Desktop\\record.txt"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
	        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
	        //不关闭文件会导致资源的泄露，读写文件都同理
	        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
	        try (FileReader reader = new FileReader(pathname);
	             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
	        ) {
	        	
	            //因为不知道有几行数据，所以先存入list集合中
	            while ((line = br.readLine()) != null) {
	                list.add(line);
	                	            }
	            br.close();
                
	                //System.out.println(list);
	                //System.out.println(list.size());

	            //网友推荐更加简洁的写法
	            } catch (IOException e) {
	            e.printStackTrace();
	        }
	        //System.out.println((String) list.get(0)+"呜呜呜呜");
	        //System.out.println((String) list.get(1)+"wwwww");
	        //System.out.println(list.size());
	        String[] split=null;
	      
	        String[] strs1 = list.toArray(new String[]{});
	        
	        for (int i=0;i<list.size();i++) {
	        	split=strs1[i].split(",");
	        	list1.add(split[0]);
	        	list1.add(split[1]);
	        	list1.add(split[2]);
	        	list1.add(split[3]);
	        	}
	        String[] strs2 = list1.toArray(new String[]{});
	        for(int i=0;i<strs2.length;i++)
	        {
	        	System.out.print(strs2[i]+"\n");
	        }
	       	
	        
	        
	        
	        try {
	            // blog是数据库表，root是数据库用户名，password是数据库密码
	        	Connection dbc = new DBConnect().getConnection();   
	   	   	 
	            if (dbc != null) {
	                System.out.println("获取连接成功aaaa"); 
	                String sql="insert into explo_table(robotID,time_begin,time_end,treasure_num)"+"value(?,?,?,?)";
	                PreparedStatement pstmt = dbc.prepareStatement(sql);
	                
	                for(int i=0;i<strs2.length;i++) {
	                	pstmt.setString(1, strs2[i*4]);
	           pstmt.setString(2, strs2[i*4+1]);
	           pstmt.setString(3, strs2[i*4+2]);
	           pstmt.setString(4, strs2[i*4+3]);
	            pstmt.executeUpdate();
	                }
	                   
	             } else {
	                 System.out.println("获取连接失败");
	            }
	            
	            
	           
	         
	          
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("有问题");
	        
	        }
	}
	
}
	       
	      

      
    
	
	
	  
   
	
	
	


