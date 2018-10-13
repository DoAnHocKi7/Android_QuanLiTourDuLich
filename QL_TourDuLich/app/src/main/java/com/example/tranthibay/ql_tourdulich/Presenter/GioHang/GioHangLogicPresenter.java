package com.example.tranthibay.ql_tourdulich.Presenter.GioHang;

import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaChonModel;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;

import java.util.ArrayList;

public class GioHangLogicPresenter implements GioHangImplementPresenter {
    @Override
    public int tinhTongTien(ArrayList<TourDaChonModel> gioHang) {
        int tongTien =0;
        for (TourDaChonModel tour:gioHang) {
            tongTien +=tour.getTongTien();
        }
        return tongTien;
    }

    @Override
    public int tinhTongSoNguoi() {
        int tsNguoi =0;
        for (TourDaChonModel tour: MainActivity.GioHang) {
            tsNguoi +=tour.getSoNguoi();
        }
        return tsNguoi;
    }
}
