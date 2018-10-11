package com.example.tranthibay.ql_tourdulich.View.GioHang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tranthibay.ql_tourdulich.Adapter.TourDaDatReCyclerAdapter;
import com.example.tranthibay.ql_tourdulich.Adapter.TourRecyclerViewAdapter;
import com.example.tranthibay.ql_tourdulich.Constants.TourConstants;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;
import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;

public class GioHangActivity extends AppCompatActivity {
  private  RecyclerView recyclerView_gioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gio_hang );
        anhXa();
        loadGioHang();
    }
    private void loadGioHang(){
        TourDaDatReCyclerAdapter mRcvAdapter = new TourDaDatReCyclerAdapter( MainActivity.GioHang, this );
        GridLayoutManager layoutManager = new GridLayoutManager( this, 1 );
        layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        recyclerView_gioHang.setLayoutManager( layoutManager );
        recyclerView_gioHang.setAdapter( mRcvAdapter );
    }
    private void anhXa(){
        recyclerView_gioHang=(RecyclerView)findViewById( R.id. gio_hang_recycle_view_gio_hang);
    }
}
