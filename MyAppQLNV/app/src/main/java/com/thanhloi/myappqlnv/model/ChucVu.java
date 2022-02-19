package com.thanhloi.myappqlnv.model;

public class ChucVu {
    private int maCV;
    private String tenCV;
    private String trangThai;

    public ChucVu(int maCV, String tenCV, String trangThai) {
        this.maCV = maCV;
        this.tenCV = tenCV;
        this.trangThai = trangThai;
    }
    public ChucVu(String tenCV, String trangThai) {
        this.tenCV = tenCV;
        this.trangThai = trangThai;
    }
    @Override
    public String toString(){
        return tenCV;
    }
    public int getMaCV() {
        return maCV;
    }

    public void setMaCV(int maCV) {
        this.maCV = maCV;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
