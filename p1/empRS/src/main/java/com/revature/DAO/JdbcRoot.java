package com.revature.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JdbcRoot {

	private static final String sql_user = "postgres";
	private static final String sql_pass = "Mantapp!23";
	private static final String url = "jdbc:postgresql://localhost:5432/ers";
	private static Connection connection;

	public static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			
			try {
				Class.forName("org.postgresql.Driver"); 
			} 
			
			catch (ClassNotFoundException e) {
				System.out.println("nein");
				e.printStackTrace();
			}
			
			connection = DriverManager.getConnection(url, sql_user, sql_pass);
		}

		if (connection.isClosed()) {
			System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(url, sql_user, sql_pass);
		}
		
		return connection;
	}

	public static ReimDao getReimburseDAO() {
		return new ReimDaoImp();
	}

	public static EmpDao getEmployeeDAO() {
		return new EmpDaoImp();
	}
}
