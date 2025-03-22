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
public class HoaDonChiTiet {

    private int ma_khach_hang;
    private String ma_phong;
    private int ma_dat_phong;
    private String ma_nhan_vien;
    private int ma_dich_vu;
    private String loai_phong;
    private BigDecimal tong_tien;
    private Date ngay_dat_phong;
    private Date ngay_nhan_phong;
    private Date ngay_tra_phong;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int ma_khach_hang, String ma_phong, int ma_dat_phong, String ma_nhan_vien, int ma_dich_vu, String loai_phong, BigDecimal tong_tien, Date ngay_dat_phong, Date ngay_nhan_phong, Date ngay_tra_phong) {
        this.ma_khach_hang = ma_khach_hang;
        this.ma_phong = ma_phong;
        this.ma_dat_phong = ma_dat_phong;
        this.ma_nhan_vien = ma_nhan_vien;
        this.ma_dich_vu = ma_dich_vu;
        this.loai_phong = loai_phong;
        this.tong_tien = tong_tien;
        this.ngay_dat_phong = ngay_dat_phong;
        this.ngay_nhan_phong = ngay_nhan_phong;
        this.ngay_tra_phong = ngay_tra_phong;
    }

    public int getMa_khach_hang() {
        return ma_khach_hang;
    }

    public void setMa_khach_hang(int ma_khach_hang) {
        this.ma_khach_hang = ma_khach_hang;
    }

    public String getMa_phong() {
        return ma_phong;
    }

    public void setMa_phong(String ma_phong) {
        this.ma_phong = ma_phong;
    }

    public int getMa_dat_phong() {
        return ma_dat_phong;
    }

    public void setMa_dat_phong(int ma_dat_phong) {
        this.ma_dat_phong = ma_dat_phong;
    }

    public String getMa_nhan_vien() {
        return ma_nhan_vien;
    }

    public void setMa_nhan_vien(String ma_nhan_vien) {
        this.ma_nhan_vien = ma_nhan_vien;
    }

    public int getMa_dich_vu() {
        return ma_dich_vu;
    }

    public void setMa_dich_vu(int ma_dich_vu) {
        this.ma_dich_vu = ma_dich_vu;
    }

    public String getLoai_phong() {
        return loai_phong;
    }

    public void setLoai_phong(String loai_phong) {
        this.loai_phong = loai_phong;
    }

    public BigDecimal getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(BigDecimal tong_tien) {
        this.tong_tien = tong_tien;
    }

    public Date getNgay_dat_phong() {
        return ngay_dat_phong;
    }

    public void setNgay_dat_phong(Date ngay_dat_phong) {
        this.ngay_dat_phong = ngay_dat_phong;
    }

    public Date getNgay_nhan_phong() {
        return ngay_nhan_phong;
    }

    public void setNgay_nhan_phong(Date ngay_nhan_phong) {
        this.ngay_nhan_phong = ngay_nhan_phong;
    }

    public Date getNgay_tra_phong() {
        return ngay_tra_phong;
    }

    public void setNgay_tra_phong(Date ngay_tra_phong) {
        this.ngay_tra_phong = ngay_tra_phong;
    }

}
