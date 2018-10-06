package com.example.tranthibay.ql_tourdulich.View.ChiTietTour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.tranthibay.ql_tourdulich.Constants.TourConstants;
import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaDatModel;
import com.example.tranthibay.ql_tourdulich.R;

public class ChiTietTourActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_chi_tiet_tour );
        anhXa();
        layThongTinTour();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.chi_tiet_tour_toolbar_menu, menu );
        return super.onCreateOptionsMenu( menu );
    }

    private void anhXa() {
        toolbar = (Toolbar) findViewById( R.id.fragment_chit_tiet_tour_toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
    }

    void layThongTinTour() {
        Intent intent = getIntent();
        TourDaDatModel tour = (TourDaDatModel) intent.getSerializableExtra( TourConstants.PASSEDTOUR );
    }
}
