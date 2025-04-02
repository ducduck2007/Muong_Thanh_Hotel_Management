/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.HoaDonChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ggame
 */
public class HoaDonChiTietDAO {

    public List<HoaDonChiTiet> getThongTinDatPhong(String maKhachHang, String ngayDatPhong, String maNhanVien) {
        List<HoaDonChiTiet> danhSach = new ArrayList<>();

        String sql = "SELECT ttp.ma_khach_hang, kh.ten_khach_hang, ttp.ma_phong, ttp.ma_dat_phong, ? AS ma_nhan_vien, "
                + "COALESCE(dvht.ma_dich_vu, '') AS ma_dich_vu, dvht.ten_dich_vu, dvht.noi_dung, "
                + "ttp.loai_phong, ttp.tong_tien, ttp.ngay_dat_phong, ttp.ngay_nhan_phong, ttp.ngay_tra_phong "
                + "FROM thong_tin_dat_phong ttp "
                + "LEFT JOIN khach_hang kh ON ttp.ma_khach_hang = kh.ma_khach_hang "
                + "LEFT JOIN dich_vu_ho_tro dvht ON ttp.ma_khach_hang = dvht.ma_khach_hang "
                + "WHERE ttp.ma_khach_hang = ? AND ttp.ngay_dat_phong = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            int maKH;
            java.sql.Date sqlDate;

            try {
                maKH = Integer.parseInt(maKhachHang);
            } catch (NumberFormatException e) {
                System.err.println("⚠ ERROR: maKhachHang không hợp lệ! Dữ liệu nhận được: " + maKhachHang);
                return danhSach;
            }

            try {
                sqlDate = java.sql.Date.valueOf(ngayDatPhong);
            } catch (IllegalArgumentException e) {
                System.err.println("⚠ ERROR: ngayDatPhong không hợp lệ! Dữ liệu nhận được: " + ngayDatPhong);
                return danhSach;
            }

            ps.setString(1, maNhanVien);
            ps.setInt(2, maKH);
            ps.setDate(3, sqlDate);

            System.out.println("DEBUG: Query - " + ps.toString());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonChiTiet hd = new HoaDonChiTiet();
                    hd.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                    hd.setTen_khach_hang(rs.getString("ten_khach_hang"));
                    hd.setMa_phong(rs.getString("ma_phong"));
                    hd.setMa_dat_phong(rs.getInt("ma_dat_phong"));
                    hd.setMa_nhan_vien(rs.getString("ma_nhan_vien"));
                    hd.setMa_dich_vu(rs.getInt("ma_dich_vu"));
                    hd.setTen_dich_vu(rs.getString("ten_dich_vu"));
                    hd.setNoi_dung_ho_tro(rs.getString("noi_dung"));
                    hd.setLoai_phong(rs.getString("loai_phong"));
                    hd.setTong_tien(rs.getBigDecimal("tong_tien"));
                    hd.setNgay_dat_phong(rs.getDate("ngay_dat_phong"));
                    hd.setNgay_nhan_phong(rs.getDate("ngay_nhan_phong"));
                    hd.setNgay_tra_phong(rs.getDate("ngay_tra_phong"));

                    danhSach.add(hd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    public void insertHoaDonChiTiet(HoaDonChiTiet hoaDon) {
        String checkServiceSql = "SELECT ten_dich_vu, ma_dich_vu FROM dich_vu_ho_tro WHERE ma_dich_vu = ?";
        String checkEmptyServiceSql = "SELECT COUNT(*) FROM dich_vu_ho_tro";
        String insertDefaultServiceSql = "INSERT INTO dich_vu_ho_tro (ten_dich_vu) VALUES ('Không có')";
        String getLastServiceIdSql = "SELECT TOP 1 ma_dich_vu FROM dich_vu_ho_tro ORDER BY ma_dich_vu DESC";
        String checkSql = "SELECT COUNT(*) FROM hoa_don_chi_tiet WHERE ma_khach_hang = ? AND ten_khach_hang = ? AND ma_phong = ? "
                + "AND ma_dat_phong = ? AND ma_nhan_vien = ? AND ma_dich_vu = ? AND loai_phong = ? "
                + "AND tong_tien = ? AND ngay_dat_phong = ? AND ngay_nhan_phong = ? AND ngay_tra_phong = ?";

        String insertSql = "INSERT INTO hoa_don_chi_tiet (ma_khach_hang, ten_khach_hang, ma_phong, ma_dat_phong, ma_nhan_vien, "
                + "ma_dich_vu, ten_dich_vu, loai_phong, tong_tien, ngay_dat_phong, ngay_nhan_phong, ngay_tra_phong) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement checkEmptyServiceStmt = conn.prepareStatement(checkEmptyServiceSql); PreparedStatement insertDefaultServiceStmt = conn.prepareStatement(insertDefaultServiceSql); PreparedStatement getLastServiceIdStmt = conn.prepareStatement(getLastServiceIdSql); PreparedStatement checkServiceStmt = conn.prepareStatement(checkServiceSql); PreparedStatement checkStmt = conn.prepareStatement(checkSql); PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            ResultSet emptyServiceRs = checkEmptyServiceStmt.executeQuery();
            if (emptyServiceRs.next() && emptyServiceRs.getInt(1) == 0) {
                insertDefaultServiceStmt.executeUpdate();
            }

            int maDichVu = 0;
            String tenDichVu = "Không sử dụng dịch vụ";

            checkServiceStmt.setInt(1, hoaDon.getMa_dich_vu());
            ResultSet serviceRs = checkServiceStmt.executeQuery();
            if (serviceRs.next()) {
                tenDichVu = serviceRs.getString("ten_dich_vu");
                maDichVu = serviceRs.getInt("ma_dich_vu");
            } else {
                ResultSet lastServiceIdRs = getLastServiceIdStmt.executeQuery();
                if (lastServiceIdRs.next()) {
                    maDichVu = lastServiceIdRs.getInt("ma_dich_vu");
                }
            }

            checkStmt.setInt(1, hoaDon.getMa_khach_hang());
            checkStmt.setString(2, hoaDon.getTen_khach_hang());
            checkStmt.setString(3, hoaDon.getMa_phong());
            checkStmt.setInt(4, hoaDon.getMa_dat_phong());
            checkStmt.setString(5, hoaDon.getMa_nhan_vien());
            checkStmt.setInt(6, maDichVu);
            checkStmt.setString(7, hoaDon.getLoai_phong());
            checkStmt.setBigDecimal(8, hoaDon.getTong_tien());
            checkStmt.setDate(9, new java.sql.Date(hoaDon.getNgay_dat_phong().getTime()));
            checkStmt.setDate(10, new java.sql.Date(hoaDon.getNgay_nhan_phong().getTime()));
            checkStmt.setDate(11, new java.sql.Date(hoaDon.getNgay_tra_phong().getTime()));

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "⚠ Hóa đơn chi tiết đã được in rồi!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            insertStmt.setInt(1, hoaDon.getMa_khach_hang());
            insertStmt.setString(2, hoaDon.getTen_khach_hang());
            insertStmt.setString(3, hoaDon.getMa_phong());
            insertStmt.setInt(4, hoaDon.getMa_dat_phong());
            insertStmt.setString(5, hoaDon.getMa_nhan_vien());
            insertStmt.setInt(6, maDichVu);
            insertStmt.setString(7, tenDichVu);
            insertStmt.setString(8, hoaDon.getLoai_phong());
            insertStmt.setBigDecimal(9, hoaDon.getTong_tien());
            insertStmt.setDate(10, new java.sql.Date(hoaDon.getNgay_dat_phong().getTime()));
            insertStmt.setDate(11, new java.sql.Date(hoaDon.getNgay_nhan_phong().getTime()));
            insertStmt.setDate(12, new java.sql.Date(hoaDon.getNgay_tra_phong().getTime()));

            int affectedRows = insertStmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "✅ Hóa đơn chi tiết đã được lưu!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "❌ Lỗi: Không thể lưu hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "❌ Lỗi kết nối CSDL!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<HoaDonChiTiet> getData() {
        String sql = "SELECT * FROM hoa_don_chi_tiet";
        List<HoaDonChiTiet> list = new ArrayList<>();

        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();

            while (rs.next()) {
                HoaDonChiTiet ttp = new HoaDonChiTiet();
                ttp.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                ttp.setTen_khach_hang(rs.getString("ten_khach_hang"));
                ttp.setMa_phong(rs.getString("ma_phong"));
                ttp.setMa_dat_phong(rs.getInt("ma_dat_phong"));
                ttp.setMa_nhan_vien(rs.getString("ma_nhan_vien"));
                ttp.setMa_dich_vu(rs.getInt("ma_dich_vu"));
                ttp.setTen_dich_vu(rs.getString("ten_dich_vu"));
                ttp.setLoai_phong(rs.getString("loai_phong"));
                ttp.setTong_tien(rs.getBigDecimal("tong_tien"));
                ttp.setNgay_dat_phong(rs.getDate("ngay_dat_phong"));
                ttp.setNgay_nhan_phong(rs.getDate("ngay_nhan_phong"));
                ttp.setNgay_tra_phong(rs.getDate("ngay_tra_phong"));
                list.add(ttp);
            }
            rs.close();
            preStm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
