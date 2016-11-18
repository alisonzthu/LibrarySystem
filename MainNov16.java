package Nov16LibrarySystem;

import java.awt.*;
import java.sql.Connection;



public class MainNov16 {


    public static void main(String[] args){

        //1. connect to database



        //2. display software GUI
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                Connection connection = ConnectionManager.getInstance().getConnection();
                Frame frame = new Frame();

            }
        });


        //3. try to log in to the system/software
        //如果checkUserInfo为真,则登录


        //4. insert, delete, update(on books only now)

        //5. search, return table or graphics

        //6. close connection,触发条件为关闭窗口


    }
}
