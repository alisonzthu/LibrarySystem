package Nov16LibrarySystem.UIPackage;

import javax.swing.*;
import java.awt.*;

/*
对所有用户可能的输出格式错误进行预警,防止程序中断!
2.texts gathered from the user are better be trimmed, but I haven't done it because we need to create way more Strings.
 */
public class UIComponents extends JPanel {
    private static JPanel cards;
    protected static JTextField userName;
    protected static JPasswordField userPassword;

    public static void setUserName(String uName) {
        userName.setText(uName);
    }

    public static String getUserName(){
        return userName.getText();
    }

    public static void setUserPassword(String newpass) {
        userPassword.setText(newpass);
    }

    public UIComponents() {

        //在cards里要加上所有可能会显示的panel
        cards = new JPanel(new CardLayout());

        CreateLogIn createLogIn = new CreateLogIn();
        cards.add(createLogIn.createLoginGUI());//maincard1

        CreateList createList = new CreateList();
        cards.add(createList.createListPanel());//maincard2

        CreateCollectionOfBooks clecOfBooks = new CreateCollectionOfBooks();
        cards.add(clecOfBooks.collectionOfBooks());//maincard3

        CreateStudentInfo stuInfo = new CreateStudentInfo();
        cards.add(stuInfo.studentInfPanel());//maincard4

        CreateSearchBooks srchBooks = new CreateSearchBooks();
        cards.add(srchBooks.searchBooks());//maincard5

        CreateBorrowBooks brwBooks = new CreateBorrowBooks();
        cards.add(brwBooks.borrowBooks());//maincard6

        CreateChangePwd chngpwd = new CreateChangePwd();
        cards.add(chngpwd.changePwd());//maincard7

        add(cards);
    }

    public static void nextCard() {
        CardLayout layout = (CardLayout) cards.getLayout();
        layout.next(cards);
    }

    public static void firstCard() {
        CardLayout layout = (CardLayout) cards.getLayout();
        layout.first(cards);
    }
}

    /*
    ---------------------createLoginGUI: maincard1

   -------------------- create lists panel:maincard2
   -----------------------collectionOfBooks: maincard3
   ----------------------studentInformationPanel:maincard4
   ----------------------searchBooks:maincard5
  -----------------------borrowBook panel: maincard6
    */
