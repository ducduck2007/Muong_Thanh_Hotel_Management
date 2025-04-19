/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Ggame
 */
public class LichLamViec {

    private int MaLich;
    private String MaNhanVien;
    private String NgayLamViec;
    private String CaLamViec;
    private String tenNhanVien;
    private int vaiTro;

    public LichLamViec() {
    }

    public LichLamViec(int MaLich, String MaNhanVien, String NgayLamViec, String CaLamViec, String tenNhanVien, int vaiTro) {
        this.MaLich = MaLich;
        this.MaNhanVien = MaNhanVien;
        this.NgayLamViec = NgayLamViec;
        this.CaLamViec = CaLamViec;
        this.tenNhanVien = tenNhanVien;
        this.vaiTro = vaiTro;
    }

    public int getMaLich() {
        return MaLich;
    }

    public void setMaLich(int MaLich) {
        this.MaLich = MaLich;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getNgayLamViec() {
        return NgayLamViec;
    }

    public void setNgayLamViec(String NgayLamViec) {
        this.NgayLamViec = NgayLamViec;
    }

    public String getCaLamViec() {
        return CaLamViec;
    }

    public void setCaLamViec(String CaLamViec) {
        this.CaLamViec = CaLamViec;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }

    

}
