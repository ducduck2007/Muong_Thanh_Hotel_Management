/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.ThongTinDatPhong;
import Models.ThongTinPhong;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
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
}
