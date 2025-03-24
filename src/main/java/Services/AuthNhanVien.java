/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.QuanLyNhanVien;

/**
 *
 * @author Admin
 */
public class AuthNhanVien {

    public static QuanLyNhanVien user = null;

    public static void clear() {
        AuthNhanVien.user = null;
    }

    public static int isManager() {
        return user.getVai_tro();
    }
    
    public static String getMaNhanVien() {
        return user.getMa_nhan_vien();
    }
}
