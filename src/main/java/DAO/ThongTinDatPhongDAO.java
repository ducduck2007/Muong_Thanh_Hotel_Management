/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.ThongTinDatPhong;
import Models.ThongTinPhong;
import Services.AuthKhachHang;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Ggame
 */
public class ThongTinDatPhongDAO {

    public List<ThongTinDatPhong> getDataPD() {
        List<ThongTinDatPhong> list = new ArrayList<>();
        String sql;
        boolean locTheoKhachHang = AuthKhachHang.user != null;

        if (locTheoKhachHang) {
            sql = "SELECT * FROM thong_tin_dat_phong WHERE ngay_tra_phong > '1900-01-01' AND type = 1 AND ma_khach_hang = ?";
        } else {
            sql = "SELECT * FROM thong_tin_dat_phong WHERE ngay_tra_phong > '1900-01-01' AND type = 1";
        }

        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);

            if (locTheoKhachHang) {
                preStm.setInt(1, AuthKhachHang.maKhachHang());
            }

            ResultSet rs = preStm.executeQuery();
            while (rs.next()) {
                ThongTinDatPhong ttdp = new ThongTinDatPhong();
                ttdp.setMa_dat_phong(rs.getInt("ma_dat_phong"));
                ttdp.setMa_phong(rs.getString("ma_phong"));
                ttdp.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                ttdp.setLoai_phong(rs.getString("loai_phong"));
                ttdp.setNgay_dat_phong(rs.getDate("ngay_dat_phong"));
                ttdp.setTong_tien(rs.getBigDecimal("tong_tien"));
                ttdp.setGhi_chu(rs.getString("ghi_chu"));
                ttdp.setNgay_nhan_phong(rs.getDate("ngay_nhan_phong"));
                ttdp.setNgay_tra_phong(rs.getDate("ngay_tra_phong"));
                list.add(ttdp);
            }

            rs.close();
            preStm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<ThongTinDatPhong> getDataPH() {
        List<ThongTinDatPhong> list = new ArrayList<>();
        String sql;
        boolean locTheoKhachHang = AuthKhachHang.user != null;

        if (locTheoKhachHang) {
            sql = "SELECT * FROM thong_tin_dat_phong WHERE ngay_tra_phong > '1900-01-01' AND type = 3 AND ma_khach_hang = ?";
        } else {
            sql = "SELECT * FROM thong_tin_dat_phong WHERE ngay_tra_phong > '1900-01-01' AND type = 3";
        }

        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);

            if (locTheoKhachHang) {
                preStm.setInt(1, AuthKhachHang.maKhachHang());
            }

            ResultSet rs = preStm.executeQuery();
            while (rs.next()) {
                ThongTinDatPhong ttdp = new ThongTinDatPhong();
                ttdp.setMa_dat_phong(rs.getInt("ma_dat_phong"));
                ttdp.setMa_phong(rs.getString("ma_phong"));
                ttdp.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                ttdp.setLoai_phong(rs.getString("loai_phong"));
                ttdp.setNgay_dat_phong(rs.getDate("ngay_dat_phong"));
                ttdp.setTong_tien(rs.getBigDecimal("tong_tien"));
                ttdp.setGhi_chu(rs.getString("ghi_chu"));
                ttdp.setNgay_nhan_phong(rs.getDate("ngay_nhan_phong"));
                ttdp.setNgay_tra_phong(rs.getDate("ngay_tra_phong"));
                list.add(ttdp);
            }

            rs.close();
            preStm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<ThongTinDatPhong> getDataPDOld() {
        List<ThongTinDatPhong> list = new ArrayList<>();
        String sql;
        boolean locTheoKhachHang = AuthKhachHang.user != null;

        if (locTheoKhachHang) {
            sql = "SELECT * FROM thong_tin_dat_phong WHERE ngay_tra_phong > '1900-01-01' AND type = 2 AND ma_khach_hang = ?";
        } else {
            sql = "SELECT * FROM thong_tin_dat_phong WHERE ngay_tra_phong > '1900-01-01' AND type = 2";
        }

        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);

            if (locTheoKhachHang) {
                preStm.setInt(1, AuthKhachHang.maKhachHang());
            }

            ResultSet rs = preStm.executeQuery();
            while (rs.next()) {
                ThongTinDatPhong ttdp = new ThongTinDatPhong();
                ttdp.setMa_dat_phong(rs.getInt("ma_dat_phong"));
                ttdp.setMa_phong(rs.getString("ma_phong"));
                ttdp.setMa_khach_hang(rs.getInt("ma_khach_hang"));
                ttdp.setLoai_phong(rs.getString("loai_phong"));
                ttdp.setNgay_dat_phong(rs.getDate("ngay_dat_phong"));
                ttdp.setTong_tien(rs.getBigDecimal("tong_tien"));
                ttdp.setGhi_chu(rs.getString("ghi_chu"));
                ttdp.setNgay_nhan_phong(rs.getDate("ngay_nhan_phong"));
                ttdp.setNgay_tra_phong(rs.getDate("ngay_tra_phong"));
                list.add(ttdp);
            }

            rs.close();
            preStm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<ThongTinPhong> getDataPT() {
        String sql = "SELECT a.*, b.* FROM thong_tin_phong a LEFT JOIN thiet_lap_gia b ON a.loai_phong = b.loai_phong WHERE a.trang_thai = N'Trống'";
        List<ThongTinPhong> list = new ArrayList<>();

        try {
            Connection conn = DataProvider.dataConnection();
            PreparedStatement preStm = conn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();

            while (rs.next()) {
                ThongTinPhong ttp = new ThongTinPhong();
                ttp.setMa_phong(rs.getString("ma_phong"));
                ttp.setLoai_phong(rs.getString("loai_phong"));
                ttp.setTrang_thai(rs.getString("trang_thai"));
                ttp.setGia_tien(rs.getString("gia_phong"));
                ttp.setThoi_luong(rs.getInt("thoi_luong"));
                ttp.setGhi_chu(rs.getString("ghi_chu"));
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

    public static int getGiaPhong(String loaiPhong) {
        int giaPhong = 0;
        String sql = "SELECT gia_phong FROM thiet_lap_gia WHERE loai_phong = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, loaiPhong);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                giaPhong = rs.getInt("gia_phong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return giaPhong;
    }

    public String layMaKhachHangTheoEmail(String email) {
        String sql = "SELECT ma_khach_hang FROM khach_hang WHERE email = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("ma_khach_hang");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(ThongTinDatPhong ttdp) {
        String insertSQL = "INSERT INTO thong_tin_dat_phong (ma_phong, ma_khach_hang, loai_phong, ngay_dat_phong, tong_tien, ghi_chu, ngay_nhan_phong, ngay_tra_phong, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String updateSQL = "UPDATE thong_tin_phong SET trang_thai = N'Đã được đặt' WHERE ma_phong = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStmInsert = conn.prepareStatement(insertSQL); PreparedStatement preStmUpdate = conn.prepareStatement(updateSQL)) {

            preStmInsert.setString(1, ttdp.getMa_phong());
            preStmInsert.setInt(2, ttdp.getMa_khach_hang());
            preStmInsert.setString(3, ttdp.getLoai_phong());
            preStmInsert.setDate(4, new java.sql.Date(ttdp.getNgay_dat_phong().getTime()));
            preStmInsert.setBigDecimal(5, ttdp.getTong_tien());
            preStmInsert.setString(6, ttdp.getGhi_chu());
            preStmInsert.setDate(7, new java.sql.Date(ttdp.getNgay_nhan_phong().getTime()));
            preStmInsert.setDate(8, new java.sql.Date(ttdp.getNgay_tra_phong().getTime()));
            preStmInsert.setInt(9, 1); // type = 1 (đặt phòng)

            if (preStmInsert.executeUpdate() > 0) {
                preStmUpdate.setString(1, ttdp.getMa_phong());
                preStmUpdate.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(ThongTinDatPhong ttdp) {
        String selectSQL = "SELECT * FROM thong_tin_dat_phong WHERE ma_dat_phong = ?";
        String updateSQL = "UPDATE thong_tin_dat_phong SET type = 2 WHERE ma_dat_phong = ?";
        String insertSQL = "INSERT INTO thong_tin_dat_phong (ma_phong, ma_khach_hang, loai_phong, ngay_dat_phong, tong_tien, ghi_chu, ngay_nhan_phong, ngay_tra_phong, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStmSelect = conn.prepareStatement(selectSQL); PreparedStatement preStmUpdate = conn.prepareStatement(updateSQL); PreparedStatement preStmInsert = conn.prepareStatement(insertSQL)) {

            preStmSelect.setInt(1, ttdp.getMa_dat_phong());
            ResultSet rs = preStmSelect.executeQuery();

            if (rs.next()) {
                boolean isSame = true;

                if (!ttdp.getMa_phong().equals(rs.getString("ma_phong"))) {
                    isSame = false;
                }
                if (ttdp.getMa_khach_hang() != rs.getInt("ma_khach_hang")) {
                    isSame = false;
                }
                if (!ttdp.getLoai_phong().equals(rs.getString("loai_phong"))) {
                    isSame = false;
                }
                if (!ttdp.getNgay_dat_phong().equals(rs.getDate("ngay_dat_phong"))) {
                    isSame = false;
                }
                if (ttdp.getTong_tien().compareTo(rs.getBigDecimal("tong_tien")) != 0) {
                    isSame = false;
                }
                if ((ttdp.getGhi_chu() == null && rs.getString("ghi_chu") != null)
                        || (ttdp.getGhi_chu() != null && !ttdp.getGhi_chu().equals(rs.getString("ghi_chu")))) {
                    isSame = false;
                }
                if (!ttdp.getNgay_nhan_phong().equals(rs.getDate("ngay_nhan_phong"))) {
                    isSame = false;
                }
                if (!ttdp.getNgay_tra_phong().equals(rs.getDate("ngay_tra_phong"))) {
                    isSame = false;
                }

                if (isSame) {
                    return null; // không có gì thay đổi
                }
            }

            preStmUpdate.setInt(1, ttdp.getMa_dat_phong());
            if (preStmUpdate.executeUpdate() <= 0) {
                return false;
            }

            preStmInsert.setString(1, ttdp.getMa_phong());
            preStmInsert.setInt(2, ttdp.getMa_khach_hang());
            preStmInsert.setString(3, ttdp.getLoai_phong());
            preStmInsert.setDate(4, new java.sql.Date(ttdp.getNgay_dat_phong().getTime()));
            preStmInsert.setBigDecimal(5, ttdp.getTong_tien());
            preStmInsert.setString(6, ttdp.getGhi_chu());
            preStmInsert.setDate(7, new java.sql.Date(ttdp.getNgay_nhan_phong().getTime()));
            preStmInsert.setDate(8, new java.sql.Date(ttdp.getNgay_tra_phong().getTime()));

            return preStmInsert.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean exists(String maDatPhong) {
        String checkSQL = "SELECT COUNT(*) FROM thong_tin_dat_phong WHERE ma_dat_phong = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement checkStmt = conn.prepareStatement(checkSQL)) {

            checkStmt.setString(1, maDatPhong);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maDatPhong) {
        String selectSQL = "SELECT ma_phong FROM thong_tin_dat_phong WHERE ma_dat_phong = ?";
        String updatePhongSQL = "UPDATE thong_tin_phong SET trang_thai = N'Trống' WHERE ma_phong = ?";
        String updateDPTypeSQL = "UPDATE thong_tin_dat_phong SET type = 3 WHERE ma_dat_phong = ?";

        try (
                Connection conn = DataProvider.dataConnection(); PreparedStatement selectStmt = conn.prepareStatement(selectSQL); PreparedStatement updatePhongStmt = conn.prepareStatement(updatePhongSQL); PreparedStatement updateDPTypeStmt = conn.prepareStatement(updateDPTypeSQL)) {
            String maPhong = null;
            selectStmt.setString(1, maDatPhong);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                maPhong = rs.getString("ma_phong");
            }

            if (maPhong == null) {
                JOptionPane.showMessageDialog(null, "❌ Không tìm thấy mã phòng tương ứng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            updatePhongStmt.setString(1, maPhong);
            updatePhongStmt.executeUpdate();

            updateDPTypeStmt.setString(1, maDatPhong);
            return updateDPTypeStmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Hóa đơn đã được in. Không thể hủy đặt phòng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertHuyDP(String ma_dat_phong) {
        String sql = "INSERT INTO dich_vu_ho_tro (ma_khach_hang, ten_dich_vu, noi_dung, trang_thai) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement preStm = conn.prepareStatement(sql)) {
            int ma_khach_hang = AuthKhachHang.maKhachHang();
            String ten_dich_vu = "HỦY ĐẶT PHÒNG";
            String noi_dung = "Ad hủy giúp tui phòng này (Mã đặt phòng: " + ma_dat_phong + ")";
            String trang_thai = "Chưa xử lý";

            preStm.setInt(1, ma_khach_hang);
            preStm.setString(2, ten_dich_vu);
            preStm.setString(3, noi_dung);
            preStm.setString(4, trang_thai);

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getEmailByMaKhachHang(int maKhachHang) {
        String email = "";
        String sql = "SELECT email FROM khach_hang WHERE ma_khach_hang = ?";

        try (Connection conn = DataProvider.dataConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maKhachHang);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString("email");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return email;
    }

    public String selectTheoMaPhong(String maPhong) {
        String sql = "SELECT loai_phong FROM thong_tin_phong WHERE ma_phong = ?";
        try (Connection conn = DataProvider.dataConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maPhong);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("loai_phong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean daDatPhong(String maKhachHang) {
        String sql = "SELECT COUNT(*) FROM thong_tin_dat_phong WHERE ma_khach_hang = ? AND type = 1";
        try (Connection conn = DataProvider.dataConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachHang);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
