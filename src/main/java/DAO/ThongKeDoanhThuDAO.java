/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.ThongKeDoanhThu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ggame
 */
public class ThongKeDoanhThuDAO {

    public List<ThongKeDoanhThu> getData() {
        String sql = "SELECT ngay_dat_phong, ten_khach_hang AS khach_hang, SUM(tong_tien) AS tong_tien, STUFF( (SELECT DISTINCT ',' + ma_phong FROM hoa_don_chi_tiet AS sub WHERE sub.ngay_dat_phong = main.ngay_dat_phong AND sub.ten_khach_hang = main.ten_khach_hang FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 1, '' ) AS ma_phong\n"
                + "FROM hoa_don_chi_tiet AS main GROUP BY ngay_dat_phong, ten_khach_hang";
        List<ThongKeDoanhThu> list = new ArrayList<>();

        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();

            while (rs.next()) {
                ThongKeDoanhThu tkdt = new ThongKeDoanhThu();
                tkdt.setDs_thue_phong(rs.getString("ma_phong"));
                tkdt.setKhach_hang(rs.getString("khach_hang"));
                tkdt.setTong_tien(rs.getString("tong_tien"));
                tkdt.setNgay_dat_phong(rs.getString("ngay_dat_phong"));
                list.add(tkdt);
            }
            rs.close();
            preStm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<ThongKeDoanhThu> getData2(String khachHang, String ngayDatPhong) {
        String sql = "SELECT * FROM hoa_don_chi_tiet WHERE ten_khach_hang = ? AND ngay_dat_phong = ?";
        List<ThongKeDoanhThu> list = new ArrayList<>();

        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);
            preStm.setString(1, khachHang);
            preStm.setString(2, ngayDatPhong);
            ResultSet rs = preStm.executeQuery();

            while (rs.next()) {
                ThongKeDoanhThu tkdt = new ThongKeDoanhThu();
                tkdt.setMa_phong(rs.getString("ma_phong"));
                tkdt.setLoai_phong(rs.getString("loai_phong"));
                tkdt.setTong_tien(rs.getString("tong_tien"));
                tkdt.setKhach_hang(rs.getString("ten_khach_hang"));
                tkdt.setDich_vu(rs.getString("ten_dich_vu"));
                tkdt.setNgay_dat_phong(rs.getString("ngay_dat_phong"));
                tkdt.setNgay_nhan_phong(rs.getString("ngay_nhan_phong"));
                tkdt.setNgay_tra_phong(rs.getString("ngay_tra_phong"));
                list.add(tkdt);
            }

            rs.close();
            preStm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
