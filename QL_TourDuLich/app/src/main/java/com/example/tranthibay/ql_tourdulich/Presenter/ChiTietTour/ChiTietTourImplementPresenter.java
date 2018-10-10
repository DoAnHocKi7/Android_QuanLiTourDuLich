package com.example.tranthibay.ql_tourdulich.Presenter.ChiTietTour;

import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaDatModel;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;

public interface ChiTietTourImplementPresenter {

    void getTourLienQuan(TourModel tour);
    void themTourVaoGio(TourDaDatModel model);
    boolean kiemTraTourDaDat(String maTour);
}
