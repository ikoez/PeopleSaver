package csy.a2_1002751337;

import android.app.Activity;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;


public class EnterFragment extends Fragment implements Serializable {
    private View view;
    private TextView text;
    private EditText age;
    private EditText name;
    private Spinner sp;
    private String movie;
    private String[] ls;
    private InfoContain infoContain=new InfoContain();
    private InfoList infoList =new InfoList();
    ArrayList<String> list=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_enter, container, false);

        text = (TextView)view.findViewById(R.id.countenter);
        age = (EditText) view.findViewById(R.id.editage);
        name = (EditText) view.findViewById(R.id.editname);
        Button buttonNext = (Button) view.findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new ButtonClickListener());
        Button buttonDone = (Button) view.findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new ButtonClickListener());

        infoList =  ((MainActivity)getActivity()).MainInfoListOut();

        text.setText("There are "+infoList.size()+" people in current list");

        Spinner sp=(Spinner)view.findViewById(R.id.movie);
        ls=getResources().getStringArray(R.array.selectmovies);
        for(int i=0;i<ls.length;i++){
            list.add(ls[i]);
        }
        adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                movie = ls[arg2];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
//        sp.setPrompt("please select movie");





        return view;

    }

    class ButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.buttonNext){
                if ((age.getText().toString().length()!=0) & (name.getText().toString().length()!=0)) {
                    infoContain = new InfoContain();
                    infoContain.agein(age.getText().toString());
                    infoContain.namein(name.getText().toString());
                    infoContain.moviein(movie);
                    infoList.personin(infoContain);
                    ((MainActivity)getActivity()).MainInfoListIn(infoList);
                    name.setText("");
                    age.setText("");
                    name.setHint("Please enter your name");
                    age.setHint("Please enter your age");
                    text.setText("There are " + infoList.size() + " people in current list");
                }


            }
            if(v.getId() == R.id.buttonDone) {
                if ((age.getText().toString().length()!=0) & (name.getText().toString().length()!=0)) {
                    infoContain = new InfoContain();
                    infoContain.agein(age.getText().toString());
                    infoContain.namein(name.getText().toString());
                    infoContain.moviein(movie);
                    infoList.personin(infoContain);
                    ((MainActivity)getActivity()).MainInfoListIn(infoList);
                }

                FirstFragment firstFragment = new FirstFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_layout, firstFragment);
                getFragmentManager().popBackStack();
                transaction.commitAllowingStateLoss();
               // getFragmentManager().beginTransaction().replace(R.id.main_layout, firstFragment).commit();
            }
        }
    }


}
