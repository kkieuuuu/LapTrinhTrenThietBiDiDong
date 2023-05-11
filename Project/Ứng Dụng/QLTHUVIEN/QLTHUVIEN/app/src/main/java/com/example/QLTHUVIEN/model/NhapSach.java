package com.example.QLTHUVIEN.model;

public class NhapSach {
    private  int maPNS;
    private String tenTG;
    private String maTT;
    private String maSach;
    private String ngayNhap;
    private int SoLuong;
    private String maNXB;

    public NhapSach() {
    }

    public NhapSach(int maPNS, String tenTG, String maTT, String maSach, String ngayNhap, int soLuong, String maNXB) {
        this.maPNS = maPNS;
        this.tenTG = tenTG;
        this.maTT = maTT;
        this.maSach = maSach;
        this.ngayNhap = ngayNhap;
        SoLuong = soLuong;
        this.maNXB = maNXB;
    }

    public int getMaPNS() {
        return maPNS;
    }

    public void setMaPNS(int maPNS) {
        this.maPNS = maPNS;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public String getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(String maNXB) {
        this.maNXB = maNXB;
    }
}
