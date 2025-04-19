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
        String sql = "SELECT llv.ma_lich, llv.ma_nhan_vien, nv.ten_nhan_vien, nv.vai_tro, llv.ngay_lam_viec, llv.ca_lam_viec "
                + "FROM lich_lam_viec llv "
                + "JOIN nhan_vien nv ON llv.ma_nhan_vien = nv.ma_nhan_vien";

        List<LichLamViec> list = new ArrayList<>();

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql); ResultSet rs = preStm.executeQuery()) {

            while (rs.next()) {
                LichLamViec llv = new LichLamViec();
                llv.setMaLich(rs.getInt("ma_lich"));
                llv.setMaNhanVien(rs.getString("ma_nhan_vien"));
                llv.setTenNhanVien(rs.getString("ten_nhan_vien"));
                llv.setVaiTro(rs.getInt("vai_tro"));
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
        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {

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
        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {

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
        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {

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

    public boolean insert(LichLamViec llv) {
        if (!existsMaNhanVien(llv.getMaNhanVien())) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại!");
            return false;
        }

        try (Connection conn = DataProvider.dataConnection()) {
            String checkAllWeekSql = "SELECT ma_lich FROM lich_lam_viec WHERE ma_nhan_vien = ? AND ngay_lam_viec = 'Cả tuần'";
            try (PreparedStatement checkAllWeekStm = conn.prepareStatement(checkAllWeekSql)) {
                checkAllWeekStm.setString(1, llv.getMaNhanVien());
                ResultSet rs = checkAllWeekStm.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Nhân viên đã có lịch làm việc cả tuần, không thể thêm lịch riêng từng ngày!");
                    return false;
                }
            }

            if (llv.getNgayLamViec().equalsIgnoreCase("Cả tuần")) {
                String checkAnyDaySql = "SELECT ma_lich FROM lich_lam_viec WHERE ma_nhan_vien = ? AND ngay_lam_viec != 'Cả tuần'";
                try (PreparedStatement checkAnyDayStm = conn.prepareStatement(checkAnyDaySql)) {
                    checkAnyDayStm.setString(1, llv.getMaNhanVien());
                    ResultSet rs = checkAnyDayStm.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Nhân viên đã có lịch từng ngày, không thể thêm lịch cả tuần!");
                        return false;
                    }
                }
            }

            String checkDuplicateSql = "SELECT ma_lich FROM lich_lam_viec WHERE ma_nhan_vien = ? AND ngay_lam_viec = ? AND ca_lam_viec = ?";
            try (PreparedStatement checkDupStm = conn.prepareStatement(checkDuplicateSql)) {
                checkDupStm.setString(1, llv.getMaNhanVien());
                checkDupStm.setString(2, llv.getNgayLamViec());
                checkDupStm.setString(3, llv.getCaLamViec());
                ResultSet rs = checkDupStm.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Nhân viên đã có lịch làm việc trùng ngày và ca!");
                    return false;
                }
            }

            String insertSql = "INSERT INTO lich_lam_viec (ma_nhan_vien, ngay_lam_viec, ca_lam_viec) VALUES (?, ?, ?)";
            try (PreparedStatement preStm = conn.prepareStatement(insertSql)) {
                preStm.setString(1, llv.getMaNhanVien());
                preStm.setString(2, llv.getNgayLamViec());
                preStm.setString(3, llv.getCaLamViec());
                return preStm.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public LichLamViec findByMaNV(String maNV) {
        String sql = "select * from lich_lam_viec where ma_nhan_vien = ?";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            pps.setString(1, maNV);
            ResultSet rs = pps.executeQuery();
            if (rs.next()) {
                LichLamViec lv = new LichLamViec();
                lv.setMaLich(rs.getInt("ma_lich"));
                lv.setMaNhanVien(rs.getString("ma_nhan_vien"));
                lv.setNgayLamViec(rs.getString("ngay_lam_viec"));
                lv.setCaLamViec(rs.getString("ca_lam_viec"));
                return lv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
