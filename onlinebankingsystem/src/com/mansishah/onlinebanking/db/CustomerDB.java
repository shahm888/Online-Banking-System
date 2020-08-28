package com.mansishah.onlinebanking.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mansishah.onlinebanking.domain.Customer;
import com.mansishah.onlinebanking.domain.Login;

public class CustomerDB {

	static Connection conn;
	static PreparedStatement ps;
	
	public static Login getLogin(String username, String password) {
		Login l = new Login();
		try {
			conn = MySQLAccess.getConnection();
			ps = conn.prepareStatement("Select * from obs.login where username = ? && password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				l.setUsername(rs.getString(1));
				l.setPassword(rs.getString(2));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}

	public static Customer getCustomerbyUserName(String userName) {
		Customer c = null;
		try {
			conn = MySQLAccess.getConnection();
			ps = conn.prepareStatement("Select * from obs.customer where c_username = ?");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				c = new Customer();
				c.setCust_acc_no(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setAddress(rs.getString(3));
				c.setPh_num(rs.getString(4));
				c.setDob(rs.getString(5));
				c.setEmail(rs.getString(6));
				c.setSSN(rs.getString(7));
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return c;
	}
	
	public static Customer getCustomerbyAccNo(int custAccNo) {
		Customer c = null;
		try {
			conn = MySQLAccess.getConnection();
			ps = conn.prepareStatement("Select * from obs.customer where c_acc_no = ?");
			ps.setInt(1, custAccNo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				c = new Customer();
				c.setCust_acc_no(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setAddress(rs.getString(3));
				c.setPh_num(rs.getString(4));
				c.setDob(rs.getString(5));
				c.setEmail(rs.getString(6));
				c.setSSN(rs.getString(7));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return c;
	}

	public static int insertCustomer(Login l, Customer c) {
		int status = 0, c_status=0;

		try {
			conn = MySQLAccess.getConnection();
			ps = conn.prepareStatement("INSERT INTO obs.login values(?,?)");
			ps.setString(1, l.getUsername());
			ps.setString(2, l.getPassword());
			status = ps.executeUpdate();
			if (status==1) {
				ps = conn.prepareStatement("INSERT INTO obs.customer values(?,?,?,?,?,?,?,?)");
				ps.setInt(1, c.getCust_acc_no());
				ps.setString(2, c.getName());
				ps.setString(3, c.getAddress());
				ps.setString(4, c.getPh_num());
				ps.setString(5, c.getDob());
				ps.setString(6, c.getEmail());
				ps.setString(7, c.getSSN());
				ps.setString(8, l.getUsername());
				c_status = ps.executeUpdate();
			}
			/* remove entry from login. & customer is fails*/
			if(c_status==1) {
				ps = conn.prepareStatement("Insert into obs.account values (?,?)");
				ps.setInt(1, c.getCust_acc_no());
				ps.setDouble(2, 0.0);
				
				status = ps.executeUpdate();
			}
			conn.close();
		} catch (Exception e) {

		}
		return status;
	}

	public static boolean checkforUsernName(String uName) {
		boolean found = false;
		try {
			conn = MySQLAccess.getConnection();
			ps = conn.prepareStatement("Select * from obs.login where username = ?");
			ps.setString(1, uName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				found = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return found;
	}
	
	public static String validateUserName(String uName) {
		int len = uName.length();
		if(len < 6) {
			return "Username has to be minimum of 6 characters.";
		}
		if(len > 20) {
			return "Username cannot be more than 15 characters.";
		}
		//Check for same userName in DB.
		if(checkforUsernName(uName)) {
			return "Username already exist.";
		}
		return null;
	}

	public static boolean validatePasssword(String cPswd) {
		int len = cPswd.length();
		final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{6,40})";
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(cPswd);
		
		return matcher.matches();
	}

	
}
