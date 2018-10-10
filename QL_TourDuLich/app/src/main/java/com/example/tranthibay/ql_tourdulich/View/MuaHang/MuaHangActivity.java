package com.example.tranthibay.ql_tourdulich.View.MuaHang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tranthibay.ql_tourdulich.Constants.TourConstants;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;
import com.example.tranthibay.ql_tourdulich.R;

import java.util.concurrent.TimeUnit;

public class MuaHangActivity extends AppCompatActivity implements MuaHangView {
    private TourModel tour;

    Toolbar toolbar;
    private TextView tv_diaDiem;
    private TextView tv_gia;
    private TextView tv_khachSan;
    private TextView tv_giaKS;
    private TextView tv_diaDiemKS;
    private TextView tv_tGianTour;
    private EditText edt_soNguoi;

    private Button btn_themVaoGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mua_hang );
        tour = layThongTinTour();
        anhXa();
        loadTourDuocChon();
    }

    private TourModel layThongTinTour() {
        Intent intent = getIntent();
        TourModel tour = (TourModel) intent.getSerializableExtra( TourConstants.PASSEDTOUR );
        return tour;
    }

    private void anhXa() {
        toolbar = (Toolbar) findViewById( R.id.mua_hang_toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        tv_diaDiem = (TextView) findViewById( R.id.mua_hang_tv_DiaDiem );
        tv_gia = (TextView) findViewById( R.id.mua_hang_tv_Gia );
        tv_khachSan = (TextView) findViewById( R.id.mua_hang_tv_KhachSan );
        tv_giaKS = (TextView) findViewById( R.id.mua_hang_tv_GiaKhachSan );
        tv_diaDiemKS = (TextView) findViewById( R.id.mua_hang_tv_DiaChiKS );
        tv_tGianTour = (TextView) findViewById( R.id.mua_hang_tv_TGTour );
        edt_soNguoi = (EditText) findViewById( R.id.mua_hang_edt_SoNguoi );

        btn_themVaoGioHang = (Button) findViewById( R.id.mua_hang_btn_datTour );

    }

    private void loadTourDuocChon() {
        tv_diaDiem.setText( "Địa điểm: " + tour.getDiaDiem() );
        tv_diaDiemKS.setText( tour.getKhachSan().getDiaChiKS() );
        long diff = tour.getNgayDen().getTime() - tour.getNgayDi().getTime();
        tv_tGianTour.setText( String.valueOf( TimeUnit.DAYS.convert( diff, TimeUnit.MILLISECONDS ) + " ngày" ) );
        tv_khachSan.setText( tour.getKhachSan().getTenKhachSan() );
        tv_gia.setText( "Giá: " + tour.getGia() );
        tv_giaKS.setText( String.valueOf( tour.getKhachSan().getGiaTienKS() ) );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
            }
            break;
            case R.id.fragment_chit_tiet_tour_item_giohang: {

            }
            break;
        }
        return true;
    }

    @Override
    public void datTourNgayThanhCong() {

    }
}
