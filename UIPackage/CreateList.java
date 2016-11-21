package Nov16LibrarySystem.UIPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by macstudio on 11/21/16.
 */
public class CreateList {
    private JPanel listPanel;

    public JPanel createListPanel() {

        Icon book = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/LibrarySystem/src/Nov16LibrarySystem/UIPackage/Pics/bookIcon.png");
        Icon students = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/LibrarySystem/src/Nov16LibrarySystem/UIPackage/Pics/students.png");
        Icon magnifier = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/LibrarySystem/src/Nov16LibrarySystem/UIPackage/Pics/magnifier.png");
        Icon borrow = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/LibrarySystem/src/Nov16LibrarySystem/UIPackage/Pics/borrowBook.png");
        Icon changePass = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/LibrarySystem/src/Nov16LibrarySystem/UIPackage/Pics/changePassword.png");
        Icon backup = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/LibrarySystem/src/Nov16LibrarySystem/UIPackage/Pics/backup.png");
        Icon generateRep = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/LibrarySystem/src/Nov16LibrarySystem/UIPackage/Pics/generateReport.png");

        JPanel maincard2Panel = new JPanel(new GridBagLayout());
        listPanel = new JPanel(new GridBagLayout());

        JButton bookIcon = new JButton(book);
        bookIcon.setPreferredSize(new Dimension(100, 100));
        bookIcon.setHorizontalAlignment(JButton.CENTER);
        bookIcon.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //enter card3
                        UIComponents.nextCard();
                    }
                }
        );
        listPanel.add(bookIcon, new GBC(0, 0).setAnchor(GBC.CENTER));

        JLabel bookIconNameLine1 = new JLabel("Collection");
        bookIconNameLine1.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(bookIconNameLine1, new GBC(0, 1).setAnchor(GBC.CENTER));
        JLabel bookIconNameLine2 = new JLabel("of books");
        bookIconNameLine2.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(bookIconNameLine2, new GBC(0, 2).setAnchor(GBC.CENTER));

        JButton studentsIcon = new JButton(students);
        studentsIcon.setPreferredSize(new Dimension(100, 100));
        studentsIcon.setHorizontalAlignment(JButton.CENTER);
        studentsIcon.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //JOptionPane.showMessageDialog(null, "manage students");
                        UIComponents.nextCard();
                        UIComponents.nextCard();
                    }
                }
        );
        listPanel.add(studentsIcon, new GBC(1, 0).setAnchor(GBC.CENTER));

        JLabel studentsIconNameLine1 = new JLabel("Student'");
        studentsIconNameLine1.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(studentsIconNameLine1, new GBC(1, 1).setAnchor(GBC.CENTER));
        JLabel studentsIconNameLine2 = new JLabel("information");
        studentsIconNameLine2.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(studentsIconNameLine2, new GBC(1, 2).setAnchor(GBC.CENTER));

        JButton magnifierButton = new JButton(magnifier);
        magnifierButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        UIComponents.nextCard();
                        UIComponents. nextCard();
                        UIComponents.nextCard();//go to card5
                    }
                }
        );
        magnifierButton.setPreferredSize(new Dimension(100, 100));
        magnifierButton.setHorizontalAlignment(JButton.CENTER);
        listPanel.add(magnifierButton, new GBC(2, 0).setAnchor(GBC.CENTER));

        JLabel magnifierIconNameLine1 = new JLabel("Search");
        magnifierIconNameLine1.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(magnifierIconNameLine1, new GBC(2, 1).setAnchor(GBC.CENTER));
        JLabel magnifierIconNameLine2 = new JLabel("books");
        magnifierIconNameLine2.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(magnifierIconNameLine2, new GBC(2, 2).setAnchor(GBC.CENTER));

        JButton borrowBookButton = new JButton(borrow);
        borrowBookButton.setPreferredSize(new Dimension(100, 100));
        borrowBookButton.setHorizontalAlignment(JButton.CENTER);
        borrowBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UIComponents.nextCard();
                UIComponents. nextCard();
                UIComponents.nextCard();
                UIComponents.nextCard();//go to card6
            }
        });
        listPanel.add(borrowBookButton, new GBC(3, 0).setAnchor(GBC.CENTER));

        JLabel borrBookLine1 = new JLabel("Borrow");
        borrBookLine1.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(borrBookLine1, new GBC(3, 1).setAnchor(GBC.CENTER));
        JLabel borrBookLine2 = new JLabel("books");
        borrBookLine2.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(borrBookLine2, new GBC(3, 2).setAnchor(GBC.CENTER));

        JButton chngPss = new JButton(changePass);
        chngPss.setPreferredSize(new Dimension(100, 100));
        chngPss.setHorizontalAlignment(JButton.CENTER);
        listPanel.add(chngPss, new GBC(4, 0).setAnchor(GBC.CENTER));

        JLabel chngPssLine1 = new JLabel("Change");
        chngPssLine1.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(chngPssLine1, new GBC(4, 1).setAnchor(GBC.CENTER));
        JLabel chngPssLine2 = new JLabel("password");
        chngPssLine2.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(chngPssLine2, new GBC(4, 2).setAnchor(GBC.CENTER));

        JButton backUpButton = new JButton(backup);
        backUpButton.setPreferredSize(new Dimension(100, 100));
        backUpButton.setHorizontalAlignment(JButton.CENTER);
        listPanel.add(backUpButton, new GBC(5, 0).setAnchor(GBC.CENTER));

        JLabel backupLine1 = new JLabel("Backup");
        backupLine1.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(backupLine1, new GBC(5, 1).setAnchor(GBC.CENTER));
        JLabel backupLine2 = new JLabel("database");
        backupLine2.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(backupLine2, new GBC(5, 2).setAnchor(GBC.CENTER));

        JButton generateReportBut = new JButton(generateRep);
        generateReportBut.setPreferredSize(new Dimension(100, 100));
        generateReportBut.setHorizontalAlignment(JButton.CENTER);
        listPanel.add(generateReportBut, new GBC(6, 0).setAnchor(GBC.CENTER));

        JLabel generateRepLine1 = new JLabel("Generate");
        generateRepLine1.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(generateRepLine1, new GBC(6, 1).setAnchor(GBC.CENTER));
        JLabel generateRepLine2 = new JLabel("report");
        generateRepLine2.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(generateRepLine2, new GBC(6, 2).setAnchor(GBC.CENTER));

        JPanel upPanel = new HomeNlogoutPanel().addOutmostPanel();
        maincard2Panel.add(upPanel, new GBC(0, 0).setAnchor(GBC.WEST));
        maincard2Panel.add(listPanel, new GBC(0, 1).setAnchor(GBC.CENTER));
        return maincard2Panel;
    }
}
