To Load:

Import whole folder as Eclipse dynamic web project

Navigate to folder, and run docker-compose up -d to spin up the MySQL and PHPMyAdmin servers (running on localhost:3306 and localhost:8081 respectively)
Then just navigate to localhost:8081, sign in with server mysql_db, username root, password rootpass, and import the bank_db.sql file to initialize the tables.

In eclipse, access database through host: 127.0.0.1:3306, and username: root, password: rootpass


Wildfly Server folder should be set up, when adding a new server, just choose Wildfly 27+
Make sure to choose a new runtime enviornment, running java 17, and choose the Wildfly-server directory when creating the new runtime (rather than downloading a new one). Also make sure that under project settings "Project Facets", the java 17 version is selected, with the "runtimes" tab selecting the correct wildfly runtime that you created.

TODO:
We need to implement the functions inside the BankDAO class, which should be exclusively be functions that access data values (in order to create entity classes)

JSP:
We need to finish implementing all front end pages, including ways of executing business logic and crud functions.
Dynamically generate web content jsp, html, css.

CRUD:
- display all students
- display all accounts
- Create new Student
- Create new User
- Delete Account
- Delete User (along with all associated accounts)
Business Logic:
- Update Account:
  - Transactions: Withdraw and Deposit options
  - Transfers: take money from one account and place it in another
Restful API:
- REST Package needs implementation of all CRUD methods and business logic

External App:
- should be able to access the functionality exported by REST API. Should be a simple interpreter that can allow the user to enter commands to interact with the database.


Documentation:
ERD and Class diagram
Screenshots of application
Evaluation of product development lifecycle

