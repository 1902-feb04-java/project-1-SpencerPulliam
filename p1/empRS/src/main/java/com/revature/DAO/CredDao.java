package com.revature.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Credential;

public class CredDao extends BaseDao {
	public Credential getCred(String user, String password) {
		Credential cred = null;
		
		try {
			connection = JdbcRoot.getConnection();
			String sql = "SELECT * FROM credentials WHERE username = ? AND password = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, user);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			int id = rs.getInt("user_id");
			cred = new Credential(id, user, password);
		
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		} 
		
		finally {
			closeResources();
		}
		
		return cred;
	}
	
	public int tryLogin(String user, String password) {
		int id = -1;
		
		try {
			
			connection = JdbcRoot.getConnection();
			String sql = "SELECT user_id FROM credentials WHERE username = ? AND password = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, user);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			
			if ( rs.next()) {
				id = rs.getInt(1);
			}						
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		} 
		
		finally {
			closeResources();
		}
		
		return id;
	}
}
