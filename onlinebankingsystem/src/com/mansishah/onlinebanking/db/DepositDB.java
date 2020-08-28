package com.mansishah.onlinebanking.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mansishah.onlinebanking.domain.AccountBalance;
import com.mansishah.onlinebanking.domain.Transactions;

public class DepositDB {
	static Connection conn;
	static PreparedStatement ps;
	
	public static AccountBalance getActBalance(int cust_acc_no) {
		AccountBalance ab = new AccountBalance();
		try {
			conn = MySQLAccess.getConnection();
			ps = conn.prepareStatement("Select * from obs.account where acc_no = ?");
			ps.setInt(1, cust_acc_no);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ab.setCust_acct_no(rs.getInt(1));
				ab.setBalance(rs.getDouble(2));
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return ab;
	}
	
	public static Transactions insertDeposit(AccountBalance bal, double dAmt) {
		int status = 0;
		Transactions tr = null;
		try {
			conn = MySQLAccess.getConnection();
			ps = conn.prepareStatement("Update obs.account set acc_balance=? where acc_no = ?");
			bal.setBalance(bal.getBalance() + dAmt);
			ps.setDouble(1, bal.getBalance());
			ps.setInt(2, bal.getCust_acct_no());
			status = ps.executeUpdate();
			//Add the transaction in transaction table.
			if(status != 1)  return null;
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currTime = sdf.format(dt);
			tr = new Transactions(currTime, dAmt, "debit", bal.getCust_acct_no(), bal.getCust_acct_no());
			ps = conn.prepareStatement("Insert into obs.transaction (trans_date, trans_amnt, trans_type, trans_to, trans_acc) values (?,?,?,?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tr.getTransDate());
			ps.setDouble(2, tr.getTransAmt());
			ps.setString(3, tr.getTransType());
			ps.setInt(4, tr.getTransTo());
			ps.setInt(5, tr.getTransAcc());
			status = ps.executeUpdate();
			if(status != 1) return null;
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				tr.setTransId(rs.getInt(1));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return tr;
	}
}
