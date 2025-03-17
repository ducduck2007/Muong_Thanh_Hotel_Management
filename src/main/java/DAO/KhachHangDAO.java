/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Ggame
 */
public class KhachHangDAO {

    public boolean insertKH(KhachHang kh) {
        String sql = "INSERT INTO khach_hang (ten_khach_hang, email, mat_khau, so_dien_thoai) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {

            preStm.setString(1, kh.getTen_khach_hang());
            preStm.setString(2, kh.getEmail());
            preStm.setString(3, kh.getMat_khau());
            preStm.setString(4, kh.getSo_dien_thoai());

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkEmailKHExitsts(String email) {
        String sql = "SELECT COUNT(*) FROM khach_hang WHERE email = ?";

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

    public boolean checkLogin(String email, String password) {
        String sql = "SELECT * FROM khach_hang WHERE email = ? AND mat_khau = ?";
        try (Connection conn = DataProvider.dataConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
