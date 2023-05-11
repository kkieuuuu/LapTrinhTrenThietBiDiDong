package com.example.QLTHUVIEN.ui;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.QLTHUVIEN.dao.DaoBook;
import com.example.QLTHUVIEN.dao.DaoLib;
import com.example.QLTHUVIEN.model.LoanSlip.LoanSlip;
import com.example.QLTHUVIEN.model.TopMost;
import com.example.QLTHUVIEN.ui.Adapter.AdapterTreHen;
import com.example.QLTHUVIEN.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import soup.neumorphism.NeumorphCardView;

public class RevenueActivity extends AppCompatActivity {
    private MaterialToolbar toolBar;
    private List<TopMost> list;
    private DaoBook mDaoBook;
    private NeumorphCardView btnStartDate;
    private TextView dayStart;
    private RecyclerView recyclerviewThongKe;
    private TextView monthAndYearStart;
    private NeumorphCardView btnEndDate;
    private TextView dayEnd;
    private TextView monthAndYearEnd;
    private NeumorphCardView btnSubmit;
    private TextView tvRevenue, tvSachDenHan;
    private TextView tvDaTra,tvDaTra1;
    private TextView tvChuaTra,tvChuaTra1;
    private RadioGroup rg;
    AdapterTreHen adapterTreHen;
    String monthandYear = "2022-12";
    List<LoanSlip> lst = new ArrayList<>();

    private DatePickerDialog mDatePickerDialog;
    private String tvStartDate, tvEndDate;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);

        recyclerviewThongKe = findViewById(R.id.recyclerviewThongKe);
        toolBar = (MaterialToolbar) findViewById(R.id.tool_bar);
        btnStartDate = (NeumorphCardView) findViewById(R.id.btnStartDate);
        dayStart = (TextView) findViewById(R.id.dayStart);
        monthAndYearStart = (TextView) findViewById(R.id.monthAndYearStart);
        btnEndDate = (NeumorphCardView) findViewById(R.id.btnEndDate);
        dayEnd = (TextView) findViewById(R.id.dayEnd);
        monthAndYearEnd = (TextView) findViewById(R.id.monthAndYearEnd);
        btnSubmit = (NeumorphCardView) findViewById(R.id.btnSubmit);
        tvRevenue = (TextView) findViewById(R.id.tvRevenue);
        tvSachDenHan = findViewById(R.id.tvSachDenHan);
        tvDaTra = (TextView) findViewById(R.id.tvDaTra);
        tvChuaTra = (TextView) findViewById(R.id.tvChuaTra);
        tvDaTra1 = (TextView) findViewById(R.id.tvDaTra1);
        tvChuaTra1 = (TextView) findViewById(R.id.tvChuaTra1);
        rg = (RadioGroup) findViewById(R.id.daily_weekly_button_view);
        DaoLib daoLib = new DaoLib(this);
        adapterTreHen = new AdapterTreHen(this);
        if (daoLib.sachQuaHanTra1() != null && daoLib.sachQuaHanTra1().size() > 0) {
            lst.clear();
            lst = daoLib.sachQuaHanTra1();
            tvSachDenHan.setText("SỐ LƯỢNG SÁCH QUÁ HẠN: "+lst.size());
        }
        if (daoLib.sachDenHanTra() != null && daoLib.sachDenHanTra().size() > 0) {
            recyclerviewThongKe.setVisibility(View.VISIBLE);
            lst = daoLib.sachDenHanTra();
            adapterTreHen.setData(lst);
            recyclerviewThongKe.setAdapter(adapterTreHen);
        }
        Log.d("TAG", "onCheckedChanged: "+daoLib.sachQuaHanTra1().size());
        this.mDaoBook = new DaoBook(this);
        initToolBar();
        datepiker();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch(checkedId){
                    case R.id.radio0:
                        lst.clear();
                        recyclerviewThongKe.setVisibility(View.GONE);
                        if (daoLib.sachDenHanTra() != null && daoLib.sachDenHanTra().size() > 0) {
                            recyclerviewThongKe.setVisibility(View.VISIBLE);
                            lst = daoLib.sachDenHanTra();
                            adapterTreHen.setData(lst);
                            recyclerviewThongKe.setAdapter(adapterTreHen);
                        }
                        break;
                    case R.id.radio1:
                        lst.clear();
                        recyclerviewThongKe.setVisibility(View.GONE);
                        if (daoLib.sachQuaHanTra1() != null && daoLib.sachQuaHanTra1().size() > 0) {
                            recyclerviewThongKe.setVisibility(View.VISIBLE);
                            lst = daoLib.sachQuaHanTra1();
                            adapterTreHen.setData(lst);
                            recyclerviewThongKe.setAdapter(adapterTreHen);
                        }
                        break;
                    case R.id.radio2:
//                        mListOfLoanSlip.clear();
//                        mListOfLoanSlip = mDaoLib.getListofLoanSlipsSearch(radiochuacha.isChecked() ,radiodacha.isChecked() , etSearch.getText().toString().trim()) ;
//                        mAdapterLoanSlip.setData(mListOfLoanSlip);
//                        recyclerview.setAdapter(mAdapterLoanSlip);
                        break;

                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void datepiker() {
        btnEndDate.setOnClickListener(view -> {
            final Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH);
            int yeat = cal.get(Calendar.YEAR);
            mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    final int date = i2;
                    monthandYear = i + "-" + String.format("%02d",(i1 + 1));
                    dayEnd.setText(String.valueOf(date));
                    monthAndYearEnd.setText(monthandYear);
                    tvEndDate = i + "-" +String.format("%02d",(i1 + 1)) + "-" + String.format("%02d",i2);
                    Log.d("TAG", "onDateSet:End +" + tvEndDate);
                            checkDate( tvEndDate);
                    //   Toast.makeText(RevenueActivity.this, tvEndDate, Toast.LENGTH_SHORT).show();
                }
            }, yeat, month, day);
            mDatePickerDialog.show();


        });
        btnStartDate.setOnClickListener(view -> {
            final Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH);
            int yeat = cal.get(Calendar.YEAR);
            mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    final int date = i2;
                    String monthandYear1 =i + "-" +String.format("%02d",(i1 + 1));
                    dayStart.setText(String.valueOf(date));
                    monthAndYearStart.setText(monthandYear1);
                    tvStartDate = i + "-" +String.format("%02d",(i1 + 1)) + "-" + String.format("%02d",i2);
                    Log.d("TAG", "onDateSet:Start +" + tvStartDate);
                            checkDate(tvEndDate);

                }
            }, yeat, month, day);

            mDatePickerDialog.show();

        });
    }

    private void checkDate(String dateEnd)  {

        SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
                tvDaTra.setText("ĐÃ TRẢ : " + mDaoBook.soluongPhieuDaTraNgay(dateEnd));
                tvChuaTra.setText("CHƯA TRẢ: " + mDaoBook.soluongPhieuChuaTraNgay(dateEnd));
            tvDaTra1.setText("ĐÃ TRẢ: " + mDaoBook.soluongPhieuDaTraThang(monthandYear));
            tvChuaTra1.setText("CHƯA TRẢ: " + mDaoBook.soluongPhieuChuaTraThang(monthandYear));

            dayEnd.setTextColor(Color.rgb(152, 4, 45));
            monthAndYearEnd.setTextColor(Color.rgb(152, 4, 45));
            dayStart.setTextColor(Color.rgb(152, 4, 45));
            monthAndYearStart.setTextColor(Color.rgb(152, 4, 45));
    }


    private void initToolBar() {
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("Thống Kê");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }
}