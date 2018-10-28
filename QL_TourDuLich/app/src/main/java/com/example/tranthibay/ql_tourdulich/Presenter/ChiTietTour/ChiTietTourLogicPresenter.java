package com.example.tranthibay.ql_tourdulich.Presenter.ChiTietTour;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tranthibay.ql_tourdulich.Constants.PHPConnectionConstants;
import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaChonModel;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;
import com.example.tranthibay.ql_tourdulich.Services.VolleyCallback;
import com.example.tranthibay.ql_tourdulich.View.ChiTietTour.ChiTietTourView;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;

import org.json.JSONArray;

public class ChiTietTourLogicPresenter implements ChiTietTourImplementPresenter {

    public ChiTietTourLogicPresenter(ChiTietTourView chiTietTourView) {
        this.chiTietTourView = chiTietTourView;
    }

    ChiTietTourView chiTietTourView;

    @Override
    public void themTourVaoGio(TourDaChonModel model) {
        String maTour = model.getTourModel().getMaTour();
        if (!kiemTraTourDaDat( maTour )) {
            MainActivity.GioHang.add( model );
        } else {
            for (TourDaChonModel tour : MainActivity.GioHang) {
                if (tour.getTourModel().getMaTour().equals( maTour )) {
                    tour.setSoNguoi( tour.getSoNguoi() + model.getSoNguoi() );
                    break;
                }
            }
        }
        chiTietTourView.themGioHangThanhCong();
    }

    @Override
    public boolean kiemTraTourDaDat(String maTour) {
        for (TourDaChonModel tourDaDat : MainActivity.GioHang) {
            if (tourDaDat.getTourModel().getMaTour().equals( maTour )) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void getTourLienQuan(String maLoai, Context context, VolleyCallback callback) {
        RequestQueue requestQueue = Volley.newRequestQueue( context );
        String url = PHPConnectionConstants.HOST + "/TourDuLichAPI/TourAPI/selectTour.php?MaLoai=" + maLoai;
        JsonArrayRequest arrayRequest = new JsonArrayRequest( Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (response.length() > 0) {
                    callback.onSuccess( response );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( context, "Có lỗi khi load dữ liệu", Toast.LENGTH_SHORT ).show();
            }
        } );
        requestQueue.add( arrayRequest );
    }
}
