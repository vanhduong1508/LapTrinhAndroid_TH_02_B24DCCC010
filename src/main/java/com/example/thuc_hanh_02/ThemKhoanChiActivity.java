package com.example.thuc_hanh_02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class ThemKhoanChiActivity extends AppCompatActivity {
    private EditText etTenKhoanChi, etSoTien, etGhiChu;
    private Spinner spDanhMuc;
    private DatePicker dpNgayChiTieu;
    private Button btnLuuKhoanChi;

    private final String[] danhSachDanhMuc = {"Ăn uống", "Đi lại", "Học tập", "Giải trí", "Khác"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_khoan_chi);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Thêm khoản chi");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etTenKhoanChi = findViewById(R.id.etTenKhoanChi);
        etSoTien = findViewById(R.id.etSoTien);
        etGhiChu = findViewById(R.id.etGhiChu);
        spDanhMuc = findViewById(R.id.spDanhMuc);
        dpNgayChiTieu = findViewById(R.id.dpNgayChiTieu);
        btnLuuKhoanChi = findViewById(R.id.btnLuuKhoanChi);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, danhSachDanhMuc);
        spDanhMuc.setAdapter(adapter);

        btnLuuKhoanChi.setOnClickListener(v -> luuKhoanChi());
    }

    private void luuKhoanChi() {
        String ten = etTenKhoanChi.getText().toString().trim();
        String chuoiTien = etSoTien.getText().toString().trim();
        String ghiChu = etGhiChu.getText().toString().trim();
        String danhMuc = spDanhMuc.getSelectedItem().toString();

        if (ten.isEmpty()) {
            etTenKhoanChi.setError("Tên khoản chi không được để trống");
            etTenKhoanChi.requestFocus();
            return;
        }

        if (chuoiTien.isEmpty()) {
            etSoTien.setError("Vui lòng nhập số tiền");
            etSoTien.requestFocus();
            return;
        }

        double soTien;
        try {
            soTien = Double.parseDouble(chuoiTien);
            if (soTien <= 0) {
                etSoTien.setError("Số tiền phải lớn hơn 0");
                etSoTien.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            etSoTien.setError("Số tiền không hợp lệ");
            etSoTien.requestFocus();
            return;
        }

        int ngay = dpNgayChiTieu.getDayOfMonth();
        int thang = dpNgayChiTieu.getMonth() + 1;
        int nam = dpNgayChiTieu.getYear();
        String chuoiNgay = String.format("%02d/%02d/%d", ngay, thang, nam);

        KhoanChi khoanChiMoi = new KhoanChi(ten, soTien, danhMuc, chuoiNgay, ghiChu);

        Intent intentTraVe = new Intent();
        intentTraVe.putExtra("khoan_chi_moi", khoanChiMoi);
        setResult(RESULT_OK, intentTraVe);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}