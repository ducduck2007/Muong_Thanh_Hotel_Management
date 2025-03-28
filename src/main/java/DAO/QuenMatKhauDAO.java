/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Ggame
 */
public class QuenMatKhauDAO {

    public boolean updatePasswordInDatabase(String email, String newPassword) {
        String sql = "UPDATE khach_hang SET mat_khau = ? WHERE email = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newPassword);
            stmt.setString(2, email);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (Exception e) {
            System.err.println("Lỗi cập nhật mật khẩu trong DB: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
