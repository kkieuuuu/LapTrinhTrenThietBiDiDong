package com.example.QLTHUVIEN.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.QLTHUVIEN.dao.DaoLib;
import com.example.QLTHUVIEN.model.LoanSlip.LoanSlip;
import com.example.QLTHUVIEN.model.LoanSlip.LoanSlipBuilder;
import com.example.QLTHUVIEN.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Calendar;

public class AddLoanSlipActivity extends AppCompatActivity {
    private MaterialToolbar toolBar;
    private TextView createTaiKhoan;
    private TextView tvghichu;
    private LinearLayout contenMember;
    private Spinner spNameMember;
    private LinearLayout contenLib;
    private Spinner spLibrary;
    private LinearLayout contenBook;
    private Spinner spBook;
    private LinearLayout conPrice;
    private TextView tvGiaGoc;
    private LinearLayout contenDate;
    private TextView tvNgay,tvNgayTra;
    private LinearLayout conTenSatus;
    private Switch trangThaiSatus;
    private Button btDangKi;
    private LinearLayout ll1;
    private TextView textDangNhap;
    private DaoLib mDaoLib;
    private int idMember, idLibrary, idBook, giaKhuyenMai;
    private float giaGoc;
    private DatePickerDialog datePickerDialog;
    private int trangthai = 1;
    private float tongtien1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loan_slip);

        toolBar = (MaterialToolbar) findViewById(R.id.tool_bar);
        initToolBar();
        createTaiKhoan = (TextView) findViewById(R.id.createTaiKhoan);
        tvghichu = (TextView) findViewById(R.id.tvghichu);
        contenMember = (LinearLayout) findViewById(R.id.contenMember);
        spNameMember = (Spinner) findViewById(R.id.spNameMember);
        contenLib = (LinearLayout) findViewById(R.id.contenLib);
        spLibrary = (Spinner) findViewById(R.id.spLibrary);
        contenBook = (LinearLayout) findViewById(R.id.contenBook);
        spBook = (Spinner) findViewById(R.id.spBook);
        conPrice = (LinearLayout) findViewById(R.id.conPrice);
        tvGiaGoc = (TextView) findViewById(R.id.tvGiaGoc);
        contenDate = (LinearLayout) findViewById(R.id.contenDate);
        tvNgay = (TextView) findViewById(R.id.tvNgay);
        tvNgayTra = (TextView) findViewById(R.id.tvNgaytra);
        conTenSatus = (LinearLayout) findViewById(R.id.conTenSatus);
        trangThaiSatus = (Switch) findViewById(R.id.trangThaiSatus);
        btDangKi = (Button) findViewById(R.id.btDangKi);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        textDangNhap = (TextView) findViewById(R.id.textDangNhap);
        mDaoLib = new DaoLib(this);
        int level =  getIntent().getIntExtra("idthuthu",0);
        Toast.makeText(this, "level"+level, Toast.LENGTH_SHORT).show();
        inispiner();
        iniDate();
        btDangKi.setOnClickListener(view -> {
            if (tvNgay.getText().toString().equals("dd/MM/yyyy")) {
                tvghichu.setText("You Have Not Selected a Date");
                tvghichu.setTextColor(Color.RED);
                return;
            } else {
                LoanSlip thutthu = new LoanSlipBuilder()
                        .builderMaThuThu(idLibrary)
                        .builderMaThanhVien(idMember)
                        .builderMaSach(idBook)
                        .builderTienThue(tongtien1)
                        .builderNgayThue(tvNgay.getText().toString())
                        .builderNgayTra(tvNgayTra.getText().toString())
                        .builderTrangThaiMuong(trangthai)
                        .build();
                if (mDaoLib.themKind(thutthu) == true) {
                    Toast.makeText(this, "More success!", Toast.LENGTH_SHORT).show();
                    this.onBackPressed();

                } else {
                    Toast.makeText(this, "More failure!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void inispiner() {
        this.spNameMember.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mDaoLib.getListMember()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((CheckedTextView) view).setTextColor(Color.rgb(92, 224, 254));
                ((CheckedTextView) view).setTextSize(15);
                return view;
            }
        });
        this.spNameMember.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idMember = mDaoLib.getidMembers(spNameMember.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.spLibrary.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mDaoLib.getListLibrary()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((CheckedTextView) view).setTextColor(Color.rgb(92, 224, 254));
                ((CheckedTextView) view).setTextSize(15);
                return view;
            }
        });
        this.spLibrary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idLibrary = mDaoLib.getIdLibrary(spLibrary.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.spBook.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mDaoLib.getListBook()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((CheckedTextView) view).setTextColor(Color.rgb(92, 224, 254));
                ((CheckedTextView) view).setTextSize(15);
                return view;
            }
        });
        this.spBook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idBook = mDaoLib.getIdBook(spBook.getSelectedItem().toString());
                giaGoc = mDaoLib.getGiaGocSach(spBook.getSelectedItem().toString());
                giaKhuyenMai = mDaoLib.getGiaKhuyenMai(spBook.getSelectedItem().toString());
                float tongtien = giaGoc * (Float.parseFloat(String.valueOf(giaKhuyenMai)) / 100);
                tongtien1 = giaGoc - tongtien;
                double tongtien2 = (double) tongtien1;
                double roundOff = (double) Math.round(tongtien2 * 100) / 100;
                tvGiaGoc.setText(roundOff + " $");


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.trangThaiSatus.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                trangthai = 1;
            } else {
                trangthai = 0;
            }
        });


    }

    private void iniDate() {
        tvNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                int m = calendar.get(Calendar.MONTH);
                int y = calendar.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(AddLoanSlipActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        final String NgayGD = year + "-" + String.format("%02d",(month + 1)) + "-" + String.format("%02d",dayOfMonth);
                        tvNgay.setText(NgayGD);
                    }
                }, y, m, d);
                datePickerDialog.show();
            }
        });

        tvNgayTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                int m = calendar.get(Calendar.MONTH);
                int y = calendar.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(AddLoanSlipActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        final String NgayGD = year + "-" + String.format("%02d",(month + 1)) + "-" + String.format("%02d",dayOfMonth);
                        tvNgayTra.setText(NgayGD);
                    }
                }, y, m, d);
                datePickerDialog.show();
            }
        });
    }

    private void initToolBar() {
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }
}