package com.sensitiveMeat4664.dao;



import com.sensitiveMeat4664.bean.Account;
import com.sensitiveMeat4664.bean.Customer;
import com.sensitiveMeat4664.exceptions.CustomerException;

public interface CustomerDao {
	
	public String registerCustomer(Customer customer) throws CustomerException;
	public Account createAccount(long mobileNumber, int initialBalance) throws CustomerException;
	public boolean checkPresentCustomer(long mobileNumber);
	public Account transferMoney(int accountNumber1, int accountNumber2, int transferAmount);
}
