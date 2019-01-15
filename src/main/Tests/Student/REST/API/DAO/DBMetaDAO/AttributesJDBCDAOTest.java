package Student.REST.API.DAO.DBMetaDAO;

import Student.REST.API.DBEntities.Attributes;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AttributesJDBCDAOTest {

    private String url = "jdbc:mysql://localhost:3306/metamapz?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String un = "root";
    private String ps = "123qweasd";

    private Attributes obj1;
    private Attributes obj2;
    private AttributesJDBCDAO dao;
    @org.junit.jupiter.api.BeforeEach
    void befEach() throws SQLException {
        dao = new AttributesJDBCDAO(DriverManager.getConnection(url, un, ps));
        obj1 = dao.create(new Attributes(1, "1"));
        obj2 = dao.create(new Attributes(2, "2"));
    }

    @org.junit.jupiter.api.AfterEach
    void aftEach(){
        dao.delete(obj1.getId());
        dao.delete(obj2.getId());
    }



    @Test
    void create() throws SQLException {
        Attributes type3 = dao.create(new Attributes(4,"3"));
        //assertTrue(type3.getId() > obj2.getId());
        assertTrue(type3.getName().equals("3") && type3.getType_id() == 4);
        //assertTrue(type3.getId() > obj1.getId());
        dao.delete(type3.getId());
    }

    @Test
    void update() {
        obj1.setName("Vasya");
        obj1.setType_id(33);
        dao.update(obj1);
        Attributes t = dao.read(obj1.getId());
        assertEquals(obj1.getName(), t.getName());
        assertEquals(obj1.getId(), t.getId());
        assertEquals(obj1.getType_id(), t.getType_id());
    }

    @Test
    void delete() {
        dao.delete(obj1.getId());
        assertNull(dao.read(obj1.getId()));
    }

    @Test
    void read() {
        assertEquals(obj1.getId(), dao.read(obj1.getId()).getId());
    }

    @Test
    void readByName() {
        assertEquals(obj2.getId(), dao.readByName(obj2.getName()).getId());
    }

    @Test
    void readByTypeId() {
        assertEquals(obj2.getId(), dao.readByTypeId(obj2.getType_id()).get(0).getId());
    }

    @Test
    void getAll() {
        List<Attributes> tps = dao.getAll();
        Boolean idp = tps.contains(obj1) && tps.contains(obj2); // tps.get(tps.size() - 1).getId() == obj1.getId() || tps.get(tps.size() - 1).getId() == obj2.getId();
        assertTrue(idp);
    }
}