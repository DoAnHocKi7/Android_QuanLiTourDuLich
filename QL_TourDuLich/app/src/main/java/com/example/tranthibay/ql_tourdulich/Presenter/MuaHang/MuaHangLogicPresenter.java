package com.example.tranthibay.ql_tourdulich.Presenter.MuaHang;

import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaDatModel;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;

public class MuaHangLogicPresenter implements MuaHangImplementPresenter {
    @Override
    public void themTourVaoGio(TourDaDatModel model) {

    }

    @Override
    public boolean kiemTraTourDaDat(String maTour) {
        for (TourDaDatModel tourDaDat: MainActivity.gioHang) {
            if(tourDaDat.getTourModel().getMaTour().equals( maTour )){
                return true;
            }
        }
        return false;
    }
}
