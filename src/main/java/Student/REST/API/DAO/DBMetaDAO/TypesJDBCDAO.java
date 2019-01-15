package Student.REST.API.DAO.DBMetaDAO;

import Student.REST.API.DBEntities.Types;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TypesJDBCDAO extends DBEntitiesDAO<Types> {

    private String create = "INSERT INTO types (name) VALUES (?)";
    private String get_by_id = "SELECT * FROM types WHERE type_id=?";
    private String get_by_name = "SELECT * FROM types WHERE name=?";
    private String update = "UPDATE types SET name=? WHERE type_id=?";
    private String delete = "DELETE FROM types WHERE type_id=?";
    private String get_all = "SELECT * FROM types";

    public TypesJDBCDAO(Connection connection){
        super(connection);
    }

    @Override
    public Types create(Types entity) throws SQLException {
        Types answ = null;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(create);
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
    public void update(Types entity) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(update);
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setInt(2, entity.getId());
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
    public Types read(int id) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(get_by_id);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    String name = resultSet.getString(2);
                    return new Types(id, name);
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Types readByName(String name) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(get_by_name);
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    int id = resultSet.getInt(1);
                    return new Types(id, name);
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public List<Types> getAll() {
        List<Types> res = new ArrayList<>();

        if (connection != null){
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(get_all);
                while (resultSet.next()){
                    int typeid = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    res.add(new Types(typeid, name));
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
