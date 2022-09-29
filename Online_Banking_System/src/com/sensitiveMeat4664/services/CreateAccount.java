package com.sensitiveMeat4664.services;

import java.util.Scanner;

import com.sensitiveMeat4664.bean.Account;
import com.sensitiveMeat4664.dao.CustomerDao;
import com.sensitiveMeat4664.dao.CustomerDaoImpl;
import com.sensitiveMeat4664.exceptions.CustomerException;

public class CreateAccount {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Mobile Number");
		long mobile = sc.nextLong();
		
		System.out.println("Enter Deposit Amount");
		int deposit = sc.nextInt();
		
		CustomerDao dao = new CustomerDaoImpl();
		
		try {
			Account createdAccount =  dao.createAccount(mobile, deposit);
			
			System.out.println("Account succesfully creatd");
			System.out.println("Account Details are as follows:");
			System.out.println("Account Number = "+createdAccount.getAccountNumber());
			System.out.println("Balance = "+createdAccount.getBalance());
			
		} 
		catch (CustomerException e) {
			
			System.out.println(e.getMessage());
		}
		

	}

}
