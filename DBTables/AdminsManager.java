package Nov16LibrarySystem.DBTables;

import Nov16LibrarySystem.DBUtil.ConnectionManager;
import Nov16LibrarySystem.UIPackage.UIComponents;

import java.sql.*;

/**
 * Created by macstudio on 11/23/16.
 */
public class AdminsManager {
    private static Connection conn = ConnectionManager.getInstance().getConnection();

    public static boolean verifyUsername(String userName){
        if(userName.equals(UIComponents.getUserName())){
            return true;
        }
        return false;
    }
    public static boolean verifyPwd(String pwd, String userName) throws SQLException{
        String sql = "SELECT password FROM admins WHERE userName = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, userName);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            if(pwd.equals(rs.getString("password"))){
                rs.close();
                stmt.close();
                return true;
            }
        }
        rs.close();
        stmt.close();
        return false;
    }

    public static boolean changePassword(String newPwd, String userName) throws SQLException{
        String sql = "UPDATE admins SET password = ? WHERE userName = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, newPwd);
        stmt.setString(2, userName);
        int affected = stmt.executeUpdate();
        stmt.close();
        if(affected == 1){
            return true;
        }
        return false;
    }
}
