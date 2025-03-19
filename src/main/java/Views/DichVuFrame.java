/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import DAO.DichVuHoTroDAO;
import Models.DichVuHoTro;
import Services.AuthKhachHang;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class DichVuFrame extends javax.swing.JFrame {
    DefaultTableModel tableModel = new DefaultTableModel();
    /**
     * Creates new form NewJFrame
     */
    public DichVuFrame() {
        initComponents();
        initTable();
        fillTable();
    }
    
    public void initTable(){
        String[] cols = new String[]{"Mã dịch vụ","Mã khách hàng","Tên dịch vụ","Nội dung"};
        tableModel.setColumnIdentifiers(cols);
        tblDichVu.setModel(tableModel);
    }
    
    public void fillTable(){
        tableModel.setRowCount(0);
        DichVuHoTroDAO dvDao = new DichVuHoTroDAO();
        List<DichVuHoTro> list = dvDao.findAll();
        for (DichVuHoTro dichVuHoTro : list) {
            tableModel.addRow(new Object[]{dichVuHoTro.getMa_dich_vu(),dichVuHoTro.getMa_khach_hang(),dichVuHoTro.getTen_dich_vu(),dichVuHoTro.getNoi_dung()});
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

        btnGui = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtMaDichVu = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTenDichVu = new javax.swing.JTextField();
        txtMaKhachHang = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaNoiDung = new javax.swing.JTextArea();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDichVu = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnGui.setText("Gửi");
        btnGui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã dịch vụ");

        txtMaDichVu.setEditable(false);
        txtMaDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaDichVuActionPerformed(evt);
            }
        });

        jLabel4.setText("Tên dịch vụ");

        jLabel5.setText("Mã khách hàng");

        jLabel6.setText("Nội dung");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 255));
        jLabel1.setText("Dịch vụ hỗ trợ");

        txtTenDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDichVuActionPerformed(evt);
            }
        });

        txtMaKhachHang.setEditable(false);
        txtMaKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKhachHangActionPerformed(evt);
            }
        });

        txtaNoiDung.setColumns(20);
        txtaNoiDung.setRows(5);
        jScrollPane2.setViewportView(txtaNoiDung);

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDichVuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDichVu);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back2.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTenDichVu, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                    .addComponent(txtMaDichVu))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnGui, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(14, 14, 14)))))
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGui))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnReset))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiActionPerformed
        StringBuilder sb = new StringBuilder();
        if(txtTenDichVu.getText().equals("")){
            sb.append("Xin nhập tên dịch vụ\n");
        }
        if(txtaNoiDung.getText().equals("")){
            sb.append("Xin nhập nội dung\n");
        }
        if(sb.length() > 0){
            JOptionPane.showMessageDialog(this, sb.toString());
            return;
        }
        
        DichVuHoTroDAO dvDao = new DichVuHoTroDAO();
        DichVuHoTro dv = new DichVuHoTro();
        int maKhachHang = Integer.parseInt(txtMaKhachHang.getText());
        dv.setMa_khach_hang(maKhachHang);
        dv.setTen_dich_vu(txtTenDichVu.getText());
        dv.setNoi_dung(txtaNoiDung.getText());
        
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn gửi không ?");
        if(choice == JOptionPane.YES_OPTION){
            if(dvDao.insert(dv)){
                JOptionPane.showMessageDialog(this, "Gửi thành công");
                fillTable();
            }else {
                JOptionPane.showMessageDialog(this, "Gửi thất bại");
            }
        }
    }//GEN-LAST:event_btnGuiActionPerformed

    private void txtMaDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDichVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDichVuActionPerformed

    private void txtTenDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDichVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDichVuActionPerformed

    private void txtMaKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKhachHangActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtMaDichVu.setText("");
        txtTenDichVu.setText("");
        txtMaKhachHang.setText("");
        txtaNoiDung.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        this.dispose();
        SystemFrame sFrame = new SystemFrame();
        sFrame.setLocationRelativeTo(null);
        sFrame.setVisible(true);
        return;
    }//GEN-LAST:event_jLabel2MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        txtMaKhachHang.setText(String.valueOf(AuthKhachHang.maKhachHang()));
    }//GEN-LAST:event_formWindowOpened

    private void tblDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDichVuMouseClicked
        // TODO add your handling code here:
        int row = tblDichVu.getSelectedRow();
        if(row >= 0){
            int maDichVu = (int) tblDichVu.getValueAt(row, 0);
            DichVuHoTroDAO dvDao = new DichVuHoTroDAO();
            DichVuHoTro dv = dvDao.findById(maDichVu);
            txtMaDichVu.setText(String.valueOf(dv.getMa_dich_vu()));
            txtTenDichVu.setText(dv.getTen_dich_vu());
            txtMaKhachHang.setText(String.valueOf(dv.getMa_khach_hang()));
            txtaNoiDung.setText(dv.getNoi_dung());
        }
    }//GEN-LAST:event_tblDichVuMouseClicked

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
            java.util.logging.Logger.getLogger(DichVuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DichVuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DichVuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DichVuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DichVuFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGui;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblDichVu;
    private javax.swing.JTextField txtMaDichVu;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtTenDichVu;
    private javax.swing.JTextArea txtaNoiDung;
    // End of variables declaration//GEN-END:variables
}
