package com.example.thuc_hanh_02;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DecimalFormat;
import java.util.List;

public class KhoanChiAdapter extends RecyclerView.Adapter<KhoanChiAdapter.KhoanChiViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private final List<KhoanChi> danhSachKhoanChi;
    private final OnItemClickListener listener;
    private final DecimalFormat dinhDangTien = new DecimalFormat("#,### đ");

    public KhoanChiAdapter(List<KhoanChi> danhSachKhoanChi, OnItemClickListener listener) {
        this.danhSachKhoanChi = danhSachKhoanChi;
        this.listener = listener;
    }

    @NonNull
    @Override
    public KhoanChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_khoan_chi, parent, false);
        return new KhoanChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanChiViewHolder holder, int position) {
        KhoanChi item = danhSachKhoanChi.get(position);
        holder.tvTenKhoanChi.setText(item.getTen());
        holder.tvSoTien.setText(dinhDangTien.format(item.getSoTien()));
        holder.tvDanhMuc.setText(item.getDanhMuc());
        holder.tvNgay.setText(item.getNgay());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return danhSachKhoanChi.size();
    }

    static class KhoanChiViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenKhoanChi, tvSoTien, tvDanhMuc, tvNgay;

        public KhoanChiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenKhoanChi = itemView.findViewById(R.id.tvTenKhoanChi);
            tvSoTien = itemView.findViewById(R.id.tvSoTien);
            tvDanhMuc = itemView.findViewById(R.id.tvDanhMuc);
            tvNgay = itemView.findViewById(R.id.tvNgay);
        }
    }
}