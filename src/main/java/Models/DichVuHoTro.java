/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Admin
 */
public class DichVuHoTro {
    int ma_dich_vu, ma_khach_hang;
    String ten_dich_vu, noi_dung;

    public DichVuHoTro() {
    }

    public DichVuHoTro(int ma_dich_vu, int ma_khach_hang, String ten_dich_vu, String noi_dung) {
        this.ma_dich_vu = ma_dich_vu;
        this.ma_khach_hang = ma_khach_hang;
        this.ten_dich_vu = ten_dich_vu;
        this.noi_dung = noi_dung;
    }

    public int getMa_dich_vu() {
        return ma_dich_vu;
    }

    public void setMa_dich_vu(int ma_dich_vu) {
        this.ma_dich_vu = ma_dich_vu;
    }

    public int getMa_khach_hang() {
        return ma_khach_hang;
    }

    public void setMa_khach_hang(int ma_khach_hang) {
        this.ma_khach_hang = ma_khach_hang;
    }

    public String getTen_dich_vu() {
        return ten_dich_vu;
    }

    public void setTen_dich_vu(String ten_dich_vu) {
        this.ten_dich_vu = ten_dich_vu;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }
    
}
