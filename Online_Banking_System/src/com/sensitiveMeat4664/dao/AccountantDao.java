package com.sensitiveMeat4664.dao;

import com.sensitiveMeat4664.bean.Customer;

public interface AccountantDao {
	
	public boolean accountantLogin(int accountantId);
	public Customer addCustomer(String fName, String lName, long mobile, String email, int zipcode, String state, String address);
	
	

}
