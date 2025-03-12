/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
public class LoginSystemDAO {

    public boolean checkLogin(String email, String password) {
        String sql = "SELECT * FROM nhan_vien WHERE email = ? AND mat_khau = ?";
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
