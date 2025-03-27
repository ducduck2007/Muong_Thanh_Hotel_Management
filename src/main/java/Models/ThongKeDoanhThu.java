/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.math.BigDecimal;

/**
 *
 * @author Ggame
 */
public class ThongKeDoanhThu {

    private String ds_thue_phong;
    private String khach_hang;
    private String tong_tien;
    private String ngay_dat_phong;

    private String ma_phong;
    private String loai_phong;
    private String dich_vu;
    private String ngay_nhan_phong;
    private String ngay_tra_phong;

    public ThongKeDoanhThu() {
    }

    public ThongKeDoanhThu(String ds_thue_phong, String khach_hang, String tong_tien, String ngay_dat_phong, String ma_phong, String loai_phong, String dich_vu, String ngay_nhan_phong, String ngay_tra_phong) {
        this.ds_thue_phong = ds_thue_phong;
        this.khach_hang = khach_hang;
        this.tong_tien = tong_tien;
        this.ngay_dat_phong = ngay_dat_phong;
        this.ma_phong = ma_phong;
        this.loai_phong = loai_phong;
        this.dich_vu = dich_vu;
        this.ngay_nhan_phong = ngay_nhan_phong;
        this.ngay_tra_phong = ngay_tra_phong;
    }

    public String getDs_thue_phong() {
        return ds_thue_phong;
    }

    public void setDs_thue_phong(String ds_thue_phong) {
        this.ds_thue_phong = ds_thue_phong;
    }

    public String getKhach_hang() {
        return khach_hang;
    }

    public void setKhach_hang(String khach_hang) {
        this.khach_hang = khach_hang;
    }

    public String getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(String tong_tien) {
        this.tong_tien = tong_tien;
    }

    public String getNgay_dat_phong() {
        return ngay_dat_phong;
    }

    public void setNgay_dat_phong(String ngay_dat_phong) {
        this.ngay_dat_phong = ngay_dat_phong;
    }

    public String getMa_phong() {
        return ma_phong;
    }

    public void setMa_phong(String ma_phong) {
        this.ma_phong = ma_phong;
    }

    public String getLoai_phong() {
        return loai_phong;
    }

    public void setLoai_phong(String loai_phong) {
        this.loai_phong = loai_phong;
    }

    public String getDich_vu() {
        return dich_vu;
    }

    public void setDich_vu(String dich_vu) {
        this.dich_vu = dich_vu;
    }

    public String getNgay_nhan_phong() {
        return ngay_nhan_phong;
    }

    public void setNgay_nhan_phong(String ngay_nhan_phong) {
        this.ngay_nhan_phong = ngay_nhan_phong;
    }

    public String getNgay_tra_phong() {
        return ngay_tra_phong;
    }

    public void setNgay_tra_phong(String ngay_tra_phong) {
        this.ngay_tra_phong = ngay_tra_phong;
    }

}
