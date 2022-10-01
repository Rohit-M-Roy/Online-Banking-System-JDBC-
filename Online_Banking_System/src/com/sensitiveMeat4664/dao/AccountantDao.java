package com.sensitiveMeat4664.dao;

import java.util.List;

import javax.security.auth.login.AccountException;

import com.sensitiveMeat4664.bean.Account;
import com.sensitiveMeat4664.bean.AccountDetails;
import com.sensitiveMeat4664.bean.Customer;

public interface AccountantDao {
	
	public boolean accountantLogin(int accountantId);
	public String addCustomer(Customer customer);
	public boolean removeAccount(int accountId);
	public AccountDetails veiwAccount (int accountId) throws AccountException;
	public List<Account> viewAllAccounts() throws AccountException;
	
	

}
