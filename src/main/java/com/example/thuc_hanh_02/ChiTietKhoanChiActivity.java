package com.example.thuc_hanh_02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class ChiTietKhoanChiActivity extends AppCompatActivity {
    private TextView tvChiTietTen, tvChiTietSoTien, tvChiTietDanhMuc, tvChiTietNgay, tvChiTietGhiChu;
    private Button btnQuayLai, btnXoaKhoanChi;
    private int viTri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_khoan_chi);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Chi tiết khoản chi");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvChiTietTen = findViewById(R.id.tvChiTietTen);
        tvChiTietSoTien = findViewById(R.id.tvChiTietSoTien);
        tvChiTietDanhMuc = findViewById(R.id.tvChiTietDanhMuc);
        tvChiTietNgay = findViewById(R.id.tvChiTietNgay);
        tvChiTietGhiChu = findViewById(R.id.tvChiTietGhiChu);
        btnQuayLai = findViewById(R.id.btnQuayLai);
        btnXoaKhoanChi = findViewById(R.id.btnXoaKhoanChi);

        KhoanChi khoanChi = (KhoanChi) getIntent().getSerializableExtra("khoan_chi");
        viTri = getIntent().getIntExtra("vi_tri", -1);

        if (khoanChi != null) {
            DecimalFormat dinhDangTien = new DecimalFormat("#,### đ");
            tvChiTietTen.setText(khoanChi.getTen());
            tvChiTietSoTien.setText(dinhDangTien.format(khoanChi.getSoTien()));
            tvChiTietDanhMuc.setText(khoanChi.getDanhMuc());
            tvChiTietNgay.setText(khoanChi.getNgay());
            tvChiTietGhiChu.setText(khoanChi.getGhiChu().isEmpty() ? "(Không có ghi chú)" : khoanChi.getGhiChu());
        }

        btnQuayLai.setOnClickListener(v -> finish());

        btnXoaKhoanChi.setOnClickListener(v -> {
            Intent intentTraVe = new Intent();
            intentTraVe.putExtra("da_xoa", true);
            intentTraVe.putExtra("vi_tri", viTri);
            setResult(RESULT_OK, intentTraVe);
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}