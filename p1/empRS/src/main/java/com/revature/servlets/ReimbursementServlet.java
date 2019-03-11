package com.revature.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.revature.DAO.JdbcRoot;
import com.revature.DAO.ReimDao;
import com.revature.models.Reimbursement;

@WebServlet("/reimbursement_crud")
@MultipartConfig 
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected Reimbursement ParseReimbursement(HttpServletRequest request) {
		
		int employeeId = -1;
		employeeId = (int) request.getSession(false).getAttribute("userId");
		Double amount = Double.valueOf(request.getParameter("request_amount"));
		String desc = request.getParameter("request_description");
		Reimbursement imburse = new Reimbursement(amount, employeeId, desc);
		
		try {
			Part imageData = request.getPart("image");
			InputStream inStream = null;
			ByteArrayOutputStream outStream = null;
			
			inStream = imageData.getInputStream();
			outStream = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[1024];
			
			while (inStream.read(buffer) != -1) { 
			
				outStream.write(buffer);
			}
			
			imburse.setImage(outStream.toByteArray()); 
		}
		catch(IOException exception) {
			
			System.out.println("Fail!");
			exception.printStackTrace();
		} 
		
		catch (ServletException e) {
			e.printStackTrace();
		}	
		
		return imburse;		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String crud = request.getParameter("crud");
		Reimbursement recompense;
		ReimDao reDAO = JdbcRoot.getReimburseDAO();
		HttpSession session = request.getSession(false);
		
		{
			switch(crud) {
				case "create": {
					
					recompense = this.ParseReimbursement(request);
					
					if (reDAO.addRequest(recompense)) { 
		
						response.getWriter().write("Added Successfully");
						System.out.println("Added Successfully");
					}
					
					else {
						response.getWriter().write("Request was not added");
						System.out.println("FAILURE");
					}
					
					break;
				}
				
				case "read": {
					String reqStatus = request.getParameter("status");
					String who = request.getParameter("who");
					System.out.println("who and stat "+who.equals("all"));

					
					if (who.equals("all")) {
						
					
						List<Reimbursement> requests = new ArrayList<Reimbursement>();
						requests = reDAO.getAllRequestsByStatus(reqStatus);
						
						String json = new Gson().toJson(requests);
						response.getWriter().write(json);						
					}
					
					else if(who.equals("current")) {
						
						List<Reimbursement> requests = new ArrayList<Reimbursement>();
						requests = reDAO.getAllRequestsByStatusAndEmployee((int) session.getAttribute("userId"),reqStatus);
						String json = new Gson().toJson(requests);
						response.getWriter().write(json);
					}
					
					else {
						
						List<Reimbursement> requests = new ArrayList<Reimbursement>();
						requests = reDAO.getAllRequestsByStatusAndEmployee(Integer.parseInt(who),reqStatus);
						String json = new Gson().toJson(requests);
						response.getWriter().write(json);
					}
			
					break;
				}
				
				case "update": {	
					
					int userId = (int) session.getAttribute("userId");
					int reqId = Integer.parseInt(request.getParameter("id"));
					String status = request.getParameter("status");
					response.getWriter().write(""+reDAO.updateRequest(reqId, userId, status));
					break;
				}
				
				case "delete": {
					break;
				}
			}
		}
	}
}
