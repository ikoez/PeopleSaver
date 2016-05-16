package csy.a2_1002751337;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
//import android.app.Fragment;


public class LoadFragment extends Fragment {
    private String getfile;
    private View view;
    private String[] filenames ;
    private InfoList infoList;
    private String content;
    private ClassifyString classifyString;
    private InfoContain infoContain = new InfoContain();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_load, container, false);

        String path="/data/data/csy.a2_1002751337/files";
      //  Toast.makeText(getActivity(), path, Toast.LENGTH_SHORT).show();
        File file=new File(path);
        File[] filelist =file.listFiles();
        filenames=new String[filelist.length];
        for(int i=0;i<filelist.length;i++){
            filenames[i]=filelist[i].getName().toString();
        }

        ListView listView=(ListView)view.findViewById(R.id.loadlistview);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, filenames));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView adapter, View view, int position, long id) {
                getfile = filenames[position];
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = getActivity().openFileInput(getfile);
                    int len = 0;
                    byte[] buffer = new byte[1024];
                    ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
                    while ((len = fileInputStream.read(buffer)) != -1) {
                        byteArrayInputStream.write(buffer, 0, len);
                    }
                    content = new String(byteArrayInputStream.toByteArray());
                    //    Log.d(TAG, string);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                infoList = new InfoList();
//                infoList = classifyString.divide(content);
               int ii, lenc;
                String[] ss = content.split("\r\n");
                lenc = ss.length;

                for (ii=0; ii<lenc; ii=ii+3){
                    infoContain = new InfoContain();
                    infoContain.namein(ss[ii]);
                    infoContain.agein(ss[ii + 1]);
                    infoContain.moviein(ss[ii + 2]);

                    infoList.personin(infoContain);
        //            Toast.makeText(getActivity(), infoList.personout(0).nameout(), Toast.LENGTH_SHORT).show();

                }

                ((MainActivity)getActivity()).MainInfoListIn(infoList);
                ((MainActivity)getActivity()).MainLoadFile(getfile);
                Toast.makeText(getActivity(), "Choosed "+getfile, Toast.LENGTH_SHORT).show();
                FirstFragment firstFragment = new FirstFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_layout, firstFragment);
                getFragmentManager().popBackStack();
                transaction.commitAllowingStateLoss();
              //  getFragmentManager().beginTransaction().replace(R.id.main_layout, firstFragment).commit();

            }
        });


        return view;
    }



 /*   public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            FirstFragment fragment1 = new FirstFragment();
            getFragmentManager().beginTransaction().replace(R.id.main_layout, fragment1).commit();

        }
        return super.onKeyDown(keyCode, event);
    }
*/

}
