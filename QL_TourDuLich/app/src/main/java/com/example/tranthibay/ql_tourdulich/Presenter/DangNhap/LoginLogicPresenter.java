package com.example.tranthibay.ql_tourdulich.Presenter.DangNhap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.tranthibay.ql_tourdulich.Constants.DangNhapConstants;
import com.example.tranthibay.ql_tourdulich.Model.DangNhap.LoginModel;
import com.example.tranthibay.ql_tourdulich.Services.TaiKhoanServices;
import com.example.tranthibay.ql_tourdulich.Services.VolleyCallback;
import com.example.tranthibay.ql_tourdulich.View.DangNhap.LoginView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginLogicPresenter implements LoginImlementPresenter {
    LoginView loginView;

    public LoginLogicPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void xuLiDangNhap(final String userName, final String passWord, Context context) {
        TaiKhoanServices services = new TaiKhoanServices( context );
        try {
            services.selectTaiKhoan( new VolleyCallback() {
                @Override
                public void onSuccess(JSONArray response) {
                    ArrayList<LoginModel> loginModels = new ArrayList<>();
                    if (response != null) {
                        JSONArray jsonArray = new JSONArray();
                        jsonArray = (JSONArray) response;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject item = new JSONObject();
                            try {
                                item = jsonArray.getJSONObject( i );
                                loginModels.add( new LoginModel( item.getString( "username" ), item.getString( "Password" ) ) );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    boolean isExist=loginModels.stream().filter( m->(m.getPassWord()).equals( passWord )&&m.getUserName().equals( userName ) ).count()==1;
                    if (isExist) {
                        ghiNhoDangNhap( context,userName );
                        loginView.dangNhapThanhCong(userName);
                    } else {
                        loginView.dangNhapThatBai();
                    }
                }
            } );
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void ghiNhoDangNhap(Context context, String userName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences( context );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( DangNhapConstants.Username, userName );
        editor.commit();
    }

    @Override
    public  String layUserDaDangNhap(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences( context );
        return sharedPreferences.getString( DangNhapConstants.Username, null );
    }

    @Override
    public void xoaUserDaDangNhap(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences( context );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
