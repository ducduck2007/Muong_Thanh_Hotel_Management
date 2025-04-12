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

    public List<DichVuHoTro> findAll() {
        String sql = "SELECT * FROM dich_vu_ho_tro";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            List<DichVuHoTro> list = new ArrayList<>();
            ResultSet rs = pps.executeQuery();
            while (rs.next()) {
                DichVuHoTro dv = new DichVuHoTro();
                dv.setMa_dich_vu(rs.getInt("ma_dich_vu"));
                dv.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                dv.setTen_dich_vu(rs.getString("ten_dich_vu"));
                dv.setNoi_dung(rs.getString("noi_dung"));
                dv.setTrang_thai(rs.getString("trang_thai"));
                dv.setPhan_hoi_khach_hang(rs.getString("phan_hoi_khach_hang"));
                list.add(dv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DichVuHoTro> findByKhachHang(int maKhachHang) {
        String sql = "select * from dich_vu_ho_tro where ma_khach_hang = ?";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            pps.setInt(1, maKhachHang);
            List<DichVuHoTro> list = new ArrayList<>();
            ResultSet rs = pps.executeQuery();
            while (rs.next()) {
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

    public DichVuHoTro findById(int maDichVu) {
        String sql = "select * from dich_vu_ho_tro where ma_dich_vu = ?";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            pps.setInt(1, maDichVu);
            ResultSet rs = pps.executeQuery();
            if (rs.next()) {
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

    public boolean insert(DichVuHoTro dv) {
        String sql = "INSERT INTO dich_vu_ho_tro (ma_khach_hang, ten_dich_vu, noi_dung, trang_thai) VALUES (?, ?, ?, ?)";

        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            pps.setInt(1, dv.getMa_khach_hang());
            pps.setString(2, dv.getTen_dich_vu());
            pps.setString(3, dv.getNoi_dung());
            pps.setString(4, "Chưa xử lý");
            return pps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTrangThai(int maDichVu, String trangThai, String phanHoi) {
        String sql = "UPDATE dich_vu_ho_tro SET trang_thai = ?, phan_hoi_khach_hang = ? WHERE ma_dich_vu = ?";
        try {
            Connection con = DataProvider.dataConnection();
            PreparedStatement pps = con.prepareCall(sql);
            pps.setString(1, trangThai);
            pps.setString(2, phanHoi);
            pps.setInt(3, maDichVu);
            return pps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
