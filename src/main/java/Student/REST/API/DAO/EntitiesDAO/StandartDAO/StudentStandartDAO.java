package Student.REST.API.DAO.EntitiesDAO.StandartDAO;

import Student.REST.API.DAO.DAO;
import Student.REST.API.Entties.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentStandartDAO implements DAO<Student> {

    Connection connection;
    PreparedStatement preparedStatement;

    private String create = "INSERT INTO students (firstname, secondname, age, sgroup) VALUES (?, ?, ?, ?)";
    private String get_by_id = "SELECT * FROM students WHERE id=?";
    private String update = "UPDATE students SET firstname=?, secondname=?, age=?, sgroup=? WHERE id=?";
    private String get_id = "SELECT id FROM students WHERE firstname=? AND secondname=? AND age=? AND sgroup=?";
    private String delete = "DELETE FROM students WHERE id=?";
    private String get_all = "SELECT * FROM students";


    public StudentStandartDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public Student create(Student entity) throws SQLException {
        if (connection != null)
        {
            try{
                preparedStatement = connection.prepareStatement(create);

                preparedStatement.setString(1, entity.getFirstname());
                preparedStatement.setString(2, entity.getSecondname());
                preparedStatement.setInt(3, entity.getAge());
                preparedStatement.setString(4, entity.getGroup());

                preparedStatement.execute();

                preparedStatement = connection.prepareStatement(get_id);

                preparedStatement.setString(1, entity.getFirstname());
                preparedStatement.setString(2, entity.getSecondname());
                preparedStatement.setInt(3, entity.getAge());
                preparedStatement.setString(4, entity.getGroup());

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    return new Student(resultSet.getInt("id"), entity.getFirstname(), entity.getSecondname(), entity.getAge(), entity.getGroup());
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return  null;
    }

    @Override
    public void update(Student entity) {
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(update);

                preparedStatement.setString(1, entity.getFirstname());
                preparedStatement.setString(2, entity.getSecondname());
                preparedStatement.setInt(3, entity.getAge());
                preparedStatement.setString(4, entity.getGroup());
                preparedStatement.setInt(5, entity.getId());

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
    public Student read(int id) {
        if (connection != null){
            try{
                preparedStatement = connection.prepareStatement(get_by_id);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    String fn = resultSet.getString("firstname");
                    String sn = resultSet.getString("secondname");
                    int age = resultSet.getInt("age");
                    String gr = resultSet.getString("sgroup");

                    return new Student(id, fn, sn, age, gr);
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Student readByName(String name) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        List<Student> res = new ArrayList<>();

        if (connection != null){
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(get_all);
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    int age = resultSet.getInt("age");
                    String fn = resultSet.getString("firstname");
                    String sn = resultSet.getString("secondname");
                    String gr = resultSet.getString("sgroup");
                    res.add(new Student(id, fn, sn, age, gr));
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
