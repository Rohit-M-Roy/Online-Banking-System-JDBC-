package com.sensitiveMeat4664.bean;

import java.util.Objects;

public class Accounts {
	
	private int accountNumber;
	private int customerId;
	private int balance;
	
	Accounts(){
		
	}
	
	Accounts(int number, int id, int balance){
		setAccountNumber(number);
		setCustomerId(id);
		setBalance(balance);
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accounts other = (Accounts) obj;
		return accountNumber == other.accountNumber;
	}

	@Override
	public String toString() {
		return "Accounts [accountNumber=" + accountNumber + ", customerId=" + customerId + ", balance=" + balance + "]";
	}
	
	

}
