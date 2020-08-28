package com.mansishah.onlinebanking.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mansishah.onlinebanking.db.CustomerDB;
import com.mansishah.onlinebanking.db.TransactionDB;
import com.mansishah.onlinebanking.domain.AccountBalance;
import com.mansishah.onlinebanking.domain.Customer;
import com.mansishah.onlinebanking.domain.Transactions;

/**
 * Servlet implementation class TransferAction
 */
@SuppressWarnings("serial")
@WebServlet("/transferAction")
public class TransferAction extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession reqSession = request.getSession();
		
		AccountBalance ab = (AccountBalance) reqSession.getAttribute("actBalance");
		int receiverAccNo = Integer.parseInt(request.getParameter("transAct"));
		double transAmt = Double.parseDouble(request.getParameter("transAmt"));
		ArrayList<Transactions> trList;
		Transactions tr = null;
		trList = (ArrayList<Transactions>) reqSession.getAttribute("transList");
		// validate account number by looking up in DB.
		Customer custRecv = CustomerDB.getCustomerbyAccNo(receiverAccNo);
		if(custRecv == null) {
			request.setAttribute("AccMsg", "Account not found, Please enter correct Account Number.");
		} else {
			//validate transAmt is present in Customer's Balance.
			if (transAmt < 0.0D) {
				request.setAttribute("AmtMsg", "Enter positive value for transfer.");
			} else if(transAmt > ab.getBalance()) {
				request.setAttribute("AmtMsg", "Your Account does not have sufficient balance.");
			} else {
				tr = TransactionDB.transferAmtToReceiver(ab, custRecv, transAmt);
				if(tr != null) {
					trList.add(tr);
					request.setAttribute("transMsgS", "Transaction was done successfully.");
				}else {
					request.setAttribute("transMsgE", "Transaction was aborted due to internal error.");
				}
			}
		}
		request.getRequestDispatcher("transfer.jsp").forward(request, response);
	}

}
