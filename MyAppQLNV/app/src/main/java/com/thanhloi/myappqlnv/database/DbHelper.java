package com.thanhloi.myappqlnv.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "QLNhanVien.sqlite", null, 2);//Tạo CSDL
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE CHUCVU(MaCV integer primary key autoincrement,"+"TenCV text, TrangThai text)";
        db.execSQL(sql);
        sql="INSERT INTO CHUCVU VALUES(null,'Giám đốc','GiamDoc')";
        db.execSQL(sql);
        sql="INSERT INTO CHUCVU VALUES(null,'Trưởng phòng','TruongPhong')";
        db.execSQL(sql);
        sql="INSERT INTO CHUCVU VALUES(null,'Nhân Viên','NhanVien')";
        db.execSQL(sql);

        sql="CREATE TABLE NHANVIEN(MaNV integer primary key autoincrement,"+"TenNV text, Luong text,Hinh text,"+" MaCV integer references CHUCVU(MaCV))";
        db.execSQL(sql);
        sql="INSERT INTO NHANVIEN VALUES(null,'Lê Thị Ngà','20000','image4',1)";
        db.execSQL(sql);
        sql="INSERT INTO NHANVIEN VALUES(null,'Lê Thị Thu','12000','image1',2)";
        db.execSQL(sql);
        sql="INSERT INTO NHANVIEN VALUES(null,'Nguyễn Văn A','8000','image2',3)";
        db.execSQL(sql);
        sql="INSERT INTO NHANVIEN VALUES(null,'Hồ Thị B','9000','image3',3)";
        db.execSQL(sql);
        sql="INSERT INTO NHANVIEN VALUES(null,'Lê Kim Q','14000','image5',2)";
        db.execSQL(sql);
        sql="INSERT INTO NHANVIEN VALUES(null,'Vu Trọng K','10000','image6',3)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CHUCVU");
        db.execSQL("DROP TABLE IF EXISTS NHANVIEN");
        onCreate(db);
    }
}
