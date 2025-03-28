/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.LichLamViec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ggame
 */
public class LichLamViecDAO {

    public List<LichLamViec> getData() {
        String sql = "SELECT * FROM lich_lam_viec";
        List<LichLamViec> list = new ArrayList<>();

        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();

            while (rs.next()) {
                LichLamViec llv = new LichLamViec();
                llv.setMaLich(rs.getInt("ma_lich"));
                llv.setMaNhanVien(rs.getString("ma_nhan_vien"));
                llv.setNgayLamViec(rs.getString("ngay_lam_viec"));
                llv.setCaLamViec(rs.getString("ca_lam_viec"));
                list.add(llv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean existsMaLich(int maLich) {
        String sql = "SELECT COUNT(*) FROM lich_lam_viec WHERE ma_lich = ?";
        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);
            preStm.setInt(1, maLich);
            ResultSet rs = preStm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsMaNhanVien(String maNhanVien) {
        String sql = "SELECT COUNT(*) FROM nhan_vien WHERE ma_nhan_vien = ?";
        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);
            preStm.setString(1, maNhanVien);
            ResultSet rs = preStm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(LichLamViec llv) {
        if (!existsMaLich(llv.getMaLich())) {
            JOptionPane.showMessageDialog(null, "Mã lịch làm việc không tồn tại!");
            return false;
        }

        if (!existsMaNhanVien(llv.getMaNhanVien())) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại!");
            return false;
        }

        String sql = "UPDATE lich_lam_viec SET ma_nhan_vien = ?, ngay_lam_viec = ?, ca_lam_viec = ? WHERE ma_lich = ?";
        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);

            preStm.setString(1, llv.getMaNhanVien());
            preStm.setString(2, llv.getNgayLamViec());
            preStm.setString(3, llv.getCaLamViec());
            preStm.setInt(4, llv.getMaLich());

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
