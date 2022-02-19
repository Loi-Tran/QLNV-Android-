package com.thanhloi.myappqlnv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thanhloi.myappqlnv.database.DbHelper;
import com.thanhloi.myappqlnv.model.NhanVien;

import java.util.ArrayList;

public class NhanVienDao {
    DbHelper helper;
    public NhanVienDao(Context context){
        helper=new DbHelper(context);
    }
    public ArrayList<NhanVien> getAll(){
        ArrayList<NhanVien> ds=new ArrayList<>();
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="SELECT * FROM NHANVIEN";
        Cursor cs=db.rawQuery(sql,null);
        cs.moveToFirst();//đưa con trỏ về dòng đầu tiên
        while (cs.isAfterLast()==false){
            int ma=cs.getInt(0);
            String tenNV=cs.getString(1);
            String luong=cs.getString(2);
            String hinh=cs.getString(3);
            int maCV=cs.getInt(4);

            NhanVien nv=new NhanVien(ma,tenNV,luong, hinh, maCV);
            ds.add(nv);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return ds;
    }
    public ArrayList<NhanVien> getAll(String trangthai){
        ArrayList<NhanVien> ds=new ArrayList<>();
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="SELECT * FROM NHANVIEN JOIN CHUCVU ON NHANVIEN.MaCV=CHUCVU.MaCV WHERE TrangThai='"+trangthai+"'";
        Cursor cs=db.rawQuery(sql,null);
        cs.moveToFirst();//đưa con trỏ về dòng đầu tiên
        while (cs.isAfterLast()==false){
            int ma=cs.getInt(0);
            String tenNV=cs.getString(1);
            String luong=cs.getString(2);
            String hinh=cs.getString(3);
            int maCV=cs.getInt(4);
            NhanVien nv=new NhanVien(ma,tenNV,luong, hinh, maCV);
            ds.add(nv);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return ds;
    }
    public boolean insert(NhanVien nhanVien){
      SQLiteDatabase db=helper.getWritableDatabase();
       ContentValues values=new ContentValues();
      values.put("TenNV",nhanVien.getTenNV());
       values.put("Luong",nhanVien.getLuong());
        values.put("Hinh",nhanVien.getHinh());
        values.put("ChucVu",nhanVien.getChucVu());
       long row = db.insert("NHANVIEN",null,values);
       return (row>0);
    }
    public boolean insert(String tenNV,String luong,String hinh,int chucVu){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("TenNV",tenNV);
        values.put("Luong",luong);
        values.put("Hinh",hinh);
        values.put("ChucVu",chucVu);
        long row = db.insert("NHANVIEN",null,values);
        return (row>0);
    }
    public boolean update(NhanVien nv){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("TenNV",nv.getTenNV());
        values.put("Luong",nv.getLuong());
        values.put("Hinh",nv.getHinh());
        values.put("ChucVu",nv.getChucVu());
        int row = db.update("CHUCVU",values,"MaCV=?",
                new String[]{nv.getMaNV()+""});
        return (row>0);
    }
    public boolean delete(int maNV){
        SQLiteDatabase db=helper.getWritableDatabase();
        int row = db.delete("NHANVIEN","MaNV=?",
                new String[]{maNV+""});
        return (row>0);
    }
}
