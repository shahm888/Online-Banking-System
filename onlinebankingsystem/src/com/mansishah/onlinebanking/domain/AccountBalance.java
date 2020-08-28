package com.mansishah.onlinebanking.domain;

public class AccountBalance {
	private int cust_acct_no;
	private double balance;
	
	public AccountBalance(int cust_acct_no, double balance) {
		this.cust_acct_no = cust_acct_no;
		this.balance = balance;
	}
	
	public AccountBalance() {
		
	}

	public int getCust_acct_no() {
		return cust_acct_no;
	}

	public void setCust_acct_no(int cust_acct_no) {
		this.cust_acct_no = cust_acct_no;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountBalance [cust_acct_no=" + cust_acct_no + ", balance=" + balance + "]";
	}
	
}
