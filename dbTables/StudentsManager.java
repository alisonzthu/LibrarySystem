package Nov16LibrarySystem.dbTables;

import Nov16LibrarySystem.ConnectionManager;
import Nov16LibrarySystem.JavaBeans.Student;

import javax.swing.*;
import java.sql.*;

/**
 * Created by macstudio on 11/11/16.
 */
public class StudentsManager {

    public static boolean updateStudent(Student bean){
        Connection conn = ConnectionManager.getInstance().getConnection();
        String sql = "UPDATE students SET " + "StudentName = ?, Year = ?, Semester = ?, ContactNo = ? WHERE RegistrationNo = ?";
        try(
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setString(1, bean.getStudentName());
            stmt.setInt(2, bean.getYear());
            stmt.setString(3, bean.getSmster());
            stmt.setLong(4, bean.getContactNo());
            stmt.setInt(5, bean.getRegistrationNo());

            int affected = stmt.executeUpdate();
            if(affected == 1){
                return true;
            }else{
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static Student searchByRegiNo(String regNo){
        try{
            int regNum = Integer.parseInt(regNo);
            String sql = "SELECT StudentName, Year, Semester, ContactNo FROM students WHERE RegistrationNo = ?";
            Connection conn = ConnectionManager.getInstance().getConnection();
            ResultSet rs = null;
            try(
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    ){
                stmt.setInt(1, regNum);
                rs = stmt.executeQuery();
                if(rs.next()){
                    Student bean = new Student();
                    bean.setRegistrationNo(regNum);
                    bean.setStudentName(rs.getString("StudentName"));
                    bean.setYear(rs.getInt("Year"));
                    bean.setSmster(rs.getString("Semester"));
                    bean.setContactNo(rs.getLong("ContactNo"));
                    return bean;
                }else{
                    JOptionPane.showMessageDialog(null, "No students found!");
                    return null;
                }
            }catch(SQLException ex){
                ex.printStackTrace();
                return null;
            }finally {
                try{
                    if(rs != null){
                        rs.close();
                    }
                }catch(SQLException exception){
                    exception.printStackTrace();
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Registration number should be pure numeric!");
            return null;
        }
    }

    public static void addStudent(Student bean){
        String sql = "INSERT INTO students (RegistrationNo, StudentName, Year, Semester, ContactNo)" + "VALUES(?, ?, ?, ?, ?)";
        Connection conn = ConnectionManager.getInstance().getConnection();
        try(
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, bean.getRegistrationNo());
            stmt.setString(2, bean.getStudentName());
            stmt.setInt(3, bean.getYear());
            stmt.setString(4, bean.getSmster());
            stmt.setLong(5, bean.getContactNo());
            int affected = stmt.executeUpdate();
            if(affected == 1){
                JOptionPane.showMessageDialog(null, "Student added!");
            }else{
                JOptionPane.showMessageDialog(null, "Failed to add student!");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean studentExisted(String regiNo){
        try {
            int regiNum = Integer.parseInt(regiNo);
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT RegistrationNo FROM students";
            try(
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
            ){
                while(rs.next()){
                    int existingRegiNo = rs.getInt("RegistrationNo");
                    if(existingRegiNo == regiNum){
                        return true;
                    }
                }
              return false;
            }catch(SQLException e){
                e.printStackTrace();
                return false;
            }
        }catch(Exception e){
//            JOptionPane.showMessageDialog(null, "Registration number should be pure numeric!");
            return false;
        }

    }
}
