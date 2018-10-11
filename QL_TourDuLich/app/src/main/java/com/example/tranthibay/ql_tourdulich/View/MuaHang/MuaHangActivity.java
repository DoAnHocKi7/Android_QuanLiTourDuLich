package com.example.tranthibay.ql_tourdulich.View.MuaHang;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tranthibay.ql_tourdulich.Constants.TourConstants;
import com.example.tranthibay.ql_tourdulich.Model.MuaHang.KhachHangModel;
import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaChonModel;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;
import com.example.tranthibay.ql_tourdulich.Presenter.MuaHang.MuaHangLogicPresenter;
import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.View.GioHang.GioHangActivity;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;

import java.util.concurrent.TimeUnit;

public class MuaHangActivity extends AppCompatActivity implements MuaHangView {
    private TourModel tour;
    private KhachHangModel khachHangModel;

    Toolbar toolbar;
    private TextView tv_diaDiem;
    private TextView tv_gia;
    private TextView tv_khachSan;
    private TextView tv_giaKS;
    private TextView tv_diaDiemKS;
    private TextView tv_tGianTour;
    private EditText edt_soNguoi;

    /*-------------Get Khach Hang---------------*/
    private EditText edt_hoKH;
    private EditText edt_tenKH;
    private EditText edt_sdtKH;
    private EditText edt_emailKH;
    private EditText edt_diaChiKH;
    private RadioGroup rdoGroup_gTinhKH;
    /*-------------------------*/
    private Button btn_DatTourNgay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mua_hang );
        tour = layThongTinTour();
        anhXa();
        loadTourDuocChon();
        datTourNgay();
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

        /*-------------Get Khach Hang---------------*/
        edt_hoKH = (EditText) findViewById( R.id.mua_hang_edt_Ho );
        edt_tenKH = (EditText) findViewById( R.id.mua_hang_edt_Ten );
        edt_sdtKH = (EditText) findViewById( R.id.mua_hang_edt_sdt );
        edt_emailKH = (EditText) findViewById( R.id.mua_hang_edt_email );
        edt_diaChiKH = (EditText) findViewById( R.id.mua_hang_edt_DiaChi );
        rdoGroup_gTinhKH = (RadioGroup) findViewById( R.id.mua_hang_grRado_gioiTinh );
        /*-------------------------*/
        btn_DatTourNgay = (Button) findViewById( R.id.mua_hang_btn_datTour );

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.chi_tiet_tour_toolbar_menu, menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
            }
            break;
            case R.id.tool_bar_item_gio_hang: {
                Intent intent = new Intent( this, GioHangActivity.class );
                startActivity( intent );
            }
            break;
            case R.id.tool_bar_item_home: {
                Intent intent = new Intent( this, MainActivity.class );
                startActivity( intent );
            }
            break;
        }
        return true;
    }

    private void datTourNgay() {
        btn_DatTourNgay.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MuaHangLogicPresenter logicPresenter = new MuaHangLogicPresenter( MuaHangActivity.this, MuaHangActivity.this );
                logicPresenter.thanhToanNgayMotTour( new TourDaChonModel(), new KhachHangModel() );
            }
        } );
    }

    private void layThongTinKhachHang() {
        int checkedID = rdoGroup_gTinhKH.getCheckedRadioButtonId();
        RadioButton rdo_GioiTinh = (RadioButton) findViewById( checkedID );
        this.khachHangModel=new KhachHangModel()
    }

    @Override
    public void datTourNgayThanhCong() {

    }
}
