/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.ThongTinPhong;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThongTinPhongDAO {

    public BigDecimal getGiaPhongByLoaiPhong(String loaiPhong) {
        BigDecimal giaPhong = BigDecimal.ZERO;
        String sql = "SELECT gia_phong FROM thiet_lap_gia WHERE loai_phong = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, loaiPhong);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                giaPhong = rs.getBigDecimal("gia_phong");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return giaPhong;
    }

    public List<ThongTinPhong> getData() {
        String sql = "SELECT a.*, b.gia_phong FROM thong_tin_phong a LEFT JOIN thiet_lap_gia b ON a.loai_phong = b.loai_phong";
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

    public static List<ThongTinPhong> timKiemPhong(String loaiPhong, String trangThai) {
        List<ThongTinPhong> danhSach = new ArrayList<>();
        String sql = "SELECT a.*, b.gia_phong "
                + "FROM thong_tin_phong a "
                + "LEFT JOIN thiet_lap_gia b ON TRIM(a.loai_phong) = TRIM(b.loai_phong) "
                + "WHERE 1=1";

        List<String> params = new ArrayList<>();

        if (!loaiPhong.equals("-- Tìm theo loại phòng --")) {
            sql += " AND TRIM(a.loai_phong) = TRIM(?)";
            params.add(loaiPhong);
        }
        if (!trangThai.equals("-- Tìm theo trạng thái phòng --")) {
            sql += " AND TRIM(a.trang_thai) = TRIM(?)";
            params.add(trangThai);
        }

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setString(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ThongTinPhong phong = new ThongTinPhong();
                phong.setMa_phong(rs.getString("ma_phong"));
                phong.setLoai_phong(rs.getString("loai_phong"));
                phong.setGia_tien(rs.getString("gia_phong") != null ? rs.getString("gia_phong") : "0");
                phong.setTrang_thai(rs.getString("trang_thai"));
                phong.setGhi_chu(rs.getString("ghi_chu"));
                danhSach.add(phong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return danhSach;
    }

    public boolean update(ThongTinPhong ttp) {
        String sql = "UPDATE thong_tin_phong SET loai_phong = ?, trang_thai = ?, ghi_chu = ? WHERE ma_phong = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, ttp.getLoai_phong());
            preStm.setString(2, ttp.getTrang_thai());
            preStm.setString(3, ttp.getGhi_chu());
            preStm.setString(4, ttp.getMa_phong());

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsMaPhong(String maPhong) {
        String sql = "SELECT COUNT(*) FROM thong_tin_phong WHERE ma_phong = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, maPhong);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
