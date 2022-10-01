package com.sensitiveMeat4664.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sensitiveMeat4664.bean.Account;
import com.sensitiveMeat4664.bean.Customer;
import com.sensitiveMeat4664.bean.Transaction;
import com.sensitiveMeat4664.exceptions.CustomerException;
import com.sensitiveMeat4664.utility.DBConnectionUtil;

public class CustomerDaoImpl implements CustomerDao{

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
	public Account transferMoney(int accountNumber1, int accountNumber2, int transferAmount) throws CustomerException {
		
		Account senderAccount = null;
		
		try(Connection conn = DBConnectionUtil.dbConnector()){
			
			int acc1Balance = 0;
			
			//check if account2 is present
			PreparedStatement ps = conn.prepareStatement("select * from accounts where accountId = ?");
			
			ps.setInt(1, accountNumber2);
			
			ResultSet result = ps.executeQuery();
			//account 2 Exsists
			if(result.next()) {
				
				
				//check for enough balance
				ps.setInt(1, accountNumber1);
				
				ResultSet account1Details = ps.executeQuery();
				
				if(account1Details.next()) {
					
					acc1Balance = account1Details.getInt("balance");
					//check eligibility for transfer
					if(acc1Balance < transferAmount) {
						
						throw new CustomerException("Not enough Balance for transfer");
					}
					
					
					PreparedStatement debitFromAccount1 = conn.prepareStatement("update accounts set balance = balance - ? where accountId = ?");
					
					debitFromAccount1.setInt(1, transferAmount);
					debitFromAccount1.setInt(2, accountNumber1);
					
					int checkdebit = debitFromAccount1.executeUpdate();
					
					if(checkdebit > 0) {
						System.out.println("Money out of acc1");
						
						//setting return package
						ps.setInt(1, accountNumber1);
						ResultSet account1DetailsAfterTransfer = ps.executeQuery();
						
						if(account1DetailsAfterTransfer.next()) {
						senderAccount = new Account();
						
						senderAccount.setAccountNumber(account1DetailsAfterTransfer.getInt("AccountId"));
						senderAccount.setMobile(account1DetailsAfterTransfer.getLong("AccMobile"));
						senderAccount.setBalance(account1DetailsAfterTransfer.getInt("balance"));
						}
						
						PreparedStatement creditInAccount2 = conn.prepareStatement("update accounts set balance = balance + ? where accountId = ?");
						
						creditInAccount2.setInt(1, transferAmount);
						creditInAccount2.setInt(2, accountNumber2);
						
						int checkCredit = creditInAccount2.executeUpdate();
						if(checkCredit > 0) {
							
							System.out.println("Transfer successull");
							
							//Tracking into Transation Table
							
							PreparedStatement trackDebitAndCredit = conn.prepareStatement("insert into transactions (type,amount,accountId) values(?,?,?)");
							
							trackDebitAndCredit.setString(1, "debit");
							trackDebitAndCredit.setInt(2, -transferAmount);
							trackDebitAndCredit.setInt(3, accountNumber1);
							
							trackDebitAndCredit.executeUpdate();
							
							trackDebitAndCredit.setString(1, "credit");
							trackDebitAndCredit.setInt(2, transferAmount);
							trackDebitAndCredit.setInt(3, accountNumber2);
							
						}
						
						
					}else {
						
						throw new CustomerException("Problem in money transfer");
					}
					
				}
				
			}else {
				throw new CustomerException("No such account in the database");
			}
			
		}catch(SQLException e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return senderAccount;
		
	}


	
	
	@Override
	public List<Transaction> veiwTransactionHistory(int accountNumber) throws CustomerException {
		
		List<Transaction> transactionHistory = new ArrayList<>();
		
		try(Connection conn = DBConnectionUtil.dbConnector()){
			
			PreparedStatement ps = conn.prepareStatement("select * from transactions where accountId = ?");
			
			ps.setInt(1, accountNumber);
			
			ResultSet transactionSet = ps.executeQuery();
			
			while(transactionSet.next()) {
				
				Transaction transaction = new Transaction();
				
				transaction.setTransactionId(transactionSet.getInt("transactionId"));
				transaction.setTransactionType(transactionSet.getString("type"));
				transaction.setTransactionAmount(transactionSet.getInt("amount"));
				transaction.setAccountIdRelatedToTheTransaction(transactionSet.getInt("accountId"));
				
				transactionHistory.add(transaction);
				
				
				
			}
			
			if(transactionHistory.isEmpty())
				throw new CustomerException("No transactions. . .");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return transactionHistory;
	}


	@Override
	public int depositMoney(int accountNo, int amount) throws CustomerException {
		int finalBalance = 0;
		
		try(Connection conn = DBConnectionUtil.dbConnector()){
			
			//check if account2 is present
			PreparedStatement ps = conn.prepareStatement("select * from accounts where accountId = ?");
			
			ps.setInt(1, accountNo);
			
			ResultSet result = ps.executeQuery();
			
			if(result.next()) {
				
				PreparedStatement depositStatement = conn.prepareStatement("update accounts set balance = balance + ? where accountId = ?");
				
				depositStatement.setInt(1, amount);
				depositStatement.setInt(2, accountNo);
				
				int x = depositStatement.executeUpdate();
				
				if(x>0) {
					
					ps.setInt(1, accountNo);
					
					ResultSet updatedRes = ps.executeQuery();
					
					while(updatedRes.next()) {
						
						finalBalance = updatedRes.getInt("balance");
						
					}
					
				}
				
			}
			else {
				throw new CustomerException("No such account in dataBase");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return finalBalance;
	}


	@Override
	public int withdrawMoney(int accountNo, int amount) throws CustomerException {
		int finalBalance = 0;
		
		try(Connection conn = DBConnectionUtil.dbConnector()){
			
			PreparedStatement ps = conn.prepareStatement("select * from accounts where accountId = ?");
			
			ps.setInt(1, accountNo);
			
			ResultSet result = ps.executeQuery();
			
			if(result.next()) {
				
				if(result.getInt("balance")<amount) {
					throw new CustomerException("Low balance. . .");
				}
				
				PreparedStatement withdrawStatement = conn.prepareStatement("update accounts set balance = balance - ? where accountId = ?");
				
				withdrawStatement.setInt(1, amount);
				withdrawStatement.setInt(2, accountNo);
				
				int x = withdrawStatement.executeUpdate();
				
				if(x > 0) {
					
					ps.setInt(1, accountNo);
					
					ResultSet updatedresult = ps.executeQuery();
					
					while(updatedresult.next()) {
						
						finalBalance = updatedresult.getInt("balance");
					
					}
					
				}
			}
			else {
				throw new CustomerException("No such account in dataBase");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return finalBalance;
	}
	

}
