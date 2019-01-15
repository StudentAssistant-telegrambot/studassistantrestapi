package Student.REST.API.DBEntities;

import java.util.Objects;

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

    @Override
    public boolean equals(java.lang.Object object){
        if (object != null)
            if (object instanceof Types)
                return ((Types) object).getId() == getId() && Objects.equals(((Types) object).getName(), getName());
        return false;
    }
}
