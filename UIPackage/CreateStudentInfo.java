package Nov16LibrarySystem.UIPackage;

import Nov16LibrarySystem.DBTables.StudentsManager;
import Nov16LibrarySystem.JavaBeans.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by macstudio on 11/21/16.
 */
public class CreateStudentInfo {
    public JPanel studentInfPanel() {
        final String ADDPANEL = "Add student/member";
        final String DETAIL = "Student details";
        final String EDITPANEL = "Edit student/member";
        final int extraWindowWidth = 100;

        JPanel upPanel = new HomeNlogoutPanel().addOutmostPanel();
        JPanel maincard4Panel = new JPanel(new GridBagLayout());
        maincard4Panel.add(upPanel, new GBC(0, 0).setAnchor(GBC.WEST));

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel card1 = new JPanel() {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };

        card1.setLayout(new GridBagLayout());
        JPanel centerPanelcard1 = new JPanel(new GridBagLayout());

        centerPanelcard1.add(new JLabel("Student's Registration no.:"), new GBC(0, 0).setAnchor(GBC.EAST));
        JTextField regNocard1 = new JTextField(15);
        centerPanelcard1.add(regNocard1, new GBC(1, 0).setAnchor(GBC.WEST).setWeight(100, 0));
        centerPanelcard1.add(new JLabel("Student's name:"), new GBC(0, 1).setAnchor(GBC.EAST));
        JTextField stuNamecard1 = new JTextField(15);
        centerPanelcard1.add(stuNamecard1, new GBC(1, 1).setAnchor(GBC.WEST).setWeight(100, 0));
        centerPanelcard1.add(new JLabel("Year:"), new GBC(0, 2).setAnchor(GBC.EAST));
        Integer[] years = {2014, 2015, 2016, 2017, 2018};
        JComboBox<Integer> yearBox = new JComboBox<Integer>(years);
        centerPanelcard1.add(yearBox, new GBC(1, 2).setAnchor(GBC.WEST).setWeight(100, 0));
        centerPanelcard1.add(new JLabel("Semester:"), new GBC(0, 3).setAnchor(GBC.EAST));
        String[] semester = {"Spring", "Fall"};
        JComboBox<String> semesterBox = new JComboBox<>(semester);
        centerPanelcard1.add(semesterBox, new GBC(1, 3).setAnchor(GBC.WEST).setWeight(100, 0));
        centerPanelcard1.add(new JLabel("Contact no.:"), new GBC(0, 4).setAnchor(GBC.EAST));
        JTextField contactNocard1 = new JTextField(15);
        centerPanelcard1.add(contactNocard1, new GBC(1, 4).setAnchor(GBC.WEST).setWeight(100, 0));

        centerPanelcard1.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        card1.add(centerPanelcard1, new GBC(0, 0).setAnchor(GBC.CENTER));
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton, BorderLayout.CENTER);
        card1.add(buttonPanel, new GBC(0, 1).setAnchor(GBC.CENTER));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student bean = new Student();
                if (regNocard1.getText().isEmpty() || stuNamecard1.getText().isEmpty() || contactNocard1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields should be filled");
                    return;
                }
                try {
                    boolean existed = StudentsManager.studentExisted(regNocard1.getText());
                    if (existed) {
                        JOptionPane.showMessageDialog(null, "Student Existed!");
                    } else {
                        int regi = Integer.parseInt(regNocard1.getText());
                        int yr = Integer.parseInt(yearBox.getSelectedItem().toString());
                        Long ctctNo = Long.parseLong(contactNocard1.getText());

                        bean.setRegistrationNo(regi);
                        bean.setStudentName(stuNamecard1.getText());
                        bean.setYear(yr);
                        bean.setSmster(semesterBox.getSelectedItem().toString());
                        bean.setContactNo(ctctNo);
                        try {
                            boolean added = StudentsManager.addStudent(bean);
                            if (added) {
                                JOptionPane.showMessageDialog(null, "Student added!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to add student!");
                            }
                        } catch (SQLException e1) {
                            JOptionPane.showMessageDialog(null, "SQLException!");
                        }
                    }
                } catch (SQLException esql) {
                    JOptionPane.showMessageDialog(null, "SQLException");
                } catch (NumberFormatException en) {
                    JOptionPane.showMessageDialog(null, "Invalid input");
                }
            }
        });

        JPanel card2 = new JPanel(new BorderLayout());
        JPanel upperPanelcard2 = new JPanel(new GridBagLayout());
        upperPanelcard2.add(new JLabel("Registration No:"), new GBC(0, 0).setAnchor(GBC.EAST));
        JTextField regNocard2 = new JTextField(10);
        upperPanelcard2.add(regNocard2, new GBC(1, 0).setAnchor(GBC.WEST));
        card2.add(upperPanelcard2, BorderLayout.NORTH);

        JPanel centerPanelcard2 = new JPanel(new GridBagLayout());
        centerPanelcard2.add(new JLabel("Registration no: "), new GBC(0, 0).setAnchor(GBC.EAST).setInsets(0, 70, 10, 0));
        JLabel regNoLabel = new JLabel();
        centerPanelcard2.add(regNoLabel, new GBC(1, 0).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));
        centerPanelcard2.add(new JLabel("Student's Name: "), new GBC(0, 1).setAnchor(GBC.EAST).setInsets(0, 70, 10, 0));
        JLabel stuNameLabel = new JLabel();
        centerPanelcard2.add(stuNameLabel, new GBC(1, 1).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));
        centerPanelcard2.add(new JLabel("Year: "), new GBC(0, 2).setAnchor(GBC.EAST).setInsets(0, 70, 10, 0));
        JLabel yearLabel = new JLabel();
        centerPanelcard2.add(yearLabel, new GBC(1, 2).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));
        centerPanelcard2.add(new JLabel("Semester: "), new GBC(0, 3).setAnchor(GBC.EAST).setInsets(0, 70, 10, 0));
        JLabel smstrLabel = new JLabel();
        centerPanelcard2.add(smstrLabel, new GBC(1, 3).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));
        centerPanelcard2.add(new JLabel("Contact no: "), new GBC(0, 4).setAnchor(GBC.EAST).setInsets(0, 70, 10, 0));
        JLabel contactLabel = new JLabel();
        centerPanelcard2.add(contactLabel, new GBC(1, 4).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));
        card2.add(centerPanelcard2, BorderLayout.CENTER);
        regNocard2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean existed = StudentsManager.studentExisted(regNocard2.getText());
                    if (existed) {
                        Student searchedBean = StudentsManager.searchByRegiNo(regNocard2.getText());
                        regNoLabel.setText(Integer.toString(searchedBean.getRegistrationNo()));
                        stuNameLabel.setText(searchedBean.getStudentName());
                        yearLabel.setText(Integer.toString(searchedBean.getYear()));
                        smstrLabel.setText(searchedBean.getSmster());
                        contactLabel.setText(Long.toString(searchedBean.getContactNo()));

                    } else {
                        JOptionPane.showMessageDialog(null, "No students found!");
                    }
                } catch (SQLException esql) {
                    JOptionPane.showMessageDialog(null, "SQLException");
                } catch (NumberFormatException enumb) {
                    JOptionPane.showMessageDialog(null, "Invalid input");
                }
            }
        });

        JPanel card3 = new JPanel(new GridBagLayout());
        JPanel centerPanelcard3 = new JPanel(new GridBagLayout());
        JLabel prompt = new JLabel("Enter student's Registration no:");
        prompt.setForeground(Color.BLUE);
        centerPanelcard3.add(prompt, new GBC(0, 0).setAnchor(GBC.EAST));
        JTextField regNocard3 = new JTextField(8);
        centerPanelcard3.add(regNocard3, new GBC(1, 0).setAnchor(GBC.WEST));
        JButton remove = new JButton("Remove");
        centerPanelcard3.add(remove, new GBC(2, 0).setAnchor(GBC.CENTER));

        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure to remove this student?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        String regNum = regNocard3.getText();
                        if(!StudentsManager.studentExisted(regNum)){
                            JOptionPane.showMessageDialog(null, "No such student!");
                            return;
                        }
                        if (StudentsManager.removeStudent(regNum)) {
                            JOptionPane.showMessageDialog(null, "Student removed!");
                        }
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, "SQLException, failed to remove!");
                    } catch (NumberFormatException e2){
                        JOptionPane.showMessageDialog(null, "Invalid input, failed to remove!");
                    }
                }
            }
        });

        centerPanelcard3.add(new JLabel("Student's Name:"), new GBC(0, 1).setAnchor(GBC.EAST));
        JTextField stuNamecard3 = new JTextField(8);
        centerPanelcard3.add(stuNamecard3, new GBC(1, 1).setAnchor(GBC.WEST));
        centerPanelcard3.add(new JLabel("Year:"), new GBC(0, 2).setAnchor(GBC.EAST));
        JComboBox<Integer> yearcard3 = new JComboBox<>(years);
        centerPanelcard3.add(yearcard3, new GBC(1, 2).setAnchor(GBC.WEST));

        centerPanelcard3.add(new JLabel("Semester:"), new GBC(0, 3).setAnchor(GBC.EAST));
        JComboBox<String> smstrcard3 = new JComboBox<>(semester);
        centerPanelcard3.add(smstrcard3, new GBC(1, 3).setAnchor(GBC.WEST));
        centerPanelcard3.add(new JLabel("Contact no.:"), new GBC(0, 4).setAnchor(GBC.EAST));
        JTextField contactNocard3 = new JTextField(8);
        centerPanelcard3.add(contactNocard3, new GBC(1, 4).setAnchor(GBC.WEST));

        card3.add(centerPanelcard3, new GBC(0, 1).setAnchor(GBC.CENTER));
        regNocard3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Student newBean = StudentsManager.searchByRegiNo(regNocard3.getText());
                            if (newBean != null) {
                                stuNamecard3.setText(newBean.getStudentName());
                                yearcard3.setSelectedItem(newBean.getYear());
                                smstrcard3.setSelectedItem(newBean.getSmster());
                                contactNocard3.setText(Long.toString(newBean.getContactNo()));
                            } else {
                                JOptionPane.showMessageDialog(null, "No students found!");
                            }
                        } catch (NumberFormatException e1) {
                            JOptionPane.showMessageDialog(null, "Invalid input!");
                        } catch (SQLException e2) {
                            JOptionPane.showMessageDialog(null, "SQLException!");
                        }
                    }
                }
        );
        JPanel goButtonPanel = new JPanel();
        JButton goButton = new JButton("Go");
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student updateStudent = new Student();
                try {
                    updateStudent.setRegistrationNo(Integer.parseInt(regNocard3.getText()));
                    updateStudent.setStudentName(stuNamecard3.getText());
                    updateStudent.setYear(Integer.parseInt(yearcard3.getSelectedItem().toString()));
                    updateStudent.setSmster(smstrcard3.getSelectedItem().toString());
                    updateStudent.setContactNo(Long.parseLong(contactNocard3.getText()));
                    boolean updateState = StudentsManager.updateStudent(updateStudent);
                    if (updateState) {
                        JOptionPane.showMessageDialog(null, "Student updated!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Update failed!");
                    }
                } catch (NumberFormatException en) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "SQLException!");
                }
            }
        });
        goButtonPanel.add(goButton);
        card3.add(goButtonPanel, new GBC(0, 2).setAnchor(GBC.CENTER));

        tabbedPane.addTab(ADDPANEL, card1);
        tabbedPane.addTab(DETAIL, card2);
        tabbedPane.addTab(EDITPANEL, card3);

        maincard4Panel.add(tabbedPane, new GBC(0, 1, 6, 1).setAnchor(GBC.CENTER));
        return maincard4Panel;
    }
}
