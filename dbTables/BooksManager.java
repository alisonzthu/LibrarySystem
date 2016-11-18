package Nov16LibrarySystem.dbTables;

import Nov16LibrarySystem.ConnectionManager;
import Nov16LibrarySystem.JavaBeans.Book;

import javax.swing.*;
import java.sql.*;

/**
 * Created by macstudio on 11/11/16.
 */
public class BooksManager {
    public static boolean updateBook(Book bean) {
        String sql = "UPDATE books SET " + "ISBNNo = ?, BookTitle = ?, AuthorName = ?, BookshelfNo = ?, RowNo = ?, ColumnNo = ?, Edition = ?, CLAccession = ? WHERE AccessionNo = ?";
        Connection conn = ConnectionManager.getInstance().getConnection();
        try(
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setString(1, bean.getISBN());
            stmt.setString(2, bean.getbookTitle());
            stmt.setString(3, bean.getAuthor());
            stmt.setInt(4, bean.getBookshelfNo());
            stmt.setInt(5, bean.getRowNo());
            stmt.setInt(6, bean.getColumnNo());
            stmt.setInt(7, bean.getEdition());
            stmt.setInt(8, bean.getClAccession());
            stmt.setString(9, bean.getAccessionNo());

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

    public static Book searchByAcsNum(String acsNum) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        String sql = "SELECT * FROM books WHERE AccessionNo = ?";
        ResultSet rs = null;
        try(
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setString(1, acsNum);
            rs = stmt.executeQuery();
            if(rs.next()){
                Book bean = new Book();
                bean.setISBN(rs.getString("ISBNNo"));
                bean.setbookTitle(rs.getString("BookTitle"));
                bean.setAuthor(rs.getString("AuthorName"));
                bean.setAccessionNo(acsNum);
                bean.setBookshelfNo(rs.getInt("BookshelfNo"));
                bean.setRowNo(rs.getInt("RowNo"));
                bean.setColumnNo(rs.getInt("ColumnNo"));
                bean.setEdition(rs.getInt("Edition"));
                bean.setClAccession(rs.getInt("CLAccession"));
                return bean;
            }else{
                JOptionPane.showMessageDialog(null, "No books found");
                return null;
            }


        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static void addBook(Book bean){
        String sql = "INSERT INTO books (ISBNNo, BookTitle, AuthorName, AccessionNo, BookshelfNo, RowNo, ColumnNo, Edition, CLAccession)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try(

                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setString(1, bean.getISBN());
            stmt.setString(2, bean.getbookTitle());
            stmt.setString(3, bean.getAuthor());
            stmt.setString(4, bean.getAccessionNo());
            stmt.setInt(5, bean.getBookshelfNo());
            stmt.setInt(6, bean.getRowNo());
            stmt.setInt(7, bean.getColumnNo());
            stmt.setInt(8, bean.getEdition());
            stmt.setInt(9, bean.getClAccession());
            int affected = stmt.executeUpdate();
            if(affected == 1){
                JOptionPane.showMessageDialog(null, "New book added");
            }else{
                JOptionPane.showMessageDialog(null, "Failed to add new book");
            }
        }catch(SQLException e){
            System.err.println(e);
        }
    }

    public static boolean bookExisted(String acsNo){
        String sql = "SELECT AccessionNo FROM books";
        Connection conn = ConnectionManager.getInstance().getConnection();
        try(

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                ){
            while(rs.next()){
                String existingacsNo = rs.getString("AccessionNo");
                if(acsNo.equalsIgnoreCase(existingacsNo)){
                    return true;
                }
            }
            return false;

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean removeBook(String acsNo){
        String sql = "DELETE FROM books WHERE AccessionNo = ?";
        Connection conn = ConnectionManager.getInstance().getConnection();
        try(
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setString(1, acsNo);
            int affected = stmt.executeUpdate();

            if(affected == 1){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    //this method may be improved
    public static String[] searchByISBN(String isbn){
        String sql = "SELECT * FROM books WHERE ISBNNo = ?";
        Connection conn = ConnectionManager.getInstance().getConnection();
        ResultSet rs = null;
        try(
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setString(1, isbn);
            rs = stmt.executeQuery();
            rs.last();
            int nrows = rs.getRow();
            if(nrows == 0){
                JOptionPane.showMessageDialog(null, "No such book found");
                return null;
            }else {
                rs.beforeFirst();
                //String[] result = new String[4 + nrows];
                int count = 0;
                while (rs.next()) {
                    if(rs.getInt("available") == 1){
                        count++;
                    }
                }
                rs.first();
                if(count == 0){
                    String[] result = new String[5]; //the first 4 rows are ISBNNo, booktitle, author and total available copies.
                    result[0] = rs.getString("ISBNNo");
                    result[1] = rs.getString("BookTitle");
                    result[2] = rs.getString("AuthorName");
                    result[3] = Integer.toString(count);
                    result[4] = "";
                    return result;
                }else{
                    String[] result = new String[4 + count];
                    result[0] = rs.getString("ISBNNo");
                    result[1] = rs.getString("BookTitle");
                    result[2] = rs.getString("AuthorName");
                    result[3] = Integer.toString(count);

                    int i = 0;
                    while(i < count){
                        if(rs.getString("available").equals("1")) {
                            result[4 + i] = rs.getString("AccessionNo");
                            i++;
                        }
                        rs.next();
                    }
                    return result;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL Error!");
            return null;
        }finally {
            try{
                if(rs != null){
                    rs.close();
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}
