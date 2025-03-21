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
public class ThongTinDatPhong {

    private int ma_dat_phong;
    private String ma_phong;
    private int ma_khach_hang;
    private String loai_phong;
    private Date ngay_dat_phong;
    private BigDecimal tong_tien;
    private String ghi_chu;
    private Date ngay_nhan_phong;
    private Date ngay_tra_phong;

    public ThongTinDatPhong() {
    }

    public ThongTinDatPhong(int ma_dat_phong, String ma_phong, int ma_khach_hang, String loai_phong, Date ngay_dat_phong, BigDecimal tong_tien, String ghi_chu, Date ngay_nhan_phong, Date ngay_tra_phong) {
        this.ma_dat_phong = ma_dat_phong;
        this.ma_phong = ma_phong;
        this.ma_khach_hang = ma_khach_hang;
        this.loai_phong = loai_phong;
        this.ngay_dat_phong = ngay_dat_phong;
        this.tong_tien = tong_tien;
        this.ghi_chu = ghi_chu;
        this.ngay_nhan_phong = ngay_nhan_phong;
        this.ngay_tra_phong = ngay_tra_phong;
    }

    public int getMa_dat_phong() {
        return ma_dat_phong;
    }

    public void setMa_dat_phong(int ma_dat_phong) {
        this.ma_dat_phong = ma_dat_phong;
    }

    public String getMa_phong() {
        return ma_phong;
    }

    public void setMa_phong(String ma_phong) {
        this.ma_phong = ma_phong;
    }

    public int getMa_khach_hang() {
        return ma_khach_hang;
    }

    public void setMa_khach_hang(int ma_khach_hang) {
        this.ma_khach_hang = ma_khach_hang;
    }

    public String getLoai_phong() {
        return loai_phong;
    }

    public void setLoai_phong(String loai_phong) {
        this.loai_phong = loai_phong;
    }

    public Date getNgay_dat_phong() {
        return ngay_dat_phong;
    }

    public void setNgay_dat_phong(Date ngay_dat_phong) {
        this.ngay_dat_phong = ngay_dat_phong;
    }

    public BigDecimal getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(BigDecimal tong_tien) {
        this.tong_tien = tong_tien;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
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
