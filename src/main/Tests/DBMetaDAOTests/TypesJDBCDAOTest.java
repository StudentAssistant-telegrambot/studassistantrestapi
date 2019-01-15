package DBMetaDAOTests;

import Student.REST.API.DAO.DBMetaDAO.TypesJDBCDAO;
import Student.REST.API.DBEntities.Types;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import static org.junit.jupiter.api.Assertions.*;

class TypesJDBCDAOTest {

    private String url = "jdbc:mysql://localhost:3306/metamapz?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String un = "root";
    private String ps = "123qweasd";

    private Types type1;
    private Types type2;
    private TypesJDBCDAO dao;


    @org.junit.jupiter.api.BeforeEach
    void befEach() throws SQLException {
        dao = new TypesJDBCDAO(DriverManager.getConnection(url, un, ps));
        type1 = dao.create(new Types("1"));
        type2 = dao.create(new Types("2"));
    }

    @org.junit.jupiter.api.AfterEach
    void aftEach(){
        dao.delete(type1.getId());
        dao.delete(type2.getId());
    }

    @org.junit.jupiter.api.Test
    void create() throws SQLException {
        Types type3 = dao.create(new Types("3"));
        assertTrue(type3.getId() > type2.getId());
        assertTrue(type3.getName().equals("3"));
        assertTrue(type3.getId() > type1.getId());
    }

    @org.junit.jupiter.api.Test
    void update() {
        type1.setName("Vasya");
        dao.update(type1);
        Types t = dao.read(type1.getId());
        assertEquals(type1.getName(), t.getName());
        assertEquals(type1.getId(), t.getId());
    }

    @org.junit.jupiter.api.Test
    void delete() {
        dao.delete(type1.getId());
        assertNull(dao.read(type1.getId()));
    }

    @org.junit.jupiter.api.Test
    void read() {
        assertEquals(type1.getId(), dao.read(type1.getId()).getId());
    }

    @org.junit.jupiter.api.Test
    void readByName() {
        assertEquals(type2.getId(), dao.readByName(type2.getName()).getId());
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        List<Types> tps = dao.getAll();
        Boolean idp = tps.get(1).getId() == type1.getId() || tps.get(1).getId() == type2.getId();
        assertTrue(idp);
    }
}