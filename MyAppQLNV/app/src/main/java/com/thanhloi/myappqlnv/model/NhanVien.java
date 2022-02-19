package com.thanhloi.myappqlnv.model;

public class NhanVien {
    private int maNV;
    private String tenNV;
    private String luong;
    private String hinh;
    private int chucVu;


    public NhanVien(int maNV, String tenNV, String luong, String hinh, int chucVu) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.luong = luong;
        this.hinh = hinh;
        this.chucVu = chucVu;
    }
    public NhanVien(String tenNV, String luong, String hinh, int chucVu) {
        this.tenNV = tenNV;
        this.luong = luong;
        this.hinh = hinh;
        this.chucVu = chucVu;
    }
    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }
}
