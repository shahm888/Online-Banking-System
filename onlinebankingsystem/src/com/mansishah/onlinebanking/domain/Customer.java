package com.mansishah.onlinebanking.domain;

public class Customer {
	private int cust_acc_no;
	private String name;
	private String address;
	private String email;
	private String ph_num;
	private String SSN;
	private String dob;
	
	public Customer(int cust_id, String name, String address, String email, String ph_num, String sSN, String dob) {
		this.cust_acc_no = cust_id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.ph_num = ph_num;
		SSN = sSN;
		this.dob = dob;
	}


	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public int getCust_acc_no() {
		return cust_acc_no;
	}

	public void setCust_acc_no(int cust_id) {
		this.cust_acc_no = cust_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPh_num() {
		return ph_num;
	}

	public void setPh_num(String ph_num) {
		this.ph_num = ph_num;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}


	@Override
	public String toString() {
		return "Customer [cust_acc_no=" + cust_acc_no + ", name=" + name + ", address=" + address + ", email=" + email
				+ ", ph_num=" + ph_num + ", SSN=" + SSN + ", dob=" + dob + "]";
	}

	
	
	
	
}
