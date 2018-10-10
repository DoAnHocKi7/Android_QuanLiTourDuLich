package com.example.tranthibay.ql_tourdulich.Model.ShowTour;


import java.io.Serializable;
import java.util.Date;

public class TourModel implements Serializable {

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

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

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public Date getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(Date ngayDi) {
        this.ngayDi = ngayDi;
    }

    public Date getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(Date ngayDen) {
        this.ngayDen = ngayDen;
    }

    private Date ngayDi;
    private Date ngayDen;
    private String tenTour;
    private String hinhAnh;
    private double gia;
    private String loaiTour;
    private String mota;
    private String maTour;
    private String diaDiem;
    private KhachSanModel khachSan;

    public KhachSanModel getKhachSan() {
        return khachSan;
    }

    public void setKhachSan(KhachSanModel khachSan) {
        this.khachSan = khachSan;
    }

    public TourModel() {
    }

    public TourModel(Date ngayDi, Date ngayDen, String maTour, String tenTour, String hinhAnh, double gia, String loaiTour, String mota, String diaDiem, KhachSanModel ks) {
        this.ngayDi = ngayDi;
        this.ngayDen = ngayDen;
        this.maTour = maTour;
        this.tenTour = tenTour;
        this.hinhAnh = hinhAnh;
        this.gia = gia;
        this.loaiTour = loaiTour;
        this.mota = mota;
        this.diaDiem = diaDiem;
        this.khachSan = ks;
    }
}
