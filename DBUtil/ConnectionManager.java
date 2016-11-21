package Nov16LibrarySystem.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "dbpassword";
    private static final String M_CONN_STRING = "jdbc:mysql://localhost/librarysystem";
    private static final String H_CONN_STRING = "jdbc:hsqldb:data/librarysystem";
    private static ConnectionManager instance = null;
    private DBType dbType = DBType.MYSQL;
    private Connection conn = null;

    private ConnectionManager() {
    }

    //getInstance method:
    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public void setDbType(DBType dbType) {
        this.dbType = dbType;
    }

    //openConnection method:
    public boolean openConnection() {
        try {
            switch (dbType) {
                case MYSQL:
                    conn = DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
                    return true;
                case HSQLDB:
                    conn = DriverManager.getConnection(H_CONN_STRING, USERNAME, PASSWORD);
                    return true;
                default:
                    return false;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    public Connection getConnection() {
        if (conn == null) {
            if (openConnection()) {
                System.out.println("Connection opened!");
                return conn;
            } else {
                return null;
            }
        }
        return conn;
    }

    //closeConnection
    public void closeConnection() {
        System.out.println("Closing connection");
        try {
            conn.close();
            conn = null;
        } catch (SQLException e) {
            System.out.println("Failed to disconnect");
        }
    }
}
