package com.example.tranthibay.ql_tourdulich.View.ChiTietTour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tranthibay.ql_tourdulich.Constants.TourConstants;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;
import com.example.tranthibay.ql_tourdulich.R;
import com.squareup.picasso.Picasso;

public class ChiTietTourActivity extends AppCompatActivity implements ChiTietTourView {
    private Toolbar toolbar;
    //private TourModel tourModel;
    private TextView tv_diaDiem;
    private TextView tv_gia;
    private TextView tv_tenTour;
    private TextView tv_khachSan;
    private TextView tv_moTaTour;
    private ImageView img_Tour;
    private TextView tv_GiaKS;
    private TextView tv_diaChi;
    private TextView tv_dChiKS;
    private Button btn_datTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_chi_tiet_tour );
        anhXa();
        TourModel tour = layThongTinTour();
        hienThiChiTietTour( tour );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.chi_tiet_tour_toolbar_menu, menu );
        return super.onCreateOptionsMenu( menu );
    }

    private void anhXa() {
        toolbar = (Toolbar) findViewById( R.id.fragment_chit_tiet_tour_toolbar );
        tv_diaDiem = (TextView) findViewById( R.id.chi_tiet_tour_tv_DiaDiem );
        tv_gia = (TextView) findViewById( R.id.chi_tiet_tour_tv_Gia );
        tv_tenTour = (TextView) findViewById( R.id.chi_tiet_tour_tv_TenTour );
        tv_khachSan = (TextView) findViewById( R.id.chi_tiet_tour_tv_KhachSan );
        tv_moTaTour = (TextView) findViewById( R.id.chi_tiet_tour_tv_MoTa );
        btn_datTour = (Button) findViewById( R.id.chi_tiet_tour_btn_DatTour );
        img_Tour = (ImageView) findViewById( R.id.chi_tiet_tour_imgTour );
        tv_dChiKS = (TextView) findViewById( R.id.chi_tiet_tour_tv_DiaChiKS );
        tv_GiaKS = (TextView) findViewById( R.id.chi_tiet_tour_tv_GiaKhachSan );
        tv_diaChi = (TextView) findViewById( R.id.chi_tiet_tour_tv_DiaDiem );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
    }

    private TourModel layThongTinTour() {
        Intent intent = getIntent();
        TourModel tour = (TourModel) intent.getSerializableExtra( TourConstants.PASSEDTOUR );
        return tour;
    }

    private void hienThiChiTietTour(TourModel tour) {
        tv_diaDiem.setText( tour.getDiaDiem() );
        tv_gia.setText( String.valueOf( tour.getGia() ) );
        tv_tenTour.setText( tour.getTenTour() );
        tv_khachSan.setText( tour.getKhachSan().getTenKhachSan() );
        tv_moTaTour.setText( tour.getMota() );
        tv_GiaKS.setText( String.valueOf( tour.getKhachSan().getGiaTienKS() ) );
        tv_dChiKS.setText( tour.getKhachSan().getDiaChiKS() );
        tv_diaChi.setText( tour.getDiaDiem() );
        Picasso.get().load( tour.getHinhAnh() ).into( img_Tour );
    }

    @Override
    public void showTourLienQuan() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
            }
            break;

        }
        return true;
    }
}
