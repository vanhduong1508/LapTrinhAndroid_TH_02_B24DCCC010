package com.example.thuc_hanh_02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvTongTien;
    private RecyclerView rvKhoanChi;
    private Button btnThemKhoanChi;
    private ArrayList<KhoanChi> danhSachKhoanChi;
    private KhoanChiAdapter adapter;
    private final DecimalFormat dinhDangTien = new DecimalFormat("#,### đ");

    private ActivityResultLauncher<Intent> trinhThemKhoanChi;
    private ActivityResultLauncher<Intent> trinhChiTietKhoanChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTongTien = findViewById(R.id.tvTongTien);
        rvKhoanChi = findViewById(R.id.rvKhoanChi);
        btnThemKhoanChi = findViewById(R.id.btnThemKhoanChi);

        khoiTaoDuLieuMau();
        caiDatRecyclerView();
        capNhatTongTien();
        dangKyKetQuaNhanVe();

        btnThemKhoanChi.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ThemKhoanChiActivity.class);
            trinhThemKhoanChi.launch(intent);
        });
    }

    private void khoiTaoDuLieuMau() {
        danhSachKhoanChi = new ArrayList<>();
        danhSachKhoanChi.add(new KhoanChi("Ăn sáng", 30000, "Ăn uống", "12/10/2026", "Ăn phở"));
        danhSachKhoanChi.add(new KhoanChi("Đổ xăng", 50000, "Đi lại", "12/10/2026", "Xăng xe máy"));
        danhSachKhoanChi.add(new KhoanChi("Mua sách", 120000, "Học tập", "13/10/2026", "Sách Lập trình Android"));
    }

    private void caiDatRecyclerView() {
        adapter = new KhoanChiAdapter(danhSachKhoanChi, position -> {
            Intent intent = new Intent(MainActivity.this, ChiTietKhoanChiActivity.class);
            intent.putExtra("khoan_chi", danhSachKhoanChi.get(position));
            intent.putExtra("vi_tri", position);
            trinhChiTietKhoanChi.launch(intent);
        });
        rvKhoanChi.setLayoutManager(new LinearLayoutManager(this));
        rvKhoanChi.setAdapter(adapter);
    }

    private void capNhatTongTien() {
        double tong = 0;
        for (KhoanChi k : danhSachKhoanChi) {
            tong += k.getSoTien();
        }
        tvTongTien.setText(dinhDangTien.format(tong));
    }

    private void dangKyKetQuaNhanVe() {
        trinhThemKhoanChi = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        KhoanChi khoanChiMoi = (KhoanChi) result.getData().getSerializableExtra("khoan_chi_moi");
                        if (khoanChiMoi != null) {
                            danhSachKhoanChi.add(khoanChiMoi);
                            adapter.notifyItemInserted(danhSachKhoanChi.size() - 1);
                            capNhatTongTien();
                        }
                    }
                }
        );

        trinhChiTietKhoanChi = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        boolean daXoa = result.getData().getBooleanExtra("da_xoa", false);
                        int viTri = result.getData().getIntExtra("vi_tri", -1);
                        if (daXoa && viTri != -1 && viTri < danhSachKhoanChi.size()) {
                            danhSachKhoanChi.remove(viTri);
                            adapter.notifyItemRemoved(viTri);
                            capNhatTongTien();
                        }
                    }
                }
        );
    }
}