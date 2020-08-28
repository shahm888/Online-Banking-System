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
import com.mansishah.onlinebanking.db.DepositDB;
import com.mansishah.onlinebanking.db.TransactionDB;
import com.mansishah.onlinebanking.domain.AccountBalance;
import com.mansishah.onlinebanking.domain.Customer;
import com.mansishah.onlinebanking.domain.Login;
import com.mansishah.onlinebanking.domain.Transactions;

/**
 * Servlet implementation class LoginRegister
 */
@SuppressWarnings("serial")
@WebServlet("/loginRegister")
public class LoginRegister extends HttpServlet {
	final static int min = 1;
	final static int max = Integer.MAX_VALUE;
	static int customer_id = (int) (Math.random() * ((max - min) + 1)) + min;
	public LoginRegister() {
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("name");
		String password = request.getParameter("password");
		String submitType = request.getParameter("submit");

		Login l = CustomerDB.getLogin(userName, password);
		if(submitType.equals("login") && (l!=null) && (l.getUsername()!= null)) {
			//get Customer Information as well:
			Customer c = CustomerDB.getCustomerbyUserName(l.getUsername());
			AccountBalance ab = DepositDB.getActBalance(c.getCust_acc_no());
			ArrayList <Transactions> transList = TransactionDB.getTransactions(c.getCust_acc_no());
			HttpSession session = request.getSession(true);
			session.setAttribute("uName", l.getUsername());
			session.setAttribute("CustomerItems", c);
			session.setAttribute("actBalance", ab);
			session.setAttribute("transList", transList);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		} else if(submitType.equals("Create")) {
			// Create new instance of customer.
			Customer c = new Customer();
			Login l1 = new Login();
			
			String uName = request.getParameter("username");
			String cPswd = request.getParameter("password1");
			String matchPswd = request.getParameter("password2");
			String validateMsg = null;
			boolean formValidate = true;
			// validate uName & cPswd
			validateMsg = CustomerDB.validateUserName(uName);
			if(validateMsg != null) {
				request.setAttribute("errorInUName", validateMsg);
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
			if(!CustomerDB.validatePasssword(cPswd)) {
				request.setAttribute("ErrInPswd", "Incorrect Password.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
			if(!cPswd.equals(matchPswd)) {
				request.setAttribute("PswdNotMatch", "Password does not match.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
			l1.setUsername(uName);
			l1.setPassword(cPswd);
			c.setCust_acc_no(customer_id++);
			c.setName(request.getParameter("name"));
			c.setAddress(request.getParameter("address"));
			c.setPh_num(request.getParameter("ph_num"));
			c.setDob(request.getParameter("dob"));
			c.setEmail(request.getParameter("email"));
			c.setSSN(request.getParameter("SSN"));
			CustomerDB.insertCustomer(l1, c);
			request.setAttribute("successMessage", "Registeration Done, Please Login to continue!!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "User not found, Click on Create Account");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
