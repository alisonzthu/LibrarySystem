# Library Management System:
1. UIPackage has the main component. What its classes do：<br />
    1）"UIComponents" creates different page views and put them under the control of a CardLayout manager；<br />
    2）"CreateLogIn" creates the log in panel；<br />
    3）"CreateList" creates the panel that contains the main menu(search books, student's information, etc.)；<br />
    4) "CreateCollectionOfBooks" creates a panel that allows librarians to add, edit and check books；<br />
    5) "CreateStudentInfo" creates a panel to add, edit and update student information；<br />
    6) "CreateSearchBooks" creates a panel to search book by different properties；<br />
    7) "CreateChangePwd" creates a panel for the user to change password；<br />
    8) "Frame" creates the outer frame of the whole software；<br />
    9) "HomeNlogoutPanel" creates the prototype of Home and Logout buttons on each page<br />
    10) "GBC" is the GridBagLayout manager of the whole program.<br />
2.DBUtil package interacts with the database:<br />
    1)"ConnectionManager" operates how the program opens, connects and closes Connection. It's a singleton class；<br />
    2) "DBType" is an enumeration (in case there are multiple database systems to interact with)；<br />
    3) "InputHelper" gets and checks user input, especially during login；<br />
3.DBTables package manages each table, set methods for the user to interact with the database.<br />
<br />
4.TableModels package offers table models for the layout of tables generated inside the software.<br />
<br />
5.JavaBeans gets and sets the row data of tables..<br />


