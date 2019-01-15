package Student.REST.API.DAO.EntitiesDAO;

import Student.REST.API.DAO.EntitiesDAO.StandartDAO.StudentStandartDAO;
import Student.REST.API.Entties.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class StudentDBTest {

    private String url = "jdbc:mysql://localhost:3306/metamapz?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String un = "root";
    private String ps = "123qweasd";

    StudentStandartDAO ssdao;
    StudentDAO sdao;

    Student student;

    public StudentDBTest() throws SQLException {
        Connection c = DriverManager.getConnection(url, un, ps);
        ssdao = new StudentStandartDAO(c);
        sdao = new StudentDAO(c);
    }

    @BeforeEach
    void befeach(){
        student = new Student("Ivan", "Petrov", 19, "IT-62");
    }

    @Test
    void compSandSSDAOs() throws SQLException {
        Student metast = sdao.create(student);
        Student stst = ssdao.create(student);

        assertEquals(metast, stst);

        Student metareadst = sdao.read(metast.getId());
        Student streadst = ssdao.read(stst.getId());

        assertEquals(metareadst, streadst);

        sdao.delete(metast.getId());
        ssdao.delete(stst.getId());
    }
}
