package com.sensitiveMeat4664.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sensitiveMeat4664.bean.Account;
import com.sensitiveMeat4664.bean.Customer;
import com.sensitiveMeat4664.exceptions.CustomerException;
import com.sensitiveMeat4664.utility.DBConnectionUtil;

public abstract class CustomerDaoImpl implements CustomerDao{

	@Override
	public String registerCustomer(Customer customer) throws CustomerException{
		
		String message = "Customer Not Registered";
		
		try(Connection conn = DBConnectionUtil.dbConnector()){
			//creating Prepared Statement
			PreparedStatement insertStatement = conn.prepareStatement("insert into customer (cfirstName,clastName,email,zipcode,state,address,mobile) values (?,?,?,?,?,?,?);");
			
			insertStatement.setString(1, customer.getCustomerFirstName());
			insertStatement.setString(2, customer.getCustomerLastName());
			insertStatement.setString(3, customer.getCustomerEmail());
			insertStatement.setInt(4, customer.getZipcode());
			insertStatement.setString(5, customer.getState());
			insertStatement.setString(6, customer.getAddress());
			insertStatement.setLong(7, customer.getCustomerMobile());
			
			int check = insertStatement.executeUpdate();
			
			if(check > 0) {
				message = "Customer Registered";
			}
			
		}
		catch(SQLException e) {
			throw new CustomerException("Invalid details");
		}
		return message;
	}

	@Override
	public Account createAccount(long mobileNumber, int initialBalance) throws CustomerException {
		
		Account account = null;
		
		try(Connection conn = DBConnectionUtil.dbConnector()){
			
			//checking if the customer is present in the database
			if(checkPresentCustomer(mobileNumber)) {
				
				PreparedStatement insertStatment = conn.prepareStatement("insert into accounts (AccMobile,balance) values (?,?)");
				
				insertStatment.setLong(1, mobileNumber);
				insertStatment.setInt(2, initialBalance);
				
				int check = insertStatment.executeUpdate();
				
				if(check > 0) {
					
					PreparedStatement giveAccountDetails = conn.prepareStatement("select * from accounts where AccMobile = ? and balance = ?");
					
					giveAccountDetails.setLong(1, mobileNumber);
					giveAccountDetails.setInt(2, initialBalance);
					
					ResultSet res = giveAccountDetails.executeQuery();
					
					if(res.next()) {
						
						account = new Account(res.getInt("AccountId"),res.getLong("AccMobile"),res.getInt("balance"));
						
						//transaction table updated
						transactionEntry(res.getInt("AccountId"), initialBalance, "deposit");
					}
					
				}
				
			}
			else {
				
				throw new CustomerException("Mobile number not present in database");
			}
			
		    
		}
		catch(SQLException e) {
			e.printStackTrace();
//			throw new CustomerException("Wrong Mobile number");
		}
		
		return account;
	}

	
	
	@Override
	public boolean checkPresentCustomer(long mobileNumber) {
		
		boolean ispresent = false;
		
		try(Connection conn = DBConnectionUtil.dbConnector()){
		 PreparedStatement	checkMobileStatement = conn.prepareStatement("select * from customer where mobile = ?");
			
		 checkMobileStatement.setLong(1, mobileNumber);
			
		 ResultSet result = checkMobileStatement.executeQuery();
		 
		 ispresent = result.next();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return ispresent;
		
	}
	
	public static void transactionEntry(int accountNumber, int amount,String transactionType) {
		
		try(Connection conn = DBConnectionUtil.dbConnector()){
			
			PreparedStatement ps = conn.prepareStatement("insert into transactions (type,amount,accountId) values(?,?,?)");
			
			ps.setString(1, transactionType);
			ps.setInt(2, amount);
			ps.setInt(3, accountNumber);
			
			ps.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Account transferMoney(int accountNumber1, int accountNumber2, int transferAmount) {
		
		Account senderAccount = null;
		
		return senderAccount;
		
	}
	

}
