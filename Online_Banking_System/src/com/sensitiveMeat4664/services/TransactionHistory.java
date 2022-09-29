package com.sensitiveMeat4664.services;

import java.util.List;
import java.util.Scanner;

import com.sensitiveMeat4664.bean.Transaction;
import com.sensitiveMeat4664.dao.CustomerDao;
import com.sensitiveMeat4664.dao.CustomerDaoImpl;
import com.sensitiveMeat4664.exceptions.CustomerException;

public class TransactionHistory {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter account id:");
		int accnum = sc.nextInt();
		
		CustomerDao dao = new CustomerDaoImpl();
		
		try {
			List<Transaction> history = dao.veiwTransactionHistory(accnum);
			
			for(Transaction ele : history) {
				System.out.println(ele);
			}
			
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		sc.close();
	}

}
