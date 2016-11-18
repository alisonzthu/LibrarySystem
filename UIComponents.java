package Nov16LibrarySystem;

import Nov16LibrarySystem.GBC;
import Nov16LibrarySystem.JavaBeans.Book;
import Nov16LibrarySystem.JavaBeans.Student;
import Nov16LibrarySystem.TableModels.BookTableModel;
import Nov16LibrarySystem.dbTables.BooksManager;
import Nov16LibrarySystem.dbTables.StudentsManager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;

/*
对所有用户可能的输出格式错误进行预警,防止程序中断!
2.texts gathered from the user are better be trimmed, but I haven't done it because we need to create way more Strings.
 */
public class UIComponents extends JPanel {
    private static JPanel homepage;
    public static JPanel loginPanel;
    public static JPanel listPanel;
    private static JPanel cards;
    public JTextField userName;
    public JPasswordField userPassword;

    public UIComponents() {

//在cards里要加上所有可能会显示的panel

        cards = new JPanel(new CardLayout());

        JPanel loginGUI = createLoginGUI();//maincard1
        cards.add(loginGUI);
        JPanel listGUI = createListPanel();//maincard2
        cards.add(listGUI);
        JPanel cOfBooks = collectionOfBooks();//maincard3
        cards.add(cOfBooks);
        JPanel stuInfPanel = studentInfPanel();//maincard4
        cards.add(stuInfPanel);
        JPanel srchBooks = searchBooks();//maincard5
        cards.add(srchBooks);

        add(cards);


    }

    private void nextCard() {
        CardLayout layout = (CardLayout) cards.getLayout();
        layout.next(cards);
    }

    private void previousCard() {
        CardLayout layout = (CardLayout) cards.getLayout();
        layout.previous(cards);
    }

    private void firstCard() {
        CardLayout layout = (CardLayout) cards.getLayout();
        layout.first(cards);
    }

    /*
    ---------------------createLoginGUI-------------------------------maincard1
     */
    public JPanel createLoginGUI() {
        JLabel uniLogo;
        JLabel uniName;
        JLabel userNamePrompt;
        JLabel userPasswordPrompt;
        JButton enter;
        JLabel prgrmrInfo;

        homepage = new JPanel();
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());

        //university Logo
        Icon logo = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/UOMLogo.png");
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
        userName = new JTextField(10);
        userName.setHorizontalAlignment(JTextField.CENTER);
        loginPanel.add(userName, new GBC(1, 6).setAnchor(GBC.CENTER).setWeight(100, 0));
        //password prompt
        userPasswordPrompt = new JLabel("Password");
        loginPanel.add(userPasswordPrompt, new GBC(1, 7).setAnchor(GBC.CENTER));
        //password input section
        userPassword = new JPasswordField(10);
        userPassword.setHorizontalAlignment(JTextField.CENTER);
        loginPanel.add(userPassword, new GBC(1, 8).setAnchor(GBC.CENTER).setWeight(100, 0));//may add an ActionListener for this item

        JPanel enterButtonPanel = new JPanel(new BorderLayout());
        Icon enterIcon = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/arrowToEnter.png");
        enter = new JButton(enterIcon);
        //add the same actionListener on enter button and userPassword
        Handler h = new Handler();
        enter.addActionListener(h);
        userPassword.addActionListener(h);

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
            char[] password = userPassword.getPassword();
            String passwordStr = new String(password);
            //if userInfo incorrect
            if (!InputHelper.checkUserInfo(userName.getText(), passwordStr)) {
                JOptionPane.showMessageDialog(null, "Wrong information!");

            } else {
                //if userInfo correct:
                nextCard();
            }
        }
    }

    /*
   -------------------- create lists panel:-------------------------------maincard2
     */
    private JPanel createListPanel() {
        //a makeIcon method?
        Icon book = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/bookIcon.png");
        Icon students = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/students.png");
        Icon magnifier = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/magnifier.png");
        Icon borrow = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/borrowBook.png");
        Icon changePass = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/changePassword.png");
        Icon backup = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/backup.png");
        Icon generateRep = new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/generateReport.png");
        listPanel = new JPanel(new GridBagLayout());

        JButton bookIcon = new JButton(book);
        bookIcon.setPreferredSize(new Dimension(100, 100));
        bookIcon.setHorizontalAlignment(JButton.CENTER);
        bookIcon.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //enter card3
                        nextCard();
                    }
                }
        );
        listPanel.add(bookIcon, new GBC(1, 3).setAnchor(GBC.CENTER));

        JLabel bookIconNameLine1 = new JLabel("Collection");
        bookIconNameLine1.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(bookIconNameLine1, new GBC(1, 4).setAnchor(GBC.CENTER));
        JLabel bookIconNameLine2 = new JLabel("of books");
        bookIconNameLine2.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(bookIconNameLine2, new GBC(1, 5).setAnchor(GBC.CENTER));

        JButton studentsIcon = new JButton(students);
        studentsIcon.setPreferredSize(new Dimension(100, 100));
        studentsIcon.setHorizontalAlignment(JButton.CENTER);
        studentsIcon.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //JOptionPane.showMessageDialog(null, "manage students");
                        nextCard();
                        nextCard();
                    }
                }
        );
        listPanel.add(studentsIcon, new GBC(2, 3).setAnchor(GBC.CENTER));

        JLabel studentsIconNameLine1 = new JLabel("Student'");
        studentsIconNameLine1.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(studentsIconNameLine1, new GBC(2, 4).setAnchor(GBC.CENTER));
        JLabel studentsIconNameLine2 = new JLabel("information");
        studentsIconNameLine2.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(studentsIconNameLine2, new GBC(2, 5).setAnchor(GBC.CENTER));

        JButton magnifierButton = new JButton(magnifier);
        magnifierButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        nextCard();
                        nextCard();
                        nextCard();//go to card5
                    }
                }
        );
        magnifierButton.setPreferredSize(new Dimension(100, 100));
        magnifierButton.setHorizontalAlignment(JButton.CENTER);
        listPanel.add(magnifierButton, new GBC(3, 3).setAnchor(GBC.CENTER));

        JLabel magnifierIconNameLine1 = new JLabel("Search");
        magnifierIconNameLine1.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(magnifierIconNameLine1, new GBC(3, 4).setAnchor(GBC.CENTER));
        JLabel magnifierIconNameLine2 = new JLabel("books");
        magnifierIconNameLine2.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(magnifierIconNameLine2, new GBC(3, 5).setAnchor(GBC.CENTER));

        JButton borrowBookButton = new JButton(borrow);
        borrowBookButton.setPreferredSize(new Dimension(100, 100));
        borrowBookButton.setHorizontalAlignment(JButton.CENTER);
        listPanel.add(borrowBookButton, new GBC(4, 3).setAnchor(GBC.CENTER));

        JLabel borrBookLine1 = new JLabel("Borrow");
        borrBookLine1.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(borrBookLine1, new GBC(4, 4).setAnchor(GBC.CENTER));
        JLabel borrBookLine2 = new JLabel("books");
        borrBookLine2.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(borrBookLine2, new GBC(4, 5).setAnchor(GBC.CENTER));

        JButton chngPss = new JButton(changePass);
        chngPss.setPreferredSize(new Dimension(100, 100));
        chngPss.setHorizontalAlignment(JButton.CENTER);
        listPanel.add(chngPss, new GBC(5, 3).setAnchor(GBC.CENTER));

        JLabel chngPssLine1 = new JLabel("Change");
        chngPssLine1.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(chngPssLine1, new GBC(5, 4).setAnchor(GBC.CENTER));
        JLabel chngPssLine2 = new JLabel("password");
        chngPssLine2.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(chngPssLine2, new GBC(5, 5).setAnchor(GBC.CENTER));

        JButton backUpButton = new JButton(backup);
        backUpButton.setPreferredSize(new Dimension(100, 100));
        backUpButton.setHorizontalAlignment(JButton.CENTER);
        listPanel.add(backUpButton, new GBC(6, 3).setAnchor(GBC.CENTER));

        JLabel backupLine1 = new JLabel("Backup");
        backupLine1.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(backupLine1, new GBC(6, 4).setAnchor(GBC.CENTER));
        JLabel backupLine2 = new JLabel("database");
        backupLine2.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(backupLine2, new GBC(6, 5).setAnchor(GBC.CENTER));

        JButton generateReportBut = new JButton(generateRep);
        generateReportBut.setPreferredSize(new Dimension(100, 100));
        generateReportBut.setHorizontalAlignment(JButton.CENTER);
        listPanel.add(generateReportBut, new GBC(7, 3).setAnchor(GBC.CENTER));

        JLabel generateRepLine1 = new JLabel("Generate");
        generateRepLine1.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(generateRepLine1, new GBC(7, 4).setAnchor(GBC.CENTER));
        JLabel generateRepLine2 = new JLabel("report");
        generateRepLine2.setHorizontalAlignment(JLabel.CENTER);
        listPanel.add(generateRepLine2, new GBC(7, 5).setAnchor(GBC.CENTER));

        JButton homeButton = new JButton("Home");
        homeButton.setHorizontalAlignment(JButton.CENTER);
        homeButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        firstCard();
                        nextCard();
                    }
                }
        );
        listPanel.add(homeButton, new GBC(5, 0).setAnchor(GBC.CENTER).setInsets(20));

        JButton logoutButton = new JButton(new ImageIcon("/Volumes/Macintosh HD/Alison/Programming study/my projects/logout.png"));
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if(JOptionPane.showConfirmDialog(null, "Are you sure to log out?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                   //log out
                   firstCard();
                   userName.setText("");
                   userPassword.setText("");
               }
            }
        });
        listPanel.add(logoutButton, new GBC(6, 0).setAnchor(GBC.CENTER));
        return listPanel;
    }

    //---------------------------collectionOfBooks-----maincard3-----------------------------
    public JPanel collectionOfBooks() {
        final String ADDPANEL = "Add new book";
        final String LISTBOOKPANEL = "List of books";
        final String EDITPANEL = "Edit book information";

        JPanel maincard3Panel = new JPanel(new GridBagLayout());
        JButton home = new JButton("Home");
        JPanel homeButtonPanel = new JPanel();
        homeButtonPanel.add(home);
        home.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        firstCard();
                        nextCard();
                    }
                }
        );

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
                } else if (BooksManager.bookExisted(acsNocard1.getText())) {
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

                    BooksManager.addBook(bean);
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

        centerPanelcard2.setPreferredSize(new Dimension(610, 75));
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
        upperPanel.add(new JLabel("Accession Number: "));
        JTextField acsNocard3 = new JTextField(8);
        upperPanel.add(acsNocard3);

        JButton removeBut = new JButton("Remove");
        upperPanel.add(removeBut);
        removeBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure to remove this book?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (BooksManager.removeBook(acsNocard3.getText())) {
                        JOptionPane.showMessageDialog(null, "Book removed!");
                    } else {
                        JOptionPane.showMessageDialog(null, "No such book.");
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
                        }
                    }
                }

        );
        JPanel goButtonPanel = new JPanel();
        JButton goButton = new JButton("Go");
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //try/catch block to prevent NumberFormatException
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
                    if (BooksManager.updateBook(newBean)) {
                        JOptionPane.showMessageDialog(null, "Updated book successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update!");
                    }
                } catch(NumberFormatException exp) {
                    JOptionPane.showMessageDialog(null, "Failed to update!");
                }
            }
        });
        goButtonPanel.add(goButton);
        card3.add(goButtonPanel, new GBC(0, 2).setAnchor(GBC.CENTER));

        tabbedPane.addTab(ADDPANEL, card1);
        tabbedPane.addTab(LISTBOOKPANEL, card2);
        tabbedPane.addTab(EDITPANEL, card3);

        maincard3Panel.add(homeButtonPanel, new GBC(5, 0).setAnchor(GBC.WEST));
        maincard3Panel.add(tabbedPane, new GBC(0, 1, 6, 1).setAnchor(GBC.CENTER));
        return maincard3Panel;
    }

    //------------studentInformationPanel------------------maincard4
    public JPanel studentInfPanel() {
        final String ADDPANEL = "Add student/member";
        final String DETAIL = "Student details";
        final String EDITPANEL = "Edit student/member";
        final int extraWindowWidth = 100;

        JPanel maincard4Panel = new JPanel(new GridBagLayout());

        JButton homeButton = new JButton("Home");
        JPanel homeButtonPanel = new JPanel();
        homeButtonPanel.add(homeButton);
        homeButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        firstCard();
                        nextCard();
                    }
                }
        );

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
                } else if (StudentsManager.studentExisted(regNocard1.getText())) {
                    JOptionPane.showMessageDialog(null, "Student Existed!");
                } else {
                    try {
                        int regi = Integer.parseInt(regNocard1.getText());
                        int yr = Integer.parseInt(yearBox.getSelectedItem().toString());
                        Long ctctNo = Long.parseLong(contactNocard1.getText());

                        bean.setRegistrationNo(regi);
                        bean.setStudentName(stuNamecard1.getText());
                        bean.setYear(yr);
                        bean.setSmster(semesterBox.getSelectedItem().toString());
                        bean.setContactNo(ctctNo);
                        StudentsManager.addStudent(bean);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input!");
                    }
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
                if (StudentsManager.studentExisted(regNocard2.getText())) {
                    Student searchedBean = StudentsManager.searchByRegiNo(regNocard2.getText());
                    regNoLabel.setText(Integer.toString(searchedBean.getRegistrationNo()));
                    stuNameLabel.setText(searchedBean.getStudentName());
                    yearLabel.setText(Integer.toString(searchedBean.getYear()));
                    smstrLabel.setText(searchedBean.getSmster());
                    contactLabel.setText(Long.toString(searchedBean.getContactNo()));

                } else {
                    JOptionPane.showMessageDialog(null, "No students found!");
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
                        Student newBean = StudentsManager.searchByRegiNo(regNocard3.getText());
                        stuNamecard3.setText(newBean.getStudentName());
                        yearcard3.setSelectedItem(newBean.getYear());
                        smstrcard3.setSelectedItem(newBean.getSmster());
                        contactNocard3.setText(Long.toString(newBean.getContactNo()));
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
                    if (StudentsManager.updateStudent(updateStudent)) {
                        JOptionPane.showMessageDialog(null, "Student updated!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Update failed!");
                    }
                } catch (NumberFormatException en) {
                    en.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Format exception, update failed!");
                }
            }
        });
        goButtonPanel.add(goButton);
        card3.add(goButtonPanel, new GBC(0, 2).setAnchor(GBC.CENTER));

        tabbedPane.addTab(ADDPANEL, card1);
        tabbedPane.addTab(DETAIL, card2);
        tabbedPane.addTab(EDITPANEL, card3);

        maincard4Panel.add(homeButtonPanel, new GBC(5, 0).setAnchor(GBC.WEST));
        maincard4Panel.add(tabbedPane, new GBC(0, 1, 6, 1).setAnchor(GBC.CENTER));
        return maincard4Panel;
    }

    //----------------searchBooks---------------maincard5
    public JPanel searchBooks() {
        final String BYNUMBER = "Search by Accession number";
        final String BYISBN = "Search by ISBN number";
        final String BYTITLE = "Search by Title";
        final int extraWindowWidth = 100;

        JPanel maincard5Panel = new JPanel(new GridBagLayout());

        JButton homeButton = new JButton("Home");
        JPanel homeButtonPanel = new JPanel();
        homeButtonPanel.add(homeButton);
        homeButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        firstCard();
                        nextCard();
                    }
                }
        );

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
                Book bean = BooksManager.searchByAcsNum(acsNo);
                if (bean != null) {
                    isbncard1.setText(bean.getISBN());
                    bookTitlecard1.setText(bean.getbookTitle());
                    authorNamecard1.setText(bean.getAuthor());
                    bookshelfcard1.setText(Integer.toString(bean.getBookshelfNo()));
                    rowNocard1.setText(Integer.toString(bean.getRowNo()));
                    colNocard1.setText(Integer.toString(bean.getColumnNo()));
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
                String[] srchByISBN = BooksManager.searchByISBN(isbnNoTextcard2.getText());
                if (srchByISBN != null) {
                    isbncard2.setText(srchByISBN[0]);
                    bookTitlecard2.setText(srchByISBN[1]);
                    authorNamecard2.setText(srchByISBN[2]);
                    availNocard2.setText(srchByISBN[3]);
                    if (!srchByISBN[3].equals("0")) {
                        String[] avlnums = new String[srchByISBN.length - 4];
                        for (int i = 0; i < avlnums.length; i++) {
                            avlnums[i] = srchByISBN[i + 4];
                        }
                        for (String str : avlnums) {
                            availAcsNocard2.addItem(str);
                        }
                    } else {
                        availAcsNocard2.removeAllItems();
                    }
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

        maincard5Panel.add(homeButtonPanel, new GBC(5, 0).setAnchor(GBC.WEST));
        maincard5Panel.add(tabbedPane, new GBC(0, 1, 6, 2).setAnchor(GBC.CENTER));
        return maincard5Panel;
    }

}
