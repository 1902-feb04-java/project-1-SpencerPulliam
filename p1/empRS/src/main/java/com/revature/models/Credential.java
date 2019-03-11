package com.revature.models;

public class Credential {
	
	int userId;
	String userName, userPassword;
	
	public Credential(int id, String user, String password) {
		this.userId = id;
		this.userName = user;
		this.userPassword = password;
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}
}
