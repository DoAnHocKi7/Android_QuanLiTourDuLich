package com.example.tranthibay.ql_tourdulich.View.DangKy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tranthibay.ql_tourdulich.Model.DangKy.SignUpModel;
import com.example.tranthibay.ql_tourdulich.Presenter.DangKy.SignUpLogicPresenter;
import com.example.tranthibay.ql_tourdulich.R;

public class SignUpActivity extends AppCompatActivity implements SignUpView {
    private Button btn_signUp;
    private EditText edt_Ten;
    private EditText edt_Ho;
    private EditText edt_Username;
    private EditText edt_Sdt;
    private EditText edt_DiaChi;
    private EditText edt_Password;
    private EditText edt_Repassword;
    private RadioGroup rdoGrp_gGioiTinh;
    private RadioButton rdo_GioiTinh;
    private EditText edt_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dang_ky );
        /*------------Ánh xạ-------------------*/
        btn_signUp = (Button) findViewById( R.id.dangky_btn_signUp );
        edt_Ho = (EditText) findViewById( R.id.dangky_edt_Ho );
        edt_Ten = (EditText) findViewById( R.id.dangky_edt_Ten );
        edt_Username = (EditText) findViewById( R.id.dangky_edt_username );
        edt_Sdt = (EditText) findViewById( R.id.dangky_edt_sdt );
        edt_DiaChi = (EditText) findViewById( R.id.dangky_edt_DiaChi );
        edt_Password = (EditText) findViewById( R.id.dangky_edt_Password );
        edt_Repassword = (EditText) findViewById( R.id.dangky_edt_Repassword );
        edt_email = (EditText) findViewById( R.id.dangky_edt_email );
        rdoGrp_gGioiTinh = (RadioGroup) findViewById( R.id.dangky_grRado_gioiTinh );

        /*----------------Xử lý đăng ký---------------------*/
        btn_signUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedID = rdoGrp_gGioiTinh.getCheckedRadioButtonId();
                rdo_GioiTinh = (RadioButton) findViewById( checkedID );
                String email = edt_email.getText().toString();
                String ho = edt_Ho.getText().toString();
                String ten = edt_Ten.getText().toString();
                String username = edt_Username.getText().toString();
                String password = edt_Password.getText().toString();
                String repassword = edt_Repassword.getText().toString();
                String sdt = edt_Sdt.getText().toString();
                String diachi = edt_DiaChi.getText().toString();
                String gioiTinh = rdo_GioiTinh.getText().toString();
                if (!(ho.isEmpty() || ten.isEmpty() || username.isEmpty() || password.isEmpty() || repassword.isEmpty()
                        || sdt.isEmpty() || diachi.isEmpty())) {
                    if (password.equals( repassword )) {
                        SignUpModel signUpModel = new SignUpModel( username, password, sdt, gioiTinh,
                                diachi, "1", email, ho + ten );
                        SignUpLogicPresenter signUpLogicPresenter = new SignUpLogicPresenter( SignUpActivity.this, SignUpActivity.this );
                        signUpLogicPresenter.xuLyDangKy( signUpModel );
                    }
                }

            }
        } );
        /*--------------------------------------------------*/


        /*--------------------------------------------------*/


    }

    public SignUpActivity() {
        super();
    }

    @Override
    public void dangKyThanhCong() {
        Toast.makeText( this, "Đã đăng kí thành công", Toast.LENGTH_LONG ).show();
    }

    @Override
    public void dangKyThatBai() {
        Toast.makeText( this, "Tài khoản đã được dùng", Toast.LENGTH_LONG ).show();
    }
}
