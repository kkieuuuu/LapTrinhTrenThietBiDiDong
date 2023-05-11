package com.example.QLTHUVIEN.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlHeper extends SQLiteOpenHelper {

    public static final String TABLE_THANHVIEN = "THANHVIEN";
    public static final String KEY_MATV = "MATHANHVIEN";
    public static final String KEY_HOTENTV = "HOTENTHANHVIEN";
    public static final String KEY_NAMSINHTV = "NAMSINHTHANHVIEN";
    public static final String KEY_TAIKHOANTV = "TAIKHOANTHANHVIEN";
    public static final String KEY_MATKHAUTV = "MATKHAUTHANHVIEN";


    public static final String TABLE_THUTHU = "THUTHU";
    public static final String KEY_MATT = "MATHUTHU";
    public static final String KEY_TAIKHOANTT = "TAIKHOANTHUTHU";
    public static final String KEY_HOTENTT = "HOTENTHUTHU";
    public static final String KEY_MATKHAUTT = "MATKHAUTHUTHU";
    public static final String KEY_IMAGE = "IMAGETHUTHU";


    public static final String TABLE_LOAISACH = "LOAISACH";
    public static final String KEY_MALOAI = "MALOAISACH";
    public static final String KEY_TENLOAI = "TENLOAISACH";

    public static final String TABLE_SACH = "SACH";
    public static final String KEY_MASACH = "MASACH";
    public static final String KEY_TENSACH = "TENSACH";
    public static final String KEY_GIATHUE = "GIATHUE";
    public static final String KEY_GIATHUEKHUYENMAI = "KHUYENMAI";
    public static final String KEY_SACH_LOAISACH_MALOAI = "LOAISACH";

    public static final String TABLE_PHIEUMUON = "PHIEUMUONSACH";
    public static final String KEY_MAPHIEUMUON = "MAPHIEUMUON";
    public static final String KEY_PHIEUMUON_THUTHU_MATHUTHU = "MATHUTHU";
    public static final String KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN = "MATHANHVIEN";
    public static final String KEY_PHIEUMUON_SACH_MASACH = "MASACH";
    public static final String KEY_TIENTHUE = "TIENTHUE";
    public static final String KEY_NGAYMUON = "NGAYMUON";
    public static final String KEY_NGAYTRA = "NGAYTRA";
    public static final String KEY_TRASACH = "TRASACH";

    public static final String TABLE_PHIEUNHAPSACH = "PHIEUNHAPSACH";
    public static final String KEY_MAPNS = "MAPHIEUNHAP";
    public static final String KEY_SOLUONG = "SOLUONG";
    public static final String KEY_NGAYNHAP = "NGAYNHAP";
    public static final String KEY_PHIEUNHAPSACH_MATT = "MATT";
    public static final String KEY_PHIEUNHAPSACH_MASACH = "MASACH";
    public static final String KEY_TENTG = "TENTG";
    public static final String KEY_PHIEUNHAPSACH_MANXB = "MANXB";

    public static final String TABLE_NXB = "NHAXUATBAN";
    public static final String KEY_MANXB = "MANXB";
    public static final String KEY_TENNXB = "TEN";
    public static final String KEY_SDTNXB = "SDT";
    public static final String KEY_DIACHIMXB = "DIACHI";

    public MySqlHeper(Context context) {
        super(context, "QUANLITHUVIEN.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql1 = "CREATE TABLE " + TABLE_THANHVIEN
                + "("
                + KEY_MATV + " INTEGER PRIMARY KEY , "
                + KEY_HOTENTV + " TEXT NOT NULL, "
                + KEY_NAMSINHTV + " INTEGER NOT NULL,"
                + KEY_TAIKHOANTV + " TEXT NOT NULL ,"
                + KEY_MATKHAUTV + " TEXT NOT NULL"
                + ")";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "CREATE TABLE " + TABLE_THUTHU
                + "("
                + KEY_MATT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_HOTENTT + " TEXT NOT NULL ,"
                + KEY_TAIKHOANTT + " TEXT NOT NULL ,"
                + KEY_MATKHAUTT + " TEXT NOT NULL,"
                + KEY_IMAGE + " BLOB "
                + ")";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "CREATE TABLE " + TABLE_LOAISACH
                + "("
                + KEY_MALOAI + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_TENLOAI + " TEXT NOT NULL "
                + ")";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "CREATE TABLE " + TABLE_SACH
                + "("
                + KEY_MASACH + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_TENSACH + " TEXT NOT NULL ,"
                + KEY_GIATHUE + " MONEY NOT NULL ,"
                + KEY_GIATHUEKHUYENMAI + " MONEY  ,"
                + KEY_SACH_LOAISACH_MALOAI + " INTEGER REFERENCES " + TABLE_LOAISACH + "( " + KEY_SACH_LOAISACH_MALOAI + ")"
                + ")";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "CREATE TABLE " + TABLE_PHIEUMUON
                + "("
                + KEY_MAPHIEUMUON + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_PHIEUMUON_THUTHU_MATHUTHU + " INTEGER REFERENCES " + TABLE_THUTHU + "( " + KEY_PHIEUMUON_THUTHU_MATHUTHU + "),"
                + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN + " INTEGER REFERENCES " + TABLE_THANHVIEN + "( " + KEY_PHIEUMUON_THANHVIEN_MATHANHVIEN + "),"
                + KEY_PHIEUMUON_SACH_MASACH + " INTEGER REFERENCES " + TABLE_SACH + "( " + KEY_PHIEUMUON_SACH_MASACH + "),"
                + KEY_TIENTHUE + " MONEY NOT NULL ,"
                + KEY_NGAYMUON + " DATE NOT NULL ,"
                + KEY_NGAYTRA + " DATE NOT NULL ,"
                + KEY_TRASACH + " INTEGER NOT NULL "
                + ")";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_THUTHU + " VALUES ( null,'LÂM HỒ THIÊN TỐNG','thientong' ,'thientong' , null)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_LOAISACH + " VALUES ( 1 ,'SELF-HELP BOOK')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_LOAISACH + " VALUES ( 2 ,'ENGLISH')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_LOAISACH + " VALUES (  3,'COMIC')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_LOAISACH + " VALUES (  4,'NOVEL')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_SACH + " VALUES ( 11 ,'ONENESS WITH ALL LIFE',12, 24, 1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_SACH + " VALUES ( 222 ,'TOEFL IBT PREP', 14, 26, 2)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_SACH + " VALUES (33 ,'AMERICAN BORN CHINESE',18, 35, 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_SACH + " VALUES ( 44 ,'THE ANXIETY TOOLKIT', 19, 28, 1)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_SACH + " VALUES ( 55 ,'A DEATH IN THE FAMILY', 12, 43, 3)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_SACH + " VALUES (66 ,'A STEP FROM HEAVEN', 36, 54, 4)";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_THANHVIEN + " VALUES ( 111,'NGUYỄN THỊ KIM KIỀU',2002,'kimkieu' ,'kimkieu')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_THANHVIEN + " VALUES ( 222 ,'HOÀNG NHẬT', 2003,'hoangnhat','hoangnhat')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_THANHVIEN + " VALUES ( 333 ,'THỚI VIỆT TRÀ', 2002,'viettra','vietra')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_THANHVIEN + " VALUES ( 444 ,'LỆ THỦY', 2003,'lethuy','lethuy')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_THANHVIEN + " VALUES ( 555 ,'MINH MINH', 2000,'minhminh','minhminh')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "INSERT INTO " + TABLE_THANHVIEN + " VALUES ( 666 ,'THÀNH VŨ', 2001,'thanhvu','thanhvu')";
        sqLiteDatabase.execSQL(sql1);
        sql1 = "CREATE TABLE " + TABLE_NXB
                + "("
                + KEY_MANXB + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_TENNXB + " TEXT NOT NULL ,"
                + KEY_DIACHIMXB + " TEXT NOT NULL ,"
                + KEY_SDTNXB + " TEXT NOT NULL "
                + ")";
        sqLiteDatabase.execSQL(sql1);

        sql1 = "CREATE TABLE " + TABLE_PHIEUNHAPSACH
                + "("
                + KEY_MAPNS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_TENTG + " TEXT NOT NULL ,"
                + KEY_PHIEUNHAPSACH_MATT + " TEXT NOT NULL ,"
                + KEY_PHIEUNHAPSACH_MASACH + " TEXT NOT NULL ,"
                + KEY_NGAYNHAP + " TEXT NOT NULL, "
                + KEY_SOLUONG + " INTEGER NOT NULL, "
                + KEY_PHIEUNHAPSACH_MANXB + " TEXT NOT NULL "
                + ")";
        sqLiteDatabase.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_THANHVIEN);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_THUTHU);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LOAISACH);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SACH);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PHIEUMUON);
        onCreate(sqLiteDatabase);

    }
}
