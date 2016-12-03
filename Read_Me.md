Library Management System:
1. UIPackage has the main component. What its classes do：
    1）"UIComponents" creates different page views and put them under the control of a CardLayout manager；
    2）"CreateLogIn" creates the log in panel；；
    3）"CreateList" creates the panel that contains the main menu(search books, student's information, etc.)；
    4) "CreateCollectionOfBooks" creates a panel that allows librarians to add, edit and check books；
    5) "CreateStudentInfo" creates a panel to add, edit and update student information；
    6) "CreateSearchBooks" creates a panel to search book by different properties；
    7) "CreateChangePwd" creates a panel for the user to change password；
    8) "Frame" creates the outer frame of the whole software；
    9) "HomeNlogoutPanel" creates the prototype of Home and Logout buttons on each page
    10) "GBC" is the GridBagLayout manager of the whole program.
2.DBUtil package interacts with the database:
    1)"ConnectionManager" operates how the program opens, connects and closes Connection. It's a singleton class；
    2) "DBType" is an enumeration (in case there are multiple database systems to interact with)；
    3) "InputHelper" gets and checks user input, especially during login；
3.DBTables package manages each table, set methods for the user to interact with the database

4.TableModels package offers table models for the layout of tables generated inside the software

5.JavaBeans gets and sets the row data of tables.


