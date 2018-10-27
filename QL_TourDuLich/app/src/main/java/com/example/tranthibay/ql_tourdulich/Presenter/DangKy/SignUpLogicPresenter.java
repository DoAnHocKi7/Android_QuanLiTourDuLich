package com.example.tranthibay.ql_tourdulich.Presenter.DangKy;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tranthibay.ql_tourdulich.Constants.DangNhapConstants;
import com.example.tranthibay.ql_tourdulich.Constants.PHPConnectionConstants;
import com.example.tranthibay.ql_tourdulich.Model.DangKy.SignUpModel;
import com.example.tranthibay.ql_tourdulich.Model.DangNhap.LoginModel;
import com.example.tranthibay.ql_tourdulich.Services.TaiKhoanServices;
import com.example.tranthibay.ql_tourdulich.Services.VolleyCallback;
import com.example.tranthibay.ql_tourdulich.View.DangKy.SignUpView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SignUpLogicPresenter implements SignUpImplementPresenter {
    private Context context;
    public boolean isAlready;
    private SignUpView signUpView;

    public SignUpLogicPresenter(Context context, SignUpView signUpView) {
        this.context = context;
        this.isAlready = isAlready;
        this.signUpView = signUpView;
    }

//    @Override
//    public void xuLyDangKy(SignUpModel model) {
//
//        TaiKhoanServices services = new TaiKhoanServices( context );
//        try {
//            services.selectTaiKhoan( new VolleyCallback() {
//                @Override
//                public void onSuccess(JSONArray response) {
//
//                    for (int i = 0; i < response.length(); i++) {
//                        try {
//                            JSONObject item = response.getJSONObject( i );
//                            if (item.getString( DangNhapConstants.Username ).equals( model.getUserName() )) {
//                                signUpView.dangKyThatBai();
//                                return;
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    services.insertTaiKhoan( model );
//                    signUpView.dangKyThanhCong();
//                }
//            } );
//        } catch (JSONException e) {
//            Toast.makeText( context, "Kiểm tra lại kết nối", Toast.LENGTH_SHORT ).show();
//            e.printStackTrace();
//        }
//    }

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

    @Override
    public void xuLyDangKy(SignUpModel model) {
        String url = PHPConnectionConstants.HOST + "/TourDuLichAPI/KhachHangAPI/dangKyTKKhachHang.php";

        RequestQueue requestQueue= Volley.newRequestQueue( context );
        StringRequest request=new StringRequest( Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals( "ThanhCong" )){
                    signUpView.dangKyThanhCong();
                    Log.e("Thao tac dang ky","Thanh cong!");
                }else{
                    signUpView.dangKyThatBai();
                    Log.e("Thao tac dang ky","That bai!");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "có lỗi khi load dữ liệu", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put("TenKH",model.getUserName());
                params.put("MatKhauKH",model.getPassword());
                params.put( "EmailKH",model.getEmail() );
                params.put("SdtKH",model.getSdt());
                params.put("GTinhKH",model.getGioiTinh());
                params.put("DiaChiKH",model.getDiaChi());
                return params;
            }
        };
        requestQueue.add( request );
    }
}
