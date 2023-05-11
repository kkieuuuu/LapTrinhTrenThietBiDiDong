package com.example.QLTHUVIEN.dao;


import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_GIATHUE;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_GIATHUEKHUYENMAI;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_HOTENTT;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_HOTENTV;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_MAPHIEUMUON;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_MASACH;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_MATT;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_MATV;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_NGAYMUON;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_NGAYTRA;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_PHIEUMUON_SACH_MASACH;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_PHIEUMUON_THUTHU_MATHUTHU;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_TENSACH;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_TIENTHUE;
import static com.example.QLTHUVIEN.database.MySqlHeper.KEY_TRASACH;
import static com.example.QLTHUVIEN.database.MySqlHeper.TABLE_PHIEUMUON;
import static com.example.QLTHUVIEN.database.MySqlHeper.TABLE_SACH;
import static com.example.QLTHUVIEN.database.MySqlHeper.TABLE_THANHVIEN;
import static com.example.QLTHUVIEN.database.MySqlHeper.TABLE_THUTHU;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.QLTHUVIEN.database.MySqlHeper;
import com.example.QLTHUVIEN.model.LoanSlip.LoanSlip;
import com.example.QLTHUVIEN.model.LoanSlip.LoanSlipBuilder;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DaoLib {
    SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
    MySqlHeper mMySqlHeper;
    SQLiteDatabase mSQLiteDatabase;

    public DaoLib(Context context) {
        mMySqlHeper = new MySqlHeper(context);
    }


    public int getidMembers(String tenMember) {
        int id = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_THANHVIEN + "." + KEY_MATV +
                " AS idMember FROM " + TABLE_THANHVIEN +
                " WHERE " + TABLE_THANHVIEN + "."
                + KEY_HOTENTV + " ='" + tenMember + "'";
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

    public int getIdLibrary(String tenLibrary) {
        int id = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_THUTHU + "." + KEY_MATT +
                " AS idLibrary FROM " + TABLE_THUTHU +
                " WHERE " + TABLE_THUTHU + "."
                + KEY_HOTENTT + " ='" + tenLibrary + "'";
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

    public int getIdBook(String tenBook) {
        int id = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_SACH + "." + KEY_MASACH +
                " AS idBook FROM " + TABLE_SACH +
                " WHERE " + TABLE_SACH + "."
                + KEY_TENSACH + " ='" + tenBook + "'";
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

    public List<String> getListMember() {
        List<String> result = new ArrayList<String>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_THANHVIEN;
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

    public List<String> getListLibrary() {
        List<String> result = new ArrayList<String>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_THUTHU;
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

    public List<String> getListBook() {
        List<String> result = new ArrayList<String>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_SACH;
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

    public int getGiaKhuyenMai(String tenSach) {
        int id = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_SACH + "." + KEY_GIATHUEKHUYENMAI +
                " AS moneykhuyenmai FROM " + TABLE_SACH +
                " WHERE " + TABLE_SACH + "."
                + KEY_TENSACH + " ='" + tenSach + "'";
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

    public float getGiaGocSach(String tenSach) {
        float id = 0;
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_SACH + "." + KEY_GIATHUE +
                " AS moneygoc FROM " + TABLE_SACH +
                " WHERE " + TABLE_SACH + "."
                + KEY_TENSACH + " ='" + tenSach + "'";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                id = Float.parseFloat(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return id;
    }

    public List<LoanSlip> getListofLoanSlips() {
        List<LoanSlip> list = new ArrayList<LoanSlip>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT " + TABLE_PHIEUMUON + "." + KEY_MAPHIEUMUON +
                "," + TABLE_THANHVIEN + "." + KEY_HOTENTV + " AS TENNGUOIMUON" +
                "," + TABLE_THUTHU + "." + KEY_HOTENTT + " AS TENNGUOICHOMUON" +
                "," + TABLE_SACH + "." + KEY_TENSACH + " AS TENSACHMUON" +
                " , " + KEY_TIENTHUE +
                " , " + KEY_NGAYMUON +
                " , " + KEY_NGAYTRA +
                ", " + KEY_TRASACH
                + " FROM " + TABLE_PHIEUMUON +
                " JOIN " + TABLE_THANHVIEN + " ON " + TABLE_THANHVIEN + "." + KEY_MATV + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN
                + " JOIN " + TABLE_THUTHU + " ON " + TABLE_THUTHU + "." + KEY_MATT + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_THUTHU_MATHUTHU
                + " JOIN " + TABLE_SACH + " ON " + TABLE_SACH + "." + KEY_MASACH + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH;

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        LoanSlip user;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                int id = Integer.parseInt(cursor.getString(0));
                String tenThanhVien = cursor.getString(1);
                String tenLIb = cursor.getString(2);
                String tenBook = cursor.getString(3);
                float giaThue = Float.parseFloat(cursor.getString(4));
                String ngayMuon = cursor.getString(5);
                String ngayTra = cursor.getString(6);
                int trangthai = Integer.parseInt(cursor.getString(7));
                user = new LoanSlipBuilder()
                        .builderId(id)
                        .builderTienThue(giaThue)
                        .builderNgayThue(ngayMuon)
                        .builderNgayTra(ngayTra)
                        .builderTrangThaiMuong(trangthai)
                        .builderTenNguoiMuong(tenThanhVien)
                        .builderNguoiChoMuon(tenLIb)
                        .builderTenSachMuong(tenBook)
                        .build();
                list.add(user);
                cursor.moveToNext();

            }
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return list;
    }

    public boolean themKind(LoanSlip mode) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PHIEUMUON_THUTHU_MATHUTHU, mode.getMaTHuThu());
        values.put(KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN, mode.getMaThanhVien());
        values.put(KEY_PHIEUMUON_SACH_MASACH, mode.getMaSach());
        values.put(KEY_TIENTHUE, mode.getTienThue());
        values.put(KEY_NGAYMUON, mode.getNgayThue());
        values.put(KEY_NGAYTRA, mode.getNgayTra());
        values.put(KEY_TRASACH, mode.getTrangThaiMuon());
        long result = this.mSQLiteDatabase.insert(TABLE_PHIEUMUON, null, values);
        if (result <= 0) {
            return false;
        }
        return true;
    }

    public boolean deleteTitle(int id) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        return mSQLiteDatabase.delete(TABLE_PHIEUMUON, KEY_MAPHIEUMUON + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    public boolean editeLoanSlip(LoanSlip mode) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PHIEUMUON_THUTHU_MATHUTHU, mode.getMaTHuThu());
        values.put(KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN, mode.getMaThanhVien());
        values.put(KEY_PHIEUMUON_SACH_MASACH, mode.getMaSach());
        values.put(KEY_TIENTHUE, mode.getTienThue());
        values.put(KEY_NGAYMUON, mode.getNgayThue());
        values.put(KEY_NGAYTRA, mode.getNgayTra());
        values.put(KEY_TRASACH, mode.getTrangThaiMuon());
        int r = this.mSQLiteDatabase.update(TABLE_PHIEUMUON, values, KEY_MAPHIEUMUON + "=?", new String[]{String.valueOf(mode.getId())});
        if (r <= 0) {
            return false;
        }
        return true;
    }

    public List<LoanSlip> getListofLoanSlipsSearch(boolean chuacha, boolean dacha, String etSearch) {
        List<LoanSlip> list = new ArrayList<LoanSlip>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "";
        if (chuacha == true && dacha == false && etSearch.isEmpty()) {
            sql = "SELECT " + TABLE_PHIEUMUON + "." + KEY_MAPHIEUMUON +
                    "," + TABLE_THANHVIEN + "." + KEY_HOTENTV + " AS TENNGUOIMUON" +
                    "," + TABLE_THUTHU + "." + KEY_HOTENTT + " AS TENNGUOICHOMUON" +
                    "," + TABLE_SACH + "." + KEY_TENSACH + " AS TENSACHMUON" +
                    " , " + KEY_TIENTHUE +
                    " , " + KEY_NGAYMUON +
                    " , " + KEY_NGAYTRA +
                    ", " + KEY_TRASACH
                    + " FROM " + TABLE_PHIEUMUON +
                    " JOIN " + TABLE_THANHVIEN + " ON " + TABLE_THANHVIEN + "." + KEY_MATV + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN
                    + " JOIN " + TABLE_THUTHU + " ON " + TABLE_THUTHU + "." + KEY_MATT + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_THUTHU_MATHUTHU
                    + " JOIN " + TABLE_SACH + " ON " + TABLE_SACH + "." + KEY_MASACH + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH
                    + " WHERE " + TABLE_PHIEUMUON + "." + KEY_TRASACH + " = 0";
        } else if (chuacha == false && dacha == true && etSearch.isEmpty()) {
            sql = "SELECT " + TABLE_PHIEUMUON + "." + KEY_MAPHIEUMUON +
                    "," + TABLE_THANHVIEN + "." + KEY_HOTENTV + " AS TENNGUOIMUON" +
                    "," + TABLE_THUTHU + "." + KEY_HOTENTT + " AS TENNGUOICHOMUON" +
                    "," + TABLE_SACH + "." + KEY_TENSACH + " AS TENSACHMUON" +
                    " , " + KEY_TIENTHUE +
                    " , " + KEY_NGAYMUON +
                    ", " + KEY_TRASACH
                    + " FROM " + TABLE_PHIEUMUON +
                    " JOIN " + TABLE_THANHVIEN + " ON " + TABLE_THANHVIEN + "." + KEY_MATV + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN
                    + " JOIN " + TABLE_THUTHU + " ON " + TABLE_THUTHU + "." + KEY_MATT + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_THUTHU_MATHUTHU
                    + " JOIN " + TABLE_SACH + " ON " + TABLE_SACH + "." + KEY_MASACH + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH
                    + " WHERE " + TABLE_PHIEUMUON + "." + KEY_TRASACH + " = 1";
        } else if (chuacha == false && dacha == false && !etSearch.isEmpty()) {
            sql = "SELECT " + TABLE_PHIEUMUON + "." + KEY_MAPHIEUMUON +
                    "," + TABLE_THANHVIEN + "." + KEY_HOTENTV + " AS TENNGUOIMUON" +
                    "," + TABLE_THUTHU + "." + KEY_HOTENTT + " AS TENNGUOICHOMUON" +
                    "," + TABLE_SACH + "." + KEY_TENSACH + " AS TENSACHMUON" +
                    " , " + KEY_TIENTHUE +
                    " , " + KEY_NGAYMUON +
                    " , " + KEY_NGAYTRA +
                    ", " + KEY_TRASACH
                    + " FROM " + TABLE_PHIEUMUON +
                    " JOIN " + TABLE_THANHVIEN + " ON " + TABLE_THANHVIEN + "." + KEY_MATV + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN
                    + " JOIN " + TABLE_THUTHU + " ON " + TABLE_THUTHU + "." + KEY_MATT + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_THUTHU_MATHUTHU
                    + " JOIN " + TABLE_SACH + " ON " + TABLE_SACH + "." + KEY_MASACH + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH
                    + " WHERE " + " TENMEMBERMUON = '" + etSearch + "'";
        } else if (chuacha == true && !etSearch.isEmpty()) {
            sql = "SELECT " + TABLE_PHIEUMUON + "." + KEY_MAPHIEUMUON +
                    "," + TABLE_THANHVIEN + "." + KEY_HOTENTV + " AS TENNHUOIMUON" +
                    "," + TABLE_THUTHU + "." + KEY_HOTENTT + " AS TENNGUOICHOMUON" +
                    "," + TABLE_SACH + "." + KEY_TENSACH + " AS TENSACHMUON" +
                    " , " + KEY_TIENTHUE +
                    " , " + KEY_NGAYMUON +
                    " , " + KEY_NGAYTRA +
                    ", " + KEY_TRASACH
                    + " FROM " + TABLE_PHIEUMUON +
                    " JOIN " + TABLE_THANHVIEN + " ON " + TABLE_THANHVIEN + "." + KEY_MATV + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN
                    + " JOIN " + TABLE_THUTHU + " ON " + TABLE_THUTHU + "." + KEY_MATT + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_THUTHU_MATHUTHU
                    + " JOIN " + TABLE_SACH + " ON " + TABLE_SACH + "." + KEY_MASACH + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH
                    + " WHERE " + TABLE_PHIEUMUON + "." + KEY_TRASACH + " = 0 AND " + TABLE_THANHVIEN + "." + KEY_HOTENTV + " = '" + etSearch + "'";
        } else if (dacha == true && !etSearch.isEmpty()) {
            sql = "SELECT " + TABLE_PHIEUMUON + "." + KEY_MAPHIEUMUON +
                    "," + TABLE_THANHVIEN + "." + KEY_HOTENTV + " AS TENNGUOIMUON" +
                    "," + TABLE_THUTHU + "." + KEY_HOTENTT + " AS TENNGUOICHOMUON" +
                    "," + TABLE_SACH + "." + KEY_TENSACH + " AS TENSACHMUON" +
                    " , " + KEY_TIENTHUE +
                    " , " + KEY_NGAYMUON +
                    " , " + KEY_NGAYTRA +
                    ", " + KEY_TRASACH
                    + " FROM " + TABLE_PHIEUMUON +
                    " JOIN " + TABLE_THANHVIEN + " ON " + TABLE_THANHVIEN + "." + KEY_MATV + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN
                    + " JOIN " + TABLE_THUTHU + " ON " + TABLE_THUTHU + "." + KEY_MATT + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_THUTHU_MATHUTHU
                    + " JOIN " + TABLE_SACH + " ON " + TABLE_SACH + "." + KEY_MASACH + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH
                    + " WHERE " + TABLE_PHIEUMUON + "." + KEY_TRASACH + " = 1 AND  " + TABLE_THANHVIEN + "." + KEY_HOTENTV + " = '" + etSearch + "'";
        }

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor!= null){
            LoanSlip user;
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int id = Integer.parseInt(cursor.getString(0));
                    String tenThanhVien = cursor.getString(1);
                    String tenLIb = cursor.getString(2);
                    String tenBook = cursor.getString(3);
                    float giaThue = Float.parseFloat(cursor.getString(4));
                    String ngayMuon = cursor.getString(5);
                    String ngaytra = cursor.getString(6);
                    int trangthai = Integer.parseInt(cursor.getString(7));
                    user = new LoanSlipBuilder()
                            .builderId(id)
                            .builderTienThue(giaThue)
                            .builderNgayThue(ngayMuon)
                            .builderNgayTra(ngaytra)
                            .builderTrangThaiMuong(trangthai)
                            .builderTenNguoiMuong(tenThanhVien)
                            .builderNguoiChoMuon(tenLIb)
                            .builderTenSachMuong(tenBook)
                            .build();
                    list.add(user);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        this.mSQLiteDatabase.close();
        return list;
    }


    public List<LoanSlip> sachDenHanTra(String daynow) {
        List<LoanSlip> list = new ArrayList<LoanSlip>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "";
            sql = "SELECT " + TABLE_PHIEUMUON + "." + KEY_MAPHIEUMUON +
                    "," + TABLE_THANHVIEN + "." + KEY_HOTENTV + " AS TENMEMBERMUON" +
                    "," + TABLE_THUTHU + "." + KEY_HOTENTT + " AS TENNGUOICHOMUON" +
                    "," + TABLE_SACH + "." + KEY_TENSACH + " AS TENSACHMUON" +
                    " , " + KEY_TIENTHUE +
                    " , " + KEY_NGAYMUON +
                    " , " + KEY_NGAYTRA +
                    ", " + KEY_TRASACH
                    + " FROM " + TABLE_PHIEUMUON +
                    " JOIN " + TABLE_THANHVIEN + " ON " + TABLE_THANHVIEN + "." + KEY_MATV + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN
                    + " JOIN " + TABLE_THUTHU + " ON " + TABLE_THUTHU + "." + KEY_MATT + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_THUTHU_MATHUTHU
                    + " JOIN " + TABLE_SACH + " ON " + TABLE_SACH + "." + KEY_MASACH + " = " + TABLE_PHIEUMUON + "." + KEY_PHIEUMUON_SACH_MASACH
                    + " WHERE " +" (SELECT DATEDIFF(day, 'PHIEUMUON.NGAYTRA', '2017/08/28')AS DateDiff)"+ TABLE_PHIEUMUON + "." + KEY_TRASACH + " = 0";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor!= null){
            LoanSlip user;
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int id = Integer.parseInt(cursor.getString(0));
                    String tenThanhVien = cursor.getString(1);
                    String tenLIb = cursor.getString(2);
                    String tenBook = cursor.getString(3);
                    float giaThue = Float.parseFloat(cursor.getString(4));
                    String ngayMuon = cursor.getString(5);
                    String ngaytra = cursor.getString(6);
                    int trangthai = Integer.parseInt(cursor.getString(7));
                    user = new LoanSlipBuilder()
                            .builderId(id)
                            .builderTienThue(giaThue)
                            .builderNgayThue(ngayMuon)
                            .builderNgayTra(ngaytra)
                            .builderTrangThaiMuong(trangthai)
                            .builderTenNguoiMuong(tenThanhVien)
                            .builderNguoiChoMuon(tenLIb)
                            .builderTenSachMuong(tenBook)
                            .build();
                    list.add(user);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        this.mSQLiteDatabase.close();
        return list;
    }


    public List<LoanSlip> sachQuaHanTra1() {
        List<LoanSlip> list = new ArrayList<LoanSlip>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "";
        sql = " SELECT PHIEUMUON.MAPHIEUMUON,THANHVIEN.HOTENTHANHVIEN AS TENMEMBERMUON,THUTHU.HOTENTHUTHU AS TENNGUOICHOMUON" +
                "                ,SACH.TENSACH AS TENSACHMUON" +
                "                ,TIENTHUE ,NGAY  ,NGAYTRA" +
                "                , TRASACH" +
                "                FROM PHIEUMUON" +
                "                 JOIN THANHVIEN  ON THANHVIEN.MATHANHVIEN = PHIEUMUON.MATHANHVIEN" +
                "              JOIN THUTHU ON THUTHU.MATHUTHU  = PHIEUMUON.MATHUTHU" +
                "                JOIN SACH ON SACH.MASACH  = PHIEUMUON.MASACH" +
                "               WHERE (SELECT julianday() - julianday(NGAYTRA)) > 0 AND TRASACH = 0";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor!= null){
            LoanSlip user;
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int id = Integer.parseInt(cursor.getString(0));
                    String tenThanhVien = cursor.getString(1);
                    String tenLIb = cursor.getString(2);
                    String tenBook = cursor.getString(3);
                    float giaThue = Float.parseFloat(cursor.getString(4));
                    String ngayMuon = cursor.getString(5);
                    String ngaytra = cursor.getString(6);
                    int trangthai = Integer.parseInt(cursor.getString(7));
                    user = new LoanSlipBuilder()
                            .builderId(id)
                            .builderTienThue(giaThue)
                            .builderNgayThue(ngayMuon)
                            .builderNgayTra(ngaytra)
                            .builderTrangThaiMuong(trangthai)
                            .builderTenNguoiMuong(tenThanhVien)
                            .builderNguoiChoMuon(tenLIb)
                            .builderTenSachMuong(tenBook)
                            .build();
                    list.add(user);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        this.mSQLiteDatabase.close();
        return list;
    }

    public List<LoanSlip> sachDenHanTra() {
        List<LoanSlip> list = new ArrayList<LoanSlip>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "";
        sql = " SELECT PHIEUMUON.MAPHIEUMUON,THANHVIEN.HOTENTHANHVIEN AS TENMEMBERMUON,THUTHU.HOTENTHUTHU AS TENNGUOICHOMUON" +
                "                ,SACH.TENSACH AS TENSACHMUON" +
                "                ,TIENTHUE ,NGAY  ,NGAYTRA" +
                "                , TRASACH" +
                "                FROM PHIEUMUON" +
                "                 JOIN THANHVIEN  ON THANHVIEN.MATHANHVIEN = PHIEUMUON.MATHANHVIEN" +
                "              JOIN THUTHU ON THUTHU.MATHUTHU  = PHIEUMUON.MATHUTHU" +
                "                JOIN SACH ON SACH.MASACH  = PHIEUMUON.MASACH" +
                "               WHERE (SELECT julianday() - julianday(NGAYTRA)) > -2 AND TRASACH = 0 AND (SELECT julianday() - julianday(NGAYTRA)) < 0";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor!= null){
            LoanSlip user;
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int id = Integer.parseInt(cursor.getString(0));
                    String tenThanhVien = cursor.getString(1);
                    String tenLIb = cursor.getString(2);
                    String tenBook = cursor.getString(3);
                    float giaThue = Float.parseFloat(cursor.getString(4));
                    String ngayMuon = cursor.getString(5);
                    String ngaytra = cursor.getString(6);
                    int trangthai = Integer.parseInt(cursor.getString(7));
                    user = new LoanSlipBuilder()
                            .builderId(id)
                            .builderTienThue(giaThue)
                            .builderNgayThue(ngayMuon)
                            .builderNgayTra(ngaytra)
                            .builderTrangThaiMuong(trangthai)
                            .builderTenNguoiMuong(tenThanhVien)
                            .builderNguoiChoMuon(tenLIb)
                            .builderTenSachMuong(tenBook)
                            .build();
                    list.add(user);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        this.mSQLiteDatabase.close();
        return list;
    }


    public List<LoanSlip> getThongke() {
        List<LoanSlip> list = new ArrayList<LoanSlip>();
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "";
        sql = " SELECT PHIEUMUON.MAPHIEUMUON,THANHVIEN.HOTENTHANHVIEN AS TENMEMBERMUON,THUTHU.HOTENTHUTHU AS TENNGUOICHOMUON" +
                "                ,SACH.TENSACH AS TENSACHMUON" +
                "                ,TIENTHUE ,NGAY  ,NGAYTRA" +
                "                , TRASACH" +
                "                FROM PHIEUMUON" +
                "                 JOIN THANHVIEN  ON THANHVIEN.MATHANHVIEN = PHIEUMUON.MATHANHVIEN" +
                "              JOIN THUTHU ON THUTHU.MATHUTHU  = PHIEUMUON.MATHUTHU" +
                "                JOIN SACH ON SACH.MASACH  = PHIEUMUON.MASACH" +
                "               WHERE (SELECT julianday() - julianday(NGAYTRA)) > -2 AND TRASACH = 0 AND (SELECT julianday() - julianday(NGAYTRA)) < 0";

        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        if (cursor!= null){
            LoanSlip user;
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int id = Integer.parseInt(cursor.getString(0));
                    String tenThanhVien = cursor.getString(1);
                    String tenLIb = cursor.getString(2);
                    String tenBook = cursor.getString(3);
                    float giaThue = Float.parseFloat(cursor.getString(4));
                    String ngayMuon = cursor.getString(5);
                    String ngaytra = cursor.getString(6);
                    int trangthai = Integer.parseInt(cursor.getString(7));
                    user = new LoanSlipBuilder()
                            .builderId(id)
                            .builderTienThue(giaThue)
                            .builderNgayThue(ngayMuon)
                            .builderNgayTra(ngaytra)
                            .builderTrangThaiMuong(trangthai)
                            .builderTenNguoiMuong(tenThanhVien)
                            .builderNguoiChoMuon(tenLIb)
                            .builderTenSachMuong(tenBook)
                            .build();
                    list.add(user);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        this.mSQLiteDatabase.close();
        return list;
    }
}
