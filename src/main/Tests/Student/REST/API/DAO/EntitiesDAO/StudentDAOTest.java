package Student.REST.API.DAO.EntitiesDAO;

import Student.REST.API.Entties.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {

    private String url = "jdbc:mysql://localhost:3306/metamapz?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String un = "root";
    private String ps = "123qweasd";

    private StudentDAO studentDAO;

    private Student student1;
    private Student student2;

    public StudentDAOTest() throws SQLException {
        studentDAO = new StudentDAO(DriverManager.getConnection(url, un, ps));
    }

    @BeforeEach
    void befeach() throws SQLException {
        student1 = studentDAO.create(new Student("Ivan", "Petrov", 33, "IT-62"));
        student2 = studentDAO.create(new Student("Vasya", "Ivanov", 99, "IT-94"));
    }

    @AfterEach
    void afteach(){
        studentDAO.delete(student1.getId());
        studentDAO.delete(student2.getId());
    }

    @Test
    void create() throws SQLException {
        Student st = studentDAO.create(new Student("1234", "56789", 12, "34567"));
        assertTrue(st.getAge() == 12 && Objects.equals(st.getFirstname(), "1234") &&
                Objects.equals(st.getSecondname(), "56789") && Objects.equals(st.getGroup(), "34567"));
        studentDAO.delete(st.getId());
    }

    @Test
    void update() {
        student1.setGroup("IO-62");
        student1.setAge(100);
        student1.setFirstname("Vasyl");

        studentDAO.update(student1);

        Student st = studentDAO.read(student1.getId());

        assertTrue(st.equals(student1));
    }

    @Test
    void delete() {
        studentDAO.delete(student1.getId());
        assertNull(studentDAO.read(student1.getId()));
        assertNotNull(studentDAO.read(student2.getId()));
    }

    @Test
    void read() {
        assertNotNull(studentDAO.read(student1.getId()));
        assertEquals(student1, studentDAO.read(student1.getId()));
        assertNotEquals(student1, studentDAO.read(student2.getId()));
    }

    @Test
    void readByName() {
        // returning null
    }

    @Test
    void getAll() {
        List<Student> students = new ArrayList<>();
        students = studentDAO.getAll();

        boolean iscont = students.contains(student1) && students.contains(student2);

        assertTrue(iscont);
    }
}