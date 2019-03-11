package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDao {
    protected Connection connection = null;
	protected PreparedStatement stmt = null;
	
	protected void closeResources() {
		try {
		    if (stmt != null)
		        stmt.close();
		} 
		
		catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}

		try {
			if (connection != null)
				connection.close();
		} 
		
		catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
}
