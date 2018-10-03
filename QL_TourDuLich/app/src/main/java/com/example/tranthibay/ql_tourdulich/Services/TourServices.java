package com.example.tranthibay.ql_tourdulich.Services;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.example.tranthibay.ql_tourdulich.Model.DangNhap.LoginModel;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;

import java.util.ArrayList;

public class TourServices {
    private RequestQueue requestQueue;
    final private Context context;
    ArrayList<TourModel> loginModels;

    public TourServices(RequestQueue requestQueue, Context context, ArrayList<TourModel> loginModels) {
        this.requestQueue = requestQueue;
        this.context = context;
        this.loginModels = loginModels;
    }

    //public void selectTour
}
