package Nov16LibrarySystem.DBTables;

import Nov16LibrarySystem.DBUtil.ConnectionManager;
import Nov16LibrarySystem.JavaBeans.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by macstudio on 11/11/16.
 */
public class BooksManager {
    private static Connection conn = ConnectionManager.getInstance().getConnection();
    public static boolean updateBook(Book bean) throws SQLException {
        String sql = "UPDATE books SET " + "ISBNNo = ?, BookTitle = ?, AuthorName = ?, BookshelfNo = ?, RowNo = ?, ColumnNo = ?, Edition = ?, CLAccession = ? WHERE AccessionNo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

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
        stmt.close();
        if (affected == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static Book searchByAcsNum(String acsNum) throws SQLException {
        String sql = "SELECT * FROM books WHERE AccessionNo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = null;

        stmt.setString(1, acsNum);
        rs = stmt.executeQuery();
        if (rs.next()) {
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
            rs.close();
            stmt.close();
            return bean;
        } else {
            rs.close();
            stmt.close();
            return null;
        }
    }

    public static int addBook(Book bean) throws SQLException {
        String sql = "INSERT INTO books (ISBNNo, BookTitle, AuthorName, AccessionNo, BookshelfNo, RowNo, ColumnNo, Edition, CLAccession)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

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
        if (stmt != null) {
            stmt.close();
        }
        return affected;
    }

    public static boolean bookExisted(String acsNo) throws SQLException {
        String sql = "SELECT AccessionNo FROM books";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String existingacsNo = rs.getString("AccessionNo");
            if (acsNo.equalsIgnoreCase(existingacsNo)) {
                rs.close();
                stmt.close();
                return true;
            }
        }
        rs.close();
        stmt.close();
        return false;
    }

    public static boolean removeBook(String acsNo) throws SQLException {
        String sql = "DELETE FROM books WHERE AccessionNo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, acsNo);
        int affected = stmt.executeUpdate();
        if (stmt != null) {
            stmt.close();
        }
        if (affected == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static Book searchAvailByISBN(String isbn) throws SQLException {
        String sql = "SELECT * FROM books WHERE ISBNNo = ?";
        ResultSet rs = null;
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, isbn);
        rs = stmt.executeQuery();
        if (rs.next()) {
            Book bean = new Book();
            bean.setISBN(isbn);
            bean.setbookTitle(rs.getString("BookTitle"));
            bean.setAuthor(rs.getString("AuthorName"));
            bean.setAccessionNo(rs.getString("AccessionNo"));
            bean.setBookshelfNo(rs.getInt("BookshelfNo"));
            bean.setRowNo(rs.getInt("RowNo"));
            bean.setColumnNo(rs.getInt("ColumnNo"));
            bean.setEdition(rs.getInt("Edition"));
            bean.setClAccession(rs.getInt("CLAccession"));
            bean.setAvailable(rs.getInt("available"));
            rs.close();
            stmt.close();
            return bean;
        } else {
            rs.close();
            stmt.close();
            return null;
        }
    }

    public static String[] getAvailBooksByISBN(String isbn) throws SQLException {
        String sql = "SELECT AccessionNo from books WHERE (ISBNNo = ? AND available = 1)";
        ResultSet rs = null;
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, isbn);
        rs = stmt.executeQuery();
        rs.last();
        int nRows = rs.getRow();
        if (nRows == 0) {
            rs.close();
            stmt.close();
            return null;
        } else {
            rs.beforeFirst();
            ArrayList<String> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rs.getString("AccessionNo"));
            }
            String[] resultCopies = Arrays.copyOf(result.toArray(), result.toArray().length, String[].class);
            rs.close();
            stmt.close();
            return resultCopies;
        }
    }
}
