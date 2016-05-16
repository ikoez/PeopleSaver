package csy.a2_1002751337;

/**
 * Created by SEC on 2016/1/24.
 */
public class ClassifyString {
    private InfoList infoList = new InfoList();
    private InfoContain infoContain = new InfoContain();
    private String age;
    private String name;
    private String movie;


    public InfoList divide(String content){
        int i, len;
        String[] ss = content.split("\r\n");
        len = ss.length;
        i=0;
        while (i<len){
            name=ss[i];
            age=ss[i+1];
            movie=ss[i+2];
            infoContain.agein(age);
            infoContain.namein(name);
            infoContain.moviein(movie);
            infoList.personin(infoContain);
            i=i+3;
            continue;
        }
        return infoList;
    }
}
