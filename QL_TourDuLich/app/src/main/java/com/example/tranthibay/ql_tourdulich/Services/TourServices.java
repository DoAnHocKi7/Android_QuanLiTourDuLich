package com.example.tranthibay.ql_tourdulich.Services;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tranthibay.ql_tourdulich.Constants.PHPConnectionConstants;
import com.example.tranthibay.ql_tourdulich.Model.DangNhap.LoginModel;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;

import org.json.JSONArray;

import java.util.ArrayList;

public class TourServices {
    private RequestQueue requestQueue;
    final private Context context;
    ArrayList<TourModel> tourModels;

    public TourServices(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue( this.context );
        this.tourModels = new ArrayList<>();
    }


    public void selectTours(final VolleyCallback callback) {
        String url = PHPConnectionConstants.HOST + "/TourDuLichAPI/TourAPI/selectTour.php";
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

        this.requestQueue.add( arrayRequest );
    }
}
