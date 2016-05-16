package csy.a2_1002751337;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
//    public InfoContain infoContain;
    private InfoList infoList;
    private String filename = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        infoList = new InfoList();
        FirstFragment fragment1 = new FirstFragment();
      //  getFragmentManager().beginTransaction().addToBackStack(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment1).commit();

    }
    public void MainInfoListIn(InfoList temp)
    {
        infoList=temp;
    }

    public InfoList MainInfoListOut()
    {
        return infoList;
    }

    public void MainLoadFile(String temp)
    {
        filename=temp;
    }

    public String MainExitFile()
    {
        return filename;
    }



}
