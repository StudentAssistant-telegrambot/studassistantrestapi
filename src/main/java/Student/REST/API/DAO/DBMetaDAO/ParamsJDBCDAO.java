package Student.REST.API.DAO.DBMetaDAO;

import Student.REST.API.DBEntities.Params;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParamsJDBCDAO extends DBEntitiesDAO<Params> {

    private String create = "INSERT INTO params (obj_id, att_id, value) VALUES (?, ?, ?)";
    private String get_by_id = "SELECT * FROM params WHERE params_id=?";
    private String get_by_obj_id = "SELECT * FROM params WHERE obj_id=?";
    private String get_by_att_id = "SELECT * FROM params WHERE att_id=?";
    private String get_by_all_prms = "SELECT * FROM params WHERE obj_id=? AND att_id=? AND value=?";
    private String update = "UPDATE params SET obj_id=?, att_id=?, value=? WHERE params_id=?";
    private String delete = "DELETE FROM params WHERE params_id=?";
    private String get_all = "SELECT * FROM params";


    public ParamsJDBCDAO(Connection connection){
        super(connection);
    }



    @Override
    public Params create(Params entity) throws SQLException {
        Params answ = null;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(create);
                preparedStatement.setInt(1, entity.getObject_id());
                preparedStatement.setString(3, entity.getValue());
                preparedStatement.setInt(2, entity.getAttribute_id());
                preparedStatement.execute();

                answ = readByAllAttrs(entity.getObject_id(), entity.getAttribute_id(), entity.getValue());
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        return answ == null ? entity : answ;
    }

    @Override
    public void update(Params entity) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(update);
                preparedStatement.setInt(1, entity.getObject_id());
                preparedStatement.setInt(2, entity.getAttribute_id());
                preparedStatement.setString(3, entity.getValue());
                preparedStatement.setInt(4, entity.getId());
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
    public Params read(int id) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(get_by_id);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    String value = resultSet.getString(4);
                    int objid = resultSet.getInt(2);
                    int attid = resultSet.getInt(3);
                    return new Params(id, objid, attid, value);
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Params readByName(String name) {
        return null;
    }

    public Params readByAllAttrs(int obj_id, int att_id, String value){

        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(get_by_all_prms);
                preparedStatement.setInt(1, obj_id);
                preparedStatement.setInt(2, att_id);
                preparedStatement.setString(    3, value);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    int id = resultSet.getInt(1);
                    return new Params(id, obj_id, att_id, value);
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Params> readByObjId(int objId){
        List<Params> res = new ArrayList<>();

        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(get_by_obj_id);
                preparedStatement.setInt(1, objId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    int id = resultSet.getInt(1);
                    int attid = resultSet.getInt(3);
                    String value = resultSet.getString(4);
                    res.add(new Params(id, objId, attid, value));
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return res;
    }

    public List<Params> readByAttId(int attId){
        List<Params> res = new ArrayList<>();

        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(get_by_att_id);
                preparedStatement.setInt(1, attId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    int id = resultSet.getInt(1);
                    int objid = resultSet.getInt(2);
                    String value = resultSet.getString(4);
                    res.add(new Params(id, objid, attId, value));
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return res;
    }

    @Override
    public List<Params> getAll() {
        List<Params> res = new ArrayList<>();

        if (connection != null){
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(get_all);
                while (resultSet.next()){
                    int id = resultSet.getInt(1);
                    int objid = resultSet.getInt(2);
                    int attid = resultSet.getInt(3);
                    String value = resultSet.getString(4);
                    res.add(new Params(id, objid, attid, value));
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
