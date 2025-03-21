package edu.ssafy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/memberdb?serverTimezone=UTC";    
    private static final String USER = "ssafy";
    private static final String PASSWORD = "ssafy";
    
    static {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	}
    	catch (ClassNotFoundException e) {
    		throw new RuntimeException("jdbc not found", e);
    	}
    }
    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASSWORD);
        properties.setProperty("profileSQL", "true");
        return DriverManager.getConnection(URL, properties);
    }
    
    public static void close(AutoCloseable... closeables) {
    	for (AutoCloseable res : closeables) {
			try {
				res.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
}