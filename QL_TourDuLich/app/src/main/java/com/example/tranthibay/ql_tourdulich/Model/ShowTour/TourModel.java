package com.example.tranthibay.ql_tourdulich.Model.ShowTour;

public class TourModel {

    private String maTour;

    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public String getTenTour() {
        return tenTour;
    }

    public void setTenTour(String tenTour) {
        this.tenTour = tenTour;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getLoaiTour() {
        return loaiTour;
    }

    public void setLoaiTour(String loaiTour) {
        this.loaiTour = loaiTour;
    }

    private String tenTour;
    private String hinhAnh;
    private double gia;
    private String loaiTour;

    public TourModel() {
    }

    public TourModel(String maTour, String tenTour, String hinhAnh, double gia, String loaiTour) {
        this.maTour = maTour;
        this.tenTour = tenTour;
        this.hinhAnh = hinhAnh;
        this.gia = gia;
        this.loaiTour = loaiTour;
    }
}
