package com.example.tranthibay.ql_tourdulich.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tranthibay.ql_tourdulich.Constants.TourConstants;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;
import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.View.ChiTietTour.ChiTietTourActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TourRecyclerViewAdapter extends RecyclerView.Adapter<TourRecyclerViewAdapter.RecyclerViewHolder> {

    //private List<TourModel> data = new ArrayList<>();
    private List<TourModel> data;
    private Context context;

    public TourRecyclerViewAdapter(List<TourModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( context );
        View view = inflater.inflate( R.layout.item_row_tour, parent, false );
        return new RecyclerViewHolder( view );
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.tv_TenTour.setText( data.get( position ).getTenTour() );
        holder.tv_GiaTour.setText( "Giá: " + String.valueOf( data.get( position ).getGia() ) );

        String imgSrc = data.get( position ).getHinhAnh();
        Picasso.get().load( imgSrc ).into( holder.img_Tour );


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_TenTour;
        TextView tv_GiaTour;
        ImageView img_Tour;

        public RecyclerViewHolder(View itemView) {
            super( itemView );
            tv_TenTour = (TextView) itemView.findViewById( R.id.tv_TenTour );
            tv_GiaTour = (TextView) itemView.findViewById( R.id.frag_ShowTour_tv_GiaTour );
            img_Tour = (ImageView) itemView.findViewById( R.id.show_tour_item_img_Tour );
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TourModel tourModel = data.get( getAdapterPosition() );
                    Intent intent = new Intent( context, ChiTietTourActivity.class );
                    intent.putExtra( TourConstants.PASSEDTOUR, tourModel );
                    context.startActivity( intent );
                }
            } );
        }

    }
}
