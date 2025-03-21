/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Ggame
 */
public class HoaDon {

    private int ma_khach_hang;
    private String so_dien_thoai;
    private BigDecimal tong_tien;
    private String danh_sach_phong;
    private Date ngay_dat_phong;

    public HoaDon() {
    }

    public HoaDon(int ma_khach_hang, String so_dien_thoai, BigDecimal tong_tien, String danh_sach_phong, Date ngay_dat_phong) {
        this.ma_khach_hang = ma_khach_hang;
        this.so_dien_thoai = so_dien_thoai;
        this.tong_tien = tong_tien;
        this.danh_sach_phong = danh_sach_phong;
        this.ngay_dat_phong = ngay_dat_phong;
    }

    public int getMa_khach_hang() {
        return ma_khach_hang;
    }

    public void setMa_khach_hang(int ma_khach_hang) {
        this.ma_khach_hang = ma_khach_hang;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public BigDecimal getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(BigDecimal tong_tien) {
        this.tong_tien = tong_tien;
    }

    public String getDanh_sach_phong() {
        return danh_sach_phong;
    }

    public void setDanh_sach_phong(String danh_sach_phong) {
        this.danh_sach_phong = danh_sach_phong;
    }

    public Date getNgay_dat_phong() {
        return ngay_dat_phong;
    }

    public void setNgay_dat_phong(Date ngay_dat_phong) {
        this.ngay_dat_phong = ngay_dat_phong;
    }

}
