package Student.REST.API.DAO.DBMetaDAO;

import Student.REST.API.DBEntities.Attributes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AttributesJDBCDAO extends DBEntitiesDAO<Attributes> {

    private String create = "INSERT INTO atts (name, type_id) VALUES (?, ?)";
    private String get_by_id = "SELECT * FROM atts WHERE att_id=?";
    private String get_by_name = "SELECT * FROM atts WHERE name=?";
    private String get_by_type_id = "SELECT * FROM atts WHERE type_id=?";
    private String update = "UPDATE atts SET type_id=?, name=? WHERE att_id=?";
    private String delete = "DELETE FROM atts WHERE att_id=?";
    private String get_all = "SELECT * FROM atts";


    public AttributesJDBCDAO(Connection connection){
        super(connection);
    }



    @Override
    public Attributes create(Attributes entity) throws SQLException {
        Attributes answ = null;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(create);
                preparedStatement.setInt(2, entity.getType_id());
                preparedStatement.setString(1, entity.getName());
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
    public void update(Attributes entity) {
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
    public Attributes read(int id) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(get_by_id);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    String name = resultSet.getString(2);
                    int typeid = resultSet.getInt(3);
                    return new Attributes(id, typeid, name);
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Attributes readByName(String name) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(get_by_name);
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    int id = resultSet.getInt(1);
                    int typeid = resultSet.getInt(3);
                    return new Attributes(id, typeid, name);
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Attributes> readByTypeId(int typeId){
        List<Attributes> res = new ArrayList<>();

        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(get_by_type_id);
                preparedStatement.setInt(1, typeId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    res.add(new Attributes(id, typeId, name));
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return res;
    }

    @Override
    public List<Attributes> getAll() {
        List<Attributes> res = new ArrayList<>();

        if (connection != null){
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(get_all);
                while (resultSet.next()){
                    int id = resultSet.getInt(1);
                    int typeid = resultSet.getInt(3);
                    String name = resultSet.getString(2);
                    res.add(new Attributes(id, typeid, name));
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
