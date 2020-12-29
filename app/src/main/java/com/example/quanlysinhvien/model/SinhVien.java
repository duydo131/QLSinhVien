package com.example.quanlysinhvien.model;

public class SinhVien {
    private long ID;
    private String mssv;
    private String hoten;
    private String ngaysinh;
    private String email;
    private String diachi;

    public SinhVien() {}

    public SinhVien(int ID, String mssv, String hoten, String ngaysinh, String email, String diachi) {
        this.ID = ID;
        this.mssv = mssv;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.email = email;
        this.diachi = diachi;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
