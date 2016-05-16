package csy.a2_1002751337;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.List;

public class ViewActivity extends AppCompatActivity {
    private InfoList infoList= new InfoList();
    private InfoContain infoContain;
    private String[] peoplelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Bundle bundle = getIntent().getExtras();


        infoList = (InfoList) bundle.getSerializable("Key");
        peoplelist= new String[infoList.size()];
        for(int i=0;i<infoList.size();i++)
        {
            String person="Name: "+infoList.personout(i).nameout()+"\r\n"+"Age: "+infoList.personout(i).ageout()+"\r\n"+"Favorite movie: "+infoList.personout(i).movieout();
            peoplelist[i] = person;
        }
        ListView listView=(ListView)findViewById(R.id.viewlistview);
        listView.setAdapter(new ArrayAdapter<String>(ViewActivity.this, android.R.layout.simple_list_item_1, peoplelist));
    }


}
