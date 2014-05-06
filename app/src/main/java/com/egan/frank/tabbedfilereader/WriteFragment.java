package com.egan.frank.tabbedfilereader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by eganortho on 4/10/14.
 */
public class WriteFragment extends Fragment {
    EditText fileName, entry;
    String fileString, entryString;
//    File dir;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
//        dir = new File("/sdcard/Documents/FrankEganText");
//        dir.mkdirs();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragement_write, container, false);
        fileName = (EditText) v.findViewById(R.id.editText1);
        entry = (EditText) v.findViewById(R.id.editText2);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_save, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.action_save:
                fileString = fileName.getText().toString();
                if (fileString.contentEquals(""))
                    fileString = "Untitled";
                entryString = entry.getText().toString();
                writeFile(entryString);
                MainActivity.refresh(entry);
                MainActivity.refresh(fileName);
                return true;

            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        //default:
        return super.onOptionsItemSelected(item);
    }

    void writeFile(String s) {
        try {
            FileOutputStream fos = new FileOutputStream(getFile());
            fos.write(s.getBytes());
            fos.close();
            Toast.makeText(getActivity(), "File saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getActivity(), "its broken", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getActivity(), "its broken", Toast.LENGTH_SHORT).show();
        }
    }

    File getFile() {
//        File dir = new File("/sdcard/Documents/FrankEganText");
//        dir.mkdirs();
        File file = new File(MainActivity.dir, fileString + ".txt");
        return file;
    }
}
