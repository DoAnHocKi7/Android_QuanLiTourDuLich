package com.example.tranthibay.ql_tourdulich.Model.MuaHang;

import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;

import java.io.Serializable;
import java.util.Date;

public class TourDaDatModel implements Serializable{
    private int soNguoi;
    private Date ngayDat;
    private double tongTien;
    private TourModel tourModel;

    public TourDaDatModel(int soNguoi, Date ngayDat, double tongTien, TourModel tourModel) {
        this.soNguoi = soNguoi;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
        this.tourModel = tourModel;
    }

    public TourModel getTourModel() {
        return tourModel;
    }

    public void setTourModel(TourModel tourModel) {
        this.tourModel = tourModel;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }


    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }


}
