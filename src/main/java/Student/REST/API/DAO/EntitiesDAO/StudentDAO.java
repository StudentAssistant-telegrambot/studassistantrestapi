package Student.REST.API.DAO.EntitiesDAO;

import Student.REST.API.DBEntities.Attributes;
import Student.REST.API.DBEntities.Object;
import Student.REST.API.DBEntities.Params;
import Student.REST.API.DBEntities.Types;
import Student.REST.API.Entties.Student;
import org.w3c.dom.Attr;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentDAO extends EntitiesDAO<Student> {



    public StudentDAO(Connection connection){
        super(connection);
    }

    @Override
    public Student create(Student entity) throws SQLException {
        //Check for type and attributes

        Types types = typesJDBCDAO.readByName(Student.EntityName);
        if (types == null)
            types = typesJDBCDAO.create(new Types(Student.EntityName));

        // Create new object

        Object object = objectJDBCDAO.create(new Object(types.getId(), entity.getFullName()));
        entity.setId(object.getId());

        // Check attributes

        Attributes firstnameatt = attributesJDBCDAO.readByName(Student.firstNameAtt);
        if (firstnameatt == null)
            firstnameatt = attributesJDBCDAO.create(new Attributes(types.getId(), Student.firstNameAtt));

        Attributes secondnameatt = attributesJDBCDAO.readByName(Student.secondNameAtt);
        if (secondnameatt == null)
            secondnameatt = attributesJDBCDAO.create(new Attributes(types.getId(), Student.secondNameAtt));

        Attributes ageatt = attributesJDBCDAO.readByName(Student.ageAtt);
        if (ageatt == null)
            ageatt = attributesJDBCDAO.create(new Attributes(types.getId(), Student.ageAtt));

        Attributes groupatt = attributesJDBCDAO.readByName(Student.groupAtt);
        if (groupatt == null)
            groupatt = attributesJDBCDAO.create(new Attributes(types.getId(), Student.groupAtt));

        // Creating new values for attributes

        paramsJDBCDAO.create(new Params(object.getId(), firstnameatt.getId(), entity.getFirstname()));
        paramsJDBCDAO.create(new Params(object.getId(), secondnameatt.getId(), entity.getSecondname()));
        paramsJDBCDAO.create(new Params(object.getId(), ageatt.getId(), String.valueOf(entity.getAge())));
        paramsJDBCDAO.create(new Params(object.getId(), groupatt.getId(), entity.getGroup()));

        return entity;

    }

    @Override
    public void update(Student entity) {
        Types types = typesJDBCDAO.readByName(Student.EntityName);
        if (types == null)
            return;

        Object object = objectJDBCDAO.read(entity.getId());

        Types types1 = typesJDBCDAO.read(object.getType_id());

        List<Attributes> atts = attributesJDBCDAO.readByTypeId(types1.getId());

        List<Params> objparams = paramsJDBCDAO.readByObjId(object.getId());

        Params params = null;

        // firstname

        for (Attributes att : atts){
            if (Objects.equals(att.getName(), Student.firstNameAtt))
            {
                for (int i = 0; i < objparams.size(); i++)
                    if (objparams.get(i).getAttribute_id() == att.getId())
                    {
                        params = objparams.get(i);
                        params.setValue(entity.getFirstname());

                        paramsJDBCDAO.update(params);
                        break;
                    }
            }
        }

        //secondname

        for (Attributes att : atts){
            if (Objects.equals(att.getName(), Student.secondNameAtt))
            {
                for (int i = 0; i < objparams.size(); i++)
                    if (objparams.get(i).getAttribute_id() == att.getId())
                    {
                        params = objparams.get(i);
                        params.setValue(entity.getSecondname());

                        paramsJDBCDAO.update(params);
                        break;
                    }
            }
        }

        // age

        for (Attributes att : atts){
            if (Objects.equals(att.getName(), Student.ageAtt))
            {
                for (int i = 0; i < objparams.size(); i++)
                    if (objparams.get(i).getAttribute_id() == att.getId())
                    {
                        params = objparams.get(i);
                        params.setValue(String.valueOf(entity.getAge()));

                        paramsJDBCDAO.update(params);
                        break;
                    }
            }
        }

        // group

        for (Attributes att : atts){
            if (Objects.equals(att.getName(), Student.groupAtt))
            {
                for (int i = 0; i < objparams.size(); i++)
                    if (objparams.get(i).getAttribute_id() == att.getId())
                    {
                        params = objparams.get(i);
                        params.setValue(entity.getGroup());

                        paramsJDBCDAO.update(params);
                        break;
                    }
            }
        }
    }

    @Override
    public void delete(int id) {
        Types types = typesJDBCDAO.readByName(Student.EntityName);
        if (types == null)
            return;

        Object object = objectJDBCDAO.read(id);

        if (object == null)
            return;

        Types types1 = typesJDBCDAO.read(object.getType_id());

        List<Params> objparams = paramsJDBCDAO.readByObjId(object.getId());

        for (int i = 0; i < objparams.size(); i++)
            paramsJDBCDAO.delete(objparams.get(i).getId());

        objectJDBCDAO.delete(object.getId());
    }

    @Override
    public Student read(int id) {
        Types types = typesJDBCDAO.readByName(Student.EntityName);
        if (types == null)
            return null;

        Object object = objectJDBCDAO.read(id);

        if (object == null)
            return null;

        Types types1 = typesJDBCDAO.read(object.getType_id());

        List<Attributes> atts = attributesJDBCDAO.readByTypeId(types1.getId());

        List<Params> objparams = paramsJDBCDAO.readByObjId(object.getId());

        Student student = new Student();
        student.setId(id);

        for (Params p : objparams){
            for (Attributes att : atts)
            {
                if (p.getAttribute_id() == att.getId())
                {
                    if (Objects.equals(att.getName(), Student.firstNameAtt))
                    {
                        student.setFirstname(p.getValue());
                        //break;
                    }

                    if (Objects.equals(att.getName(), Student.secondNameAtt))
                    {
                        student.setSecondname(p.getValue());
                        //break;
                    }
                    if (Objects.equals(att.getName(), Student.ageAtt))
                    {
                        student.setAge(Integer.parseInt(p.getValue()));
                        //break;
                    }
                    if (Objects.equals(att.getName(), Student.groupAtt))
                    {
                        student.setGroup(p.getValue());
                        //break;
                    }
                }
            }
        }

        return student;
    }

    @Override
    public Student readByName(String name) {
        //return read(objectJDBCDAO.readByName(name).getId());
        return null;
    }

    @Override
    public List<Student> getAll() {
        Types types = typesJDBCDAO.readByName(Student.EntityName);

        if (types == null)
            return null;

        List<Object> objects = objectJDBCDAO.readByTypeId(types.getId());

        if (objects == null)
            return null;

        List<Student> students = new ArrayList<>();

        for (Object obj : objects) {
            students.add(read(obj.getId()));
        }

        return students;
    }
}
