package com.sensitiveMeat4664.bean;

import java.util.Objects;

public class Transaction {
	
	private int transactionId;
	private String transactionType;
	private int transactionAmount;
	private int AccountIdRelatedToTheTransaction;
	
	public Transaction(){
		
	}
	
	public Transaction(int id, String type, int amount, int accountid){
		setTransactionId(id);
		setTransactionType(type);
		setTransactionAmount(amount);
		setAccountIdRelatedToTheTransaction(accountid);
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(int transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public int getAccountIdRelatedToTheTransaction() {
		return AccountIdRelatedToTheTransaction;
	}

	public void setAccountIdRelatedToTheTransaction(int accountIdRelatedToTheTransaction) {
		AccountIdRelatedToTheTransaction = accountIdRelatedToTheTransaction;
	}

	@Override
	public int hashCode() {
		return Objects.hash(transactionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return transactionId == other.transactionId;
	}

	@Override
	public String toString() {
		return "Transactions [transactionId=" + transactionId + ", transactionType=" + transactionType
				+ ", transactionAmount=" + transactionAmount + ", AccountIdRelatedToTheTransaction="
				+ AccountIdRelatedToTheTransaction + "]";
	}
	
	
	
}
