package com.litsoft.c3p0;

 
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;

public class UseDataSourceFactory {
	
	
	public static void main(String[] args) {
		
		
		DataSource ds_unpooled;
		try {
			ds_unpooled = DataSources.
			unpooledDataSource("jdbc:mysql://127.0.0.1:3360/hibernate?user=root&password=root");
			
			DataSource ds_pooled = DataSources.pooledDataSource( ds_unpooled ); 
			// The DataSource ds_pooled is now a fully configured and usable pooled DataSource.
			// The DataSource is using a default pool configuration, and Postgres' JDBC driver 
			// is presumed to have already been loaded via the jdbc.drivers system property or an 
			// explicit call to Class.forName("org.postgresql.Driver") elsewhere.
		 
		
		
		
		
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		
		
		
	}
	
	

}
