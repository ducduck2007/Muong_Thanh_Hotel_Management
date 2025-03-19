/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.KhachHang;

/**
 *
 * @author Admin
 */
public class AuthKhachHang {
    public static KhachHang user = null;
    
    public static void clear(){
        AuthKhachHang.user = null;
    }
    
    public static int maKhachHang(){
        return user.getMa_khach_hang();
    }
}
