package com.example.tranthibay.ql_tourdulich.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaChonModel;
import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TourDaDatReCyclerAdapter extends RecyclerView.Adapter<TourDaDatReCyclerAdapter.RecyclerViewHolder> {
    private ArrayList<TourDaChonModel> data;
    private Context context;

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
        holder.tv_giaTourDaDat.setText( "Giá tour: " + String.valueOf( data.get( position ).getTourModel().getGia() ) );
        holder.tv_thanhTienGioHang.setText( "Tổng tiền: " + String.valueOf( data.get( position ).getTongTien() ) );
        String imgSrc = data.get( position ).getTourModel().getHinhAnh();
        Picasso.get().load( imgSrc ).into( holder.img_tourDaDat );
        holder.img_delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove( position );
                notifyDataSetChanged();
                Toast.makeText( context, "Đã xóa Tour này khỏi giỏ hàng", Toast.LENGTH_SHORT ).show();
                //MainActivity.GioHang.remove( position );
            }
        } );
        holder.btn_themNguoi.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themNguoi( holder, position );
            }
        } );

        holder.btn_truNguoi.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                truNguoi( holder, position );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void truNguoi(RecyclerViewHolder holder, int position) {
        int soNguoi = data.get( position ).getSoNguoi() - 1;
        double tongTien = soNguoi * data.get( position ).getTourModel().getGia();
        MainActivity.GioHang.get( position ).setSoNguoi( Integer.valueOf( soNguoi ) );
        MainActivity.GioHang.get( position ).setTongTien(tongTien);
        data.get( position ).setSoNguoi( soNguoi );
        data.get( position ).setTongTien( tongTien );
        holder.tv_thanhTienGioHang.setText( String.valueOf( tongTien ) );
        holder.tv_soNguoi.setText( String.valueOf( soNguoi ) );
    }

    private void themNguoi(RecyclerViewHolder holder, int position) {
        int soNguoi = data.get( position ).getSoNguoi() + 1;
        double tongTien = soNguoi * data.get( position ).getTourModel().getGia();
        MainActivity.GioHang.get( position ).setSoNguoi( Integer.valueOf( soNguoi ) );
        MainActivity.GioHang.get( position ).setTongTien(tongTien);
        data.get( position ).setSoNguoi( soNguoi );
        data.get( position ).setTongTien( tongTien );
        holder.tv_thanhTienGioHang.setText( String.valueOf( tongTien ) );
        holder.tv_soNguoi.setText( String.valueOf( soNguoi ) );
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView img_tourDaDat;
        TextView tv_giaTourDaDat;
        TextView tv_thanhTienGioHang;
        TextView tv_soNguoi;
        ImageView img_delete;

        Button btn_themNguoi;
        Button btn_truNguoi;

        public RecyclerViewHolder(@NonNull View itemView) {
            super( itemView );
            btn_truNguoi = itemView.findViewById( R.id.gio_hang_btn_TruNguoi );
            btn_themNguoi = itemView.findViewById( R.id.gio_hang_btn_ThemNguoi );
            img_delete = itemView.findViewById( R.id.gio_hang_img_XoaTour );
            tv_soNguoi = itemView.findViewById( R.id.gio_hang_row_tv_SLNguoi );
            img_tourDaDat = itemView.findViewById( R.id.gio_hang_row_img_TourDaDat );
            tv_thanhTienGioHang = itemView.findViewById( R.id.gio_hang_row_tv_ThanhTien );
            tv_giaTourDaDat = itemView.findViewById( R.id.gio_hang_row_tv_GiaTour );
        }
    }
}
