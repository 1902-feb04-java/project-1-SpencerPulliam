package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateRequest {
	public void createRequest() {
		Connection conn = null;
	      Statement stmt = null;
	      
	      try {
	    	  
	         Class.forName("org.postgresql.Driver");
	         conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/empreimburse","postgres", "Mantapp!23");
	   
	         
	         stmt = conn.createStatement();
	         String sql = "SELECT * FROM employees"; 
	         ResultSet rs = stmt.executeQuery(sql);
	         
	         while (rs.next()) {
	        	String fn = rs.getString("first_name");
	        	System.out.println(fn); 
	         }
	         
	         stmt.close();
	         conn.close();
	      }
	      
	      catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      
	      System.out.println("Employees selected.");
	   }
	}


