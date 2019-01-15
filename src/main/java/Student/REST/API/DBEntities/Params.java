package Student.REST.API.DBEntities;

import java.util.Objects;

public class Params {
    private int id;
    private int object_id;
    private int attribute_id;
    String value;

    public Params(){}

    public Params(int object_id, int attribute_id, String value){
        this.object_id = object_id;
        this.attribute_id = attribute_id;
        this.value = value;
    }
    public Params(int id, int object_id, int attribute_id, String value){
        this.id = id;
        this.object_id = object_id;
        this.attribute_id = attribute_id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getAttribute_id() {
        return attribute_id;
    }

    public int getObject_id() {
        return object_id;
    }

    public String getValue() {
        return value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAttribute_id(int attribute_id) {
        this.attribute_id = attribute_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(java.lang.Object object){
        if (object != null)
            if (object instanceof Params)
                return ((Params) object).getId() == getId() && ((Params) object).getObject_id() == getObject_id() &&
                        ((Params) object).getAttribute_id() == getAttribute_id() && Objects.equals(((Params) object).getValue(), getValue());
        return false;
    }
}
