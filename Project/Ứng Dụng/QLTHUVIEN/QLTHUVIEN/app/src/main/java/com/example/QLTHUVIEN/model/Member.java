package com.example.QLTHUVIEN.model;

import java.io.Serializable;

public class Member implements Serializable {
    private int id;
    private String name;
    private int nameSinh;
    private String taiKhoan;
    private String matKhau;

    public Member() {
    }

    public Member(int id, String name, int nameSinh) {
        this.id = id;
        this.name = name;
        this.nameSinh = nameSinh;
    }

    public Member(int id, String name, int nameSinh, String taiKhoan, String matKhau) {
        this.id = id;
        this.name = name;
        this.nameSinh = nameSinh;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNameSinh() {
        return nameSinh;
    }

    public void setNameSinh(int nameSinh) {
        this.nameSinh = nameSinh;
    }
}
