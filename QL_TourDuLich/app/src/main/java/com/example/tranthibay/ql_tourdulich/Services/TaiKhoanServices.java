package com.example.tranthibay.ql_tourdulich.Services;

import android.content.Context;
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
import com.example.tranthibay.ql_tourdulich.Model.DangKy.SignUpModel;
import com.example.tranthibay.ql_tourdulich.Model.DangNhap.LoginModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TaiKhoanServices {
    private RequestQueue requestQueue;
    final private Context context;
    ArrayList<LoginModel> loginModels;
    public boolean isExist;
    public TaiKhoanServices(Context context) {
        this.isExist=false;
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(this.context);
        this.loginModels = new ArrayList<>();
    }

    public void selectTaiKhoan(final VolleyCallback callback) throws JSONException {

        String url = PHPConnectionConstants.HOST + "/TourDuLichAPI/TaiKhoanAPI/DangNhap/selectTaiKhoan.php";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (response.length() > 0) {
                    callback.onSuccess(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Có lỗi khi load dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });

        this.requestQueue.add(arrayRequest);
    }

    public void insertTaiKhoan(final SignUpModel model) {
        String url = PHPConnectionConstants.HOST + "/TourDuLichAPI/TaiKhoanAPI/DangNhap/insertTaiKhoan.php";


        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response","Successful!");
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
                params.put("username",model.getUserName());
                params.put("password",model.getPassword());
                params.put( "email",model.getEmail() );
                params.put("sdt",model.getSdt());
                params.put("gioiTinh",model.getGioiTinh());
                params.put("diaChi",model.getDiaChi());
                params.put("loaiTK",model.getLoaiTK());
                params.put("ten",model.getTen());
                return params;
            }
        };
        this.requestQueue.add( request );
    }

    public  void kiemTraTKTonTai(String username){

    }
}
