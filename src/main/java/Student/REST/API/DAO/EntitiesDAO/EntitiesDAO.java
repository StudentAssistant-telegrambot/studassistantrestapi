package Student.REST.API.DAO.EntitiesDAO;

import Student.REST.API.DAO.DAO;
import Student.REST.API.DAO.DBMetaDAO.AttributesJDBCDAO;
import Student.REST.API.DAO.DBMetaDAO.ObjectJDBCDAO;
import Student.REST.API.DAO.DBMetaDAO.ParamsJDBCDAO;
import Student.REST.API.DAO.DBMetaDAO.TypesJDBCDAO;

import java.sql.Connection;

public abstract class EntitiesDAO<E> implements DAO<E> {

    protected Connection connection;
    protected TypesJDBCDAO typesJDBCDAO;
    protected ParamsJDBCDAO paramsJDBCDAO;
    protected ObjectJDBCDAO objectJDBCDAO;
    protected AttributesJDBCDAO attributesJDBCDAO;

    public EntitiesDAO(Connection connection){
        this.connection = connection;
        typesJDBCDAO = new TypesJDBCDAO(connection);
        paramsJDBCDAO = new ParamsJDBCDAO(connection);
        objectJDBCDAO = new ObjectJDBCDAO(connection);
        attributesJDBCDAO = new AttributesJDBCDAO(connection);
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
