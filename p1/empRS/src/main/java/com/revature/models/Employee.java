package com.revature.models;

public class Employee {
  
    int id;
    String jobTitle;
    String firstName, lastName;
    int reportsTo;
    boolean isManager;

    static int count = 0;

    public Employee() {
        this.id = -1;
        this.jobTitle = null;
        this.firstName = null;
        this.lastName = null;
        this.reportsTo = -1;
    }
    
    public Employee(int id, String job, String fName, String lName, int manId, boolean isBoss) {
        this.id = id;
        this.jobTitle = job;
        this.firstName = fName;
        this.lastName = lName;
        this.reportsTo = manId;
        this.isManager = isBoss;
        System.out.println(this.toString());
    }
    
    @Override
    public String toString() {
        String info = "ID: " + id + ", Name: " + firstName + " " + lastName + ", Job: " 
        + jobTitle + ", Mananger ID: " + reportsTo + ", is a Manager?: " + isManager;
        return info;
    }
    
    public void setId(int num) {
    	this.id = num;
    }

    public int getId() {
        return this.id;
    }
    
    public int getManagerId() {
        return this.reportsTo;
    }

    public String getTitle() {
        return this.jobTitle;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public boolean IsAManager() {
        return this.isManager;
    }
}