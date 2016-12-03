package Nov16LibrarySystem.UIPackage;

import Nov16LibrarySystem.DBTables.AdminsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by macstudio on 11/23/16.
 */

//maincard7
public class CreateChangePwd {

    public JPanel changePwd(){
        JPanel mainPanel = new JPanel(new GridBagLayout());
        JPanel upPanel = new HomeNlogoutPanel().addOutmostPanel();
        mainPanel.add(upPanel, new GBC(0, 0).setAnchor(GBC.WEST));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setPreferredSize(new Dimension(450, 350));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Change password"));
        centerPanel.add(new JLabel("Please enter userName:"), new GBC(0, 0).setAnchor(GBC.CENTER));
        JTextField userName = new JTextField(10);
        centerPanel.add(userName, new GBC(0, 1).setAnchor(GBC.CENTER));
        centerPanel.add(new JLabel("Please enter old password:"), new GBC(0, 2).setAnchor(GBC.CENTER));
        JPasswordField oldPwd = new JPasswordField(10);
        centerPanel.add(oldPwd, new GBC(0, 3).setAnchor(GBC.CENTER));
        centerPanel.add(new JLabel("Please enter new password:"), new GBC(0, 4).setAnchor(GBC.CENTER));
        JPasswordField newPwd1 = new JPasswordField(10);
        centerPanel.add(newPwd1, new GBC(0, 5).setAnchor(GBC.CENTER));
        centerPanel.add(new JLabel("Please enter new password again:"), new GBC(0, 6).setAnchor(GBC.CENTER));
        JPasswordField newPwd2 = new JPasswordField(10);
        centerPanel.add(newPwd2, new GBC(0, 7).setAnchor(GBC.CENTER));
        JButton goButton = new JButton(new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/LibrarySystem/src/Nov16LibrarySystem/UIPackage/Pics/goButton.png"));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(goButton);
        centerPanel.add(buttonPanel, new GBC(0, 8).setAnchor(GBC.CENTER));
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userName.getText();
                if(AdminsManager.verifyUsername(username)){
                    try{
                        if(AdminsManager.verifyPwd(new String(oldPwd.getPassword()), username)){
                            if(new String(newPwd1.getPassword()).equals(new String(newPwd2.getPassword()))){
                                if(AdminsManager.changePassword(new String(newPwd1.getPassword()), username)){
                                    JOptionPane.showMessageDialog(null, "Changed password successfully!");
                                    UIComponents.firstCard();
                                    UIComponents.setUserName("");
                                    UIComponents.setUserPassword("");
                                }else{
                                    JOptionPane.showMessageDialog(null, "Failed to change password!");
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Please enter the same new password!");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Wrong password!");
                        }
                    }catch(SQLException e1){
                        JOptionPane.showMessageDialog(null, "SQLException, failed to change password!");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Wrong userName!");
                }
            }
        });
        mainPanel.add(centerPanel, new GBC(0, 1).setAnchor(GBC.CENTER));
        return mainPanel;
    }
}
