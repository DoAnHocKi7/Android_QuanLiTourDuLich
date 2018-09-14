package com.example.tranthibay.ql_tourdulich.ListView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import com.example.tranthibay.ql_tourdulich.R;

import java.util.List;

public class TourAdapter1 extends ArrayAdapter {

    private Context Context;
    private int Resource;
    private List<String> TourModels;


    public TourAdapter1(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.Context = context;
        this.Resource = resource;
        this.TourModels = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String tourView;
        if(convertView==null){
            convertView=LayoutInflater.from(Context).inflate(R.layout.listview_item_tour,parent,false);
            tourView=new String();
            convertView.setTag(tourView);
        }
        else{
            tourView=(String) convertView.getTag();
        }
       return convertView;
    }
}
