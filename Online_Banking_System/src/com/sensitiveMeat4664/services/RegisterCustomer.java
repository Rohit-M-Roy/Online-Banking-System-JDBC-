package com.sensitiveMeat4664.services;

import java.util.Scanner;

import com.sensitiveMeat4664.bean.Customer;
import com.sensitiveMeat4664.dao.CustomerDao;
import com.sensitiveMeat4664.dao.CustomerDaoImpl;
import com.sensitiveMeat4664.exceptions.CustomerException;

public class RegisterCustomer {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter First Name:");
		String fname = sc.next();
		
		System.out.println("Enter Last Name:");
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
		
		
		Customer customer  = new Customer(fname,lname,mobile,email,state,zip,address);
		
		CustomerDao registration = new CustomerDaoImpl();
		
		try {
			String result = registration.registerCustomer(customer);
			System.out.println(result);
		} catch (CustomerException e) {
			
			e.printStackTrace();
		}
		sc.close();

	}

}
