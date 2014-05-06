package com.egan.frank.tabbedfilereader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eganortho on 4/10/14.
 */
public class ReadFragment extends Fragment implements View.OnClickListener {
    Spinner spinner;
    TextView entry;
    Button load;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_read, container, false);
        spinner = (Spinner) v.findViewById(R.id.spinner1);
        entry = (TextView) v.findViewById(R.id.textView2);
        load = (Button) v.findViewById(R.id.loadBtn);
        load.setOnClickListener(this);
        getFilename();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getFilename();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_refresh, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.action_refresh:
                getFilename();
                return true;

            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        //default:
        return super.onOptionsItemSelected(item);
    }

    private void getFilename() {
        // TODO Auto-generated method stub
        File dir = new File("/sdcard/Documents/FrankEganText");
        File fileArray[] = dir.listFiles();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < fileArray.length; i++) {
            Log.d("fileNames", fileArray[i].getName().toString());
            list.add(fileArray[i].getName().toString());
        }
        ArrayAdapter<String> fileNameAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, list);
        spinner.setAdapter(fileNameAdapter);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.loadBtn:
                String selectFile = String.valueOf(spinner.getSelectedItem());
                openFile(selectFile);
                break;
        }
    }

    private void openFile(String selectFile) {
        // TODO Auto-generated method stub
        String value = "";
        File file = new File("/sdcard/Documents/FrankEganText/");
        FileInputStream fis;
        try {
            fis = new FileInputStream(file.toString() + "/" + selectFile);
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {
                value += new String(input);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        entry.setText(value);
    }

}
