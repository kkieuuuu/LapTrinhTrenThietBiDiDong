package com.example.QLTHUVIEN.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.QLTHUVIEN.dao.DaoNhapSach;
import com.example.QLTHUVIEN.model.NhapSach;
import com.example.QLTHUVIEN.model.ThuThu;
import com.example.QLTHUVIEN.ui.Adapter.AdapterPhieuNhap;
import com.example.QLTHUVIEN.MainActivity;
import com.example.QLTHUVIEN.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DathangActivity extends AppCompatActivity {

   DaoNhapSach mDao;
    EditText edtDH ;
    RecyclerView rcyDH ;
    Button btnThemLT;
    Button fabDH;
    Spinner ListNXB;
    TextView cancelBtn,edtNgayNhap;
    ThuThu thuthu;
    private EditText titleThemLoai,etTenTG,edtMaSach,edSoLuong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dathang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("NHẬP SÁCH TỪ NHÀ XUẤT BẢN");
        edtDH = findViewById(R.id.etSearchDH);
        rcyDH = findViewById(R.id.recyclerviewDH);
        fabDH = findViewById(R.id.fabAddDH);
        mDao = new DaoNhapSach(this);
        thuthu = (ThuThu) getIntent().getSerializableExtra("THỦ THƯ");
        List<NhapSach> lst = new ArrayList<>();
        if ( mDao.getListNhapSach()!= null){
            lst = mDao.getListNhapSach();
        }

        AdapterPhieuNhap adapterPhieuNhap = new AdapterPhieuNhap(this);
        if (lst !=null){
            adapterPhieuNhap.setData(lst);
        }
          rcyDH.setAdapter(adapterPhieuNhap);

      fabDH.setOnClickListener(view ->{
          final Dialog dialog = new Dialog(this);
          dialog.setCancelable(false);
          dialog.setContentView(R.layout.dialogthemphieunhap);
          Window window = dialog.getWindow();
          ListNXB = dialog.findViewById(R.id.spn_NXB);
          cancelBtn = dialog.findViewById(R.id.cancelPN);
          btnThemLT = dialog.findViewById(R.id.btnAddPN);
          etTenTG = dialog.findViewById(R.id.etTenTG);
          edtMaSach = dialog.findViewById(R.id.edtMaSach);
          edSoLuong = dialog.findViewById(R.id.edSoLuong);
          edtNgayNhap = dialog.findViewById(R.id.edtNgayNhap);
          window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
          if (dialog != null && dialog.getWindow() != null) {
              dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
          }
          String colors[] = {"Penguin Books","First Second Books","TarcherPerigee","DC COMICS","Front Street Press", "Wiley","RELX Group","Scholastic"};
          ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, colors);
          spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          ListNXB.setAdapter(spinnerArrayAdapter);
          edtNgayNhap.setOnClickListener(view1 -> {

              final Calendar c = Calendar.getInstance();
              int year = c.get(Calendar.YEAR);
              int month = c.get(Calendar.MONTH);
              int day = c.get(Calendar.DAY_OF_MONTH);

              DatePickerDialog datePickerDialog = new DatePickerDialog(
                      this,
                      new DatePickerDialog.OnDateSetListener() {
                          @Override
                          public void onDateSet(DatePicker view, int year,
                                                int monthOfYear, int dayOfMonth) {
                              edtNgayNhap.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                          }
                      },
                      year, month, day);
              datePickerDialog.show();
          });
          btnThemLT.setOnClickListener(view1 -> {
              if (etTenTG.getText().toString()==null || etTenTG.getText().toString()==""){
                  etTenTG.setError("VUI LÒNG NHẬP TÊN TÁC GIẢ");
              }else  if (edtMaSach.getText().toString()==null || edtMaSach.getText().toString()==""){
                  edtMaSach.setError("VUI LÒNG NHẬP MÃ SÁCH");
              }else if (edSoLuong.getText().toString()==null|| edSoLuong.getText().toString()==""){
                  edSoLuong.setError("VUI LÒNG NHẬP SỐ LƯỢNG");
              }else if (edtNgayNhap.getText().toString()==null || edtNgayNhap.getText().toString()==""){
                  edtNgayNhap.setError("VUI LÒNG NHẬP NGÀY NHẬP");
              }else {
                  NhapSach nhapSach = new NhapSach(-1,etTenTG.getText().toString(),String.valueOf(thuthu.getMaThuThu()),edtMaSach.getText().toString(),edtNgayNhap.getText().toString(),Integer.parseInt(edSoLuong.getText().toString()),ListNXB.getSelectedItem().toString());
                  if (mDao.themPhieuNhapSach( nhapSach)){
                      Toast.makeText(this, "TẠO PHIẾU NHẬP THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                      adapterPhieuNhap.setData(mDao.getListNhapSach());
                  }else {
                      Toast.makeText(this, "TẠO PHIẾU NHẬP KHÔNG THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                  }
              }
          });
           cancelBtn.setOnClickListener( view1 -> {
               dialog.dismiss();
           });
          dialog.show();

      });
    }

    @Override
    public boolean onSupportNavigateUp() {
     onBackPressed();
        return super.onSupportNavigateUp();
    }
}