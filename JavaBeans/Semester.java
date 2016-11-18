package Nov16LibrarySystem.JavaBeans;

/**
 * Created by macstudio on 11/11/16.
 */
public enum Semester {
    SPRING("Spring"), FALL("Fall");

    private String name;
    Semester(String name){
        this.name = name;
    }
    public String toString(){
        return name;
    }
}
