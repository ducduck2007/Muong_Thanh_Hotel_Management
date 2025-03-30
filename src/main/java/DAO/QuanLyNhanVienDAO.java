/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.QuanLyNhanVien;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Ggame
 */
public class QuanLyNhanVienDAO {

    public List<QuanLyNhanVien> getDataNV() {
        String sql = "SELECT * FROM nhan_vien WHERE ma_quan_ly IS NULL";
        List<QuanLyNhanVien> list = new ArrayList<>();

        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();

            while (rs.next()) {
                QuanLyNhanVien qlnv = new QuanLyNhanVien();
                qlnv.setMa_nhan_vien(rs.getString("ma_nhan_vien"));
                qlnv.setTen_nhan_vien(rs.getString("ten_nhan_vien"));
                qlnv.setEmail(rs.getString("email"));
                qlnv.setVai_tro(rs.getInt("vai_tro"));
                qlnv.setGhi_chu(rs.getString("ghi_chu"));
                list.add(qlnv);
            }
            rs.close();
            preStm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<QuanLyNhanVien> getDataQL() {
        String sql = "SELECT * FROM nhan_vien WHERE ma_quan_ly IS NOT NULL";
        List<QuanLyNhanVien> list = new ArrayList<>();

        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();

            while (rs.next()) {
                QuanLyNhanVien qlnv = new QuanLyNhanVien();
                qlnv.setMa_quan_ly(rs.getString("ma_quan_ly"));
                qlnv.setTen_nhan_vien(rs.getString("ten_nhan_vien"));
                qlnv.setEmail(rs.getString("email"));
                qlnv.setGhi_chu(rs.getString("ghi_chu"));
                list.add(qlnv);
            }
            rs.close();
            preStm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean insertNV(QuanLyNhanVien qlnv) {
        String sql = "INSERT INTO nhan_vien (ma_nhan_vien, ten_nhan_vien, email, mat_khau, vai_tro, ghi_chu) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {

            preStm.setString(1, qlnv.getMa_nhan_vien());
            preStm.setString(2, qlnv.getTen_nhan_vien());
            preStm.setString(3, qlnv.getEmail());
            preStm.setString(4, qlnv.getMat_khau());
            preStm.setInt(5, qlnv.getVai_tro());
            preStm.setString(6, qlnv.getGhi_chu() != null ? qlnv.getGhi_chu() : "");

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateNV(QuanLyNhanVien qlnv) {
        System.out.printf("DEBUG: Updating NV - MaNV: %s, Ten: %s, Email: %s, MK: %s, VaiTro: %d, GhiChu: %s\n",
                qlnv.getMa_nhan_vien(),
                qlnv.getTen_nhan_vien(),
                qlnv.getEmail(),
                qlnv.getMat_khau(),
                qlnv.getVai_tro(),
                qlnv.getGhi_chu()
        );

        String sql = "UPDATE nhan_vien SET ten_nhan_vien = ?, email = ?, mat_khau = ?, vai_tro = ?, ghi_chu = ? WHERE ma_nhan_vien = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, qlnv.getTen_nhan_vien());
            preStm.setString(2, qlnv.getEmail());
            preStm.setString(3, qlnv.getMat_khau());
            preStm.setInt(4, qlnv.getVai_tro());
            preStm.setString(5, qlnv.getGhi_chu() != null ? qlnv.getGhi_chu() : "");
            preStm.setString(6, qlnv.getMa_nhan_vien());

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteNV(QuanLyNhanVien qlnv) {
        String sql = "DELETE FROM nhan_vien WHERE ma_nhan_vien = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {

            preStm.setString(1, qlnv.getMa_nhan_vien());

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkMaNhanVienExists(String ma_nhan_vien) {
        String sql = "SELECT COUNT(*) FROM nhan_vien WHERE ma_nhan_vien = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {

            preStm.setString(1, ma_nhan_vien);
            ResultSet rs = preStm.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkEmailNVExitsts(String email) {
        String sql = "SELECT COUNT(*) FROM nhan_vien WHERE email = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {

            preStm.setString(1, email);
            ResultSet rs = preStm.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateQL(QuanLyNhanVien qlnv) {
        String sql = "UPDATE nhan_vien SET ten_nhan_vien = ?, email = ?, mat_khau = ?, ghi_chu = ? WHERE ma_quan_ly = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {

            preStm.setString(1, qlnv.getTen_nhan_vien());
            preStm.setString(2, qlnv.getEmail());
            preStm.setString(3, qlnv.getMat_khau());
            preStm.setString(4, qlnv.getGhi_chu() != null ? qlnv.getGhi_chu() : "");
            preStm.setString(5, qlnv.getMa_quan_ly());

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<QuanLyNhanVien> searchNhanVienByName(String ten_nhan_vien) {
        List<QuanLyNhanVien> listNV = new ArrayList<>();
        String sql = "SELECT * FROM nhan_vien WHERE ten_nhan_vien LIKE ? AND ma_quan_ly IS NULL";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {

            preStm.setString(1, "%" + ten_nhan_vien + "%");
            ResultSet rs = preStm.executeQuery();

            while (rs.next()) {
                QuanLyNhanVien nv = new QuanLyNhanVien();
                nv.setMa_nhan_vien(rs.getString("ma_nhan_vien"));
                nv.setTen_nhan_vien(rs.getString("ten_nhan_vien"));
                nv.setEmail(rs.getString("email"));
                nv.setMat_khau(rs.getString("mat_khau"));
                nv.setVai_tro(rs.getInt("vai_tro"));
                nv.setGhi_chu(rs.getString("ghi_chu"));

                listNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    }

    public String getMatKhauByMaNV(String maNhanVien) {
        String sql = "SELECT mat_khau FROM nhan_vien WHERE ma_nhan_vien = ?";
        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {

            preStm.setString(1, maNhanVien);
            ResultSet rs = preStm.executeQuery();

            if (rs.next()) {
                return rs.getString("mat_khau");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getMatKhauByMaQL(String maQuanLy) {
        String sql = "SELECT mat_khau FROM nhan_vien WHERE ma_quan_ly = ?";
        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {
            preStm.setString(1, maQuanLy);
            ResultSet rs = preStm.executeQuery();

            if (rs.next()) {
                return rs.getString("mat_khau");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
