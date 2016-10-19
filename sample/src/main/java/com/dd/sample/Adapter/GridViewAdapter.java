package com.dd.sample.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dd.CircularProgressButton;
import com.dd.sample.R;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Chenlij on 2016/10/9.
 */
public class GridViewAdapter extends BaseAdapter {
    Handler handler = new Handler();
    List<String> list;
    Context context;
    HashMap<Integer, String> hashMap;
    LayoutInflater lf;
    private String TAG = "GridViewAdapter";

    public GridViewAdapter(List<String> list, Context context, HashMap<Integer, String> hashMap) {
        this.list = list;
        this.context = context;
        this.hashMap = hashMap;
        lf = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyViewHolder myViewHolder;
        if (convertView == null) {
            convertView = lf.inflate(R.layout.item_gridview, null);
            myViewHolder = new MyViewHolder();
            myViewHolder.circularProgressButton = (CircularProgressButton) convertView.findViewById(R.id.circularProgressButton);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.circularProgressButton.setText(getItem(position).toString());
//        ImageView imageView = (ImageView) view.findViewById(R.id.grid_image);
//        TextView textView = (TextView) view.findViewById(R.id.grid_text);
//        textView.setText(getItem(position).toString());
        // for (int i = 0; i < list.size(); i++) {

        String string = hashMap.get(position);

        if (null != string && string.equals(list.get(position))) {
//            imageView.setBackgroundResource(android.R.color.holo_green_light);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    myViewHolder.circularProgressButton.setProgress(0);
                }
            });

        }
        // }
        return convertView;
    }

    public class MyViewHolder {
        CircularProgressButton circularProgressButton;
    }


}
