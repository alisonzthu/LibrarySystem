package Nov16LibrarySystem.TableModels;

import Nov16LibrarySystem.DBUtil.ConnectionManager;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

/**
 * Created by macstudio on 11/10/16.
 */
public class BookTableModel extends AbstractTableModel{
    private String[] columnNames = {};
    private Object[][] data = {};

    public int getColumnCount(){
        return columnNames.length;
    }

    public int getRowCount(){
        return data.length;
    }

    public Object getValueAt(int row, int col){
        return data[row][col];
    }

    public String getColumnName(int col){
        return columnNames[col];
    }

    public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
    }

    public static DefaultTableModel getTableModel(String sql){
        try(
                Statement stmt = ConnectionManager.getInstance().getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                ){
            ResultSetMetaData metaData = rs.getMetaData();
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for(int column = 1; column <= columnCount; column++){
                columnNames.add(metaData.getColumnName(column));
            }

            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while(rs.next()){
                Vector<Object> vector = new Vector<Object>();
                for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
            return new DefaultTableModel(data, columnNames);


        }catch(SQLException e){
            System.err.print(e);
            return null;
        }
    }

    public static DefaultTableModel getTableModelByTitle(String sql, String bookTitle){
        ResultSet rs = null;
        try(
                PreparedStatement stmt = ConnectionManager.getInstance().getConnection().prepareStatement(sql);

        ){
            stmt.setString(1, bookTitle);
            rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for(int column = 1; column <= columnCount; column++){
                columnNames.add(metaData.getColumnName(column));
            }

            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while(rs.next()){
                Vector<Object> vector = new Vector<Object>();
                for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
            return new DefaultTableModel(data, columnNames);


        }catch(SQLException e){
            System.err.print(e);
            return null;
        }
    }
}
