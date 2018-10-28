package com.example.tranthibay.ql_tourdulich.Presenter.ChiTietTour;

import android.content.Context;

import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaChonModel;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;
import com.example.tranthibay.ql_tourdulich.Services.VolleyCallback;

public interface ChiTietTourImplementPresenter {

    void getTourLienQuan(String maLoai, Context context, VolleyCallback callback);

    void themTourVaoGio(TourDaChonModel model);

    boolean kiemTraTourDaDat(String maTour);

}
