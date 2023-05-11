package com.example.QLTHUVIEN.dao;


import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_HOTENTV;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_MATKHAUTV;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_MATV;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_NAMSINHTV;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_TAIKHOANTV;
import static com.example.QLTHUVIEN.database.MySqlHeper.TABLE_THANHVIEN;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.QLTHUVIEN.database.MySqlHeper;
import com.example.QLTHUVIEN.model.Member;

import java.util.ArrayList;
import java.util.List;

public class DaoMember {
    MySqlHeper mMySqlHeper;
    SQLiteDatabase mSQLiteDatabase;
    public DaoMember(Context context){
        mMySqlHeper = new MySqlHeper(context);
    }

    public List<Member> getListMember(){
        List<Member> list = new ArrayList<Member>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_THANHVIEN ;

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        Member user ;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = Integer.parseInt(cursor.getString(0));
                String tenMember = cursor.getString(1);
                int namSinh = Integer.parseInt(cursor.getString(2));
                user = new Member(id , tenMember , namSinh );
                list.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return list;
    }
    public boolean deleteTitle(int id) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        return mSQLiteDatabase.delete(TABLE_THANHVIEN, KEY_MATV + "=?", new String[]{String.valueOf(id)}) > 0;
    }
    public  boolean themKind(Member mode){
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HOTENTV , mode.getName());
        values.put(KEY_NAMSINHTV , mode.getNameSinh());
        values.put(KEY_TAIKHOANTV , mode.getTaiKhoan());
        values.put(KEY_MATKHAUTV, mode.getMatKhau());
        long result = this.mSQLiteDatabase.insert(TABLE_THANHVIEN , null, values);
        if (result <= 0) {
            return false;
        }
        return true;
    }
    public boolean editKind(Member mode) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HOTENTV , mode.getName());
        values.put(KEY_NAMSINHTV , mode.getNameSinh());
        int r = this.mSQLiteDatabase.update(TABLE_THANHVIEN, values, KEY_MATV+"=?", new String[]{String.valueOf(mode.getId())});
        if (r <= 0) {
            return false;
        }
        return true;
    }
    public List<Member> getListSearch(String tenSachSearch ) {
        List<Member> list = new ArrayList<Member>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_THANHVIEN + "." +KEY_MATV +
                " , " + KEY_HOTENTV +
                " , " + KEY_NAMSINHTV +
                " FROM " + TABLE_THANHVIEN +
                " WHERE " + TABLE_THANHVIEN + "." +KEY_HOTENTV + " LIKE '%" +tenSachSearch + "%' OR " +KEY_NAMSINHTV  +" = " + "'  "+tenSachSearch+ "'  ";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        Member user ;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = Integer.parseInt(cursor.getString(0));
                String tenLoaiSach = cursor.getString(1);
                int idLoaiSach = Integer.parseInt(cursor.getString(2));
                user = new Member(id , tenLoaiSach,idLoaiSach);
                list.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return list;
    }
}
