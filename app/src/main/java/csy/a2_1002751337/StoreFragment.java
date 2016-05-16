package csy.a2_1002751337;

import android.app.AlertDialog;
//import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class StoreFragment extends Fragment {
    private InfoList infoList;
    private EditText filename;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_store, container, false);

        filename=(EditText)view.findViewById(R.id.filenamestore);
        Button show=(Button)view.findViewById(R.id.buttonSaveStore);
        show.setOnClickListener(new ButtonClickListener());


        return view;
    }

    class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.buttonSaveStore) {
               if (filename.getText().toString().length()!=0) {
                   if (matchfile(filename.getText().toString())) {

                       new AlertDialog.Builder(getActivity()).setTitle("SAME FILENAME")//设置对话框标题

                               .setMessage("Filename already exists. Do you want to overwrite the original file content?")//设置显示的内容

                               .setPositiveButton("No", new DialogInterface.OnClickListener() {//添加确定按钮


                                   @Override

                                   public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                       StoreFragment storeFragment = new StoreFragment();
                                       FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                       transaction.replace(R.id.main_layout, storeFragment);
                                       getFragmentManager().popBackStack();
                                       transaction.commitAllowingStateLoss();
                                     //  getFragmentManager().beginTransaction().replace(R.id.main_layout, storeFragment).commit();

                                   }

                               }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {//添加返回按钮


                           @Override

                           public void onClick(DialogInterface dialog, int which) {//响应事件

                               try {
                                   infoList = ((MainActivity) getActivity()).MainInfoListOut();
                                   storeinphone(filename.getText().toString(), infoList);
                                   //  ((MainActivity) getActivity()).MainInfoListIn(infoList);
                                   Toast.makeText(getActivity(), "Overwrited", Toast.LENGTH_LONG).show();

                               } catch (FileNotFoundException e) {
                                   e.printStackTrace();
                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                               FirstFragment firstFragment = new FirstFragment();
                               FragmentTransaction transaction = getFragmentManager().beginTransaction();
                               transaction.replace(R.id.main_layout, firstFragment);
                               getFragmentManager().popBackStack();
                               transaction.commitAllowingStateLoss();
                             //  getFragmentManager().beginTransaction().replace(R.id.main_layout, firstFragment).commit();

                           }

                       }).show();//在按键响应事件中显示此对话框


                   } else {


                       try {
                           infoList = ((MainActivity) getActivity()).MainInfoListOut();
                           storeinphone(filename.getText().toString(), infoList);
                           //    ((MainActivity) getActivity()).MainInfoListIn(infoList);
                           Toast.makeText(getActivity(), "Saved", Toast.LENGTH_LONG).show();

                       } catch (FileNotFoundException e) {
                           e.printStackTrace();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                       FirstFragment firstFragment = new FirstFragment();
                       FragmentTransaction transaction = getFragmentManager().beginTransaction();
                       transaction.replace(R.id.main_layout, firstFragment);
                       getFragmentManager().popBackStack();
                       transaction.commitAllowingStateLoss();
                     //  getFragmentManager().beginTransaction().replace(R.id.main_layout, firstFragment).commit();


                   }
               }
            }
        }
    }

    public boolean matchfile(String ofilename){
        boolean flag = false;
        String path="/data/data/csy.a2_1002751337/files";
        File file=new File(path);
        if (file.exists()){
            File[] filelist =file.listFiles();
            ofilename=ofilename+".txt";

            for(int i=0;i<filelist.length;i++){

                if(ofilename.equals(filelist[i].getName().toString())) {
                    flag = true;
                    break;
                }

            }
        }

       return flag;

    }

    public void storeinphone(String fileName, InfoList list) throws IOException {
        /*
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return;
        } */
        FileOutputStream fileOutputStream = null;
        fileName=fileName+".txt";
        fileOutputStream = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);
        for(int i=0;i<list.size();i++)
        {
            String person=list.personout(i).nameout()+"\r\n"+list.personout(i).ageout()+"\r\n"+list.personout(i).movieout()+"\r\n";
            fileOutputStream.write(person.getBytes());

        }

        fileOutputStream.close();
    }
}
