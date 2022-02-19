package com.thanhloi.myappqlnv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thanhloi.myappqlnv.database.DbHelper;
import com.thanhloi.myappqlnv.model.ChucVu;

import java.util.ArrayList;

public class ChucVuDao {
    DbHelper helper;
    public ChucVuDao(Context context){
        helper=new DbHelper(context);
    }
    public ArrayList<ChucVu> getAll(){
        ArrayList<ChucVu> ds=new ArrayList<>();
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="SELECT * FROM CHUCVU";
        Cursor cs=db.rawQuery(sql,null);
        cs.moveToFirst();//đưa con trỏ về dòng đầu tiên
        while (cs.isAfterLast()==false){
            int ma=cs.getInt(0);
            String ten=cs.getString(1);
            String trangthai=cs.getString(2);
            ChucVu cv=new ChucVu(ma,ten,trangthai);
            ds.add(cv);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return ds;
    }
    public ArrayList<ChucVu> getAll(String trangThai){
        ArrayList<ChucVu> ds=new ArrayList<>();
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="SELECT * FROM CHUCVU WHERE TrangThai='"+trangThai+"'";
        Cursor cs=db.rawQuery(sql,null);
        cs.moveToFirst();//đưa con trỏ về dòng đầu tiên
        while (cs.isAfterLast()==false){
            int ma=cs.getInt(0);
            String ten=cs.getString(1);
            String trangthai=cs.getString(2);
            ChucVu cv=new ChucVu(ma,ten,trangthai);
            ds.add(cv);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return ds;
    }
    public ChucVu getById(int maCV){
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="SELECT * FROM CHUCVU WHERE MaCV="+maCV;
        Cursor cs=db.rawQuery(sql,null);
        cs.moveToFirst();//đưa con trỏ về dòng đầu tiên
        int ma=cs.getInt(0);
        String ten=cs.getString(1);
        String trangthai=cs.getString(2);
        ChucVu cv=new ChucVu(ma,ten,trangthai);
        cs.close();
        db.close();
        return cv;
    }
    public boolean insert(ChucVu chucVu){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("TenCV",chucVu.getTenCV());
        values.put("TrangThai",chucVu.getTrangThai());
        long row = db.insert("CHUCVU",null,values);
        return (row>0);
    }
    public boolean insert(String tenCV,String trangThai){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("TenCV",tenCV);
        values.put("TrangThai",trangThai);
        long row = db.insert("CHUCVU",null,values);
        return (row>0);
    }
    public boolean update(ChucVu chucVu){
       // String sql="UPDATE CHUCVU SET TenCV=?,TrangThai=? WHERE MaCV=?";

        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("TenCV",chucVu.getTenCV());
        values.put("TrangThai",chucVu.getTrangThai());
        int row = db.update("CHUCVU",values,"MaCV=?",
                new String[]{chucVu.getMaCV()+""});
        return (row>0);
    }
    public boolean delete(int maCV){
      //  String sql="DELETE FROM CHUVU WHERE MaCV=?";
        SQLiteDatabase db=helper.getWritableDatabase();
        int row = db.delete("CHUCVU","MaCV=?",
                new String[]{maCV+""});
        return (row>0);
    }
}
