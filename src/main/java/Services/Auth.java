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
public class Auth {
    public static QuanLyNhanVien user = null;
    
    public static void clear(){
        Auth.user = null;
    }
    
    public static int isManager(){
        return user.getVai_tro();    }
}
