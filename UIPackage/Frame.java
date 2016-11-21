package Nov16LibrarySystem.UIPackage;

import Nov16LibrarySystem.DBUtil.ConnectionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame{


    ConnectionManager instance = ConnectionManager.getInstance();


    public Frame(){
        UIComponents components = new UIComponents();
        add(components, BorderLayout.CENTER);
        setTitle("UniMelb Library Management System");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(900, 600);
        setResizable(false);
        setLocation(300, 200);//can fine-tune the parameters later
        setVisible(true);

//        try{
//            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        UIManager.put("swing.boldMetal", Boolean.FALSE);



        addWindowListener(
                new WindowListener(){
                    public void windowOpened(WindowEvent e){}
                    public void windowIconified(WindowEvent e){}
                    public void windowDeiconified(WindowEvent e){}
                    public void windowActivated(WindowEvent e){}
                    public void windowDeactivated(WindowEvent e){}
                    public void windowClosed(WindowEvent e){}
                    public void windowClosing(WindowEvent e){
                        if((JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "Exit Confirmation",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)){
                            instance.closeConnection();
                            System.exit(0);
                        }
                    }
                }
        );
    }



}
