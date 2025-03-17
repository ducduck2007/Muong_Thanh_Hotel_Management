/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */
public class ThietLapGia {

    private int ma_gia;
    private String loai_phong;
    private BigDecimal gia_tien;
    private String ghi_chu;

    public ThietLapGia() {
    }

    public ThietLapGia(int ma_gia, String loai_phong, BigDecimal gia_tien, String ghi_chu) {
        this.ma_gia = ma_gia;
        this.loai_phong = loai_phong;
        this.gia_tien = gia_tien;
        this.ghi_chu = ghi_chu;
    }

    public int getMa_gia() {
        return ma_gia;
    }

    public void setMa_gia(int ma_gia) {
        this.ma_gia = ma_gia;
    }

    public String getLoai_phong() {
        return loai_phong;
    }

    public void setLoai_phong(String loai_phong) {
        this.loai_phong = loai_phong;
    }

    public BigDecimal getGia_tien() {
        return gia_tien;
    }

    public void setGia_tien(BigDecimal gia_tien) {
        this.gia_tien = gia_tien;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

}
