package com.example.tranthibay.ql_tourdulich.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;
import com.example.tranthibay.ql_tourdulich.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TourLienQuanAdapter extends ArrayAdapter<TourModel> {

    private Context context;
    private int resource;
    private List<TourModel> arrayTours;

    public TourLienQuanAdapter(Context context, int resource, ArrayList<TourModel> arrContact) {
        super( context, resource, arrContact );
        this.context = context;
        this.resource = resource;
        this.arrayTours = arrContact;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from( context ).inflate( R.layout.item_row_tour_lien_quan, parent, false );
            viewHolder = new ViewHolder();
            viewHolder.tv_TenTour = (TextView) convertView.findViewById( R.id.tv_TenTour );
            viewHolder.tv_GiaTour = (TextView) convertView.findViewById( R.id.frag_ShowTour_tv_GiaTour );
            viewHolder.img_Tour = (ImageView) convertView.findViewById( R.id.show_tour_item_img_Tour );


            convertView.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TourModel contact = arrayTours.get( position );
        viewHolder.tv_TenTour.setText( arrayTours.get( position ).getTenTour() );
        viewHolder.tv_GiaTour.setText( "Giá: " + String.valueOf( arrayTours.get( position ).getGia() ) );

        String imgSrc = arrayTours.get( position ).getHinhAnh();
        Picasso.get().load( imgSrc ).into( viewHolder.img_Tour );
        return convertView;
    }

    public class ViewHolder {
        TextView tv_TenTour;
        TextView tv_GiaTour;
        ImageView img_Tour;

    }
}