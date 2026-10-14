package com.example.thuc_hanh_02;

import java.io.Serializable;

public class KhoanChi implements Serializable {
    private String ten;
    private double soTien;
    private String danhMuc;
    private String ngay;
    private String ghiChu;

    public KhoanChi(String ten, double soTien, String danhMuc, String ngay, String ghiChu) {
        this.ten = ten;
        this.soTien = soTien;
        this.danhMuc = danhMuc;
        this.ngay = ngay;
        this.ghiChu = ghiChu;
    }

    public String getTen() { return ten; }
    public double getSoTien() { return soTien; }
    public String getDanhMuc() { return danhMuc; }
    public String getNgay() { return ngay; }
    public String getGhiChu() { return ghiChu; }
}