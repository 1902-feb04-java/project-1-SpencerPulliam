package com.revature.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;

public class EmpDaoImp extends BaseDao implements EmpDao {
	
	@Override
	public Employee getEmployeeById(int id) {
		String sql = String.format("WHERE id = %d", id);
		return getAllEmployees(sql).get(0);
	}
 
	@Override
	public Employee getEmployeeByLastName(String lName) {
		String ln = "'"+lName +"'"; 
		String sql = String.format("WHERE last_name = %s", ln);
		return getAllEmployees(sql).get(0);
	}
	
	@Override
	public List<Employee> getAllEmployees() {	
		return getAllEmployees("");
	}

	protected List<Employee> getAllEmployees(String sql) {
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			String base = "SELECT * FROM employees ";
			connection = JdbcRoot.getConnection();
			stmt = connection.prepareStatement(base + sql); 
			
			ResultSet rs = stmt.executeQuery();
				
			while (rs.next()) {
				
				int empId = rs.getInt("id");
				String job = rs.getString("title");
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				int myBoss = rs.getInt("reports_to");
				boolean isBoss = rs.getBoolean("is_manager");
				
				Employee emp = new Employee(empId, job, fName, lName, myBoss, isBoss);
				employees.add(emp);
			}
			
			rs.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		} 
		
		finally {
			closeResources();
		}
		
		return employees;
	}
	
	@Override
	public boolean updateEmployee(Employee e) {
		
		try {
			
			connection = JdbcRoot.getConnection();
			
			String sql = "UPDATE employees SET title = ? , first_name = ?, last_name = ?, reports_to = ?, is_manager = ?"
			+ "WHERE id = ?";
			
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, e.getTitle());
			stmt.setString(2, e.getFirstName());
			stmt.setString(3, e.getLastName());
			stmt.setInt(4, e.getManagerId());
			stmt.setBoolean(5, e.IsAManager());
			stmt.setInt(6, e.getId());

			if (stmt.executeUpdate() != 0) {
 				return true;
			}
			
			else {
				return false;
			}
		} 
		
		catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} 
		
		finally {
			closeResources();
		}
	}

	@Override
	public boolean addEmployee(Employee e) {
		
		try {
			connection = JdbcRoot.getConnection();
			String sql = "INSERT INTO employees (title, first_name, last_name, reports_to, is_manager)"
			+ "VALUES(?,?,?,?,?)";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, e.getTitle());
			stmt.setString(2, e.getFirstName());
			stmt.setString(3, e.getLastName());
			stmt.setInt(4, e.getManagerId());
			stmt.setBoolean(5, e.IsAManager());
			
			if (stmt.executeUpdate() != 0) {
				return true;
			}
			else {
				return false;
			}
		} 
		
		catch (SQLException ex) {
			ex.printStackTrace();
			return false;
			
		} 
		
		finally {
			closeResources();
		}
	}

	@Override
	public boolean deleteEmployee(int id) {
		return false;
	}
}
