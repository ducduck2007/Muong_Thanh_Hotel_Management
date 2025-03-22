/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.QuanLyNhanVien;

/**
 *
 * @author Ggame
 */
public class AuthMaNhanVien {

    public static QuanLyNhanVien user = null;

    public static void clear() {
        Auth.user = null;
    }

    public static String maNhanVien() {
        return user.getMa_quan_ly();
    }
}
