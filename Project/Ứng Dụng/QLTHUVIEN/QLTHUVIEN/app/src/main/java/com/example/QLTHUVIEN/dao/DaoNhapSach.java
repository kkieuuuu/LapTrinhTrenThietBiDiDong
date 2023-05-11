package com.example.QLTHUVIEN.dao;

import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_MAPNS;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_NGAYNHAP;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_PHIEUNHAPSACH_MANXB;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_PHIEUNHAPSACH_MASACH;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_PHIEUNHAPSACH_MATT;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_SOLUONG;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_TENTG;
import static com.example.QLTHUVIEN.database.MySqlHeper.TABLE_PHIEUNHAPSACH;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.QLTHUVIEN.database.MySqlHeper;
import com.example.QLTHUVIEN.model.NhapSach;

import java.util.ArrayList;
import java.util.List;

public class DaoNhapSach {
    MySqlHeper mMySqlHeper;
    SQLiteDatabase mSQLiteDatabase;

    public DaoNhapSach(Context context) {
        mMySqlHeper = new MySqlHeper(context);
    }

    public boolean themPhieuNhapSach(NhapSach mode) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TENTG, mode.getTenTG());
        values.put(KEY_PHIEUNHAPSACH_MATT, mode.getMaTT());
        values.put(KEY_PHIEUNHAPSACH_MASACH, mode.getMaSach());
        values.put(KEY_NGAYNHAP, mode.getNgayNhap());
        values.put(KEY_SOLUONG, mode.getSoLuong());
        values.put(KEY_PHIEUNHAPSACH_MANXB, mode.getMaNXB());
        long result = this.mSQLiteDatabase.insert(TABLE_PHIEUNHAPSACH, null, values);
        if (result <= 0) {
            return false;
        }
        return true;
    }

    public boolean editPhieuNhapSach(NhapSach mode) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TENTG, mode.getTenTG());
        values.put(KEY_PHIEUNHAPSACH_MATT, mode.getMaTT());
        values.put(KEY_PHIEUNHAPSACH_MASACH, mode.getMaSach());
        values.put(KEY_NGAYNHAP, mode.getNgayNhap());
        values.put(KEY_SOLUONG, mode.getSoLuong());
        values.put(KEY_PHIEUNHAPSACH_MANXB, mode.getMaNXB());
        int r = this.mSQLiteDatabase.update(TABLE_PHIEUNHAPSACH, values, KEY_MAPNS + "=?", new String[]{String.valueOf(mode.getMaPNS())});
        if (r <= 0) {
            return false;
        }
        return true;
    }


    public boolean deletePhieuNhap(int id) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        return mSQLiteDatabase.delete(TABLE_PHIEUNHAPSACH, KEY_MAPNS + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    public List<NhapSach> getListNhapSach() {
        List<NhapSach> list = new ArrayList<>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_PHIEUNHAPSACH;
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        NhapSach nhapSach;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = Integer.parseInt(cursor.getString(0));
                String tenTg = cursor.getString(1);
                String matt = cursor.getString(2);
                String masach = cursor.getString(3);
                String ngaynhap = cursor.getString(4);
                int soluong = Integer.parseInt(cursor.getString(5));
                String manxb = cursor.getString(6);
                nhapSach = new NhapSach(id, tenTg, matt, masach, ngaynhap, soluong, String.valueOf(manxb));
                list.add(nhapSach);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return list;
    }

}
