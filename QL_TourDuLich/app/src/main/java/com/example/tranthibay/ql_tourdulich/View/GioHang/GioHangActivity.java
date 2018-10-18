package com.example.tranthibay.ql_tourdulich.View.GioHang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tranthibay.ql_tourdulich.Adapter.TourDaDatReCyclerAdapter;
import com.example.tranthibay.ql_tourdulich.Presenter.GioHang.GioHangLogicPresenter;
import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;
import com.example.tranthibay.ql_tourdulich.View.ThanhToan.ThanhToanActivity;

public class GioHangActivity extends AppCompatActivity {
    private RecyclerView recyclerView_gioHang;
    private TextView tv_tongTien;

    private Button btn_thanhToanLuon;
    private Button btn_tiepTucMuaHang;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gio_hang );
        anhXa();
        loadGioHang();
        thanhToan();
        tiepTucMuahang();
    }

    private void loadGioHang() {
        TourDaDatReCyclerAdapter mRcvAdapter = new TourDaDatReCyclerAdapter( MainActivity.GioHang, this );
        GridLayoutManager layoutManager = new GridLayoutManager( this, 1 );
        layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        recyclerView_gioHang.setLayoutManager( layoutManager );
        recyclerView_gioHang.setAdapter( mRcvAdapter );
        tv_tongTien.setText( String.valueOf( new GioHangLogicPresenter().tinhTongTien( MainActivity.GioHang ) ) );
    }

    private void thanhToan() {
        btn_thanhToanLuon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GioHangActivity.this, ThanhToanActivity.class );
                startActivity( intent );
            }
        } );
    }

    private void tiepTucMuahang() {
        btn_tiepTucMuaHang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( GioHangActivity.this,MainActivity.class );
                startActivity( intent );
            }
        } );
    }

    private void anhXa() {
        recyclerView_gioHang = (RecyclerView) findViewById( R.id.gio_hang_recycle_view_gio_hang );
        tv_tongTien = (TextView) findViewById( R.id.gio_hang_TongTien );
        btn_thanhToanLuon = (Button) findViewById( R.id.gio_hang_btn_ThanhToanLuon );
        btn_tiepTucMuaHang = (Button) findViewById( R.id.gio_hang_btn_TiepTucMuaHang );
    }
}
