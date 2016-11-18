package Nov16LibrarySystem.JavaBeans;

/**
 * Created by macstudio on 11/11/16.
 */
public class Student {
    private int registrationNo;
    private String studentName;
    private int year;
    private String smster;
    private long contactNo;

    public int getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(int registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSmster() {
        return smster;
    }

    public void setSmster(String smster) {
        this.smster = smster;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

}
