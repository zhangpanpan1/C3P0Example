package com.litsoft.c3p0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 测试在使用C3PO和不使用c3p0两种状况下对数据库访问时 获得数据的时间对比
 * 
 *
 */
public class ConnectionDemo {
    public static void main(String[] args) throws SQLException {
        
    	
    	System.out.println("使用连接池................................");
        for (int i = 0; i < 20; i++) {
            long beginTime = System.currentTimeMillis();
            Connection conn = ConnectionManager.getInstance().getConnection();
            try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM person");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                	//System.out.println(rs.getObject(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("第" + (i + 1) + "次执行花费时间为:" + (endTime - beginTime));
        }
        System.out.println("不使用连接池................................");
   
        for (int i = 0; i < 20; i++) {
            long beginTime = System.currentTimeMillis();


            String driver = "com.mysql.jdbc.Driver";

            // URL指向要访问的数据库名scutcs

            String url = "jdbc:mysql://127.0.0.1:3306/hibernate";

            // MySQL配置时的用户名

            String user = "root";

            // Java连接MySQL配置时的密码

            String password = "root";
 

            // 加载驱动程序

            try {
				Class.forName(driver);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            // 连续数据库

            Connection conn = DriverManager.getConnection(url, user, password);

            if(!conn.isClosed()){
            	 //System.out.println("Succeeded connecting to the Database!");
            }

           
            // statement用来执行SQL语句

            Statement statement = conn.createStatement();

            // 要执行的SQL语句

            String sql = "select * from person";
            
            
          
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                                //System.out.println(rs.getObject(1)); 
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("第" + (i + 1) + "次执行花费时间为:"
                                + (endTime - beginTime));
            
        }
        
        }
}
 