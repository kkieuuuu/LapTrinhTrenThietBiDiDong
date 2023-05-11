package com.example.QLTHUVIEN.ui;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.QLTHUVIEN.dao.DaoController;
import com.example.QLTHUVIEN.model.ThuThu;
import com.example.QLTHUVIEN.MainActivity;
import com.example.QLTHUVIEN.R;
import com.example.QLTHUVIEN.databinding.ActivityDocgialoginBinding;
import com.example.QLTHUVIEN.databinding.ActivityLoginBinding;
import com.example.QLTHUVIEN.model.Member;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DocGiaLoginActivity extends AppCompatActivity {
    public static String name = "";
    public static String password = "";
    public static int id;
    public static

    ActivityDocgialoginBinding binding;
    public static String KEY_USERNAME = "TÊN NGƯỜI DÙNG";
    public static String KEY_PASSWORD = "MẬT KHẨU";
    public static String KEY_CHECKSTATUS = "checkstatus";
    private List<ThuThu> listThuThu;
    private DaoController daoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDocgialoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        listThuThu = new ArrayList<>();
        daoController = new DaoController(this);
        binding.btLogin.setOnClickListener(v -> {
            login();
        });
        binding.textDangKi.setOnClickListener(view -> {
            startActivity( new Intent( this, LoginActivity.class));
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
            Member thu = daoController.getUserLoginMember(tenTaiKhoan, matKhau);
            if (tenTaiKhoan.equals(thu.getTaiKhoan()) && matKhau.equals(thu.getMatKhau())) {
                id = thu.getId();
                name = thu.getName();
                password = thu.getMatKhau();
                Dialog dialog = new Dialog(DocGiaLoginActivity.this);
                dialog.setContentView(R.layout.showloading);
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                timerDelayRemoveDialog(1000, dialog, thu);
                dialog.show();

            } else {
                Dialog dialog = new Dialog(DocGiaLoginActivity.this);
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

    private void timerDelayRemoveDialog(int i, Dialog dialog, Member thu) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(DocGiaLoginActivity.this , MainActivity.class);
                intent.putExtra(" ", 2);
                intent.putExtra("THỬ THƯ", thu);
                startActivity(intent);
                dialog.dismiss();
            }
        }, i);
    }

}