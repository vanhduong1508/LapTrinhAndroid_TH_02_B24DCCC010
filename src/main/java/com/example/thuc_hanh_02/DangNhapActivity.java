package com.example.thuc_hanh_02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DangNhapActivity extends AppCompatActivity {
    private static final String TAI_KHOAN_MAC_DINH = "admin";
    private static final String MAT_KHAU_MAC_DINH = "123456";

    private EditText etTenDangNhap, etMatKhau;
    private TextView tvThongBaoLoi;
    private Button btnDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_dang_nhap);

        etTenDangNhap = findViewById(R.id.etTenDangNhap);
        etMatKhau = findViewById(R.id.etMatKhau);
        tvThongBaoLoi = findViewById(R.id.tvThongBaoLoi);
        btnDangNhap = findViewById(R.id.btnDangNhap);

        btnDangNhap.setOnClickListener(v -> {
            String tenDangNhap = etTenDangNhap.getText().toString().trim();
            String matKhau = etMatKhau.getText().toString().trim();

            if (tenDangNhap.isEmpty() || matKhau.isEmpty()) {
                tvThongBaoLoi.setText("Vui lòng nhập đầy đủ thông tin");
                tvThongBaoLoi.setVisibility(View.VISIBLE);
                return;
            }

            if (TAI_KHOAN_MAC_DINH.equals(tenDangNhap) && MAT_KHAU_MAC_DINH.equals(matKhau)) {
                tvThongBaoLoi.setVisibility(View.GONE);
                Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                tvThongBaoLoi.setText("Sai tên đăng nhập hoặc mật khẩu");
                tvThongBaoLoi.setVisibility(View.VISIBLE);
            }
        });
    }
}