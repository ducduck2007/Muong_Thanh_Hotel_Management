/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.ThietLapGia;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
public class ThietLapGiaDAO {

    public List<ThietLapGia> getData() {
        String sql = "SELECT * FROM thiet_lap_gia";
        List<ThietLapGia> list = new ArrayList<>();

        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();

            while (rs.next()) {
                ThietLapGia tlg = new ThietLapGia();
                tlg.setMa_gia(rs.getInt("ma_gia"));
                tlg.setLoai_phong(rs.getString("loai_phong"));
                tlg.setGia_tien(rs.getBigDecimal("gia_phong"));
                tlg.setThoi_luong(rs.getInt("thoi_luong"));
                tlg.setGhi_chu(rs.getString("ghi_chu"));
                list.add(tlg);
            }
            rs.close();
            preStm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean update(ThietLapGia tlg) {
        String sql = "UPDATE thiet_lap_gia SET loai_phong = ?, gia_phong = ?, thoi_luong = ?, ghi_chu = ? WHERE ma_gia = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, tlg.getLoai_phong());
            preStm.setBigDecimal(2, tlg.getGia_tien());
            preStm.setInt(3, tlg.getThoi_luong());
            preStm.setString(4, tlg.getGhi_chu());
            preStm.setInt(5, tlg.getMa_gia());

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsMaGia(int maGia) {
        String sql = "SELECT COUNT(*) FROM thiet_lap_gia WHERE ma_gia = ?";
        try (Connection conn = DataProvider.dataConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, maGia);
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
