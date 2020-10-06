# OnlineBankingSystem
Online banking Service Project

 In Online banking service, it provides services like login, create new users, Perform banking chores
like Deposit, transfer and view transfer summary.

Intende user of the project are anyone who wants to use banking services like deposit money and
transfer fund to another account & watch overall transfer performed.

What URLs will a user start with to use this provided application?
Ans: http://localhost:8080/onlinebankingsystem/login.jsp

Ans: There are 4 tables used here:
Login: (username, password) – this will store the login details of the customers.
Customer: (Account No., Name, Address, Phone No., Date of Birth, Email, SSN, username)
This will store the customer’s personal details.
Account: (Account No, Balance) : This will store the customer’s account, current balance details.
Transaction: (Transaction ID, Date, Amount, Type, To, From)
This will store all the transaction details for all customers.

Here, is the example of how i used MVC model for my application.
The whole project is performed using MVC- Model-View-Controller design pattern, one of the
example, let says create new user.
I have created “register.jsp” as the View, which will take inputs from users and when users clicks
“create” button, It will transfer all the user inputs to servlet “LoginRegister.java” in servlet domain. Here
All user input is validated like username has to be of alteast 6 letters. UserName has to be unique, for
that it will call “CustomerDB.java” which is in Model/DB domain. Where it will check if the username
already exists in database or not. If it exist then error message is sent to Controller domain which will
redirect the response to register.jsp and will display error message.
Once all the user inputs are validated, Controller (“LoginRegister.java”) will call insertCustomer() in
Model(CustomerDB.java) and that will insert the new customer in Customer database. On successful
insertion, Model will send positive response to Controller and controller will redirect page to login with
Success message. Now the new user can proceed with login.

Mention any optional, advanced features, you are using, such as describe what data your web service
provides and what servlet performs the service or where you used the Post-Redirect-Get pattern.
Ans: Web Contents:
- Login.jsp : Provides login Page
- Register.jsp: Provides a page where new user can register.
- Welcome.jsp: Provides the Account summary of the user where user can see all the
information related to their current balance.
- Deposit.jsp: Provides service to deposit funds to user account.
- Transfer.jsp: Provides service to transfer funds from user account to another account.
- TransferSummary.jsp: Provides details related to transfer the user has made during his
account tenure.
Servlets:
- LoginRegister.java: Process all the information from Login & register.jsp page and pass
this information to Model domain to perform DB operations.
- DepositAmt.java: This will validate user Inputs and call DepositDB.java to update the
balance for the customer.
- TransferAction.java: This servlet will perform transfer of funds from customer account
to receiver’s account. This servlet will call TransactionDB.java to update the balance of
both users in DB.
Domain: to map database tables to classes.
- Login.java
- Customer.java
- AccountBalance.java
- Transactions.java
Database: This package will have classes that will perform JDBC connection and update the
database.
- CustomerDB.java : Have methods to update or retrieve information from Customer’s
table.
- DepositDB.java : have methods to update or retrieve information of Customer’s current
balance from Account table.
- TransactionDB.java: For all deposits and transfer transactions are store and retrieved
from Transaction table in database.
- MySQLAccess.java: This class have methods to make connection to the Database using
JDBC.
