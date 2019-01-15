package Student.REST.API.Entties;

import java.util.Objects;

public class Student {

    public static String EntityName = "Student";
    public static String firstNameAtt = "firstname";
    public static String secondNameAtt = "secondname";
    public static String ageAtt = "age";
    public static String groupAtt = "group";

    private int id;
    private String firstname;
    private String secondname;
    private int age;
    private String group;

    public Student(){

    }
    public Student(String firstname, String secondname, int age, String group){
        this.firstname = firstname;
        this.secondname = secondname;
        this.age = age;
        this.group = group;
    }
    public Student(int id, String firstname, String secondname, int age, String group){
        this.firstname = firstname;
        this.secondname = secondname;
        this.age = age;
        this.group = group;
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getGroup() {
        return group;
    }

    public String getSecondname() {
        return secondname;
    }

    public int getId() {
        return id;
    }
    public String getFullName(){
        return firstname.substring(0, 2) + secondname.substring(0, 2) + String.valueOf(age).substring(0, 1) + group.substring(3, 4);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object){
        if (object != null)
            if (object instanceof Student)
                return Objects.equals(((Student) object).getFirstname(), getFirstname()) && Objects.equals(((Student) object).getSecondname(), getSecondname()) &&
                        ((Student) object).getAge() == getAge() && Objects.equals(((Student) object).getGroup(), getGroup());
        return false;
    }
}
