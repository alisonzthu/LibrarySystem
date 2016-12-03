package Nov16LibrarySystem.JavaBeans;

import java.util.Date;

/**
 * Created by macstudio on 11/24/16.
 */
public class BorrowRecord {
    private int recordID;
    private int studentRegNo;
    private String bookacsNo;
    private Date borrowedDate;
    private Date shouldReturnDate;
    private Date actualRetrunDate;

    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public int getStudentRegNo() {
        return studentRegNo;
    }

    public void setStudentRegNo(int studentRegNo) {
        this.studentRegNo = studentRegNo;
    }

    public String getBookacsNo() {
        return bookacsNo;
    }

    public void setBookacsNo(String bookacsNo) {
        this.bookacsNo = bookacsNo;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Date getShouldReturnDate() {
        return shouldReturnDate;
    }

    public void setShouldReturnDate(Date shouldReturnDate) {
        this.shouldReturnDate = shouldReturnDate;
    }

    public Date getActualRetrunDate() {
        return actualRetrunDate;
    }

    public void setActualRetrunDate(Date actualRetrunDate) {
        this.actualRetrunDate = actualRetrunDate;
    }
}
