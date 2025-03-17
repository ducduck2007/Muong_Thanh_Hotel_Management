/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Ggame
 */
public class KhachHang {

    private int ma_khach_hang;
    private String ten_khach_hang;
    private String email;
    private String mat_khau;
    private String so_dien_thoai;

    public KhachHang() {
    }

    public KhachHang(int ma_khach_hang, String ten_khach_hang, String email, String mat_khau, String so_dien_thoai) {
        this.ma_khach_hang = ma_khach_hang;
        this.ten_khach_hang = ten_khach_hang;
        this.email = email;
        this.mat_khau = mat_khau;
        this.so_dien_thoai = so_dien_thoai;
    }

    public int getMa_khach_hang() {
        return ma_khach_hang;
    }

    public void setMa_khach_hang(int ma_khach_hang) {
        this.ma_khach_hang = ma_khach_hang;
    }

    public String getTen_khach_hang() {
        return ten_khach_hang;
    }

    public void setTen_khach_hang(String ten_khach_hang) {
        this.ten_khach_hang = ten_khach_hang;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        this.mat_khau = mat_khau;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

}
