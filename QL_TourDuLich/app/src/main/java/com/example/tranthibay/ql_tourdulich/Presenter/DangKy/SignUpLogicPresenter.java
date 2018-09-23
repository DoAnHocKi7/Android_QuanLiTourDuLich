package com.example.tranthibay.ql_tourdulich.Presenter.DangKy;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.tranthibay.ql_tourdulich.Constants.DangNhapConstants;
import com.example.tranthibay.ql_tourdulich.Model.DangKy.SignUpModel;
import com.example.tranthibay.ql_tourdulich.Model.DangNhap.LoginModel;
import com.example.tranthibay.ql_tourdulich.Services.TaiKhoanServices;
import com.example.tranthibay.ql_tourdulich.Services.VolleyCallback;
import com.example.tranthibay.ql_tourdulich.View.DangKy.SignUpView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignUpLogicPresenter implements SignUpImplementPresenter {
    private Context context;
    public boolean isAlready;
    private SignUpView signUpView;

    public SignUpLogicPresenter(Context context, SignUpView signUpView) {
        this.context = context;
        this.isAlready = isAlready;
        this.signUpView = signUpView;
    }

    @Override
    public void xuLyDangKy(SignUpModel model) {
//        if (!kiemTraTK( model.getUserName() )) {
//            TaiKhoanServices services = new TaiKhoanServices( context );
//            services.insertTaiKhoan( model );
//            signUpView.dangKyThanhCong();
//        } else {
//            signUpView.dangKyThatBai();
//        }
        TaiKhoanServices services = new TaiKhoanServices( context );
        try {
            services.selectTaiKhoan( new VolleyCallback() {
                @Override
                public void onSuccess(JSONArray response) {

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject item = response.getJSONObject( i );
                            if (item.getString( DangNhapConstants.Username ).equals( model.getUserName() )) {
                                signUpView.dangKyThatBai();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    services.insertTaiKhoan( model );
                    signUpView.dangKyThanhCong();
                }
            } );
        } catch (JSONException e) {
            Toast.makeText( context, "Kiểm tra lại kết nối", Toast.LENGTH_SHORT ).show();
            e.printStackTrace();
        }
    }

    @Override
    public boolean kiemTraTK(final String userName) {
        isAlready = false;
        TaiKhoanServices services = new TaiKhoanServices( context );
        try {
            services.selectTaiKhoan( new VolleyCallback() {
                @Override
                public void onSuccess(JSONArray response) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject item = response.getJSONObject( i );
                            if (item.getString( DangNhapConstants.Username ).equals( userName )) {
                                isAlready = true;
                                break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.i( "Volleys", "Result" );
                }
            } );
        } catch (JSONException e) {
            Toast.makeText( context, "Kiểm ta lại kết nối", Toast.LENGTH_SHORT ).show();
            e.printStackTrace();
        }
        return isAlready;
    }
}
