/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.ThongTinDatPhong;
import Models.ThongTinPhong;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Ggame
 */
public class ThongTinDatPhongDAO {

    public List<ThongTinDatPhong> getDataPD() {
        String sql = "SELECT * FROM thong_tin_dat_phong";
        List<ThongTinDatPhong> list = new ArrayList<>();

        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();

            while (rs.next()) {
                ThongTinDatPhong ttdp = new ThongTinDatPhong();
                ttdp.setMa_dat_phong(rs.getInt("ma_dat_phong"));
                ttdp.setMa_phong(rs.getString("ma_phong"));
                ttdp.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                ttdp.setLoai_phong(rs.getString("loai_phong"));
                ttdp.setNgay_dat_phong(rs.getDate("ngay_dat_phong"));
                ttdp.setTong_tien(rs.getBigDecimal("tong_tien"));
                ttdp.setGhi_chu(rs.getString("ghi_chu"));
                ttdp.setNgay_nhan_phong(rs.getDate("ngay_nhan_phong"));
                ttdp.setNgay_tra_phong(rs.getDate("ngay_tra_phong"));
                list.add(ttdp);
            }
            rs.close();
            preStm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<ThongTinPhong> getDataPT() {
        String sql = "SELECT a.*, b.* FROM thong_tin_phong a LEFT JOIN thiet_lap_gia b ON a.loai_phong = b.loai_phong WHERE a.trang_thai = N'Trống'";
        List<ThongTinPhong> list = new ArrayList<>();

        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();

            while (rs.next()) {
                ThongTinPhong ttp = new ThongTinPhong();
                ttp.setMa_phong(rs.getString("ma_phong"));
                ttp.setLoai_phong(rs.getString("loai_phong"));
                ttp.setTrang_thai(rs.getString("trang_thai"));
                ttp.setGia_tien(rs.getString("gia_phong"));
                ttp.setThoi_luong(rs.getInt("thoi_luong"));
                ttp.setGhi_chu(rs.getString("ghi_chu"));
                list.add(ttp);
            }
            rs.close();
            preStm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static int getGiaPhong(String loaiPhong) {
        int giaPhong = 0;
        String sql = "SELECT gia_phong FROM thiet_lap_gia WHERE loai_phong = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, loaiPhong);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                giaPhong = rs.getInt("gia_phong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return giaPhong;
    }

    public String layMaKhachHangTheoEmail(String email) {
        String sql = "SELECT ma_khach_hang FROM khach_hang WHERE email = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("ma_khach_hang");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(ThongTinDatPhong ttdp) {
        String insertSQL = "INSERT INTO thong_tin_dat_phong (ma_phong, ma_khach_hang, loai_phong, ngay_dat_phong, tong_tien, ghi_chu, ngay_nhan_phong, ngay_tra_phong) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String updateSQL = "UPDATE thong_tin_phong SET trang_thai = N'Đã được đặt' WHERE ma_phong = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStmInsert = conn.prepareStatement(insertSQL); PreparedStatement preStmUpdate = conn.prepareStatement(updateSQL)) {

            preStmInsert.setString(1, ttdp.getMa_phong());
            preStmInsert.setInt(2, ttdp.getMa_khach_hang());
            preStmInsert.setString(3, ttdp.getLoai_phong());
            preStmInsert.setDate(4, new java.sql.Date(ttdp.getNgay_dat_phong().getTime()));
            preStmInsert.setBigDecimal(5, ttdp.getTong_tien());
            preStmInsert.setString(6, ttdp.getGhi_chu());
            preStmInsert.setDate(7, new java.sql.Date(ttdp.getNgay_nhan_phong().getTime()));
            preStmInsert.setDate(8, new java.sql.Date(ttdp.getNgay_tra_phong().getTime()));

            if (preStmInsert.executeUpdate() > 0) {
                preStmUpdate.setString(1, ttdp.getMa_phong());
                preStmUpdate.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(ThongTinDatPhong ttdp) {
        String sql = "UPDATE thong_tin_dat_phong SET ma_phong = ?, ma_khach_hang = ?, loai_phong = ?, ngay_dat_phong = ?, tong_tien = ?, ghi_chu = ?, ngay_nhan_phong = ?, ngay_tra_phong = ? WHERE ma_dat_phong = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {

            preStm.setString(1, ttdp.getMa_phong());
            preStm.setInt(2, ttdp.getMa_khach_hang());
            preStm.setString(3, ttdp.getLoai_phong());

            preStm.setDate(4, new java.sql.Date(ttdp.getNgay_dat_phong().getTime()));
            preStm.setBigDecimal(5, ttdp.getTong_tien());
            preStm.setString(6, ttdp.getGhi_chu());
            preStm.setDate(7, new java.sql.Date(ttdp.getNgay_nhan_phong().getTime()));
            preStm.setDate(8, new java.sql.Date(ttdp.getNgay_tra_phong().getTime()));

            preStm.setInt(9, ttdp.getMa_dat_phong());

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getEmailByMaKhachHang(String maKhachHang) {
        String email = "";
        String sql = "SELECT email FROM khach_hang WHERE ma_khach_hang = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maKhachHang);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString("email");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return email;
    }

}
