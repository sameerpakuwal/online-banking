package com.online_banking_model;

import java.util.Date;

public class TransactionTable {
	
	
	private  double balance;
	private double withDrawnAmount;
	private double depositeAmount;
	private int  accountId;
	private Date depositedDate;
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getWithDrawnAmount() {
		return withDrawnAmount;
	}
	public void setWithDrawnAmount(double withDrawnAmount) {
		this.withDrawnAmount = withDrawnAmount;
	}
	public double getDepositeAmount() {
		return depositeAmount;
	}
	public void setDepositeAmount(double depositeAmount) {
		this.depositeAmount = depositeAmount;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public Date getDepositedDate() {
		return depositedDate;
	}
	public void setDepositedDate(Date depositedDate) {
		this.depositedDate = depositedDate;
	}
	
	
	

}
