/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import DAO.HoaDonChiTietDAO;
import Models.HoaDonChiTiet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ggame
 */
public class HoaDonChiTietFrame extends javax.swing.JFrame {

    DefaultTableModel tableModel = new DefaultTableModel();
    private String maKhachHang;
    private String ngayDatPhong;
    private String maNhanVien;

    /**
     * Creates new form HoaDonChiTietFrame
     */
    public HoaDonChiTietFrame() {
        initComponents();
        initTable();
        fillTable();
    }

    public void initTable() {
        String[] cols_nv = new String[]{"Mã KH", "Tên KH", "Mã Phòng", "Mã ĐP", "Mã NV", "Mã DV", "Tên DV", "Loại Phòng", "Tổng Tiền", "Ngày ĐP", "Ngày NP", "Ngày TP"};
        tableModel.setColumnIdentifiers(cols_nv);
        tbl_hdct.setModel(tableModel);
    }

    public void fillTable() {
        tableModel.setRowCount(0);
        HoaDonChiTietDAO ttpDAO = new HoaDonChiTietDAO();
        List<HoaDonChiTiet> list = ttpDAO.getData();

        for (HoaDonChiTiet hoaDonChiTiet : list) {
            tableModel.addRow(new Object[]{
                hoaDonChiTiet.getMa_khach_hang(),
                hoaDonChiTiet.getTen_khach_hang(),
                hoaDonChiTiet.getMa_phong(),
                hoaDonChiTiet.getMa_dat_phong(),
                hoaDonChiTiet.getMa_nhan_vien(),
                hoaDonChiTiet.getMa_dich_vu(),
                hoaDonChiTiet.getTen_dich_vu(),
                hoaDonChiTiet.getLoai_phong(),
                hoaDonChiTiet.getTong_tien(),
                hoaDonChiTiet.getNgay_dat_phong(),
                hoaDonChiTiet.getNgay_nhan_phong(),
                hoaDonChiTiet.getNgay_tra_phong(),});
        }
    }

    public void setMaKhachHang(String maKhachHang) {
        System.out.println("Ma KH: " + maKhachHang);
        this.maKhachHang = maKhachHang;
    }

    public void setNgayDatPhong(String ngayDatPhong) {
        System.out.println("Ngay dat phong: " + ngayDatPhong);
        this.ngayDatPhong = ngayDatPhong;
    }

    public void setMaNhanVien(String maNhanVien) {
        System.out.println("Ma nhan vien: " + maNhanVien);
        this.maNhanVien = maNhanVien;
    }

    public void onLoad() {
        System.out.println("==== DEBUG: onLoad() ====");
        System.out.println("Input -> maKhachHang: " + maKhachHang + ", ngayDatPhong: " + ngayDatPhong + ", maNhanVien: " + maNhanVien);

        if (maKhachHang == null || maKhachHang.isEmpty() || ngayDatPhong == null || ngayDatPhong.isEmpty() || maNhanVien == null || maNhanVien.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠ Lỗi: Không đủ thông tin để load hóa đơn chi tiết!", "Lỗi thiếu dữ liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }

        HoaDonChiTietDAO hdctDAO = new HoaDonChiTietDAO();
        List<HoaDonChiTiet> danhSach = hdctDAO.getThongTinDatPhong(maKhachHang, ngayDatPhong, maNhanVien);

        if (danhSach.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠ Không tìm thấy thông tin đặt phòng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "✅ Tìm thấy " + danhSach.size() + " hóa đơn đặt phòng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            System.out.println("✅ Tìm thấy " + danhSach.size() + " hóa đơn đặt phòng:");
            for (HoaDonChiTiet hd : danhSach) {
                System.out.println("----------------------------");
                System.out.println("ma_khach_hang  : " + hd.getMa_khach_hang());
                System.out.println("ten_khach_hang  : " + hd.getTen_khach_hang());
                System.out.println("ma_phong       : " + hd.getMa_phong());
                System.out.println("ma_dat_phong   : " + hd.getMa_dat_phong());
                System.out.println("ma_nhan_vien   : " + hd.getMa_nhan_vien());
                System.out.println("ma_dich_vu     : " + hd.getMa_dich_vu());
                System.out.println("ten_dich_vu    : " + hd.getTen_dich_vu());
                System.out.println("loai_phong     : " + hd.getLoai_phong());
                System.out.println("tong_tien      : " + hd.getTong_tien());
                System.out.println("ngay_dat_phong : " + hd.getNgay_dat_phong());
                System.out.println("ngay_nhan_phong: " + hd.getNgay_nhan_phong());
                System.out.println("ngay_tra_phong : " + hd.getNgay_tra_phong());
                System.out.println("----------------------------");

                hdctDAO.insertHoaDonChiTiet(hd);
            }
            fillTable();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_hdct = new javax.swing.JTable();
        btnLoad = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbl_hdct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_hdct);

        btnLoad.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quay_lai.png"))); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(128, 75));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("LIST", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        onLoad();
    }//GEN-LAST:event_btnLoadActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        this.dispose();
        SystemFrame sFrame = new SystemFrame();
        sFrame.setLocationRelativeTo(null);
        sFrame.setVisible(true);
        return;
    }//GEN-LAST:event_jLabel8MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTietFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTietFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTietFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTietFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDonChiTietFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoad;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbl_hdct;
    // End of variables declaration//GEN-END:variables

}
