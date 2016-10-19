package com.dd.sample;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.dd.sample.Adapter.GridViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chenlij on 2016/10/19.
 */
public class Sample6Activity extends Activity{

    GridViewAdapter gridViewAdapter;
    GridView gridView;
    HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
    List<String> dataList;

    public static void startThisActivity(Activity activity) {
        activity.startActivity(new Intent(activity, Sample6Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_sample_6);
        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setTitle("使用GridView");
        }
        gridViewAdapter = new GridViewAdapter(getData(), Sample6Activity.this, hashMap);
        gridView.setAdapter(gridViewAdapter);
    }

    private List<String> getData() {
        int startInt = 0;
        int endInt = 0;
        dataList = new ArrayList<String>();
        startInt = 5;
        endInt = 10;
        for (int i = startInt; i <= endInt; i++) {
            dataList.add("" + i);
        }
        return dataList;
    }

}
