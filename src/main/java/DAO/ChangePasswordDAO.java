/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.ChangePassword;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author doqua
 */
public class ChangePasswordDAO {

    public static String getEmailKH(int maKhachHang) {
        String email = null;

        try {
            Connection conn = DataProvider.dataConnection();
            String query = "SELECT email FROM khach_hang WHERE ma_khach_hang = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, maKhachHang);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                email = rs.getString("email");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }

    public static String getEmailNV(String maNhanVien) {
        String email = null;

        try {
            Connection conn = DataProvider.dataConnection();
            String query = "SELECT email FROM nhan_vien WHERE ma_nhan_vien = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, maNhanVien);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                email = rs.getString("email");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }

    public boolean updateMKKH(ChangePassword cp) {
        String sql = "UPDATE khach_hang SET mat_khau = ? WHERE email = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cp.getMkKH());
            stmt.setString(2, cp.getEmailKH());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkOldPasswordKH(String email, String oldPassword) {
        String sql = "SELECT mat_khau FROM khach_hang WHERE email = ?";
        try (Connection conn = DataProvider.dataConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("mat_khau");
                    return BCrypt.checkpw(oldPassword, hashedPassword);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateMKNV(ChangePassword cp) {
        String sql = "UPDATE nhan_vien SET mat_khau = ? WHERE email = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cp.getMkNV());
            stmt.setString(2, cp.getEmailNV());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkOldPasswordNV(String email, String oldPassword) {
        String sql = "SELECT mat_khau FROM nhan_vien WHERE email = ?";
        try (Connection conn = DataProvider.dataConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("mat_khau");
                    return BCrypt.checkpw(oldPassword, hashedPassword);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getMatKhauKH(String email) {
        String matKhau = null;
        String sql = "SELECT mat_khau FROM khach_hang WHERE email = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    matKhau = rs.getString("mat_khau");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matKhau;
    }

    public String getMatKhauNV(String email) {
        String matKhau = null;
        String sql = "SELECT mat_khau FROM nhan_vien WHERE email = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    matKhau = rs.getString("mat_khau");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matKhau;
    }

}
