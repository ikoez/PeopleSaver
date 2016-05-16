package csy.a2_1002751337;

import java.io.Serializable;
import java.util.List;

public class InfoContain implements Serializable {

    private String age;
    private String name;
    private String movie;
    public void namein(String temp){
        name=temp;
    }
    public void agein(String temp){
        age=temp;
    }public void moviein(String temp){
        movie=temp;
    }
    public String nameout(){
        return name;
    }
    public String ageout(){
        return age;
    }
    public String movieout(){
        return movie;
    }


}
