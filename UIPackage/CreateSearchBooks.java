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
public class CreateSearchBooks {
    public JPanel searchBooks() {
        final String BYNUMBER = "Search by Accession number";
        final String BYISBN = "Search by ISBN number";
        final String BYTITLE = "Search by Title";
        final int extraWindowWidth = 100;

        JPanel upPanel = new HomeNlogoutPanel().addOutmostPanel();
        JPanel maincard5Panel = new JPanel(new GridBagLayout());
        maincard5Panel.add(upPanel, new GBC(0, 0).setAnchor(GBC.WEST));

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel card1 = new JPanel() {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                size.height += extraWindowWidth;
                return size;
            }
        };

        card1.setLayout(new BorderLayout());
        JPanel upperPanel = new JPanel(new GridBagLayout());
        JTextField acsNocard1 = new JTextField(8);

        upperPanel.add(new JLabel("Accession no.: "), new GBC(0, 0).setAnchor(GBC.EAST));
        upperPanel.add(acsNocard1, new GBC(1, 0).setAnchor(GBC.WEST));

        JPanel centerPanelcard1 = new JPanel(new GridBagLayout());

        centerPanelcard1.add(new JLabel("ISBN no.:"), new GBC(0, 0).setAnchor(GBC.EAST).setInsets(0, 70, 10, 0));
        JLabel isbncard1 = new JLabel();
        centerPanelcard1.add(isbncard1, new GBC(1, 0).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));
        centerPanelcard1.add(new JLabel("Book title:"), new GBC(0, 1).setAnchor(GBC.EAST).setInsets(0, 70, 10, 0));
        JLabel bookTitlecard1 = new JLabel();
        centerPanelcard1.add(bookTitlecard1, new GBC(1, 1).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));
        centerPanelcard1.add(new JLabel("Author name:"), new GBC(0, 2).setAnchor(GBC.EAST).setInsets(0, 70, 10, 0));
        JLabel authorNamecard1 = new JLabel();
        centerPanelcard1.add(authorNamecard1, new GBC(1, 2).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));
        centerPanelcard1.add(new JLabel("Bookshelf no.:"), new GBC(0, 3).setAnchor(GBC.EAST).setInsets(0, 70, 10, 0));
        JLabel bookshelfcard1 = new JLabel();
        centerPanelcard1.add(bookshelfcard1, new GBC(1, 3).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));
        centerPanelcard1.add(new JLabel("Row no.:"), new GBC(0, 4).setAnchor(GBC.EAST).setInsets(0, 70, 10, 0));
        JLabel rowNocard1 = new JLabel();
        centerPanelcard1.add(rowNocard1, new GBC(1, 4).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));
        centerPanelcard1.add(new JLabel("Column no.:"), new GBC(0, 5).setAnchor(GBC.EAST).setInsets(0, 70, 10, 0));
        JLabel colNocard1 = new JLabel();
        centerPanelcard1.add(colNocard1, new GBC(1, 5).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));

        acsNocard1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String acsNo = acsNocard1.getText();
                try {
                    Book bean = BooksManager.searchByAcsNum(acsNo);
                    if (bean != null) {
                        isbncard1.setText(bean.getISBN());
                        bookTitlecard1.setText(bean.getbookTitle());
                        authorNamecard1.setText(bean.getAuthor());
                        bookshelfcard1.setText(Integer.toString(bean.getBookshelfNo()));
                        rowNocard1.setText(Integer.toString(bean.getRowNo()));
                        colNocard1.setText(Integer.toString(bean.getColumnNo()));
                    } else {
                        JOptionPane.showMessageDialog(null, "No books found");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "SQLException");
                }
            }
        });
        card1.add(upperPanel, BorderLayout.NORTH);
        card1.add(centerPanelcard1, BorderLayout.CENTER);

        JPanel card2 = new JPanel(new BorderLayout());
        JPanel upperPanelcard2 = new JPanel(new GridBagLayout());
        upperPanelcard2.add(new JLabel("ISBN no.: "), new GBC(0, 0).setAnchor(GBC.EAST));
        JTextField isbnNoTextcard2 = new JTextField(13);

        upperPanelcard2.add(isbnNoTextcard2, new GBC(1, 0).setAnchor(GBC.WEST));
        card2.add(upperPanelcard2, BorderLayout.NORTH);

        JPanel centerPanelcard2 = new JPanel(new GridBagLayout());
        centerPanelcard2.add(new JLabel("ISBN no.:"), new GBC(0, 0).setAnchor(GBC.EAST).setInsets(0, 20, 10, 0));
        JLabel isbncard2 = new JLabel();
        centerPanelcard2.add(isbncard2, new GBC(1, 0).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));
        centerPanelcard2.add(new JLabel("Book title:"), new GBC(0, 1).setAnchor(GBC.EAST).setInsets(0, 20, 10, 0));
        JLabel bookTitlecard2 = new JLabel();
        centerPanelcard2.add(bookTitlecard2, new GBC(1, 1).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));
        centerPanelcard2.add(new JLabel("Author name:"), new GBC(0, 2).setAnchor(GBC.EAST).setInsets(0, 20, 10, 0));
        JLabel authorNamecard2 = new JLabel();
        centerPanelcard2.add(authorNamecard2, new GBC(1, 2).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));
        centerPanelcard2.add(new JLabel("Total available copies:"), new GBC(0, 3).setAnchor(GBC.EAST).setInsets(0, 20, 10, 0));
        JLabel availNocard2 = new JLabel();
        centerPanelcard2.add(availNocard2, new GBC(1, 3).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));
        centerPanelcard2.add(new JLabel("Available accession number's:"), new GBC(0, 4).setAnchor(GBC.EAST).setInsets(0, 20, 10, 0));
        JComboBox<String> availAcsNocard2 = new JComboBox<>();
        availAcsNocard2.setPrototypeDisplayValue("xxxxxxxx");
        centerPanelcard2.add(availAcsNocard2, new GBC(1, 4).setAnchor(GBC.WEST).setWeight(100, 0).setInsets(0, 20, 10, 0));

        isbnNoTextcard2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                availAcsNocard2.removeAllItems();
                try {
                    Book srchByISBN = BooksManager.searchAvailByISBN(isbnNoTextcard2.getText());
                    if (srchByISBN != null) {
                        isbncard2.setText(srchByISBN.getISBN());
                        bookTitlecard2.setText(srchByISBN.getbookTitle());
                        authorNamecard2.setText(srchByISBN.getAuthor());
                        String[] availBooks = BooksManager.getAvailBooksByISBN(isbnNoTextcard2.getText());
                        if (availBooks != null) {
                            availNocard2.setText(Integer.toString(availBooks.length));
                            for (String str : availBooks) {
                                availAcsNocard2.addItem(str);
                            }
                        } else {
                            availNocard2.setText("0");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No books found!");
                    }
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "SQLException!");
                }
            }
        });
        card2.add(centerPanelcard2, BorderLayout.CENTER);

        JPanel card3 = new JPanel(new BorderLayout());
        JPanel upperPanelcard3 = new JPanel(new GridBagLayout());
        upperPanelcard3.add(new JLabel("Book Name: "), new GBC(0, 0).setAnchor(GBC.EAST));
        JTextField bookName = new JTextField(20);

        bookName.setHorizontalAlignment(JTextField.LEFT);
        upperPanelcard3.add(bookName, new GBC(1, 0).setAnchor(GBC.WEST));
        card3.add(upperPanelcard3, BorderLayout.NORTH);

        JPanel centerPanelcard3 = new JPanel();
        card3.add(centerPanelcard3, BorderLayout.CENTER);

        bookName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                centerPanelcard3.removeAll();
                JTable bookTable = new JTable(BookTableModel.getTableModelByTitle("SELECT BookTitle, AuthorName, AccessionNo, ISBNNo, Edition, BookshelfNo, RowNo, ColumnNo FROM books WHERE BookTitle = ?", bookName.getText()));
                //need readjustment:
                JScrollPane pane = new JScrollPane(bookTable);
                bookTable.setPreferredScrollableViewportSize(new Dimension(600, 70));
                bookTable.setFillsViewportHeight(true);
                DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) bookTable.getTableHeader().getDefaultRenderer();
                renderer.setHorizontalAlignment(JLabel.CENTER);
                bookTable.setShowGrid(true);
                bookTable.setGridColor(Color.GREEN);
                bookTable.setVisible(true);

                centerPanelcard3.add(pane);
                centerPanelcard3.updateUI();

            }
        });

        tabbedPane.addTab(BYNUMBER, card1);
        tabbedPane.addTab(BYISBN, card2);
        tabbedPane.addTab(BYTITLE, card3);
        tabbedPane.setPreferredSize(new Dimension(700, 250));

        maincard5Panel.add(tabbedPane, new GBC(0, 1, 6, 2).setAnchor(GBC.CENTER));
        return maincard5Panel;
    }
}
