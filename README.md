To Load:

Import whole folder as Eclipse dynamic web projet

navigate to folder, and run docker-compose up -d to spin up the MySQL and PHPMyAdmin servers (running on localhost:3306 and localhost:8081 respectively)

in eclipse, access database through host: 127.0.0.1:3306, and username: root, password: rootpass

Wildfly Server folder should be set up, when adding a new server, just choose the runtime to be the Wildfly folder in the project directory

TODO:
We need to implement the functions inside the BankDAO class, which should be exclusively be functions that access data values (in order to create entity classes)

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
- in rest package, need to implement functions that can parse the data we receive from the DAO in a way that can be read by an external application.
Should provide a way to expose CRUD methods, as well as Business logic

External App:
- should be able to access the functionality exported by REST API. Should be a simple interpreter that can allow the user to enter commands to interact with the database.

