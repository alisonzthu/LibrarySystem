package Nov16LibrarySystem.UIPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by macstudio on 11/18/16.
 */
public class HomeNlogoutPanel extends JPanel {
    private JPanel mainPanel;
    private JPanel upperPanel;
    private JPanel centerPanel;

    public JPanel addOutmostPanel(){
        mainPanel = new JPanel(new GridBagLayout());
        upperPanel = new JPanel(new GridBagLayout());
        JButton homeButton = new JButton("Home");
        homeButton.setHorizontalAlignment(JButton.CENTER);
        homeButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
//
                        UIComponents.firstCard();
                        UIComponents.nextCard();
                    }
                }
        );
        upperPanel.add(homeButton, new GBC(0, 0).setAnchor(GBC.CENTER).setInsets(0, 50, 0, 0));

        JButton logoutButton = new JButton(new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/logout.png"));
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(null, "Are you sure to log out?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    //log out
                    UIComponents.firstCard();
                    UIComponents.setUserName("");
                    UIComponents.setUserPassword("");
                }
            }
        });
        upperPanel.add(logoutButton, new GBC(1, 0).setAnchor(GBC.CENTER));

        mainPanel.add(upperPanel, new GBC(0, 0).setInsets(30, 20, 0, 0));
        centerPanel = new JPanel();


        mainPanel.add(centerPanel, new GBC(0, 1).setAnchor(GBC.CENTER));
        return mainPanel;
    }
}
