package com.example.tranthibay.ql_tourdulich.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaChonModel;
import com.example.tranthibay.ql_tourdulich.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TourDaDatReCyclerAdapter extends RecyclerView.Adapter<TourDaDatReCyclerAdapter.RecyclerViewHolder> {

    public TourDaDatReCyclerAdapter(ArrayList<TourDaChonModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from( context );
        View view = inflater.inflate( R.layout.gio_hang_row, parent, false );
        return new RecyclerViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tv_soNguoi.setText( String.valueOf( data.get( position ).getSoNguoi() ) );
        holder.tv_giaTourDaDat.setText( String.valueOf( data.get( position ).getTourModel().getGia()) );
        holder.tv_tongTienTourDaDat.setText( String.valueOf( data.get( position ).getTongTien()) );
        String imgSrc = data.get( position ).getTourModel().getHinhAnh();
        Picasso.get().load( imgSrc ).into( holder.img_tourDaDat );
    }

    @Override
    public int getItemCount() {
      return  data.size();
    }

    private ArrayList<TourDaChonModel> data;
    private Context context;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView img_tourDaDat;
        TextView tv_giaTourDaDat;
        TextView tv_tongTienTourDaDat;
        TextView tv_soNguoi;

        public RecyclerViewHolder(@NonNull View itemView) {
            super( itemView );
            tv_soNguoi=itemView.findViewById( R.id.gio_hang_row_tv_SLNguoi );
            img_tourDaDat=itemView.findViewById( R.id.gio_hang_row_img_TourDaDat );
            tv_tongTienTourDaDat=itemView.findViewById( R.id.gio_hang_row_tv_ThanhTien );
            tv_giaTourDaDat=itemView.findViewById( R.id.gio_hang_row_tv_GiaTour );
        }
    }
}
