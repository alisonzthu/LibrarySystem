package Nov16LibrarySystem.UIPackage;

import Nov16LibrarySystem.DBUtil.InputHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
    ---------------------createLoginGUI-------------------------------maincard1
     */
public class CreateLogIn {
    private static JPanel homepage;
    private static JPanel loginPanel;
    private static JLabel uniLogo;
    private static JLabel uniName;
    private static JLabel userNamePrompt;
    private static JLabel userPasswordPrompt;
    private static JButton enter;
    private static JLabel prgrmrInfo;

    protected JPanel createLoginGUI() {


        homepage = new JPanel();
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());

        //university Logo
        Icon logo = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/LibrarySystem/src/Nov16LibrarySystem/UIPackage/Pics/UOMLogo.png");
        uniLogo = new JLabel(logo);
        loginPanel.add(uniLogo, new GBC(1, 3).setAnchor(GBC.CENTER).setInsets(100, 0, 0, 0));
        //university name
        uniName = new JLabel("Library System");
        uniName.setForeground(Color.BLUE);
        loginPanel.add(uniName, new GBC(1, 4).setAnchor(GBC.CENTER));
        //userName prompt
        userNamePrompt = new JLabel("Username");
        loginPanel.add(userNamePrompt, new GBC(1, 5).setAnchor(GBC.CENTER));
        //userName input section
        UIComponents.userName = new JTextField(10);
        UIComponents.userName.setHorizontalAlignment(JTextField.CENTER);
        loginPanel.add(UIComponents.userName, new GBC(1, 6).setAnchor(GBC.CENTER).setWeight(100, 0));
        //password prompt
        userPasswordPrompt = new JLabel("Password");
        loginPanel.add(userPasswordPrompt, new GBC(1, 7).setAnchor(GBC.CENTER));
        //password input section
        UIComponents.userPassword = new JPasswordField(10);
        UIComponents.userPassword.setHorizontalAlignment(JTextField.CENTER);
        loginPanel.add(UIComponents.userPassword, new GBC(1, 8).setAnchor(GBC.CENTER).setWeight(100, 0));//may add an ActionListener for this item

        JPanel enterButtonPanel = new JPanel(new BorderLayout());
        Icon enterIcon = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/LibrarySystem/src/Nov16LibrarySystem/UIPackage/Pics/arrowToEnter.png");
        enter = new JButton(enterIcon);
        //add the same actionListener on enter button and userPassword
        Handler h = new Handler();
        enter.addActionListener(h);
        UIComponents.userPassword.addActionListener(h);

        enterButtonPanel.add(enter, BorderLayout.WEST);
        loginPanel.add(enterButtonPanel, new GBC(2, 8).setAnchor(GBC.LINE_START));
        //add my version info
        Font font1 = new Font("Serif", Font.ITALIC, 12);
        prgrmrInfo = new JLabel("@powered by Alison");
        prgrmrInfo.setFont(font1);
        loginPanel.add(prgrmrInfo, new GBC(1, 9).setAnchor(GBC.CENTER));
        homepage.add(loginPanel, BorderLayout.CENTER);

        return homepage;
    }

    private class Handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            char[] password = UIComponents.userPassword.getPassword();
            String passwordStr = new String(password);
            //if userInfo incorrect
            if (!InputHelper.checkUserInfo(UIComponents.userName.getText(), passwordStr)) {
                JOptionPane.showMessageDialog(null, "Wrong information!");

            } else {
                //if userInfo correct:
                UIComponents.nextCard();
            }
        }
    }
}
