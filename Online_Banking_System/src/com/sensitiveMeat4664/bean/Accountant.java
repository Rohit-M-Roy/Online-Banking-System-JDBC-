package com.sensitiveMeat4664.bean;

public class Accountant {
	
	private int accountantId;
	private String fName;
	private String lName;
	private String email;
	
	public Accountant(){
		
	}
	
	public Accountant(int id, String fName, String lName, String email) {
		
		setAccountantId(id);
		setfName(fName);
		setlName(lName);
		setEmail(email);
		
	}
	
	
	public int getAccountantId() {
		return accountantId;
	}
	public void setAccountantId(int accountantId) {
		this.accountantId = accountantId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Accountant [fName=" + fName + ", lName=" + lName + ", email=" + email + "]";
	}
	
	
	

}
