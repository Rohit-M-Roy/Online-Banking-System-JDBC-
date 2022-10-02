package com.sensitiveMeat4664.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.security.auth.login.AccountException;

import com.sensitiveMeat4664.bean.Account;
import com.sensitiveMeat4664.bean.AccountDetails;
import com.sensitiveMeat4664.bean.Customer;
import com.sensitiveMeat4664.bean.Transaction;
import com.sensitiveMeat4664.dao.AccountantDao;
import com.sensitiveMeat4664.dao.AccountantDaoImpl;
import com.sensitiveMeat4664.dao.CustomerDao;
import com.sensitiveMeat4664.dao.CustomerDaoImpl;
import com.sensitiveMeat4664.exceptions.CustomerException;

public class Bank {
	
	public static void services() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("--------Welcome to the Bank----------------");
		
		System.out.println("Register Yourself(Press 1)");
		System.out.println("Already a User (Press 2)");
		System.out.println("--------------------------------------------");
		System.out.println("Accountant Login(Press 3)");
		System.out.println("Exit Input Applicatio (Press 4)");
		int firstSelection = sc.nextInt();
		
		switch(firstSelection) {
		
		case 1:
			//register user
			Customer customer = registrationDetails(sc);
			CustomerDao dao = new CustomerDaoImpl();
			
			String message;
			try {
				message = dao.registerCustomer(customer);
				
				System.out.println(message+"\n\n");
				
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case 2:
			//customer services
			customerServices(sc) ;
			break;
		
		case 3:
			//accountant services
			accountantServices(sc);
			break;
			
		case 4:
			System.out.println("---------------ThankYou-----------------");
			return;
		
		default:
			System.out.println("**Not a valid Option**\n\n");
			services();
		}
	}
	
	public static Customer registrationDetails(Scanner sc) {
		
		Customer customer = null;
		
		System.out.println("Enter your first name: ");
		String fname = sc.next();
		
		System.out.println("Enter your last name: ");
		String lname = sc.next();
		
		System.out.println("Enter Mobile No:");
		long mobile = sc.nextLong();
		
		System.out.println("Enter emailId:");
		String email = sc.next();
		
		System.out.println("Enter resident State:");
		String state = sc.next();
		
		System.out.println("Enter ZipCode:");
		int zip = sc.nextInt();
		
		sc.nextLine();
		System.out.println("Enter Address:");
		String address = sc.nextLine();
		
		customer  = new Customer(fname,lname,mobile,email,state,zip,address);
		
		return customer;
		
	}

	public static void customerServices(Scanner sc) {
		
		CustomerDao dao = new CustomerDaoImpl();
		
		System.out.println("Enter 1. to create an Account");
		System.out.println("Enter 2. to deposit");
		System.out.println("Enter 3. to withdraw");
		System.out.println("Enter 4. to transfer to another account");
		System.out.println("Enter 5. to get Transaction History");
		
		
		int choice = sc.nextInt();
		
		switch(choice) {
		
		case 1:
			
			System.out.println("Enter Mobile Number");
			long mobile = sc.nextLong();
			
			System.out.println("Enter Deposit Amount");
			int deposit = sc.nextInt();
			
			
			
			try {
				Account createdAccount =  dao.createAccount(mobile, deposit);
				
				System.out.println("Account succesfully createdd");
				System.out.println("Account Details are as follows:");
				System.out.println("Account Number = "+createdAccount.getAccountNumber());
				System.out.println("Balance = "+createdAccount.getBalance());
				System.out.println("**Account Number and Mobile Number is important for futher operations**\n\n");
			}
			catch (CustomerException e) {
				
				System.out.println(e.getMessage());
			}
			customerServices(sc); 
			break;
		case 2:
			
			System.out.println("Enter the amount you wish to deposit: ");
			int amount = sc.nextInt();
			System.out.println("Enter Account Number: ");
			int accountNo = sc.nextInt();
			int finalBalance = 0;
			try {
				finalBalance = dao.depositMoney(accountNo, amount);
			} catch (CustomerException e) {
				
				System.out.println("Server Error");
				
			}
			
			System.out.println("Amount deposit succesfully");
			System.out.println("Your current balance is :"+ finalBalance+"\n\n");
			
			customerServices(sc);
			
			break;
		case 3:
			
			System.out.println("Enter the amount you wish to Withdraw: ");
			int amountW = sc.nextInt();
			System.out.println("Enter Account Number: ");
			int accountNoW = sc.nextInt();
			int finalBalanceW = 0;
			try {
				finalBalanceW = dao.withdrawMoney(accountNoW, amountW);
			} catch (CustomerException e) {
				
				System.out.println("Server Error");
				
			}
			
			System.out.println("Amount withdrawm succesfully");
			System.out.println("Your current balance is :"+ finalBalanceW+"\n\n");
			
			customerServices(sc);
			break;
		case 4:
			
			System.out.println("Enter the amount you wish to Withdraw: ");
			int amountT = sc.nextInt();
			System.out.println("Enter Account Number: ");
			int accountNoY = sc.nextInt();
			System.out.println("Enter the Account Number you wish to transfer too");
			int accountNoT = sc.nextInt();
			
			try {
				Account account = dao.transferMoney(accountNoY, accountNoT, amountT);
			} catch (CustomerException e) {
				
				System.out.println(e.getMessage());
			}
			
			customerServices(sc);
			break;
		case 5:
			
			System.out.println("Enter Account number:");
			int accNo = sc.nextInt();
			List<Transaction> listOfTransaction = new ArrayList<>();
			
			try {
				listOfTransaction = dao.veiwTransactionHistory(accNo);
			} catch (CustomerException e) {
			
				System.out.println(e.getMessage());
			}
			
			for(Transaction ele : listOfTransaction) {
				
				System.out.println(ele);
			}
			
			customerServices(sc);
			break;
		default:
			services();
			break;
		
		}
		
	}
	
	public static void accountantServices(Scanner sc) {
		
		AccountantDao dao = new AccountantDaoImpl();
		
		System.out.println("Enter 1 to add Customer");
		System.out.println("Enter 2 to remove Account");
		System.out.println("Enter 3 to view account deatails of a particular account");
		System.out.println("Enter 4 to veiw all Accounts");
		System.out.println("Enter 5 to exit");
		
		int choice = sc.nextInt();
		
		switch(choice) {
		
		case 1:
			
			Customer customer = registrationDetails(sc);
			
			String message = dao.addCustomer(customer);
			
			System.out.println(message);
			accountantServices(sc);
			break;
		case 2:
			
			System.out.println("Enter the account Id you want to remove");
			int removeAcc = sc.nextInt();
			
			dao.removeAccount(removeAcc);

			System.out.println("Account Succesfully Removed");
			accountantServices(sc);
			break;
		case 3:
			
			System.out.println("Enter account number: ");
			int viewAccount = sc.nextInt();
			
			try {
				
				AccountDetails viewAcc = dao.veiwAccount(viewAccount);
				
				System.out.println(viewAcc);
				
			} catch (AccountException e) {
				
				System.out.println(e.getMessage());
			}
			
			accountantServices(sc);
			
			break;
		case 4:
			
			List<Account> listofAccounts = new ArrayList<>();
			
			try {
				listofAccounts = dao.viewAllAccounts();
			} catch (AccountException e) {
				System.out.println(e.getMessage());
			}
			
			for(Account ele : listofAccounts) {
				
				System.out.println(ele);
			}
			System.out.println("\n\n");
			accountantServices(sc);
			
			break;
		case 5:
			
			services();
			
			break;
			
		default:
			System.out.println("Enter Valid option\n\n\n");
			accountantServices(sc);
		
		}
		
	}
	
}

