package Nov16LibrarySystem;

import Nov16LibrarySystem.ConnectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InputHelper {



    public String getInput(String prompt){
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(prompt);
        try {
            return buff.readLine();
        }catch(IOException e){
            System.err.println(e);
            return "Error" + e.getMessage();
        }
    }

    public int getIntegerInput(String prompt) throws NumberFormatException{
        String input = getInput(prompt);
        return Integer.parseInt(input);
    }

    public double getDoubleInput(String prompt) throws NumberFormatException{
        String input = getInput(prompt);
        return Double.parseDouble(input);
    }


    public static boolean checkUserInfo(String nameInput, String passwordInput){
        ConnectionManager instance = ConnectionManager.getInstance();
        String[][] userInfo;
        String sqlLogin = "SELECT userName, password FROM admins";
        try (
                Statement stmt = instance.getConnection().createStatement();
                ResultSet rsAdmins = stmt.executeQuery(sqlLogin);
        ) {
            //将结果放到一个string array里
            int i = -1;
            rsAdmins.last();
            if(rsAdmins.getRow() < 1){
                return false;
            }else {
                userInfo = new String[rsAdmins.getRow()][2];
                rsAdmins.beforeFirst();
                while (rsAdmins.next()) {
                    i++;
                    userInfo[i][0] = rsAdmins.getString("userName");
                    userInfo[i][1] = rsAdmins.getString("password");
                }
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }

        if(checkUserInfo(userInfo, nameInput, passwordInput)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean checkUserInfo(String[][] fromDB, String nameInput, String passwordInput) {

            for (int k = 0; k < fromDB.length; k++) {
                if(fromDB[k][0].equals(nameInput) && fromDB[k][1].equals(passwordInput)){
                    return true;
                }
            }
             return false;
    }

}
