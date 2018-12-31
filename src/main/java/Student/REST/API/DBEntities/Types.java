package Student.REST.API.DBEntities;

public class Types {
    private int id;
    private String name;

    public Types(){

    }
    public Types(String name){
        this.name = name;
    }
    public Types(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
