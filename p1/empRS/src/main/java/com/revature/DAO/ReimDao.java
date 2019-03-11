package com.revature.DAO;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimDao {
	public List<Reimbursement> getAllRequests();
	public List<Reimbursement> getAllRequestsByStatusAndEmployee(int employeeId, String status);
	public List<Reimbursement> getAllRequestsByStatus(String status);
	public Reimbursement getRequestById(int id);
	
	public boolean addRequest(Reimbursement r);
	public boolean updateRequest(int requestId, int managerId, String status);
	public boolean deleteRequestById(int id);
}
