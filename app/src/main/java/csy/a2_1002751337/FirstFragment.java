package csy.a2_1002751337;

import android.app.AlertDialog;
//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by csy on 2016/1/22.
 */
public class FirstFragment extends Fragment {

    private InfoList infoList;
    private View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.firstfrgm, container, false);
        Button buttonEN = (Button) view.findViewById(R.id.buttonEN);
        Button buttonS = (Button) view.findViewById(R.id.buttonS);
        Button buttonV = (Button) view.findViewById(R.id.buttonV);
        Button buttonL = (Button) view.findViewById(R.id.buttonL);
        Button buttonE = (Button) view.findViewById(R.id.buttonE);

        buttonE.setOnClickListener(new ButtonClickListener());
        buttonL.setOnClickListener(new ButtonClickListener());
        buttonEN.setOnClickListener(new ButtonClickListener());
        buttonS.setOnClickListener(new ButtonClickListener());
        buttonV.setOnClickListener(new ButtonClickListener());

        return view;
    }

    class ButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.buttonEN){
                EnterFragment enterFragment = new EnterFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_layout, enterFragment);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();

               // getFragmentManager().beginTransaction().addToBackStack(null);
               // getFragmentManager().beginTransaction().replace(R.id.main_layout, enterFragment).commit();
            }
            if(v.getId() == R.id.buttonL){
                String path="/data/data/csy.a2_1002751337/files";

                File file=new File(path);

                if (!file.exists()){
                    Toast.makeText(getActivity(), "There is no file", Toast.LENGTH_SHORT).show();
                }
                else{
                    LoadFragment loadFragment = new LoadFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.main_layout, loadFragment);
                    transaction.addToBackStack(null);
                    transaction.commitAllowingStateLoss();

                    //   getFragmentManager().beginTransaction().addToBackStack(null);
                     //  getFragmentManager().beginTransaction().replace(R.id.main_layout, loadFragment).commit();
                }

            }
            if(v.getId() == R.id.buttonS) {
                infoList = ((MainActivity) getActivity()).MainInfoListOut();
                if (infoList.size() == 0) {
                    Toast.makeText(getActivity(), "No data. Please enter names or load file", Toast.LENGTH_LONG).show();
                } else {
                    StoreFragment storeFragment = new StoreFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.main_layout, storeFragment);
                    transaction.addToBackStack(null);
                    transaction.commitAllowingStateLoss();

                //getFragmentManager().beginTransaction().addToBackStack(null);
                //  getFragmentManager().beginTransaction().replace(R.id.main_layout, storeFragment).commit();
                }
            }
            if(v.getId() == R.id.buttonV) {
                infoList = ((MainActivity) getActivity()).MainInfoListOut();
                if (infoList.size() == 0){
                    Toast.makeText(getActivity(), "No data. Please enter names or load list", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Key", infoList);
                    intent.putExtras(bundle);
                    intent.setClass(getActivity(), ViewActivity.class);
                    startActivity(intent);
                }
            }
            if(v.getId() == R.id.buttonE){
                new AlertDialog.Builder(getActivity()).setTitle("Exit")//设置对话框标题

                        .setMessage("The new list will be saved at templefile.txt. Or the modified file will be saved as original filename.")//设置显示的内容

                        /*.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {//添加确定按钮


                            @Override

                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                FirstFragment fragment3 = new FirstFragment();
                                getFragmentManager().beginTransaction().replace(R.id.main_layout, fragment3).commit();

                            }

                        }) */.setNegativeButton("Exit",new DialogInterface.OnClickListener() {//添加返回按钮



                    @Override

                    public void onClick(DialogInterface dialog, int which) {//响应事件

                        infoList = ((MainActivity) getActivity()).MainInfoListOut();
                        String filename= ((MainActivity) getActivity()).MainExitFile();;
                        if (infoList.size() != 0){
                            if (filename==null){
                                filename="templefile.txt";
                            }
                            try {
                                storeinphone(filename, infoList);


                            }catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        android.os.Process.killProcess(android.os.Process.myPid());   //获取PID
                        System.exit(0);
                    }

                }).show();//在按键响应事件中显示此对话框

            }
        }
    }

    public void storeinphone(String fileName, InfoList list) throws IOException {
        /*
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return;
        } */
        FileOutputStream fileOutputStream = null;
        fileOutputStream = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);
        for(int i=0;i<list.size();i++)
        {
            String person=list.personout(i).nameout()+"\r\n"+list.personout(i).ageout()+"\r\n"+list.personout(i).movieout()+"\r\n";
            fileOutputStream.write(person.getBytes());

        }

        fileOutputStream.close();
    }

}
