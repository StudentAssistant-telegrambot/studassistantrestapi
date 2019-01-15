package Student.REST.API.DAO.DBMetaDAO;

import Student.REST.API.DBEntities.Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ObjectJDBCDAO extends DBEntitiesDAO<Object> {

    private String create = "INSERT INTO object (type_id, name) VALUES (?, ?)";
    private String get_by_id = "SELECT * FROM object WHERE obj_id=?";
    private String get_by_name = "SELECT * FROM object WHERE name=?";
    private String get_by_type_id = "SELECT * FROM object WHERE type_id=?";
    private String update = "UPDATE object SET type_id=?, name=? WHERE obj_id=?";
    private String delete = "DELETE FROM object WHERE obj_id=?";
    private String get_all = "SELECT * FROM object";


    public ObjectJDBCDAO(Connection connection){
        super(connection);
    }

    @Override
    public Object create(Object entity) throws SQLException {
        Object answ = null;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(create);
                preparedStatement.setInt(1, entity.getType_id());
                preparedStatement.setString(2, entity.getName());
                preparedStatement.execute();

                answ = readByName(entity.getName());
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        return answ == null ? entity : answ;

    }

    @Override
    public void update(Object entity) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(update);
                preparedStatement.setInt(1, entity.getType_id());
                preparedStatement.setString(2, entity.getName());
                preparedStatement.setInt(3, entity.getId());
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(delete);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public Object read(int id) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(get_by_id);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    String name = resultSet.getString(3);
                    int typeid = resultSet.getInt(2);
                    return new Object(id, typeid, name);
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public Object readByName(String name) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(get_by_name);
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    int id = resultSet.getInt(1);
                    int typeid = resultSet.getInt(2);
                    return new Object(id, typeid, name);
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Object> readByTypeId(int typeId){
        List<Object> res = new ArrayList<>();

        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(get_by_type_id);
                preparedStatement.setInt(1, typeId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(3);
                    res.add(new Object(id, typeId, name));
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return res;

    }

    @Override
    public List<Object> getAll() {
        List<Object> res = new ArrayList<>();

        if (connection != null){
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(get_all);
                while (resultSet.next()){
                    int id = resultSet.getInt(1);
                    int typeid = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    res.add(new Object(id, typeid, name));
                }
                return res;
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        return null;

    }
}
