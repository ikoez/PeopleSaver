package csy.a2_1002751337;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SEC on 2016/1/23.
 */
public class InfoList implements Serializable {
    private List<InfoContain> array;
    public InfoList(){
        array=new ArrayList<InfoContain>();
    }
    public void personin(InfoContain person){
        array.add(person);
    }
    public InfoContain personout(int p){
        return array.get(p);
    }
    public int size()
    {
        return array.size();
    }
}
