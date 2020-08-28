package com.mansishah.onlinebanking.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mansishah.onlinebanking.db.DepositDB;
import com.mansishah.onlinebanking.domain.AccountBalance;
import com.mansishah.onlinebanking.domain.Customer;
import com.mansishah.onlinebanking.domain.Transactions;

/**
 * Servlet implementation class DepositAmt
 */
@SuppressWarnings("serial")
@WebServlet("/depositAmt")
public class DepositAmt extends HttpServlet {
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession reqSession = request.getSession();
		AccountBalance bal = (AccountBalance) reqSession.getAttribute("actBalance");
		ArrayList<Transactions> trList;
		trList= ((ArrayList<Transactions>) reqSession.getAttribute("transList"));
		
		Transactions tr;
		double depAmt = Double.parseDouble(request.getParameter("depositAmt"));
		
		if(depAmt < 0) {
			request.setAttribute("mesg", "Invalid Amount: Enter Deposit Amount greater than 0.");
		} else {
			tr = DepositDB.insertDeposit(bal, depAmt); 
			if(tr == null){
				request.setAttribute("mesg", "Internal Error while depositing the amount.");
			} else {
				trList.add(tr);
				request.setAttribute("mesg", "Successfully Deposited the amount");
			}
		}
		request.getRequestDispatcher("deposit.jsp").forward(request, response);
	}

}
