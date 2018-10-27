package com.example.tranthibay.ql_tourdulich.View.DangNhap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tranthibay.ql_tourdulich.Constants.DangNhapConstants;
import com.example.tranthibay.ql_tourdulich.Model.DangNhap.LoginModel;
import com.example.tranthibay.ql_tourdulich.Presenter.DangNhap.LoginLogicPresenter;
import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.Services.InternalStorageHelper;
import com.example.tranthibay.ql_tourdulich.View.DangKy.SignUpActivity;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private EditText edt_Username;
    private EditText edt_Password;
    private Button btn_Dangnhap;
    private Button btn_Dangky;
    private Button btn_boQua;
    public String userDaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dang_nhap );

        /*Kiem Tra de tu dong dang nhap*/
        kiemTraDangNhap();
        /*---------------------------*/
        anhXa();

        boQuaDangNhap();
        btn_Dangky.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), SignUpActivity.class );
                startActivity( intent );

            }
        } );

        btn_Dangnhap.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edt_Username.getText().toString();
                String password = edt_Password.getText().toString();
                if (!userName.isEmpty() && !password.isEmpty()) {
                    LoginLogicPresenter loginLogic = new LoginLogicPresenter( LoginActivity.this );
                    loginLogic.xuLiDangNhap( userName, password, LoginActivity.this );
//                    Intent intent = new Intent( LoginActivity.this, MainActivity.class );
//                    intent.putExtra( DangNhapConstants.Username, userDaLogin );
//                    startActivity( intent );
                } else {
                    Toast.makeText( LoginActivity.this, "Hãy điền tất cả các trường!", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }

    private void boQuaDangNhap() {
        btn_boQua.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivity.this, MainActivity.class );
                startActivity( intent );
            }
        } );
    }

    private void anhXa() {
        edt_Username = (EditText) findViewById( R.id.login_et_userName );
        edt_Password = (EditText) findViewById( R.id.login_et_password );
        btn_Dangnhap = (Button) findViewById( R.id.login_btn_login );
        btn_boQua = (Button) findViewById( R.id.login_btn_boQua );
        btn_Dangky = (Button) findViewById( R.id.login_btn_toSignUp );
    }

    private boolean kiemTraDangNhap() {
        LoginLogicPresenter logicPresenter = new LoginLogicPresenter( this );
        userDaLogin = logicPresenter.layUserDaDangNhap( this );
        if (userDaLogin != null && !userDaLogin.isEmpty()) {
            Intent intent = new Intent( this, MainActivity.class );
            intent.putExtra( DangNhapConstants.Username, userDaLogin );
            startActivity( intent );
            return true;
        }
        return false;
    }

    @Override
    public void dangNhapThanhCong(String saveUsername) {
        //chuyen den Activity nao do voi username
        Intent intent = new Intent( this, MainActivity.class );
        intent.putExtra( DangNhapConstants.Username, userDaLogin );
        startActivity( intent );
        Toast.makeText( this, "Đăng nhập thành công", Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void dangNhapThatBai() {
        Toast.makeText( this, "Đăng nhập thất bại", Toast.LENGTH_SHORT ).show();
    }

}
