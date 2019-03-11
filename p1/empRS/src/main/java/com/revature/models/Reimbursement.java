package com.revature.models;

import java.sql.Date;
import java.util.Base64;


public class Reimbursement {

	public enum Status {
		pending, accepted, rejected, invalid
	}

	int id;
	double amount;
	int employeeId;
	
	Status status;
	byte[] image;
	Date date;
	
	String description;
	String imageString;
	int finalizedBy;
	
	public Reimbursement(int id, double money, int empId, String stat, byte[] imgData, Date date, String desc, int finisher) {
		this.id = id;
		this.amount = money;
		this.employeeId = empId;
		
		this.status = stat != null? Status.valueOf(stat.toLowerCase()): null;
		this.date = date;
		this.description = desc;
		
		this.image = imgData;
		this.finalizedBy = finisher;
	}
	
	public Reimbursement(double money, int empId, String desc) {
		this.amount = money;
		this.employeeId = empId;
		this.description = desc;
	}
	
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", employeeId=" 
	           + employeeId + ", status=" + status
			   + ", date=" + date + ", description=" + description +", file size=" + image + "]";
	}
	
	public int getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getStatus() {
		return status.toString();
	}

	public byte[] getImage() {
		return image;
	}
	
	public int getFinisher() {
		return finalizedBy;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

	public void setImage(byte[] data) {
		this.image = data;	
	}
	
	public void setImageString(byte[] data) {
		this.imageString = data == null? null: Base64.getEncoder().encodeToString(data);	
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}