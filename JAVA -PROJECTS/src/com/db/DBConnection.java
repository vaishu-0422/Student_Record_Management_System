package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String userName = "system";
	private static final String Password = "admin";
    private static Connection con ;
	
	
	public static final Connection createConnection()throws SQLException	{
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, userName, Password);
			System.out.println(" DATABASE CONNECTION VERIFIED ✅");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con ;
		
	}

	
}
