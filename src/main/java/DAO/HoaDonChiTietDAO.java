/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.HoaDonChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ggame
 */
public class HoaDonChiTietDAO {

    public List<HoaDonChiTiet> getThongTinDatPhong(String maKhachHang, String ngayDatPhong) {
        List<HoaDonChiTiet> danhSach = new ArrayList<>();
        String sql = "SELECT ma_khach_hang, ma_phong, ma_dat_phong, '' AS ma_nhan_vien, '' AS ma_dich_vu, loai_phong, tong_tien, ngay_dat_phong, ngay_nhan_phong, ngay_tra_phong FROM thong_tin_dat_phong WHERE ma_khach_hang = ? AND ngay_dat_phong = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Kiểm tra kiểu dữ liệu
            int maKH = Integer.parseInt(maKhachHang);
            java.sql.Date sqlDate = java.sql.Date.valueOf(ngayDatPhong);

            ps.setInt(1, maKH);
            ps.setDate(2, sqlDate);

            System.out.println("DEBUG: Query - " + ps.toString());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonChiTiet hd = new HoaDonChiTiet();
                    hd.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                    hd.setMa_phong(rs.getString("ma_phong"));
                    hd.setMa_dat_phong(rs.getInt("ma_dat_phong"));
                    hd.setMa_nhan_vien(rs.getString("ma_nhan_vien"));
                    hd.setMa_dich_vu(rs.getInt("ma_dich_vu"));
                    hd.setLoai_phong(rs.getString("loai_phong"));
                    hd.setTong_tien(rs.getBigDecimal("tong_tien"));
                    hd.setNgay_dat_phong(rs.getDate("ngay_dat_phong"));
                    hd.setNgay_nhan_phong(rs.getDate("ngay_nhan_phong"));
                    hd.setNgay_tra_phong(rs.getDate("ngay_tra_phong"));
                    danhSach.add(hd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSach;
    }

}
