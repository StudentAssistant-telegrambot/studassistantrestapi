package Student.REST.API.DAO.EntitiesDAO.StandartDAO;

import Student.REST.API.Entties.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class StudentStandartDAOTest {

    private String url = "jdbc:mysql://localhost:3306/metamapz?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String un = "root";
    private String ps = "123qweasd";

    private Student obj1;
    private Student obj2;
    private StudentStandartDAO dao;
    @BeforeEach
    void befEach() throws SQLException {
        dao = new StudentStandartDAO(DriverManager.getConnection(url, un, ps));
        obj1 = dao.create(new Student("Vasy", "fghjk", 33, "IT-uu8t439"));
        obj2 = dao.create(new Student("sdsdv", "sdvsdv", 789, "cfhjknl"));
    }

    @AfterEach
    void aftEach(){
        dao.delete(obj1.getId());
        dao.delete(obj2.getId());
    }



    @Test
    void create() throws SQLException {
        Student s = dao.create(new Student("qwerth", "123456", 123456, "789"));

        assertTrue(Objects.equals(s.getFirstname(), "qwerth") && Objects.equals(s.getSecondname(), "123456") &&
                Objects.equals(s.getGroup(), "789") && s.getAge() == 123456);

        dao.delete(s.getId());
    }

    @Test
    void update() {
        obj1.setFirstname("QWREG");
        obj1.setAge(987654);
        obj1.setSecondname("Ivanov");

        dao.update(obj1);

        assertEquals(obj1, dao.read(obj1.getId()));
    }

    @Test
    void delete() {
        dao.delete(obj1.getId());

        assertNull(dao.read(obj1.getId()));
        assertNotNull(dao.read(obj2.getId()));
    }

    @Test
    void read() {
        assertEquals(obj1, dao.read(obj1.getId()));
        assertNotEquals(obj1, dao.read(obj2.getId()));
    }

    @Test
    void getAll() {
        List<Student> ss = dao.getAll();

        assertTrue(ss.contains(obj1) && ss.contains(obj2));
    }
}