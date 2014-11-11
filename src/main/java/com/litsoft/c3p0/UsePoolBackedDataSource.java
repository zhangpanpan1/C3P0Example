package com.litsoft.c3p0;

 

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;
 
 
public class UsePoolBackedDataSource {
	 public static void main(String[] argv) {  
	        try {  
	            //  
	            // 收购之前，您的数据源！  
	  
	            // 获取数据源...这是唯一的C3P0的特定代码在这里  
	            DataSource unpooled = DataSources.unpooledDataSource(  
	                    "jdbc:mysql://127.0.0.1:3306/hibernate?user=root&password=root&useUnicode=true");  
	            DataSource pooled = DataSources.pooledDataSource(unpooled);  
	  
	            Connection con = null;  
	            Statement stmt = null;  
	            ResultSet rs = null;  
	            try {  
	                con = pooled.getConnection();  
	                stmt = con.createStatement();  
	                rs = stmt.executeQuery("SELECT * FROM person");  
	                while (rs.next())  
	                    System.out.println(rs.getString(1));  
	            } finally {  
	                attemptClose(rs);  
	                attemptClose(stmt);  
	                attemptClose(con);  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    static void attemptClose(ResultSet o) {  
	        try {  
	            if (o != null)  
	                o.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    static void attemptClose(Statement o) {  
	        try {  
	            if (o != null)  
	                o.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    static void attemptClose(Connection o) {  
	        try {  
	            if (o != null)  
	                o.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    private UsePoolBackedDataSource() {  
	    }  
}
