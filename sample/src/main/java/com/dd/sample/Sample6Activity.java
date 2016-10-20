package com.dd.sample;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.dd.CircularProgressButton;
import com.dd.sample.Adapter.GridViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chenlij on 2016/10/19.
 */
public class Sample6Activity extends Activity {

    Handler mhandler = new Handler();
    GridViewAdapter gridViewAdapter;
    GridView gridView;
    HashMap<Integer, String> hashMap = new HashMap<Integer, String>();  //HashMap是引用型数据
    List<String> dataList;
    private Button btn_1;
    private String TAG = "Sample6";
    int testNum = 0;
    private Button btn_2;


    public static void startThisActivity(Activity activity) {
        activity.startActivity(new Intent(activity, Sample6Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_sample_6);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("使用GridView");
        }
        gridViewAdapter = new GridViewAdapter(getData(), Sample6Activity.this, hashMap);
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(gridViewAdapter);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hashMap.put(0, "5");
                gridViewAdapter.setProgress(testNum);
                mhandler.post(task);
                gridViewAdapter.notifyDataSetChanged();
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hashMap.put(0, "5");
                gridViewAdapter.setProgress(testNum);   //只是个传值的操作，要传一次，notifyDataSetChanged一下才会有改变，如果是传引用则不会
                gridViewAdapter.notifyDataSetChanged();
            }
        });

        final CircularProgressButton circularButton1 = (CircularProgressButton) findViewById(R.id.circularButton61);
        circularButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton1.getProgress() == 0) {
//                    simulateSuccessProgress(circularButton1);
                    circularButton1.setProgress(40);
                } else {
                    circularButton1.setProgress(0);
                }
            }
        });
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
    int zzz = 0;
    private Message what;
    Runnable task = new Runnable() {
        @Override
        public void run() {
            testNum = testNum + 2;
            if (testNum > 100) {
                testNum = 0;
                hashMap.clear();
                hashMap.put(++zzz, String.valueOf(zzz + 5));
                if(zzz == 5) {
                    zzz = -1;
                }
                gridViewAdapter.setProgress(testNum);
                gridViewAdapter.notifyDataSetChanged();
            }
            gridViewAdapter.setProgress(testNum);
            gridViewAdapter.notifyDataSetChanged();     //需要在前面有变化的时候调用才能生效
            mhandler.postDelayed(task, 100);
        }
    };


}
