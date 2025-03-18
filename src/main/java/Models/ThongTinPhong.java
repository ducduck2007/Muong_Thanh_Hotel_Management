/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author ADMIN
 */
public class ThongTinPhong {

    private String ma_phong;
    private String loai_phong;
    private String trang_thai;
    private String gia_tien;
    private String ghi_chu;
    private int thoi_luong;

    public ThongTinPhong() {
    }

    public ThongTinPhong(String ma_phong, String loai_phong, String trang_thai, String gia_tien, String ghi_chu, int thoi_luong) {
        this.ma_phong = ma_phong;
        this.loai_phong = loai_phong;
        this.trang_thai = trang_thai;
        this.gia_tien = gia_tien;
        this.ghi_chu = ghi_chu;
        this.thoi_luong = thoi_luong;
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

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getGia_tien() {
        return gia_tien;
    }

    public void setGia_tien(String gia_tien) {
        this.gia_tien = gia_tien;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public int getThoi_luong() {
        return thoi_luong;
    }

    public void setThoi_luong(int thoi_luong) {
        this.thoi_luong = thoi_luong;
    }

}
