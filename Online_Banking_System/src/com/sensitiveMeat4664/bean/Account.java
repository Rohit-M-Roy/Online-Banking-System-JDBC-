package com.sensitiveMeat4664.bean;

import java.util.Objects;

public class Account {
	
	private int accountNumber;
	private long mobile;
	private int balance;
	
	Account(){
		
	}
	
	public Account(int accountNo,long mobile, int balance){
		setAccountNumber(accountNo);
		setMobile(mobile);
		setBalance(balance);
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
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
		Account other = (Account) obj;
		return accountNumber == other.accountNumber;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", mobile=" + mobile + ", balance=" + balance + "]";
	}

	
	
	

}
