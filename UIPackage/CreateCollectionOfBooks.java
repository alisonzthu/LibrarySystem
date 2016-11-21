package Nov16LibrarySystem.UIPackage;

import Nov16LibrarySystem.DBTables.BooksManager;
import Nov16LibrarySystem.JavaBeans.Book;
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
public class CreateCollectionOfBooks {
    public JPanel collectionOfBooks() {
        final String ADDPANEL = "Add new book";
        final String LISTBOOKPANEL = "List of books";
        final String EDITPANEL = "Edit book information";

        JPanel upPanel = new HomeNlogoutPanel().addOutmostPanel();
        JPanel maincard3Panel = new JPanel(new GridBagLayout());
        maincard3Panel.add(upPanel, new GBC(0, 0).setAnchor(GBC.WEST));

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel card1 = new JPanel(new GridBagLayout());
        card1.setLayout(new BorderLayout());
        JPanel mainpanelcard1 = new JPanel();
        mainpanelcard1.setLayout(new GridBagLayout());
        mainpanelcard1.add(new JLabel("Accession no.:"), new GBC(0, 0).setAnchor(GBC.EAST));
        JTextField acsNocard1 = new JTextField(15);
        mainpanelcard1.add(acsNocard1, new GBC(1, 0).setAnchor(GBC.WEST).setWeight(100, 0));
        mainpanelcard1.add(new JLabel("Book Title:"), new GBC(2, 0).setAnchor(GBC.EAST));
        JTextField bookTitlecard1 = new JTextField(15);
        mainpanelcard1.add(bookTitlecard1, new GBC(3, 0).setWeight(100, 0));
        mainpanelcard1.add(new JLabel("Bookshelf no.:"), new GBC(4, 0).setAnchor(GBC.EAST));
        JTextField bookshelfcard1 = new JTextField(15);
        mainpanelcard1.add(bookshelfcard1, new GBC(5, 0).setWeight(100, 0));
        mainpanelcard1.add(new JLabel("ISBN no.:"), new GBC(0, 1).setAnchor(GBC.EAST));
        JTextField isbncard1 = new JTextField(15);
        mainpanelcard1.add(isbncard1, new GBC(1, 1).setAnchor(GBC.WEST).setWeight(100, 0));
        mainpanelcard1.add(new JLabel("Author name:"), new GBC(2, 1).setAnchor(GBC.EAST));
        JTextField authorNamecard1 = new JTextField(15);
        mainpanelcard1.add(authorNamecard1, new GBC(3, 1).setWeight(100, 0));
        mainpanelcard1.add(new JLabel("Row no.:"), new GBC(4, 1).setAnchor(GBC.EAST));
        JTextField rowNocard1 = new JTextField(15);
        mainpanelcard1.add(rowNocard1, new GBC(5, 1).setWeight(100, 0));
        mainpanelcard1.add(new JLabel("CL Accession:"), new GBC(0, 2).setAnchor(GBC.EAST));
        JTextField clAccard1 = new JTextField(15);
        mainpanelcard1.add(clAccard1, new GBC(1, 2).setAnchor(GBC.WEST).setWeight(100, 0));
        mainpanelcard1.add(new JLabel("Edition:"), new GBC(2, 2).setAnchor(GBC.EAST));
        JTextField editioncard1 = new JTextField(15);
        mainpanelcard1.add(editioncard1, new GBC(3, 2).setWeight(100, 0));
        mainpanelcard1.add(new JLabel("Column no.:"), new GBC(4, 2).setAnchor(GBC.EAST));
        JTextField colNocard1 = new JTextField(15);
        mainpanelcard1.add(colNocard1, new GBC(5, 2).setWeight(100, 0));

        card1.add(mainpanelcard1, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton, BorderLayout.CENTER);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Book bean = new Book();
                if (bookTitlecard1.getText().isEmpty() || isbncard1.getText().isEmpty() || authorNamecard1.getText().isEmpty() || acsNocard1.getText().isEmpty() ||
                        bookshelfcard1.getText().isEmpty() || rowNocard1.getText().isEmpty() || colNocard1.getText().isEmpty() || editioncard1.getText().isEmpty() ||
                        clAccard1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Empty fields are not allowed");
                    return;
                }
                try {
                    boolean bookExistState = BooksManager.bookExisted(acsNocard1.getText());
                    if (bookExistState) {
                        JOptionPane.showMessageDialog(null, "Book with the same AccessionNo existed!");
                    } else {
                        bean.setAccessionNo(acsNocard1.getText());
                        bean.setbookTitle(bookTitlecard1.getText());
                        bean.setBookshelfNo(Integer.parseInt(bookshelfcard1.getText()));
                        bean.setISBN(isbncard1.getText());
                        bean.setAuthor(authorNamecard1.getText());
                        bean.setRowNo(Integer.parseInt(rowNocard1.getText()));
                        bean.setClAccession(Integer.parseInt(clAccard1.getText()));
                        bean.setEdition(Integer.parseInt(editioncard1.getText()));
                        bean.setColumnNo(Integer.parseInt(colNocard1.getText()));

                        if (BooksManager.addBook(bean) == 1) {
                            JOptionPane.showMessageDialog(null, "New book added");
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to add new book");
                        }
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                } catch (SQLException e2) {
                    JOptionPane.showMessageDialog(null, "SQLException!");
                }
            }
        });
        card1.add(buttonPanel, BorderLayout.SOUTH);

        JPanel card2 = new JPanel(new BorderLayout());
        JPanel lobButtonPanel = new JPanel();
        JButton lobButton = new JButton("List of Book");
        lobButtonPanel.add(lobButton);
        card2.add(lobButtonPanel, BorderLayout.NORTH);
        JPanel centerPanelcard2 = new JPanel();

        centerPanelcard2.setPreferredSize(new

                Dimension(610, 75));
        card2.add(centerPanelcard2, BorderLayout.CENTER);
        lobButton.addActionListener(new ActionListener() {
            //用updateUI()解决了界面反应迟钝的问题
            public void actionPerformed(ActionEvent event) {
                centerPanelcard2.removeAll();
                JTable bookTable = new JTable(BookTableModel.getTableModel("SELECT * FROM books"));
                JScrollPane pane = new JScrollPane(bookTable);
                bookTable.setPreferredScrollableViewportSize(new Dimension(600, 70));
                bookTable.setFillsViewportHeight(true);
                DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) bookTable.getTableHeader().getDefaultRenderer();
                renderer.setHorizontalAlignment(JLabel.CENTER);
                bookTable.setShowGrid(true);
                bookTable.setGridColor(Color.GREEN);
                bookTable.setVisible(true);
                centerPanelcard2.add(pane);
                centerPanelcard2.updateUI();
            }
        });


        JPanel card3 = new JPanel(new GridBagLayout());
        JPanel upperPanel = new JPanel();
        upperPanel.add(new

                JLabel("Accession Number: "));
        JTextField acsNocard3 = new JTextField(8);
        upperPanel.add(acsNocard3);

        JButton removeBut = new JButton("Remove");
        upperPanel.add(removeBut);
        removeBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure to remove this book?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        if (BooksManager.removeBook(acsNocard3.getText())) {
                            JOptionPane.showMessageDialog(null, "Book removed!");
                        } else {
                            JOptionPane.showMessageDialog(null, "No such book.");
                        }
                    } catch (SQLException exception) {
                        JOptionPane.showMessageDialog(null, "SQLException");
                    }
                }
            }
        });
        card3.add(upperPanel, new GBC(0, 0).setAnchor(GBC.CENTER));

        JPanel centerPanelcard3 = new JPanel(new GridBagLayout());
        centerPanelcard3.add(new JLabel("ISBN no:"), new GBC(0, 0).setAnchor(GBC.EAST));
        JTextField isbncard3 = new JTextField(8);
        centerPanelcard3.add(isbncard3, new GBC(1, 0).setAnchor(GBC.WEST));
        centerPanelcard3.add(new JLabel("Book Title:"), new GBC(2, 0).setAnchor(GBC.EAST));
        JTextField bookTitlecard3 = new JTextField(8);
        centerPanelcard3.add(bookTitlecard3, new GBC(3, 0).setAnchor(GBC.WEST));
        centerPanelcard3.add(new JLabel("Bookshelf no.:"), new GBC(4, 0).setAnchor(GBC.EAST));
        JTextField bookshelfcard3 = new JTextField(8);
        centerPanelcard3.add(bookshelfcard3, new GBC(5, 0).setAnchor(GBC.WEST));
        centerPanelcard3.add(new JLabel("CL accession:"), new GBC(0, 1).setAnchor(GBC.EAST));
        JTextField clAccard3 = new JTextField(8);
        centerPanelcard3.add(clAccard3, new GBC(1, 1).setAnchor(GBC.WEST));
        centerPanelcard3.add(new JLabel("Author Name:"), new GBC(2, 1).setAnchor(GBC.EAST));
        JTextField authorNamecard3 = new JTextField(8);
        centerPanelcard3.add(authorNamecard3, new GBC(3, 1).setAnchor(GBC.WEST));
        centerPanelcard3.add(new JLabel("Row no.:"), new GBC(4, 1).setAnchor(GBC.EAST));
        JTextField rowNocard3 = new JTextField(8);
        centerPanelcard3.add(rowNocard3, new GBC(5, 1).setAnchor(GBC.WEST));
        centerPanelcard3.add(new JLabel("Edition:"), new GBC(2, 2).setAnchor(GBC.EAST));
        JTextField editioncard3 = new JTextField(8);
        centerPanelcard3.add(editioncard3, new GBC(3, 2).setAnchor(GBC.WEST));
        centerPanelcard3.add(new JLabel("Column no.:"), new GBC(4, 2).setAnchor(GBC.EAST));
        JTextField colNocard3 = new JTextField(8);
        centerPanelcard3.add(colNocard3, new GBC(5, 2).setAnchor(GBC.WEST));

        card3.add(centerPanelcard3, new GBC(0, 1).setAnchor(GBC.CENTER));
        acsNocard3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Book bean = BooksManager.searchByAcsNum(acsNocard3.getText());
                            if (bean != null) {
                                isbncard3.setText(bean.getISBN());
                                bookTitlecard3.setText(bean.getbookTitle());
                                bookshelfcard3.setText("" + bean.getBookshelfNo());
                                clAccard3.setText("" + bean.getClAccession());
                                authorNamecard3.setText(bean.getAuthor());
                                rowNocard3.setText("" + bean.getRowNo());
                                editioncard3.setText("" + bean.getEdition());
                                colNocard3.setText("" + bean.getColumnNo());
                            } else {
                                JOptionPane.showMessageDialog(null, "No books found");
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "SQLException");
                        }
                    }
                }
        );
        JPanel goButtonPanel = new JPanel();
        JButton goButton = new JButton("Go");
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Book newBean = new Book();
                    newBean.setISBN(isbncard3.getText());
                    newBean.setbookTitle(bookTitlecard3.getText());
                    newBean.setBookshelfNo(Integer.parseInt(bookshelfcard3.getText()));
                    newBean.setClAccession(Integer.parseInt(clAccard3.getText()));
                    newBean.setAuthor(authorNamecard3.getText());
                    newBean.setRowNo(Integer.parseInt(rowNocard3.getText()));
                    newBean.setEdition(Integer.parseInt(editioncard3.getText()));
                    newBean.setColumnNo(Integer.parseInt(colNocard3.getText()));
                    String accessionNumber = acsNocard3.getText();
                    newBean.setAccessionNo(accessionNumber);
                    boolean updateBook = BooksManager.updateBook(newBean);
                    if (updateBook) {
                        JOptionPane.showMessageDialog(null, "Updated book successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update!");
                    }
                } catch (NumberFormatException exp) {
                    JOptionPane.showMessageDialog(null, "Invalid input, failed to update!");
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "SQLException!");
                }
            }
        });
        goButtonPanel.add(goButton);
        card3.add(goButtonPanel, new GBC(0, 2).setAnchor(GBC.CENTER));

        tabbedPane.addTab(ADDPANEL, card1);
        tabbedPane.addTab(LISTBOOKPANEL, card2);
        tabbedPane.addTab(EDITPANEL, card3);


        maincard3Panel.add(tabbedPane, new GBC(0, 1, 6, 1).setAnchor(GBC.CENTER));
        return maincard3Panel;
    }
}
