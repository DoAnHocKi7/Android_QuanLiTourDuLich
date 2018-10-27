package com.example.tranthibay.ql_tourdulich.Presenter.MuaHang;

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
import com.example.tranthibay.ql_tourdulich.Constants.PHPConnectionConstants;
import com.example.tranthibay.ql_tourdulich.Model.MuaHang.KhachHangModel;
import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaChonModel;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.KhachSanModel;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;
import com.example.tranthibay.ql_tourdulich.View.MuaHang.MuaHangView;

import java.util.HashMap;
import java.util.Map;

public class MuaHangLogicPresenter implements MuaHangImplementPresenter {
    MuaHangView muaHangView;
    Context context;

    public MuaHangLogicPresenter(MuaHangView muaHangView, Context context) {
        this.muaHangView = muaHangView;
        this.context = context;
    }


    @Override
    public void thanhToanNgayMotTour(TourDaChonModel model, KhachHangModel khachHangModel) {
        RequestQueue requestQueue = Volley.newRequestQueue( this.context );
        String url = PHPConnectionConstants.HOST + "/TourDuLichAPI/HopDongAPI/createHopDong.php";


        StringRequest request = new StringRequest( Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//chuyen ra man hinh ma dat hang
                muaHangView.datTourNgayThanhCong( response );
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
                if (MainActivity.KhachHangModel != null) {
                    params.put( "MaKH", khachHangModel.getMaKH() );

                }

                params.put( "TenKH", khachHangModel.getTenKH() );
                params.put( "DiaChiKH", khachHangModel.getDiaChi() );
                params.put( "EmailKH", khachHangModel.getEmail() );
                params.put( "NgayDH", model.getNgayDat().toString() );
                params.put( "SdtKH", khachHangModel.getSdt() );
                params.put( "GTinhKH", khachHangModel.getGioiTinh() );
                params.put( "TongSoNguoi", String.valueOf( model.getSoNguoi() ) );
                params.put( "SoNguoi", String.valueOf( model.getSoNguoi() ) );
                params.put( "MaTour", model.getTourModel().getMaTour() );
                //params.put( "MaKS",model.getTourModel().getKhachSan(). )
                return params;
            }
        };
        requestQueue.add( request );
    }
}
