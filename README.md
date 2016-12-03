# Library Management System:
1. UIPackage has the main component. What its classes do：
    * "UIComponents" creates different page views and put them under the control of a CardLayout manager；
    * "CreateLogIn" creates the log in panel；
    * "CreateList" creates the panel that contains the main menu(search books, student's information, etc.)；
    * "CreateCollectionOfBooks" creates a panel that allows librarians to add, edit and check books；
    * "CreateStudentInfo" creates a panel to add, edit and update student information；
    * "CreateSearchBooks" creates a panel to search book by different properties；
    * "CreateChangePwd" creates a panel for the user to change password；
    * "Frame" creates the outer frame of the whole software；
    * "HomeNlogoutPanel" creates the prototype of Home and Logout buttons on each page；
    * "GBC" is the GridBagLayout manager of the whole program.
2. DBUtil package interacts with the database:
    * "ConnectionManager" operates how the program opens, connects and closes Connection. It's a singleton class；
    * "DBType" is an enumeration (in case there are multiple database systems to interact with)；
    * "InputHelper" gets and checks user input, especially during login.
3. DBTables package manages each table, set methods for the user to interact with the database.
4. TableModels package offers table models for the layout of tables generated inside the software.
5. JavaBeans gets and sets the row data of tables.
