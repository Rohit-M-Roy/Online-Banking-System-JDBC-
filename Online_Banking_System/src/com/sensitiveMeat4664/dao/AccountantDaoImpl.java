package com.sensitiveMeat4664.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountException;

import com.sensitiveMeat4664.bean.Account;
import com.sensitiveMeat4664.bean.AccountDetails;
import com.sensitiveMeat4664.bean.Customer;
import com.sensitiveMeat4664.exceptions.CustomerException;
import com.sensitiveMeat4664.utility.DBConnectionUtil;

public class AccountantDaoImpl implements AccountantDao{

	@Override
	public boolean accountantLogin(int accountantId) {
		
		boolean login = false;
		
		try(Connection conn = DBConnectionUtil.dbConnector()){
			
			PreparedStatement ps = conn.prepareStatement("select * from accountant where accountantId = ?");
			
			ps.setInt(1,accountantId);
			
			ResultSet result = ps.executeQuery();
			
			if(result.next()) {
				login = true;
			}else {
				login = false;
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return login;
	}

	@Override
	public String addCustomer(Customer customer) {
		
		String message = null;
		CustomerDao internalDao = new CustomerDaoImpl();
		
		try {
			message =  internalDao.registerCustomer(customer);
		} catch (CustomerException e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return message;
	}

	@Override
	public boolean removeAccount(int accountId) {
		
		boolean operationCheck = false;
		
		try(Connection conn = DBConnectionUtil.dbConnector()){
			
			PreparedStatement psT = conn.prepareStatement("delete from transactions where accountid = ?");
			
			psT.setInt(1, accountId);
			
			psT.executeUpdate();
			
			
				PreparedStatement ps = conn.prepareStatement("delete from accounts where accountId = ?");
			
				ps.setInt(1, accountId);
			
				int x = ps.executeUpdate();
			
				if(x > 0) {
					operationCheck = true;
			
			
			}
			
		}
		catch(SQLException e) {
		
			e.printStackTrace();
			
		}
		
		return operationCheck;
	}

	@Override
	public AccountDetails veiwAccount(int accountId) throws AccountException {
		
		AccountDetails account = null;
		
		try(Connection conn = DBConnectionUtil.dbConnector()) {
			
			PreparedStatement ps = conn.prepareStatement("select cid,cfirstname,clastname,mobile,email,zipcode,accountid,balance from customer,accounts where mobile = accmobile and accountid = ?");
			
			ps.setInt(1, accountId);
			ResultSet res = ps.executeQuery();
			
			if(res.next()) {
				
				int cid = res.getInt("cid");
				String fName = res.getString("cfirstname");
				String lName = res.getString("clastname");
				long mobile = res.getLong("mobile");
				String email = res.getString("email");
				int zip = res.getInt("zipcode");
				int accid = res.getInt("accountid");
				int balance = res.getInt("balance");
				
				account = new AccountDetails(cid,fName,lName,mobile,email,zip,accid,balance);
				
			}else {
				throw new AccountException("No such Account...");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
				
		return account;
	}

	@Override
	public List<Account> viewAllAccounts() throws AccountException {
		
		List<Account> accounts = new ArrayList<>();
		
		try(Connection conn = DBConnectionUtil.dbConnector()){
			
			PreparedStatement ps = conn.prepareStatement("select * from accounts");
			
			ResultSet resultlist = ps.executeQuery();
			
			while(resultlist.next()) {
				
				int accid = resultlist.getInt("accountid");
				long mobile = resultlist.getLong("Accmobile");
				int balance = resultlist.getInt("balance");
				
				accounts.add(new Account(accid,mobile,balance));
				
			}
			
			if(accounts.isEmpty()) {
				throw new AccountException("Empty account table");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return accounts;
	}
	
	

}
