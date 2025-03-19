/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
    
    public KhachHang findByEmail(String email){
        String sql = "select * from khach_hang where email = ?";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            pps.setString(1, email);
            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                KhachHang kh = new KhachHang();
                kh.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                kh.setTen_khach_hang(rs.getString("ten_khach_hang"));
                kh.setEmail(rs.getString("email"));
                kh.setMat_khau(rs.getString("mat_khau"));
                kh.setSo_dien_thoai(rs.getString("so_dien_thoai"));
                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<KhachHang> findAll(){
        String sql = "select * from khach_hang";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            ResultSet rs = pps.executeQuery();
            List<KhachHang> list = new ArrayList<>();
            while(rs.next()){
                KhachHang kh = new KhachHang();
                kh.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                kh.setTen_khach_hang(rs.getString("ten_khach_hang"));
                kh.setEmail(rs.getString("email"));
                kh.setMat_khau(rs.getString("mat_khau"));
                kh.setSo_dien_thoai(rs.getString("so_dien_thoai"));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public KhachHang findById(int maKhachhang){
        String sql = "select * from khach_hang where ma_khach_hang = ?";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            pps.setInt(1, maKhachhang);
            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                KhachHang kh = new KhachHang();
                kh.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                kh.setTen_khach_hang(rs.getString("ten_khach_hang"));
                kh.setEmail(rs.getString("email"));
                kh.setMat_khau(rs.getString("mat_khau"));
                kh.setSo_dien_thoai(rs.getString("so_dien_thoai"));
                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<KhachHang> findByTen(String tenKhachHang){
        String sql = "select * from khach_hang where ten_khach_hang like '%'+?+'%'";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            pps.setString(1, tenKhachHang);
            ResultSet rs = pps.executeQuery();
            List<KhachHang> list = new ArrayList<>();
            while(rs.next()){
                KhachHang kh = new KhachHang();
                kh.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                kh.setTen_khach_hang(rs.getString("ten_khach_hang"));
                kh.setEmail(rs.getString("email"));
                kh.setMat_khau(rs.getString("mat_khau"));
                kh.setSo_dien_thoai(rs.getString("so_dien_thoai"));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean update(KhachHang kh){
        String sql = "update khach_hang set ten_khach_hang = ?, email = ?, so_dien_thoai = ? where ma_khach_hang = ?";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            pps.setString(1, kh.getTen_khach_hang());
            pps.setString(2, kh.getEmail());
            pps.setString(3, kh.getSo_dien_thoai());
            pps.setInt(4, kh.getMa_khach_hang());
            return pps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean delete(int maKhachHang){
        String sql = "delete from khach_hang where ma_khach_hang = ?";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            pps.setInt(1, maKhachHang);
            return pps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
