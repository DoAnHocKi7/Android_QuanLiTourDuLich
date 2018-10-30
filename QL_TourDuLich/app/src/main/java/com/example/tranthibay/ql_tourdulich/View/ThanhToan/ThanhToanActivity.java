package com.example.tranthibay.ql_tourdulich.View.ThanhToan;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tranthibay.ql_tourdulich.Model.MuaHang.KhachHangModel;
import com.example.tranthibay.ql_tourdulich.Presenter.ThanhToan.ThanhToanLogicPresenter;
import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;

import java.util.ArrayList;

public class ThanhToanActivity extends AppCompatActivity implements ThanhToanView {
    /*-------------Get Khach Hang---------------*/
    private EditText edt_hoKH;
    private EditText edt_tenKH;
    private EditText edt_sdtKH;
    private EditText edt_emailKH;
    private EditText edt_diaChiKH;
    private RadioGroup rdoGroup_gTinhKH;
    private RadioButton rdo_gioiTinh;
    /*----------------------------*/
    private Button btn_ThanhToanGioHang;
    /*--------------------*/
    private TextView tv_tenKH;
    private TextView tv_sdtKH;
    private TextView tv_emailKH;
    private TextView tv_diaChiKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (MainActivity.KhachHangModel == null) {
            setContentView( R.layout.activity_thanh_toan );
            anhXa();
        } else {
            setContentView( R.layout.activity_thanh_toan_gio_hang_da_dang_nhap );
            anhXaDaDangNhap();
            showKhachHangDaDangNhap();
        }
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
        int checked = rdoGroup_gTinhKH.getCheckedRadioButtonId();
        rdo_gioiTinh = (RadioButton) findViewById( checked );
        /*-------------------------*/
        btn_ThanhToanGioHang = (Button) findViewById( R.id.thanh_toan_btn_datTour );
    }

    private void anhXaDaDangNhap() {
        tv_tenKH = (TextView) findViewById( R.id.thanh_toan_da_dang_nhap_tv_HoTen );
        tv_diaChiKH = (TextView) findViewById( R.id.thanh_toan_da_dang_nhap_tv_DiaChi );
        tv_emailKH = (TextView) findViewById( R.id.thanh_toan_da_dang_nhap_tv_Email );
        tv_sdtKH = (TextView) findViewById( R.id.thanh_toan_da_dang_nhap_tv_Sdt );
        btn_ThanhToanGioHang = (Button) findViewById( R.id.thanh_toan_da_dang_nhap_btn_datTour );

    }

    private void showKhachHangDaDangNhap() {
        tv_tenKH.setText( "Tên khách hàng: " + MainActivity.KhachHangModel.getTenKH() );
        tv_emailKH.setText( "Email: " + MainActivity.KhachHangModel.getEmail() );
        tv_sdtKH.setText( "Số điện thoại: " + MainActivity.KhachHangModel.getSdt() );
        tv_diaChiKH.setText( "Địa chỉ: " + MainActivity.KhachHangModel.getDiaChi() );
    }

    private void thanhToanGioHang() {
        btn_ThanhToanGioHang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenKH, gTinhKH, emailKH, sdtKH, dChiKH;
                if (MainActivity.KhachHangModel == null) {
                    tenKH = edt_hoKH.getText() + " " + edt_tenKH.getText();
                    gTinhKH = rdo_gioiTinh.getText().toString();
                    emailKH = edt_emailKH.getText().toString();
                    dChiKH = edt_diaChiKH.getText().toString();
                    sdtKH = edt_sdtKH.getText().toString();

                } else {
                    tenKH = MainActivity.KhachHangModel.getTenKH();
                    gTinhKH = MainActivity.KhachHangModel.getGioiTinh();
                    emailKH = MainActivity.KhachHangModel.getEmail();
                    dChiKH = MainActivity.KhachHangModel.getDiaChi();
                    sdtKH = MainActivity.KhachHangModel.getSdt();
                }
                ThanhToanLogicPresenter thanhToanLogicPresenter = new ThanhToanLogicPresenter( ThanhToanActivity.this, ThanhToanActivity.this );
                thanhToanLogicPresenter.thanhToanGioHang( new KhachHangModel( "4", tenKH, gTinhKH, emailKH, sdtKH, dChiKH ) );
            }
        } );
    }

    @Override
    public void thanhToanGioHangThanhCong(String maHopDong) {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( "Đặt hàng thành công!" );
        builder.setMessage( "Hãy nhớ mã mua hàng của bạn: " + maHopDong );
        builder.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent( ThanhToanActivity.this, MainActivity.class );
                startActivity( intent );
            }
        } );

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        MainActivity.GioHang=new ArrayList<>();


    }

    @Override
    public void thanhToanGioHangThatBai() {

    }

    @Override
    public void xoaTourTrongGioThanhCong() {

    }
}
