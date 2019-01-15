package Student.REST.API.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<E> {
    E create(E entity) throws SQLException;
    void update(E entity);
    void delete(int id);
    E read(int id);
    E readByName(String name);
    List<E> getAll();
}
