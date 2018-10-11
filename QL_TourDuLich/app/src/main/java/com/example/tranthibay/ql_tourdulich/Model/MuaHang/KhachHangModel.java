package com.example.tranthibay.ql_tourdulich.Model.MuaHang;

public class KhachHangModel {
    public KhachHangModel(String maKH, String loaiKH, String tenKH, String gioiTinh, String email, String sdt, String diaChi) {
        this.maKH = maKH;
        this.loaiKH = loaiKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public KhachHangModel() {

    }

    private String maKH;
    private String loaiKH;
    private String tenKH;
    private String gioiTinh;
    private String email;
    private String sdt;
    private String diaChi;

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getLoaiKH() {
        return loaiKH;
    }

    public void setLoaiKH(String loaiKH) {
        this.loaiKH = loaiKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
