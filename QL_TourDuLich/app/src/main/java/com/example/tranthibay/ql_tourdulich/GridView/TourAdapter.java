package com.example.tranthibay.ql_tourdulich.GridView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.tranthibay.ql_tourdulich.R;

import java.util.List;

public class TourAdapter extends BaseAdapter {

     private Context Context;
    private List<String> TourModels;



    public TourAdapter(Context mContext, List<String> tourModels) {
        this.Context = mContext;
        this.TourModels = tourModels;
    }

    @Override
    public int getCount() {
        return this.TourModels.size();
    }

    @Override
    public Object getItem(int position) {
        return this.TourModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(this.Context).inflate(R.layout.gridview_tour_item,parent,false);

        return convertView;
    }

}
