/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.DichVuHoTro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DichVuHoTroDAO {
    public List<DichVuHoTro> findAll(){
        String sql = "select * from dich_vu_ho_tro";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            List<DichVuHoTro> list = new ArrayList<>();
            ResultSet rs = pps.executeQuery();
            while(rs.next()){
                DichVuHoTro dv = new DichVuHoTro();
                dv.setMa_dich_vu(rs.getInt("ma_dich_vu"));
                dv.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                dv.setTen_dich_vu(rs.getString("ten_dich_vu"));
                dv.setNoi_dung(rs.getString("noi_dung"));
                dv.setTrang_thai(rs.getString("trang_thai"));
                list.add(dv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public DichVuHoTro findById(int maDichVu){
        String sql = "select * from dich_vu_ho_tro where ma_dich_vu = ?";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            pps.setInt(1, maDichVu);
            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                DichVuHoTro dv = new DichVuHoTro();
                dv.setMa_dich_vu(rs.getInt("ma_dich_vu"));
                dv.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                dv.setTen_dich_vu(rs.getString("ten_dich_vu"));
                dv.setNoi_dung(rs.getString("noi_dung"));
                return dv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean insert(DichVuHoTro dv){
        String sql = "insert into dich_vu_ho_tro values(?,?,?)";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            pps.setInt(1, dv.getMa_khach_hang());
            pps.setString(2, dv.getTen_dich_vu());
            pps.setString(3, dv.getNoi_dung());
            return pps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateTrangThai(int maDichVu, String trangThai){
        String sql = "update dich_vu_ho_tro set trang_thai = ? where ma_dich_vu = ?";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            pps.setString(1, trangThai);
            pps.setInt(2, maDichVu);
            return pps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
