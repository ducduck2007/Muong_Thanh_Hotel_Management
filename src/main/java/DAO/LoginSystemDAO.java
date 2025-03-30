/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.QuanLyNhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ADMIN
 */
public class LoginSystemDAO {

    public boolean checkLogin(String email, String password) {
        String sql = "SELECT mat_khau FROM nhan_vien WHERE email = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("mat_khau");
                return BCrypt.checkpw(password, hashedPassword);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public QuanLyNhanVien findByEmail(String email) {
        String sql = "SELECT * FROM nhan_vien WHERE email = ?";
        try (Connection con = DataProvider.dataConnection(); PreparedStatement pps = con.prepareStatement(sql)) {
            pps.setString(1, email);
            ResultSet rs = pps.executeQuery();
            if (rs.next()) {
                QuanLyNhanVien nv = new QuanLyNhanVien();
                nv.setMa_nhan_vien(rs.getString("ma_nhan_vien"));
                nv.setTen_nhan_vien(rs.getString("ten_nhan_vien"));
                nv.setEmail(rs.getString("email"));
                nv.setVai_tro(rs.getInt("vai_tro"));
                nv.setGhi_chu(rs.getString("ghi_chu"));
                return nv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
