package Nov16LibrarySystem.DBTables;

import Nov16LibrarySystem.DBUtil.ConnectionManager;

import java.sql.*;

/**
 * Created by macstudio on 11/24/16.
 */
public class BorrowRecordsManager {
    private static Connection conn = ConnectionManager.getInstance().getConnection();

    public static boolean borrowBook(String accessionNum, String stuRegiNo)throws SQLException, NumberFormatException{
        String sql = "UPDATE books SET available = 0, BorrowedBy = ? WHERE AccessionNo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, Integer.parseInt(stuRegiNo));
        stmt.setString(2, accessionNum);
        int affected = stmt.executeUpdate();
        stmt.close();
        if(affected == 1){
            return true;
        }
        return false;
    }

    public static boolean returnBook(String acsNum) throws SQLException{
        String sql = "UPDATE books SET available = 1, BorrowedBy = 0 WHERE AccessionNo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, acsNum);
        int affected = stmt.executeUpdate();
        stmt.close();
        if(affected == 1){
            return true;
        }
        return false;
    }

    public static boolean bookBorrowed(String acsNum) throws SQLException{
        String sql = "SELECT available FROM books WHERE AccessionNo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, acsNum);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            if(rs.getInt("available") == 0){
                return true;
            }
        }
        return false;
    }
}
