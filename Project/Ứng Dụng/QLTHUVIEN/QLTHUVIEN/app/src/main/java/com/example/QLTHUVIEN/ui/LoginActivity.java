package com.example.QLTHUVIEN.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.QLTHUVIEN.MainActivity;
import com.example.QLTHUVIEN.dao.DaoController;
import com.example.QLTHUVIEN.model.ThuThu;
import com.example.QLTHUVIEN.R;
import com.example.QLTHUVIEN.databinding.ActivityLoginBinding;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static String name = "";
    public static String password = "";
    public static int id;
    public static

    ActivityLoginBinding binding;
    public static String KEY_USERNAME = "tennguoidung";
    public static String KEY_PASSWORD = "matkhau";
    public static String KEY_CHECKSTATUS = "checkstatus";
    private List<ThuThu> listThuThu;
    private DaoController daoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        listThuThu = new ArrayList<>();
        daoController = new DaoController(this);
        binding.btLogin.setOnClickListener(v -> {
            login();
        });

        binding.textDangKi.setOnClickListener(view -> {
            startActivity( new Intent( this,DocGiaLoginActivity.class));
        });
    }

    private void login() {
        boolean xetTk = false;
        String tenTaiKhoan = binding.etUsername.getText().toString();
        String matKhau = binding.etPassword.getText().toString();

        if (binding.etUsername.getText().toString().isEmpty() || binding.etUsername.getText().toString() == null) {

            binding.tvghichu.setText("You Have Not Entered Username");
            binding.tvghichu.setTextColor(Color.RED);
            return;
        } else if (binding.etPassword.getText().toString().isEmpty() || binding.etPassword.getText().toString() == null) {

            binding.tvghichu.setText("You Have Not Entered Password");
            binding.tvghichu.setTextColor(Color.RED);
            return;
        } else {
            ThuThu thu = daoController.getUserLogin(tenTaiKhoan, matKhau);
            if (tenTaiKhoan.equals(thu.getTaiKhoan()) && matKhau.equals(thu.getMaKhau())) {
                id = thu.getMaThuThu();
                name = thu.getHoTenThuThu();
                password = thu.getMaKhau();
                Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.showloading);
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                timerDelayRemoveDialog(1000, dialog, thu);
                dialog.show();
                luuThongTin();

            } else {
                Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.showloading);
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                timerDelayRemoveDialog2(1000, dialog);
                dialog.show();
                return;
            }
        }
    }

    private void timerDelayRemoveDialog2(int i, Dialog dialog) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                binding.tvghichu.setText("Incorrect Account Or Password");
                binding.tvghichu.setTextColor(Color.RED);
                dialog.dismiss();
            }
        }, i);
    }

    private void timerDelayRemoveDialog(int i, Dialog dialog, ThuThu thu) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                intent.putExtra(" ", 1);
                intent.putExtra("thuthu", thu);
                startActivity(intent);
                dialog.dismiss();
            }
        }, i);
    }

    private void luuThongTin() {
        SharedPreferences sharedPreferences = getSharedPreferences("thuVien", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String ten = binding.etUsername.getText().toString();
        String pass = binding.etPassword.getText().toString();
        boolean check = binding.cbLuuThongTin.isChecked();
        if (!check) {
            editor.clear();
        } else {
            editor.putString(KEY_USERNAME, ten);
            editor.putString(KEY_PASSWORD, pass);
            editor.putBoolean(KEY_CHECKSTATUS, check);
        }
        editor.commit();
    }

    private void layThongTin() {
        SharedPreferences sharedPreferences = getSharedPreferences("thuVien", MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean(KEY_CHECKSTATUS, false);
        if (check) {
            String tenNguoiDung = sharedPreferences.getString(KEY_USERNAME, "");
            String matKhau = sharedPreferences.getString(KEY_PASSWORD, "");
            binding.etUsername.setText(tenNguoiDung);
            binding.etPassword.setText(matKhau);
        } else {
            binding.etUsername.setText("");
            binding.etPassword.setText("");
        }
        binding.cbLuuThongTin.setChecked(check);

    }

    @Override
    protected void onResume() {
        super.onResume();
        layThongTin();
    }


}