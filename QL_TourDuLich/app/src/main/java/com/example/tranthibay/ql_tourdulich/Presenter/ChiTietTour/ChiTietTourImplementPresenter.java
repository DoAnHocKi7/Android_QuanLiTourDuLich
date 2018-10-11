package com.example.tranthibay.ql_tourdulich.Presenter.ChiTietTour;

import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaChonModel;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;

public interface ChiTietTourImplementPresenter {

    void getTourLienQuan(TourModel tour);
    void themTourVaoGio(TourDaChonModel model);
    boolean kiemTraTourDaDat(String maTour);
}
