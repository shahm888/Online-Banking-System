package com.mansishah.onlinebanking.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mansishah.onlinebanking.domain.AccountBalance;
import com.mansishah.onlinebanking.domain.Customer;
import com.mansishah.onlinebanking.domain.Transactions;

public class TransactionDB {
	static Connection conn;
	static PreparedStatement ps;

	public static ArrayList<Transactions> getTransactions(int cust_acc_no) {
		ArrayList<Transactions> trList = new ArrayList<Transactions>();
		
		try {
			conn = MySQLAccess.getConnection();
			ps = conn.prepareStatement("Select * from obs.transaction where trans_acc = ?");
			ps.setInt(1, cust_acc_no);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Transactions temp = new Transactions();
				temp.setTransId(rs.getInt(1));
				temp.setTransDate(rs.getString(2));
				temp.setTransAmt(rs.getDouble(3));
				temp.setTransType(rs.getString(4));
				temp.setTransTo(rs.getInt(5));
				temp.setTransAcc(rs.getInt(6));
				trList.add(temp);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return trList;
		
	}

	public static Transactions transferAmtToReceiver(AccountBalance ab, Customer custRecv, double transAmt) {
		Transactions tr = null;
		AccountBalance recvAB = DepositDB.getActBalance(custRecv.getCust_acc_no());
		int status = 0;
		try {
			conn = MySQLAccess.getConnection();
			conn.setAutoCommit(false);
			status = UpdateBalance(ab, ab.getBalance() - transAmt);
			if(status != 1) {
				conn.rollback();
				return null;
			}
			status = UpdateBalance(recvAB, recvAB.getBalance() + transAmt);
			if(status != 1) {
				conn.rollback();
				return null;
			}
			// insert enrty in transaction
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currTime = sdf.format(dt);
			tr = new Transactions(currTime, transAmt, "Credit", recvAB.getCust_acct_no(), ab.getCust_acct_no());
			ps = conn.prepareStatement("Insert into obs.transaction (trans_date, trans_amnt, trans_type, trans_to, trans_acc) values (?,?,?,?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tr.getTransDate());
			ps.setDouble(2, tr.getTransAmt());
			ps.setString(3, tr.getTransType());
			ps.setInt(4, tr.getTransTo());
			ps.setInt(5, tr.getTransAcc());
			status = ps.executeUpdate();
			if(status !=1 ) {
				conn.rollback();
				return null;
			}
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				tr.setTransId(rs.getInt(1));
			}
			conn.commit();
		} catch (Exception e) {
			System.out.println(e);
		}
		return tr;
	}

	private static int UpdateBalance(AccountBalance ab, double newBalance) {
		int status = 0;
		try {	
			ab.setBalance(newBalance);
			ps = conn.prepareStatement("Update obs.account SET acc_balance = ? where acc_no = ?");
			ps.setDouble(1, ab.getBalance());
			ps.setInt(2, ab.getCust_acct_no());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

}
