package com.litsoft.c3p0;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Hello world!
 *
 */
public class DBPool 
{
	private static DBPool dbPool;  
    private ComboPooledDataSource dataSource;  
  
    static {  
        dbPool = new DBPool();  
    }  
  
    public DBPool() {  
        try {  
            dataSource = new ComboPooledDataSource();  
            dataSource.setUser("root");  
            dataSource.setPassword("root");  
            dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/hibernate?user=root&password=root&useUnicode=true");  
            dataSource.setDriverClass("com.mysql.jdbc.Driver");  
            // 设置初始连接池的大小！  
            dataSource.setInitialPoolSize(2);  
            // 设置连接池的最小值！   
            dataSource.setMinPoolSize(1);  
            // 设置连接池的最大值！   
            dataSource.setMaxPoolSize(10);  
            // 设置连接池中的最大Statements数量！  
            dataSource.setMaxStatements(50);  
            // 设置连接池的最大空闲时间！  
            dataSource.setMaxIdleTime(60);  
        } catch (PropertyVetoException e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    public final static DBPool getInstance() {  
        return dbPool;  
    }  
  
    public final Connection getConnection() {  
        try {  
            return dataSource.getConnection();  
        } catch (SQLException e) {  
            throw new RuntimeException("无法从数据源获取连接 ", e);  
        }  
    }  
  
    public static void main(String[] args) throws SQLException {  
        Connection con = null;  
        try {  
            con = DBPool.getInstance().getConnection();  
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM person");  
            while (rs.next()) {  
                System.out.println(rs.getObject(1));
                System.out.println(rs.getObject(2));  
                System.out.println(rs.getObject(4));  
                System.out.println(rs.getObject(5));  
                System.out.println("----------");  
            }  
        } catch (Exception e) {  
        } finally {  
            if (con != null)  
                con.close();  
        }  
    }  
}
