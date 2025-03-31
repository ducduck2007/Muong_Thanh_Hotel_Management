/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import DAO.ThongTinPhongDAO;
import Models.ThongTinPhong;
import Services.AuthNhanVien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ggame
 */
public class ThongTinPhongFrame extends javax.swing.JFrame {

    DefaultTableModel tableModel = new DefaultTableModel();

    /**
     * Creates new form ThongTinPhong
     */
    public ThongTinPhongFrame() {
        initComponents();
        initTable();
        fillTable();
        loadCBO();
        phanQuyen();
    }

    public void phanQuyen() {
        if (AuthNhanVien.isManager() != 1) {
            btnSua.setEnabled(true);
        } else {
            btnSua.setEnabled(false);
        }
    }

    public void loadCBO() {
        ThongTinPhongDAO phongDAO = new ThongTinPhongDAO();
        String loaiPhongMacDinh = (String) cbo_loai_phong.getSelectedItem();

        BigDecimal giaPhong = phongDAO.getGiaPhongByLoaiPhong(loaiPhongMacDinh);

        txt_gia_tien.setText(giaPhong.toString());

        cbo_loai_phong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loaiPhongChon = (String) cbo_loai_phong.getSelectedItem();
                BigDecimal giaPhong = phongDAO.getGiaPhongByLoaiPhong(loaiPhongChon);

                txt_gia_tien.setText(giaPhong.toString());
            }
        });
    }

    public void initTable() {
        String[] cols_nv = new String[]{"Mã Phòng", "Loại Phòng", "Giá Tiền", "Trạng Thái", "Ghi Chú"};
        tableModel.setColumnIdentifiers(cols_nv);
        tbl_phong.setModel(tableModel);
    }

    public void fillTable() {
        tableModel.setRowCount(0);
        ThongTinPhongDAO ttpDAO = new ThongTinPhongDAO();
        List<ThongTinPhong> list = ttpDAO.getData();

        for (ThongTinPhong thongTinPhong : list) {
            tableModel.addRow(new Object[]{
                thongTinPhong.getMa_phong(),
                thongTinPhong.getLoai_phong(),
                thongTinPhong.getGia_tien(),
                thongTinPhong.getTrang_thai(),
                thongTinPhong.getGhi_chu()
            });
        }
    }

    private void capNhatTablePhong(List<ThongTinPhong> danhSachPhong) {
        DefaultTableModel model = (DefaultTableModel) tbl_phong.getModel();
        model.setRowCount(0);

        for (ThongTinPhong phong : danhSachPhong) {
            model.addRow(new Object[]{
                phong.getMa_phong(),
                phong.getLoai_phong(),
                phong.getGia_tien(),
                phong.getTrang_thai(),
                phong.getGhi_chu()
            });
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

        btg_status_phong = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_ma_phong = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbo_loai_phong = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        rdo_trong = new javax.swing.JRadioButton();
        rdo_da_duoc_dat = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        txt_gia_tien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_ghi_chu = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_phong = new javax.swing.JTable();
        btnSua = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        cbo_search_loai_phong = new javax.swing.JComboBox<>();
        cbo_search_trang_thai = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Room code");

        txt_ma_phong.setPreferredSize(new java.awt.Dimension(64, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Type room");

        cbo_loai_phong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phòng thường", "Phòng VIP", "Phòng đặc biệt" }));
        cbo_loai_phong.setPreferredSize(new java.awt.Dimension(114, 30));
        cbo_loai_phong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_loai_phongActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Room status");

        btg_status_phong.add(rdo_trong);
        rdo_trong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdo_trong.setText("Trống");

        btg_status_phong.add(rdo_da_duoc_dat);
        rdo_da_duoc_dat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdo_da_duoc_dat.setText("Đã được đặt");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Price room");

        txt_gia_tien.setEditable(false);
        txt_gia_tien.setPreferredSize(new java.awt.Dimension(64, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Note");

        txt_ghi_chu.setColumns(20);
        txt_ghi_chu.setRows(5);
        jScrollPane1.setViewportView(txt_ghi_chu);

        tbl_phong.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_phong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_phongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_phong);

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setText("Update");
        btnSua.setPreferredSize(new java.awt.Dimension(72, 30));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setText("Reset");
        jButton4.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cbo_search_loai_phong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select room type --", "Phòng thường", "Phòng VIP", "Phòng đặc biệt" }));
        cbo_search_loai_phong.setMinimumSize(new java.awt.Dimension(149, 30));
        cbo_search_loai_phong.setPreferredSize(new java.awt.Dimension(149, 30));
        cbo_search_loai_phong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_search_loai_phongActionPerformed(evt);
            }
        });

        cbo_search_trang_thai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select status --", "Trống", "Đã được đặt" }));
        cbo_search_trang_thai.setPreferredSize(new java.awt.Dimension(143, 30));
        cbo_search_trang_thai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_search_trang_thaiActionPerformed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quay_lai.png"))); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(128, 75));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1071, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(rdo_trong)
                                    .addGap(18, 18, 18)
                                    .addComponent(rdo_da_duoc_dat))
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(txt_ma_phong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbo_loai_phong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_gia_tien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbo_search_loai_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(cbo_search_trang_thai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(44, 44, 44))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ma_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_loai_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdo_trong)
                            .addComponent(rdo_da_duoc_dat))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_gia_tien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbo_search_loai_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbo_search_trang_thai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh sách phòng", jPanel1);

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

    private void cbo_loai_phongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_loai_phongActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbo_loai_phongActionPerformed

    private void tbl_phongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_phongMouseClicked
        // TODO add your handling code here:
        int row = tbl_phong.getSelectedRow();
        if (row >= 0) {
            String maPhong = tbl_phong.getValueAt(row, 0).toString();
            String loaiPhong = tbl_phong.getValueAt(row, 1).toString();
            String giaPhong = tbl_phong.getValueAt(row, 2).toString();
            String trangThai = tbl_phong.getValueAt(row, 3).toString();
            String ghiChu = tbl_phong.getValueAt(row, 4).toString();

            txt_ma_phong.setText(maPhong);
            cbo_loai_phong.setSelectedItem(loaiPhong);
            txt_gia_tien.setText(giaPhong);

            if (trangThai.equalsIgnoreCase("Trống")) {
                rdo_trong.setSelected(true);
            } else if (trangThai.equalsIgnoreCase("Đã được đặt")) {
                rdo_da_duoc_dat.setSelected(true);
            }

            txt_ghi_chu.setText(ghiChu);
        }
    }//GEN-LAST:event_tbl_phongMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txt_ghi_chu.setText("");
        txt_ma_phong.setText("");
        cbo_loai_phong.setSelectedIndex(0);
        cbo_search_loai_phong.setSelectedIndex(0);
        cbo_search_trang_thai.setSelectedIndex(0);
        btg_status_phong.clearSelection();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbo_search_loai_phongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_search_loai_phongActionPerformed
        // TODO add your handling code here:
        String loaiPhongChon = (String) cbo_search_loai_phong.getSelectedItem();
        String trangThaiChon = (String) cbo_search_trang_thai.getSelectedItem();

        List<ThongTinPhong> danhSachPhong = ThongTinPhongDAO.timKiemPhong(loaiPhongChon, trangThaiChon);
        capNhatTablePhong(danhSachPhong);
    }//GEN-LAST:event_cbo_search_loai_phongActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        cbo_search_loai_phong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbo_search_loai_phongActionPerformed(e);
            }
        });
        cbo_search_trang_thai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbo_search_trang_thaiActionPerformed(e);
            }
        });
    }//GEN-LAST:event_formWindowOpened

    private void cbo_search_trang_thaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_search_trang_thaiActionPerformed
        // TODO add your handling code here:
        String loaiPhongChon = (String) cbo_search_loai_phong.getSelectedItem();
        String trangThaiChon = (String) cbo_search_trang_thai.getSelectedItem();

        List<ThongTinPhong> danhSachPhong = ThongTinPhongDAO.timKiemPhong(loaiPhongChon, trangThaiChon);
        capNhatTablePhong(danhSachPhong);
    }//GEN-LAST:event_cbo_search_trang_thaiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        try {
            String ma_phong = txt_ma_phong.getText().trim();
            String loai_phong = (String) cbo_loai_phong.getSelectedItem();

            if (!rdo_trong.isSelected() && !rdo_da_duoc_dat.isSelected()) {
                JOptionPane.showMessageDialog(this, "⚠️ Vui lòng chọn trạng thái phòng!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String trang_thai = rdo_trong.isSelected() ? "Trống" : "Đã được đặt";

            String ghi_chu = txt_ghi_chu.getText().trim();

            if (ma_phong.isEmpty() || loai_phong.isEmpty() || ghi_chu.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ThongTinPhongDAO ttpDAO = new ThongTinPhongDAO();

            if (!ttpDAO.existsMaPhong(ma_phong)) {
                JOptionPane.showMessageDialog(this, "⚠️ Mã phòng không tồn tại! Vui lòng nhập mã phòng hợp lệ.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ThongTinPhong ttp = new ThongTinPhong();
            ttp.setMa_phong(ma_phong);
            ttp.setLoai_phong(loai_phong);
            ttp.setTrang_thai(trang_thai);
            ttp.setGhi_chu(ghi_chu);

            if (ttpDAO.update(ttp)) {
                JOptionPane.showMessageDialog(this,
                        "✅ Sửa thông tin phòng thành công!",
                        "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "❌ Sửa thông tin phòng thất bại! Vui lòng kiểm tra lại.",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            fillTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        this.dispose();
        SystemFrame sFrame = new SystemFrame();
        sFrame.setLocationRelativeTo(null);
        sFrame.setVisible(true);
        return;
    }//GEN-LAST:event_jLabel9MouseClicked

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
            java.util.logging.Logger.getLogger(ThongTinPhongFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinPhongFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinPhongFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinPhongFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongTinPhongFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btg_status_phong;
    private javax.swing.JButton btnSua;
    private javax.swing.JComboBox<String> cbo_loai_phong;
    private javax.swing.JComboBox<String> cbo_search_loai_phong;
    private javax.swing.JComboBox<String> cbo_search_trang_thai;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdo_da_duoc_dat;
    private javax.swing.JRadioButton rdo_trong;
    private javax.swing.JTable tbl_phong;
    private javax.swing.JTextArea txt_ghi_chu;
    private javax.swing.JTextField txt_gia_tien;
    private javax.swing.JTextField txt_ma_phong;
    // End of variables declaration//GEN-END:variables
}
