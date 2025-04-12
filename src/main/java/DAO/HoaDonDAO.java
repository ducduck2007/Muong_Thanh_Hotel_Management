/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ggame
 */
public class HoaDonDAO {

    public List<HoaDon> getData() {
        String sql = "SELECT kh.ma_khach_hang, kh.so_dien_thoai, dp.ngay_dat_phong, "
                + "SUM(dp.tong_tien) AS tong_tien, "
                + "STRING_AGG(dp.ma_phong, ', ') AS danh_sach_phong "
                + "FROM thong_tin_dat_phong dp "
                + "JOIN khach_hang kh ON dp.ma_khach_hang = kh.ma_khach_hang "
                + "WHERE dp.ngay_tra_phong > '1900-01-01' AND dp.type = 1 "
                + "GROUP BY kh.ma_khach_hang, kh.so_dien_thoai, dp.ngay_dat_phong "
                + "ORDER BY dp.ngay_dat_phong DESC, kh.ma_khach_hang";

        List<HoaDon> list = new ArrayList<>();

        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();

            while (rs.next()) {
                HoaDon tlg = new HoaDon();
                tlg.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                tlg.setSo_dien_thoai(rs.getString("so_dien_thoai"));
                tlg.setTong_tien(rs.getBigDecimal("tong_tien"));
                tlg.setDanh_sach_phong(rs.getString("danh_sach_phong"));
                tlg.setNgay_dat_phong(rs.getDate("ngay_dat_phong"));
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

}
