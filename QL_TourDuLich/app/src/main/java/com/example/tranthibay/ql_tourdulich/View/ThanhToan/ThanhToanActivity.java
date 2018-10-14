package com.example.tranthibay.ql_tourdulich.View.ThanhToan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.tranthibay.ql_tourdulich.Model.MuaHang.KhachHangModel;
import com.example.tranthibay.ql_tourdulich.Presenter.ThanhToan.ThanhToanLogicPresenter;
import com.example.tranthibay.ql_tourdulich.R;

public class ThanhToanActivity extends AppCompatActivity implements ThanhToanView{
    /*-------------Get Khach Hang---------------*/
    private EditText edt_hoKH;
    private EditText edt_tenKH;
    private EditText edt_sdtKH;
    private EditText edt_emailKH;
    private EditText edt_diaChiKH;
    private RadioGroup rdoGroup_gTinhKH;
    /*----------------------------*/
    private Button btn_ThanhToanGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_thanh_toan );
        anhXa();
        thanhToanGioHang();
    }

    private void anhXa() {
        /*-------------Get Khach Hang---------------*/
        edt_hoKH = (EditText) findViewById( R.id.thanh_toan_edt_Ho );
        edt_tenKH = (EditText) findViewById( R.id.thanh_toan_edt_Ten );
        edt_sdtKH = (EditText) findViewById( R.id.thanh_toan_edt_sdt );
        edt_emailKH = (EditText) findViewById( R.id.thanh_toan_edt_email );
        edt_diaChiKH = (EditText) findViewById( R.id.thanh_toan_edt_DiaChi );
        rdoGroup_gTinhKH = (RadioGroup) findViewById( R.id.thanh_toan_grRado_gioiTinh );
        /*-------------------------*/
        btn_ThanhToanGioHang = (Button) findViewById( R.id.thanh_toan_btn_datTour );
    }

    private void thanhToanGioHang(){
        btn_ThanhToanGioHang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThanhToanLogicPresenter thanhToanLogicPresenter=new ThanhToanLogicPresenter( ThanhToanActivity.this,ThanhToanActivity.this );
                thanhToanLogicPresenter.thanhToanGioHang( new KhachHangModel( "1","1","1","1","1","1","1" ) );
            }
        } );
    }

    @Override
    public void thanhToanGioHangThanhCong() {

    }

    @Override
    public void thanhToanGioHangThatBai() {

    }

    @Override
    public void xoaTourTrongGioThanhCong() {

    }
}
