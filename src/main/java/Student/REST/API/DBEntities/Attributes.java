package Student.REST.API.DBEntities;

public class Attributes {
    private int id;
    private  String name;
    private int type_id;

    public Attributes(){}

    public Attributes(int type_id, String name){
        this.type_id = type_id;
        this.name = name;
    }
    public Attributes(int id, int type_id, String name){
        this.id = id;
        this.type_id = type_id;
        this.name = name;
    }

    public int getType_id() {
        return type_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
