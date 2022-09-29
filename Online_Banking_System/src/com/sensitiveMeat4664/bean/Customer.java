package com.sensitiveMeat4664.bean;

import java.util.Objects;

public class Customer {
	
	private int customerId;
	private String customerFirstName;
	private String customerLastName;
	private long customerMobile;
	private String customerEmail;
	private String state;
	private int zipcode;
	private String address;
	
	Customer(){
		
	}
	
	public Customer(String FirstName, String LastName, long mobile, String mail,String state ,int zip, String address){
		setCustomerFirstName(FirstName);
		setCustomerLastName(LastName);
		setCustomerMobile(mobile);
		setCustomerEmail(mail);
		setState(state);
		setZipcode(zip);
		setAddress(address);
	}

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public long getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(long customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return customerId == other.customerId;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerFirstName=" + customerFirstName + ", customerLastName="
				+ customerLastName + ", customerMobile=" + customerMobile + ", customerEmail=" + customerEmail
				+ ", state=" + state + ", zipcode=" + zipcode + ", address=" + address + "]";
	}

	
	
	
	
	
	

}
