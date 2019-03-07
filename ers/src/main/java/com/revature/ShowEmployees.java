package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShowEmployees {
	
	public void listAllEmployees() {
		
		Connection conn = null;
	    Statement stmt = null;
	      
	    try {
	    	  
	        Class.forName("org.postgresql.Driver");
	        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/empreimburse","postgres", "Mantapp!23");
	   
	         
	        stmt = conn.createStatement();
	        String sql = "SELECT * FROM employees where id = 4"; 
	        ResultSet rs = stmt.executeQuery(sql);
	         
	        while (rs.next()) {
	            
	        	String id = rs.getString("id");
	        	System.out.println(id); 
	        	 
	        	String fn = rs.getString("first_name");
		        System.out.println(fn); 
		        
		        String ln = rs.getString("last_name");
			    System.out.println(ln); 
			        	 
			    String em = rs.getString("email");
				System.out.println(em); 
				        	 
				String ph = rs.getString("phone");
			    System.out.println(ph); 
			    
			    String pp = rs.getString("profile_pic");
	        	System.out.println(pp);
	        	
	        	String im = rs.getString("is_manager");
		        System.out.println(im); 
					        	
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

