package Student.REST.API.DBEntities;

public class Object {
    private int id;
    private int type_id;
    private String name;

    public Object(){}

    public Object(int type_id, String name){
        this.type_id = type_id;
        this.name = name;
    }
    public Object(int id, int type_id, String name){
        this.id = id;
        this.type_id = type_id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
}
