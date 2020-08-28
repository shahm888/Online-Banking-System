package com.mansishah.onlinebanking.domain;

public class Transactions {
	private int transId;
	private String transDate;
	private Double transAmt;
	private String transType;
	private int transTo;
	private int transAcc;
	
	public Transactions () { }
	public Transactions(String transDate, Double transAmt, String transType, int transTo, int transAcc) {
		this.transDate = transDate;
		this.transAmt = transAmt;
		this.transType = transType;
		this.transTo = transTo;
		this.transAcc = transAcc;
	}
	
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public Double getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(Double transAmt) {
		this.transAmt = transAmt;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public int getTransTo() {
		return transTo;
	}
	public void setTransTo(int transTo) {
		this.transTo = transTo;
	}
	
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	
	public int getTransAcc() {
		return transAcc;
	}
	public void setTransAcc(int transAcc) {
		this.transAcc = transAcc;
	}
	
	@Override
	public String toString() {
		return "Transactions [transId=" + transId + ", transDate=" + transDate + ", transAmt=" + transAmt
				+ ", transType=" + transType + ", transTo=" + transTo + "]";
	}
	
}
