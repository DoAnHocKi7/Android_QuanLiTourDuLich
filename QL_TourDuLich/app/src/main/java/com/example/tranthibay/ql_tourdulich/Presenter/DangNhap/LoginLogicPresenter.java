package com.example.tranthibay.ql_tourdulich.Presenter.DangNhap;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tranthibay.ql_tourdulich.Constants.DangNhapConstants;
import com.example.tranthibay.ql_tourdulich.Constants.PHPConnectionConstants;
import com.example.tranthibay.ql_tourdulich.Constants.TourConstants;
import com.example.tranthibay.ql_tourdulich.Model.DangNhap.LoginModel;
import com.example.tranthibay.ql_tourdulich.Model.MuaHang.KhachHangModel;
import com.example.tranthibay.ql_tourdulich.Services.TaiKhoanServices;
import com.example.tranthibay.ql_tourdulich.Services.VolleyCallback;
import com.example.tranthibay.ql_tourdulich.View.DangNhap.LoginView;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginLogicPresenter implements LoginImlementPresenter {
    LoginView loginView;

    public LoginLogicPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void xuLiDangNhap(final String userName, final String passWord, Context context) {
//        TaiKhoanServices services = new TaiKhoanServices( context );
//        try {
//            services.selectTaiKhoan( new VolleyCallback() {
//                @Override
//                public void onSuccess(JSONArray response) {
//                    ArrayList<LoginModel> loginModels = new ArrayList<>();
//                    if (response != null) {
//                        JSONArray jsonArray = new JSONArray();
//                        jsonArray = (JSONArray) response;
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject item = new JSONObject();
//                            try {
//                                item = jsonArray.getJSONObject( i );
//                                loginModels.add( new LoginModel( item.getString( "username" ), item.getString( "Password" ) ) );
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                    boolean isExist=loginModels.stream().filter( m->(m.getPassWord()).equals( passWord )&&m.getUserName().equals( userName ) ).count()==1;
//                    if (isExist) {
//                        ghiNhoDangNhap( context,userName );
//                        loginView.dangNhapThanhCong(userName);
//                    } else {
//                        loginView.dangNhapThatBai();
//                    }
//                }
//            } );
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        RequestQueue requestQueue = Volley.newRequestQueue( context );
        String url = PHPConnectionConstants.HOST + "/TourDuLichAPI/TaiKhoanAPI/DangNhap/selectTaiKhoan.php";


        StringRequest request = new StringRequest( Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals( "1" )) {
                    ghiNhoDangNhap( context, userName );
                    loginView.dangNhapThanhCong( userName );
                } else {
                    loginView.dangNhapThatBai();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "Error", "Có lỗi khi load dữ liệu" );
                loginView.dangNhapThatBai();
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put( "Username", userName );
                params.put( "Password", passWord );
                return params;
            }
        };
        requestQueue.add( request );
    }

    @Override
    public void ghiNhoDangNhap(Context context, String userName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences( context );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( DangNhapConstants.Username, userName );
        editor.commit();
    }

    @Override
    public String layUserDaDangNhap(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences( context );
        return sharedPreferences.getString( DangNhapConstants.Username, null );
    }

    //@Override
    public static void xoaUserDaDangNhap(Context context) {
        MainActivity.Username=null;
        MainActivity.KhachHangModel=null;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences( context );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public static void khoiTaoThongTinKhachHang(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences( context );
        MainActivity.Username = sharedPreferences.getString( DangNhapConstants.Username, null );

        //if (MainActivity.Username != null || !MainActivity.Username.isEmpty()) {//kiem tra da luu dang nhap chua
            TaiKhoanServices taiKhoanServices = new TaiKhoanServices( context );
            taiKhoanServices.layThongTinTaiKhoan( MainActivity.Username, context, new VolleyCallback() {
                @Override
                public void onSuccess(JSONArray result) {
                    try {
                        JSONObject item = result.getJSONObject( 0 );
                        String maKh = item.getString( "Ma_KH" );
                        String tenKH = item.getString( "TenKH" );
                        String sdtKH = item.getString( "Phone" );
                        String emailKH = item.getString( "Email" );
                        String diaChiKH = item.getString( "DiaChi" );
                        String gTinhKH = item.getString( "SexKH" );

                        MainActivity.KhachHangModel = new KhachHangModel( TourConstants.LOAIKHMACDINH, tenKH, gTinhKH, emailKH, sdtKH, diaChiKH );
                        MainActivity.KhachHangModel.setMaKH( maKh );

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } );
        //}

    }
}
