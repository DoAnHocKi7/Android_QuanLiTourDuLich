package com.example.tranthibay.ql_tourdulich.Presenter.ChiTietTour;

import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaChonModel;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;
import com.example.tranthibay.ql_tourdulich.View.ChiTietTour.ChiTietTourView;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;

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
                    tour.setSoNguoi( tour.getSoNguoi()+ model.getSoNguoi() );
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
    public void getTourLienQuan(TourModel tour) {

    }
}
