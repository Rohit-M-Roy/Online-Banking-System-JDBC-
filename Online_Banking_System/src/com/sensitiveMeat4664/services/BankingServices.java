package com.sensitiveMeat4664.services;

import java.util.Scanner;

import com.sensitiveMeat4664.bean.Customer;

public class BankingServices {
	
	public static void bankingservices() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("--------Welcome to the Bank----------------");
		
		System.out.println("Register Yourself(Press 1)");
		System.out.println("Already a User (Press 2)");
		System.out.println("--------------------------------------------");
		System.out.println("Accountant Login(Press 3)");
		int firstSelection = sc.nextInt();
		
		switch(firstSelection) {
		
		case 1:
			//register user
			Customer customer = registrationDetails(sc);
			
			
			break;
			
		case 2:
			//customer services
			break;
		
		case 3:
			//accountant services
			break;
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

}
