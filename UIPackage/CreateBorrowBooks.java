package Nov16LibrarySystem.UIPackage;

import Nov16LibrarySystem.DBTables.BooksManager;
import Nov16LibrarySystem.DBTables.StudentsManager;
import Nov16LibrarySystem.JavaBeans.Student;
import Nov16LibrarySystem.TableModels.BookTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by macstudio on 11/21/16.
 */
//maincard6
public class CreateBorrowBooks {
    public JPanel borrowBooks() {
        JPanel maincard6Panel = new JPanel(new GridBagLayout());
        JPanel upPanel = new HomeNlogoutPanel().addOutmostPanel();
        maincard6Panel.add(upPanel, new GBC(0, 0).setAnchor(GBC.WEST));

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel card1 = new JPanel(new GridBagLayout());
        JPanel card2 = new JPanel(new BorderLayout());
        JPanel card3 = new JPanel(new GridBagLayout());
        final String BRWBOOK = "Borrow Book";
        final String BRWLIST = "Borrower's list";
        final String RETURNBK = "Return book";

        //in card1:
        JPanel leftPanel = new JPanel(new GridBagLayout());
        JPanel middlePanel = new JPanel(new GridBagLayout());
        JPanel rightPanel = new JPanel(new GridBagLayout());

        leftPanel.add(new JLabel("Registration no"), new GBC(0, 0).setAnchor(GBC.CENTER));
        JTextField regNocard1 = new JTextField(10);
        leftPanel.add(regNocard1, new GBC(0, 1).setAnchor(GBC.WEST));
        leftPanel.setBorder(BorderFactory.createEtchedBorder());
        leftPanel.setPreferredSize(new Dimension(150, 200));
        card1.add(leftPanel, new GBC(0, 0).setAnchor(GBC.WEST).setInsets(0, 0, 0, 20));

        middlePanel.add(new JLabel("Automatically Fill up"), new GBC(0, 0).setAnchor(GBC.WEST));
        middlePanel.add(new JLabel("Name:"), new GBC(0, 1).setAnchor(GBC.WEST));
        JTextField namecard1 = new JTextField(12);
        namecard1.setEditable(false);
        middlePanel.add(namecard1, new GBC(0, 2).setAnchor(GBC.WEST));
        middlePanel.add(new JLabel("Year"), new GBC(0, 3).setAnchor(GBC.WEST));
        JComboBox<Integer> yearBox = new JComboBox<>();
        yearBox.setEditable(false);
        yearBox.setPrototypeDisplayValue(10000);
        middlePanel.add(yearBox, new GBC(0, 4).setAnchor(GBC.WEST));
        middlePanel.add(new JLabel("Semester"), new GBC(0, 5).setAnchor(GBC.WEST));
        JComboBox<String> semestercard1 = new JComboBox<>();
        semestercard1.setEditable(false);
        semestercard1.setPrototypeDisplayValue("Spring");
        middlePanel.add(semestercard1, new GBC(0, 6).setAnchor(GBC.WEST));
        middlePanel.setBorder(BorderFactory.createEtchedBorder());
        middlePanel.setPreferredSize(new Dimension(170, 200));
        card1.add(middlePanel, new GBC(1, 0).setAnchor(GBC.WEST).setInsets(0, 0, 0, 20));


        rightPanel.add(new JLabel("Accession No"), new GBC(0, 0).setAnchor(GBC.CENTER));
        JTextField acsNocard1 = new JTextField(10);
        rightPanel.add(acsNocard1, new GBC(0, 1).setAnchor(GBC.CENTER));
        JButton goButtoncard1 = new JButton(new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/LibrarySystem/src/Nov16LibrarySystem/UIPackage/Pics/goButton.png"));
        rightPanel.add(goButtoncard1, new GBC(0, 2).setAnchor(GBC.CENTER));
        rightPanel.setBorder(BorderFactory.createEtchedBorder());
        rightPanel.setPreferredSize(new Dimension(280, 200));
        card1.add(rightPanel, new GBC(2, 0).setAnchor(GBC.WEST).setInsets(0, 0, 0, 20));
        card1.setPreferredSize(new Dimension(710, 210));

        regNocard1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Student student = StudentsManager.searchByRegiNo(regNocard1.getText());
                    if(student != null){
                        namecard1.setText(student.getStudentName());
                        yearBox.removeAllItems();
                        yearBox.getModel().setSelectedItem(student.getYear());
                        semestercard1.removeAllItems();
                        semestercard1.getModel().setSelectedItem(student.getSmster());
                        middlePanel.updateUI();
                    }else{
                        JOptionPane.showMessageDialog(null, "Student not found!");
                    }
                }catch(NumberFormatException e1){
                    JOptionPane.showMessageDialog(null, "Invalid Registration Number!");
                }catch(SQLException e2){
                    JOptionPane.showMessageDialog(null, "SQLException!");
                }
            }
        });
//        goButtoncard1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String acsNum = acsNocard1.getText();
//                try{
//                    if(BooksManager.getAvailByAcsNo(acsNum)){
//                       if(BooksManager.borrowBook(acsNum, regNocard1.getText())){
//                           JOptionPane.showMessageDialog(null, "Book borrowed!");
//                       }else{
//                           JOptionPane.showMessageDialog(null, "Failed to borrow book!");
//                       }
//                    }else{
//                     JOptionPane.showMessageDialog(null, "Book doesn't exist or unavailable!");
//                    }
//                }catch(SQLException e1){
//                    JOptionPane.showMessageDialog(null, "SQLException!");
//                }catch(NumberFormatException e2){
//                    JOptionPane.showMessageDialog(null, "Invalid registration number!");
//                }
//
//            }
//        });

        //in card2:
        JPanel upPanelcard2 = new JPanel();
        JButton listofB = new JButton("List of borrowers");
        upPanelcard2.add(listofB);

        JPanel centerPanelcard2 = new JPanel();
        listofB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanelcard2.removeAll();
                JTable borrowerTable = new JTable(BookTableModel.getTableModel("SELECT students.StudentName, students.RegistrationNo, students.ContactNo, books.BookTitle, books.AccessionNo FROM students INNER JOIN books ON students.RegistrationNo = books.BorrowedBy"));
                JScrollPane pane = new JScrollPane(borrowerTable);
                borrowerTable.setPreferredScrollableViewportSize(new Dimension(600, 70));
                borrowerTable.setFillsViewportHeight(true);
                DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) borrowerTable.getTableHeader().getDefaultRenderer();
                renderer.setHorizontalAlignment(JLabel.CENTER);
                borrowerTable.setShowGrid(true);
                borrowerTable.setGridColor(Color.GREEN);
                borrowerTable.setVisible(true);
                centerPanelcard2.add(pane);
                centerPanelcard2.updateUI();
            }
        });
        card2.add(upPanelcard2, new BorderLayout().NORTH);
        card2.add(centerPanelcard2, new BorderLayout().CENTER);
        //in card3:
        card3.add(new JLabel("AccessionNo"), new GBC(0, 0).setAnchor(GBC.CENTER));
        JTextField acsNocard3 = new JTextField(10);
        card3.add(acsNocard3, new GBC(0, 1).setAnchor(GBC.CENTER));
        JPanel buttonPanel = new JPanel();
        JButton goButtoncard3 = new JButton(new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/LibrarySystem/src/Nov16LibrarySystem/UIPackage/Pics/goButton.png"));
        buttonPanel.add(goButtoncard3);
        card3.add(buttonPanel, new GBC(0, 2).setAnchor(GBC.CENTER));

//        goButtoncard3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(JOptionPane.showConfirmDialog(null, "Are you sure to return this book?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
//                    try{
//                        String acsNumber = acsNocard3.getText();
//                        if(BooksManager.bookExisted(acsNumber)){
//                            if(BooksManager.bookBorrowed(acsNumber)){
//                                if(BooksManager.returnBook(acsNocard3.getText())){
//                                    JOptionPane.showMessageDialog(null, "Book returned!");
//                                }else{
//                                    JOptionPane.showMessageDialog(null, "Failed to return Book!");
//                                }
//                            }else{
//                                JOptionPane.showMessageDialog(null, "Book was not borrowed!");
//                            }
//                        }else{
//                            JOptionPane.showMessageDialog(null, "No such book!");
//                        }
//                    }catch(SQLException e1){
//                        JOptionPane.showMessageDialog(null, "SQLException!");
//                    }
//                }
//            }
//        });

        tabbedPane.add(BRWBOOK, card1);
        tabbedPane.add(BRWLIST, card2);
        tabbedPane.add(RETURNBK, card3);
        tabbedPane.setPreferredSize(new Dimension(700, 250));

        maincard6Panel.add(tabbedPane, new GBC(0, 1).setAnchor(GBC.CENTER));
        return maincard6Panel;
    }
}
