package Nov16LibrarySystem.JavaBeans;

/**
 * Created by macstudio on 11/11/16.
 */
public class Book {
    private String iSBN;
    private String bookTitle;
    private String authorName;
    private String accessionNo;
    private int bookshelfNo;
    private int rowNo;
    private int columnNo;
    private int edition;
    private int clAccession;

    public int getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(int borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    private int borrowedBy;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    private int available;

    public String getISBN() {
        return iSBN;
    }

    public void setISBN(String isbn) {
        iSBN = isbn;
    }

    public String getbookTitle() {
        return bookTitle;
    }

    public void setbookTitle(String title) {
        bookTitle = title;
    }

    public String getAuthor() {
        return authorName;
    }

    public void setAuthor(String author) {
        authorName = author;
    }

    public String getAccessionNo() {
        return accessionNo;
    }

    public void setAccessionNo(String acsnNo) {
        accessionNo = acsnNo;
    }

    public int getBookshelfNo() {
        return bookshelfNo;
    }

    public void setBookshelfNo(int bkshelfNo) {
        bookshelfNo = bkshelfNo;
    }

    public int getRowNo() {
        return rowNo;
    }

    public void setRowNo(int rowNumber) {
        rowNo = rowNumber;
    }

    public int getColumnNo() {
        return columnNo;
    }

    public void setColumnNo(int columnNo) {
        this.columnNo = columnNo;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getClAccession() {
        return clAccession;
    }

    public void setClAccession(int clAccession) {
        this.clAccession = clAccession;
    }

}
