package Student.REST.API.DAO.DBMetaDAO;

import Student.REST.API.DAO.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class DBEntitiesDAO<E> implements DAO<E> {

    protected Connection connection;
    protected PreparedStatement preparedStatement;

    public DBEntitiesDAO(Connection connection){
        this.connection = connection;
    }

    public void Close(){
        connection = null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
