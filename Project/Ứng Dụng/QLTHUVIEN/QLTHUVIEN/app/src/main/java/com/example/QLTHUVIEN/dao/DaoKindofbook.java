package com.example.QLTHUVIEN.dao;


import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_MALOAI;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_TENLOAI;
import static com.example.QLTHUVIEN.database.MySqlHeper.TABLE_LOAISACH;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.QLTHUVIEN.database.MySqlHeper;
import com.example.QLTHUVIEN.model.Kindofbook;

import java.util.ArrayList;
import java.util.List;

public class DaoKindofbook {
    MySqlHeper mMySqlHeper;
    SQLiteDatabase mSQLiteDatabase;
    public DaoKindofbook(Context context){
        mMySqlHeper = new MySqlHeper(context);
    }


    public List<Kindofbook> getListOfKindofBooks(){
        List<Kindofbook> lisst = new ArrayList<Kindofbook>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_LOAISACH;

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        Kindofbook user ;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                user = new Kindofbook(id , name);
                lisst.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return lisst;
    }
    public boolean deleteTitle(int id) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        return mSQLiteDatabase.delete(TABLE_LOAISACH, KEY_MALOAI + "=?", new String[]{String.valueOf(id)}) > 0;
    }
    public  boolean themKind(Kindofbook mode){
               this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
              ContentValues values = new ContentValues();
              values.put(KEY_TENLOAI , mode.getTenloaiSach() );
              long result = this.mSQLiteDatabase.insert(TABLE_LOAISACH , null, values);
              if (result <= 0) {
                  return false;
              }
              return true;
          }
    public boolean editKind(Kindofbook mode) {
               this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
               ContentValues values = new ContentValues();
               values.put(KEY_TENLOAI , mode.getTenloaiSach() );
                   int r = this.mSQLiteDatabase.update(TABLE_LOAISACH, values, KEY_MALOAI+"=?", new String[]{String.valueOf(mode.getIdLoaiSach())});
                   if (r <= 0) {
                       return false;
                   }
                   return true;
               }
    public List<String> getListNameKindOfBook(){
                    List<String> result = new ArrayList<String>();
                   this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
                   String sql = "SELECT * FROM " + TABLE_LOAISACH;
                   Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
                   if (cursor.moveToFirst()) {
                       while (!cursor.isAfterLast()) {
                           String name = cursor.getString(1);
                           result.add(name);
                           cursor.moveToNext();
                       }
                   }
                   cursor.close();
                   this.mSQLiteDatabase.close();


                    return result;
               }
    public int getIdLoaiSach(String tenLoai){
                    int id = 0;
                   this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
                   String sql = "SELECT " + TABLE_LOAISACH + "." +KEY_MALOAI +
                           " AS idLoaiSach FROM " + TABLE_LOAISACH +
                           " WHERE " + TABLE_LOAISACH + "."
                           + KEY_TENLOAI + " ='" + tenLoai+"'"  ;
                   Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
                   if (cursor.moveToFirst()) {
                       while (!cursor.isAfterLast()) {
                           id = Integer.parseInt(cursor.getString(0));
                           cursor.moveToNext();
                       }
                   }
                   cursor.close();
                   this.mSQLiteDatabase.close();

                    return id;
               }
}
