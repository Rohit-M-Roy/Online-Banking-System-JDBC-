package com.sensitiveMeat4664.bean;

public class AccountDetails {
	
	private int cid;
	private String fName;
	private String lName;
	private long mobile;
	private String email;
	private int zipcode;
	private int aId;
	private int balance;
	
	public AccountDetails() {
		

	}
	
	
	
	public AccountDetails(int cid, String fName, String lName, long mobile, String email, int zipcode, int aId,
			int balance) {
		setCid(cid);
		setfName(fName);
		setlName(lName);
		setMobile(mobile);
		setEmail(email);
		setZipcode(zipcode);
		setaId(aId);
	}



	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
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
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}



	@Override
	public String toString() {
		return "AccountDetails [cid=" + cid + ", fName=" + fName + ", lName=" + lName + ", mobile=" + mobile
				+ ", email=" + email + ", zipcode=" + zipcode + ", aId=" + aId + ", balance=" + balance + "]";
	}
	
	
	

}
