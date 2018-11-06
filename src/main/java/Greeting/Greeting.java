package Greeting;

import java.util.Date;
import java.util.Random;

public class Greeting {
    private long id;
    private String content;

    public Greeting(long id, String content){
        this.content = content;
        this.id = id;
    }

    public long getId(){
        return id;
    }
    public String getContent(){
        return content;
    }
}
