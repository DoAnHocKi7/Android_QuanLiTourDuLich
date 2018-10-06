package com.example.tranthibay.ql_tourdulich.Model.MuaHang;

import java.util.Date;

public class TourDaDatModel {
    private String maTour;
    private int soNguoi;
    private Date ngayDat;
    private double donGia;
    private double tongTien;

    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
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

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public TourDaDatModel(String maTour, int soNguoi, Date ngayDat, double donGia, double tongTien) {
        this.maTour = maTour;
        this.soNguoi = soNguoi;
        this.ngayDat = ngayDat;
        this.donGia = donGia;
        this.tongTien = tongTien;
    }


}
