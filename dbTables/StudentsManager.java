package Nov16LibrarySystem.DBTables;

import Nov16LibrarySystem.DBUtil.ConnectionManager;
import Nov16LibrarySystem.JavaBeans.Student;

import java.sql.*;

/**
 * Created by macstudio on 11/11/16.
 */
public class StudentsManager {
    private static Connection conn = ConnectionManager.getInstance().getConnection();

    public static boolean updateStudent(Student bean) throws SQLException {

        String sql = "UPDATE students SET " + "StudentName = ?, Year = ?, Semester = ?, ContactNo = ? WHERE RegistrationNo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, bean.getStudentName());
        stmt.setInt(2, bean.getYear());
        stmt.setString(3, bean.getSmster());
        stmt.setLong(4, bean.getContactNo());
        stmt.setInt(5, bean.getRegistrationNo());

        int affected = stmt.executeUpdate();
        stmt.close();
        if (affected == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static Student searchByRegiNo(String regNo) throws SQLException, NumberFormatException {

        int regNum = Integer.parseInt(regNo);
        String sql = "SELECT StudentName, Year, Semester, ContactNo FROM students WHERE RegistrationNo = ?";

        ResultSet rs = null;
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, regNum);
        rs = stmt.executeQuery();
        if (rs.next()) {
            Student bean = new Student();
            bean.setRegistrationNo(regNum);
            bean.setStudentName(rs.getString("StudentName"));
            bean.setYear(rs.getInt("Year"));
            bean.setSmster(rs.getString("Semester"));
            bean.setContactNo(rs.getLong("ContactNo"));
            rs.close();
            stmt.close();
            return bean;
        } else {
            rs.close();
            stmt.close();
            return null;
        }
    }

    public static boolean addStudent(Student bean) throws SQLException {
        String sql = "INSERT INTO students (RegistrationNo, StudentName, Year, Semester, ContactNo)" + "VALUES(?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, bean.getRegistrationNo());
        stmt.setString(2, bean.getStudentName());
        stmt.setInt(3, bean.getYear());
        stmt.setString(4, bean.getSmster());
        stmt.setLong(5, bean.getContactNo());
        int affected = stmt.executeUpdate();
        stmt.close();
        if (affected == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean studentExisted(String regiNo) throws SQLException, NumberFormatException {

        int regiNum = Integer.parseInt(regiNo);
        String sql = "SELECT RegistrationNo FROM students";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int existingRegiNo = rs.getInt("RegistrationNo");
            if (existingRegiNo == regiNum) {
                rs.close();
                stmt.close();
                return true;
            }
        }
        rs.close();
        stmt.close();
        return false;
    }

    public static boolean removeStudent(String regiNum) throws NumberFormatException, SQLException{
        int regNumber = Integer.parseInt(regiNum);
        String sql = "DELETE FROM students WHERE RegistrationNo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, regNumber);
        int affected = stmt.executeUpdate();
        if(stmt != null) {
            stmt.close();
        }
        if(affected == 1){
            return true;
        }
        return false;
    }
}
