package com.example.tranthibay.ql_tourdulich.Model.ShowTour;

import java.io.Serializable;

public class KhachSanModel implements Serializable {
    private String tenKhachSan;
    private String diaChiKS;
    private double giaTienKS;
    private String loaiKS;

    public String getLoaiKS() {
        return loaiKS;
    }

    public void setLoaiKS(String loaiKS) {
        this.loaiKS = loaiKS;
    }

    public String getTenKhachSan() {
        return tenKhachSan;
    }

    public void setTenKhachSan(String tenKhachSan) {
        this.tenKhachSan = tenKhachSan;
    }

    public String getDiaChiKS() {
        return diaChiKS;
    }

    public void setDiaChiKS(String diaChiKS) {
        this.diaChiKS = diaChiKS;
    }

    public double getGiaTienKS() {
        return giaTienKS;
    }

    public void setGiaTienKS(double giaTienKS) {
        this.giaTienKS = giaTienKS;
    }

    public KhachSanModel(String tenKhachSan, String diaChiKS, double giaTienKS, String loaiKS) {
        this.tenKhachSan = tenKhachSan;
        this.diaChiKS = diaChiKS;
        this.giaTienKS = giaTienKS;
        this.loaiKS = loaiKS;
    }
}
