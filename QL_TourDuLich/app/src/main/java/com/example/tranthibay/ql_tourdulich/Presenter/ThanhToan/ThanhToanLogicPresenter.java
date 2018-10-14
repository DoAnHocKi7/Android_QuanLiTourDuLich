package com.example.tranthibay.ql_tourdulich.Presenter.ThanhToan;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tranthibay.ql_tourdulich.Constants.PHPConnectionConstants;
import com.example.tranthibay.ql_tourdulich.Model.MuaHang.KhachHangModel;
import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaChonModel;
import com.example.tranthibay.ql_tourdulich.Presenter.GioHang.GioHangLogicPresenter;
import com.example.tranthibay.ql_tourdulich.Services.VolleyCallbackString;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;
import com.example.tranthibay.ql_tourdulich.View.ThanhToan.ThanhToanView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ThanhToanLogicPresenter implements ThanhToanImplementPresenter {
    ThanhToanView thanhToanView;
    Context context;

    public ThanhToanLogicPresenter(ThanhToanView thanhToanView, Context context) {
        this.thanhToanView = thanhToanView;
        this.context = context;
    }

    @Override
    public void thanhToanGioHang(KhachHangModel khachHangModel) {
        themThongTinKhachHang( khachHangModel, new VolleyCallbackString() {
            @Override
            public void onSuccess(String maKH) {
                themThongTinHopDong( maKH, new VolleyCallbackString() {
                    @Override
                    public void onSuccess(String maHopDong) {
                        JSONArray array = new JSONArray();
                        for (TourDaChonModel tour : MainActivity.GioHang) {
                            JSONObject obj = getJSONChiTietTourDL( tour.getTourModel().getMaTour(), maHopDong, String.valueOf( tour.getSoNguoi() ) );
                            array.put( obj );
                        }
                        String arr = array.toString();
                        themChiTietHopDong( arr );
                    }
                } );
            }
        } );
    }

    private void themChiTietHopDong(String encodedJsonArrayString) {
        RequestQueue requestQueue = Volley.newRequestQueue( this.context );
        String url = PHPConnectionConstants.HOST + "/TourDuLichAPI/GioHang/ThanhToanGioHang.php";
        StringRequest request = new StringRequest( Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals( "ThanhCong" )) {
                    thanhToanView.thanhToanGioHangThanhCong();
                }else {
                    thanhToanView.thanhToanGioHangThatBai();
                }
                Log.e( "Response", "Successful!" );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "Error", "có lỗi khi load dữ liệu" );
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put( "ArrayCTTour", encodedJsonArrayString );
                return params;

            }
        };
        requestQueue.add( request );
    }

    private void themThongTinHopDong(String maKH, VolleyCallbackString callback) {
        RequestQueue requestQueue = Volley.newRequestQueue( this.context );
        String url = PHPConnectionConstants.HOST + "/TourDuLichAPI/HopDongAPI/insertHopDong.php";
        StringRequest request = new StringRequest( Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess( response );
                //chuyen ra man hinh ma dat hang
                Log.e( "Response", "Successful!" );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "Error", "có lỗi khi load dữ liệu" );
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put( "TongSoNguoi", String.valueOf( new GioHangLogicPresenter().tinhTongSoNguoi() ) );
                params.put( "MaKH", maKH );
                params.put( "NgayDH", Calendar.getInstance().getTime().toString() );
                return params;
            }
        };
        requestQueue.add( request );
    }

    private void themThongTinKhachHang(KhachHangModel khachHangModel, VolleyCallbackString callback) {
        RequestQueue requestQueue = Volley.newRequestQueue( this.context );
        String url = PHPConnectionConstants.HOST + "/TourDuLichAPI/KhachHangAPI/insertKhachHang.php";
        StringRequest request = new StringRequest( Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess( response );
                //chuyen ra man hinh ma dat hang
                Log.e( "Response", "Successful!" );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "Error", "có lỗi khi load dữ liệu" );
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                for (TourDaChonModel model : MainActivity.GioHang) {

                }
                Map<String, String> params = new HashMap<String, String>();
//                Random random = new Random();
//                String maKH = String.valueOf( random.nextInt( 1000000000 ) + 1 );
//                params.put( "maKH", maKH );
                params.put( "TenKH", khachHangModel.getTenKH() );
                params.put( "DiaChiKH", khachHangModel.getDiaChi() );
                params.put( "EmailKH", khachHangModel.getEmail() );
                params.put( "SdtKH", khachHangModel.getSdt() );
                params.put( "GTinhKH", khachHangModel.getGioiTinh() );

                return params;
            }
        };
        requestQueue.add( request );
    }

    private JSONObject getJSONChiTietTourDL(String maTour, String maHopDong, String sLNguoi) {
        JSONObject obj = new JSONObject();
        try {
            obj.put( "MaTour", maTour );
            obj.put( "MaHopDong", maHopDong );
            obj.put( "SoLuongNguoi", sLNguoi );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void xoaSPKhoiGioHang(String maTour) {
        for (TourDaChonModel tour:MainActivity.GioHang) {
            if(tour.getTourModel().getMaTour().equals( maTour )){
                MainActivity.GioHang.remove( tour );
                thanhToanView.xoaTourTrongGioThanhCong();
                break;
            }
        }
    }
}
