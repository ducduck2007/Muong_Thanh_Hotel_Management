/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Ggame
 */
public class QuanLyNhanVien {

    private String ma_nhan_vien;
    private String ten_nhan_vien;
    private String email;
    private String mat_khau;
    private boolean vai_tro;
    private String ma_quan_ly;
    private String ghi_chu;

    public QuanLyNhanVien() {
    }

    public QuanLyNhanVien(String ma_nhan_vien, String ten_nhan_vien, String email, String mat_khau, boolean vai_tro, String ma_quan_ly, String ghi_chu) {
        this.ma_nhan_vien = ma_nhan_vien;
        this.ten_nhan_vien = ten_nhan_vien;
        this.email = email;
        this.mat_khau = mat_khau;
        this.vai_tro = vai_tro;
        this.ma_quan_ly = ma_quan_ly;
        this.ghi_chu = ghi_chu;
    }

    public String getMa_nhan_vien() {
        return ma_nhan_vien;
    }

    public void setMa_nhan_vien(String ma_nhan_vien) {
        this.ma_nhan_vien = ma_nhan_vien;
    }

    public String getTen_nhan_vien() {
        return ten_nhan_vien;
    }

    public void setTen_nhan_vien(String ten_nhan_vien) {
        this.ten_nhan_vien = ten_nhan_vien;
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

    public boolean isVai_tro() {
        return vai_tro;
    }

    public void setVai_tro(boolean vai_tro) {
        this.vai_tro = vai_tro;
    }

    public String getMa_quan_ly() {
        return ma_quan_ly;
    }

    public void setMa_quan_ly(String ma_quan_ly) {
        this.ma_quan_ly = ma_quan_ly;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

}
