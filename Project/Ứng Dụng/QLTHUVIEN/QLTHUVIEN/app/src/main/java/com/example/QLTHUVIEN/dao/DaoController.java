package com.example.QLTHUVIEN.dao;

import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_HOTENTT;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_IMAGE;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_MATKHAUTT;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_MATKHAUTV;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_MATT;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_TAIKHOANTT;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_TAIKHOANTV;
import static com.example.QLTHUVIEN.database.MySqlHeper.TABLE_THANHVIEN;
import static com.example.QLTHUVIEN.database.MySqlHeper.TABLE_THUTHU;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.QLTHUVIEN.database.MySqlHeper;
import com.example.QLTHUVIEN.model.Member;
import com.example.QLTHUVIEN.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class DaoController {
    MySqlHeper mMySqlHeper;
    SQLiteDatabase mSQLiteDatabase;

    public DaoController(Context context) {
        mMySqlHeper = new MySqlHeper(context);
    }

    public ThuThu getUserLogin(String username, String password) {
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_THUTHU
                + " WHERE " + KEY_TAIKHOANTT + " = '" + username + "' and " + KEY_MATKHAUTT + " = '" + password + "'";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        ThuThu user = new ThuThu();
        if (cursor.moveToFirst()) {
            user.setMaThuThu(Integer.parseInt(cursor.getString(0)));
            user.setHoTenThuThu(cursor.getString(1));
            user.setTaiKhoan(cursor.getString(2));
            user.setMaKhau(cursor.getString(3));
            user.setThuthuPhoto(cursor.getBlob(4));
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return user;
    }

    public Member getUserLoginMember(String username, String password) {
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_THANHVIEN
                + " WHERE " + KEY_TAIKHOANTV + " = '" + username + "' and " + KEY_MATKHAUTV + " = '" + password + "'";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        Member member = new Member();
        if (cursor.moveToFirst()) {
            int id = Integer.parseInt(cursor.getString(0));
            String tenMember = cursor.getString(1);
            int namSinh = Integer.parseInt(cursor.getString(2));
            String tk = cursor.getString(3);
            String mk = cursor.getString(4);
            member = new Member(id,tenMember,namSinh,tk,mk);
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return member;
    }

    public Bitmap getPhotoSql(int idLib) {
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_THUTHU + "." + KEY_IMAGE + " AS IMAGEUSER FROM " + TABLE_THUTHU + " WHERE " + TABLE_THUTHU + "." + KEY_MATT + " = " + idLib;
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            byte[] imgByte = cursor.getBlob(0);
            cursor.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return null;
    }

    @SuppressLint("Range")
    public List<ThuThu> getListThuTHu() {
        List<ThuThu> list = new ArrayList<ThuThu>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_THUTHU;

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        ThuThu user;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_MATT)));
                String username = cursor.getString(cursor.getColumnIndex(KEY_TAIKHOANTT));
                String tenTHuThu = cursor.getString(cursor.getColumnIndex(KEY_HOTENTT));
                String password = cursor.getString(cursor.getColumnIndex(KEY_MATKHAUTT));
                byte[] imgByte = cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE));
                user = new ThuThu(username ,id , password , tenTHuThu  ,imgByte);
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
        return mSQLiteDatabase.delete(TABLE_THUTHU, KEY_MATT + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    public boolean themKind(ThuThu thuthu) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HOTENTT, thuthu.getHoTenThuThu());
        values.put(KEY_TAIKHOANTT, thuthu.getTaiKhoan());
        values.put(KEY_MATKHAUTT, thuthu.getMaKhau());
        values.put(KEY_IMAGE, thuthu.getThuthuPhoto());
        long result = this.mSQLiteDatabase.insert(TABLE_THUTHU, null, values);
        if (result <= 0) {
            return false;
        }
        return true;
    }

    public boolean editKind(ThuThu thuthu) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HOTENTT, thuthu.getHoTenThuThu());
        values.put(KEY_TAIKHOANTT, thuthu.getTaiKhoan());
        values.put(KEY_MATKHAUTT, thuthu.getMaKhau());
        values.put(KEY_IMAGE, thuthu.getThuthuPhoto());
        int r = this.mSQLiteDatabase.update(TABLE_THUTHU, values, KEY_MATT + "=?", new String[]{String.valueOf(thuthu.getMaThuThu())});
        if (r <= 0) {
            return false;
        }
        return true;
    }

    @SuppressLint("Range")
    public List<ThuThu> getListSearch(String tenSachSearch) {
        List<ThuThu> list = new ArrayList<ThuThu>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_THUTHU + "." + KEY_MATT +
                " , " + KEY_HOTENTT +
                " , " + KEY_TAIKHOANTT +
                " , " + KEY_MATKHAUTT +
                " , " + KEY_IMAGE +
                " FROM " + TABLE_THUTHU +
                " WHERE " + TABLE_THUTHU + "." + KEY_TAIKHOANTT + " LIKE '%" + tenSachSearch + "%'";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        ThuThu user;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_MATT)));
                String tenTHuThu = cursor.getString(cursor.getColumnIndex(KEY_HOTENTT));
                String username = cursor.getString(cursor.getColumnIndex(KEY_TAIKHOANTT));
                String password = cursor.getString(cursor.getColumnIndex(KEY_MATKHAUTT));
                byte[] imgByte = cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE));
                user = new ThuThu(username ,id , password , tenTHuThu  ,imgByte);
                list.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return list;
    }

    public boolean changePassword(ThuThu thuthu) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MATKHAUTT, thuthu.getMaKhau());
        int r = this.mSQLiteDatabase.update(TABLE_THUTHU, values, KEY_MATT + "=?", new String[]{String.valueOf(thuthu.getMaThuThu())});
        if (r <= 0) {
            return false;
        }
        return true;
    }
}
