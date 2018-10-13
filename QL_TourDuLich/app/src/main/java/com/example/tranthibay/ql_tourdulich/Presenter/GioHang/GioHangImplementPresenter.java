package com.example.tranthibay.ql_tourdulich.Presenter.GioHang;

import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaChonModel;

import java.util.ArrayList;

public interface GioHangImplementPresenter {
    int tinhTongTien(ArrayList<TourDaChonModel> gioHang);
    int tinhTongSoNguoi();
}
