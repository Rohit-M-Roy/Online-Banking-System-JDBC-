package com.sensitiveMeat4664.services;

import java.util.Scanner;

import com.sensitiveMeat4664.bean.Account;
import com.sensitiveMeat4664.dao.CustomerDao;
import com.sensitiveMeat4664.dao.CustomerDaoImpl;
import com.sensitiveMeat4664.exceptions.CustomerException;

public class MoneyTransfer {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter your account number:");
		int acc1 = sc.nextInt();
		
		System.out.println("Enter the account number you want to tansfer to ");
		int acc2 = sc.nextInt();
		
		System.out.println("Transfer Amount");
		int amount = sc.nextInt();
		
		CustomerDao dao = new CustomerDaoImpl();
		
		try {
			Account senderAccountDetails = dao.transferMoney(acc1, acc2, amount);
			
			System.out.println(senderAccountDetails);
			
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
	}

}
