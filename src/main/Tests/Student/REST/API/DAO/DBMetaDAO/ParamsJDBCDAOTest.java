package Student.REST.API.DAO.DBMetaDAO;

import Student.REST.API.DBEntities.Params;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParamsJDBCDAOTest {
    private String url = "jdbc:mysql://localhost:3306/metamapz?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String un = "root";
    private String ps = "123qweasd";

    private Params obj1;
    private Params obj2;
    private ParamsJDBCDAO dao;
    @org.junit.jupiter.api.BeforeEach
    void befEach() throws SQLException {
        dao = new ParamsJDBCDAO(DriverManager.getConnection(url, un, ps));
        obj1 = dao.create(new Params(1, 1, 1, "1"));
        obj2 = dao.create(new Params(2, 2, 2, "2"));
    }

    @org.junit.jupiter.api.AfterEach
    void aftEach(){
        dao.delete(obj1.getId());
        dao.delete(obj2.getId());
    }


    @Test
    void create() throws SQLException {
        Params type3 = dao.create(new Params(4, 4, 4,"3"));
        //assertTrue(type3.getId() > obj2.getId());
        assertTrue(type3.getValue().equals("3") && type3.getAttribute_id() == 4);
        //assertTrue(type3.getId() > obj1.getId());
        dao.delete(type3.getId());
    }

    @Test
    void update() {
        obj1.setAttribute_id(999);
        obj1.setObject_id(0);
        obj1.setValue("Vasya");
        dao.update(obj1);
        Params t = dao.read(obj1.getId());
        assertEquals(obj1, t);
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
        assertNull(dao.readByName("fnbfkjsdnfsd"));
    }

    @Test
    void readByAllAttrs() {
        assertEquals(obj1, dao.readByAllAttrs(obj1.getObject_id(), obj1.getAttribute_id(), obj1.getValue()));
    }

    @Test
    void readByObjId() {
        assertTrue(dao.readByObjId(obj1.getObject_id()).contains(obj1));
    }

    @Test
    void readByAttId() {
        assertTrue(dao.readByAttId(obj1.getAttribute_id()).contains(obj1));
    }

    @Test
    void getAll() {
        List<Params> tps = dao.getAll();
        Boolean idp = tps.contains(obj1) && tps.contains(obj2); // tps.get(tps.size() - 1).getId() == obj1.getId() || tps.get(tps.size() - 1).getId() == obj2.getId();
        assertTrue(idp);
    }
}