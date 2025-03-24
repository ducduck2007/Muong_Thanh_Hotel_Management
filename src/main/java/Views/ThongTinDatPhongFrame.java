/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import DAO.ThongTinDatPhongDAO;
import Models.ThongTinDatPhong;
import Models.ThongTinPhong;
import Services.AuthKhachHang;
import Services.AuthNhanVien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ggame
 */
public class ThongTinDatPhongFrame extends javax.swing.JFrame {

    DefaultTableModel tableModel_phong_dat = new DefaultTableModel();
    DefaultTableModel tableModel_phong_trong = new DefaultTableModel();

    /**
     * Creates new form ThongTinDatPhong
     */
    public ThongTinDatPhongFrame() {
        initComponents();
        initTable();
        fillTable();
        onLoad();
    }

    public void initTable() {
        String[] cols_dat = new String[]{"Mã ĐP", "Mã Phòng", "Mã KH", "Loại Phòng", "Date ĐP", "Tổng Tiền", "Date NP", "Date TP", "Ghi Chú"};
        tableModel_phong_dat.setColumnIdentifiers(cols_dat);
        tbl_ds_phong_dat.setModel(tableModel_phong_dat);

        String[] cols_trong = new String[]{"Mã Phòng", "Loại Phòng", "Giá Tiền", "Thời Lượng", "Ghi Chú"};
        tableModel_phong_trong.setColumnIdentifiers(cols_trong);
        tbl_ds_phong_trong.setModel(tableModel_phong_trong);
    }

    public void fillTable() {
        tableModel_phong_dat.setRowCount(0);
        ThongTinDatPhongDAO ttdpDAO = new ThongTinDatPhongDAO();
        List<ThongTinDatPhong> listNV = ttdpDAO.getDataPD();

        for (ThongTinDatPhong ttdp : listNV) {
            tableModel_phong_dat.addRow(new Object[]{
                ttdp.getMa_dat_phong(),
                ttdp.getMa_phong(),
                ttdp.getMa_khach_hang(),
                ttdp.getLoai_phong(),
                ttdp.getNgay_dat_phong(),
                ttdp.getTong_tien(),
                ttdp.getNgay_nhan_phong(),
                ttdp.getNgay_tra_phong(),
                ttdp.getGhi_chu(),});
        }

        tableModel_phong_trong.setRowCount(0);
        List<ThongTinPhong> listQL = ttdpDAO.getDataPT();

        for (ThongTinPhong ttp : listQL) {
            tableModel_phong_trong.addRow(new Object[]{
                ttp.getMa_phong(),
                ttp.getLoai_phong(),
                ttp.getGia_tien(),
                ttp.getThoi_luong() + " ngày",
                ttp.getGhi_chu()
            });
        }
    }

    public void onLoad() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        txt_ngay_dat_phong.setText(sdf.format(new Date()));
        txt_ngay_nhan_phong.setText(sdf.format(new Date()));

        cbo_loai_phong.addActionListener(e -> capNhatGiaPhong());

        txt_ngay_nhan_phong.addCaretListener(e -> capNhatTongTien());
        txt_ngay_tra_phong.addCaretListener(e -> capNhatTongTien());

        capNhatGiaPhong();
    }

    private void capNhatGiaPhong() {
        String loaiPhong = (String) cbo_loai_phong.getSelectedItem();
        if (loaiPhong == null) {
            return;
        }

        int giaPhong = ThongTinDatPhongDAO.getGiaPhong(loaiPhong);

        capNhatTongTien();
    }

    private void capNhatTongTien() {
        String ngayNhan = txt_ngay_nhan_phong.getText().trim();
        String ngayTra = txt_ngay_tra_phong.getText().trim();

        if (!isValidDate(ngayNhan) || !isValidDate(ngayTra)) {
            txt_tong_tien.setText("");
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date dateNhan = sdf.parse(ngayNhan);
            Date dateTra = sdf.parse(ngayTra);

            long soNgay = (dateTra.getTime() - dateNhan.getTime()) / (1000 * 60 * 60 * 24);

            if (soNgay <= 0) {
                txt_tong_tien.setText("Ngày không hợp lệ");
                return;
            }

            String loaiPhong = (String) cbo_loai_phong.getSelectedItem();
            int giaPhong = ThongTinDatPhongDAO.getGiaPhong(loaiPhong);

            int tongTien = (int) (soNgay * giaPhong);
            txt_tong_tien.setText(tongTien + "");
        } catch (ParseException e) {
            txt_tong_tien.setText("Ngày không hợp lệ");
        }
    }

    private boolean isValidDate(String date) {
        return date.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    private boolean kiemTraDinhDangNgay(String ngay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(ngay);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean kiemTraDinhDangEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(regex, email);
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_ma_phong = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbo_loai_phong = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txt_ngay_dat_phong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_ngay_nhan_phong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_ngay_tra_phong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_tong_tien = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_ghi_chu = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        txt_ma_dp = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_email_kh = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_ds_phong_dat = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_ds_phong_trong = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Mã phòng");

        jLabel2.setText("Loại phòng");

        cbo_loai_phong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phòng thường", "Phòng VIP", "Phòng đặc biệt" }));
        cbo_loai_phong.setEnabled(false);

        jLabel3.setText("Ngày đặt phòng");

        txt_ngay_dat_phong.setEditable(false);

        jLabel4.setText("Ngày nhận phòng (yyyy-MM-DD)");

        jLabel5.setText("Ngày trả phòng (yyyy-MM-dd)");

        jLabel6.setText("Tổng tiền");

        txt_tong_tien.setEditable(false);

        jLabel7.setText("Ghi chú");

        txt_ghi_chu.setColumns(20);
        txt_ghi_chu.setRows(5);
        jScrollPane1.setViewportView(txt_ghi_chu);

        jLabel8.setText("Mã đặt phòng");

        jLabel10.setText("Email khách hàng");

        tbl_ds_phong_dat.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tbl_ds_phong_dat);

        jButton1.setText("Đặt");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Sửa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Hủy đặt phòng");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ma_dp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ma_phong, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbo_loai_phong, 0, 435, Short.MAX_VALUE)
                                        .addComponent(txt_ngay_nhan_phong)
                                        .addComponent(txt_tong_tien)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_ngay_dat_phong)
                                    .addComponent(txt_ngay_tra_phong)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_email_kh, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(32, 32, 32))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_ma_dp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_ma_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbo_loai_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_ngay_dat_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ngay_nhan_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ngay_tra_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tong_tien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_email_kh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đặt Phòng", jPanel1);

        tbl_ds_phong_trong.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_ds_phong_trong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ds_phong_trongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_ds_phong_trong);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Danh Sách Phòng Trống", jPanel3);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back2.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        this.dispose();
        if (AuthKhachHang.user != null) {
            ClientFrame sFrame = new ClientFrame();
            sFrame.setLocationRelativeTo(null);
            sFrame.setVisible(true);
            return;
        } else if (AuthNhanVien.user != null) {
            SystemFrame sFrame = new SystemFrame();
            sFrame.setLocationRelativeTo(null);
            sFrame.setVisible(true);
            return;
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void tbl_ds_phong_trongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ds_phong_trongMouseClicked
        // TODO add your handling code here:
        int row = tbl_ds_phong_trong.getSelectedRow();
        if (row >= 0) {
            String maPhong = tbl_ds_phong_trong.getValueAt(row, 0).toString();
            String loaiPhong = tbl_ds_phong_trong.getValueAt(row, 1).toString();

            txt_ma_phong.setText(maPhong);
            cbo_loai_phong.setSelectedItem(loaiPhong);
        }
    }//GEN-LAST:event_tbl_ds_phong_trongMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (AuthKhachHang.user != null) {
            String ma_phong = txt_ma_phong.getText().trim();
            String ngay_dat_phong = txt_ngay_dat_phong.getText().trim();
            String ngay_nhan_phong = txt_ngay_nhan_phong.getText().trim();
            String ngay_tra_phong = txt_ngay_tra_phong.getText().trim();
            String email = txt_email_kh.getText().trim();
            String tong_tien = txt_tong_tien.getText().trim();
            String ghi_chu = txt_ghi_chu.getText().trim();
            String loai_phong = (String) cbo_loai_phong.getSelectedItem();

            if (ma_phong.isEmpty() || ngay_nhan_phong.isEmpty()
                    || ngay_tra_phong.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!kiemTraDinhDangNgay(ngay_nhan_phong) || !kiemTraDinhDangNgay(ngay_tra_phong)) {
                JOptionPane.showMessageDialog(null, "Định dạng ngày phải là yyyy-MM-dd!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!kiemTraDinhDangEmail(email)) {
                JOptionPane.showMessageDialog(null, "Email không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ThongTinDatPhong ttdp = new ThongTinDatPhong();
            ThongTinDatPhongDAO ttdpDAO = new ThongTinDatPhongDAO();
            String ma_khach_hang = ttdpDAO.layMaKhachHangTheoEmail(email);

            if (ma_khach_hang == null) {
                JOptionPane.showMessageDialog(null, "Email không tồn tại trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                ttdp.setMa_phong(ma_phong);
                ttdp.setMa_khach_hang(Integer.parseInt(ma_khach_hang));
                ttdp.setLoai_phong(loai_phong);
                ttdp.setNgay_dat_phong(sdf.parse(ngay_dat_phong));
                ttdp.setTong_tien(new BigDecimal(tong_tien));
                ttdp.setGhi_chu(ghi_chu);
                ttdp.setNgay_nhan_phong(sdf.parse(ngay_nhan_phong));
                ttdp.setNgay_tra_phong(sdf.parse(ngay_tra_phong));

                if (ttdpDAO.insert(ttdp)) {
                    JOptionPane.showMessageDialog(this,
                            "✅ Đặt phòng thành công!",
                            "Thành công",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "❌ Đặt phòng thất bại! Vui lòng kiểm tra lại.",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
                fillTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chỉ có khách hàng mới được đặt phòng");
            return;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        txt_email_kh.setText("");
        txt_ghi_chu.setText("");
        txt_ma_dp.setText("");
        txt_ma_phong.setText("");
        txt_ngay_nhan_phong.setText("");
        txt_ngay_tra_phong.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (AuthNhanVien.user != null && AuthNhanVien.isManager() == 0) {
            String ma_dat_phong = txt_ma_dp.getText().trim();
            String ma_phong = txt_ma_phong.getText().trim();
            String ngay_dat_phong = txt_ngay_dat_phong.getText().trim();
            String ngay_nhan_phong = txt_ngay_nhan_phong.getText().trim();
            String ngay_tra_phong = txt_ngay_tra_phong.getText().trim();
            String email = txt_email_kh.getText().trim();
            String tong_tien = txt_tong_tien.getText().trim();
            String ghi_chu = txt_ghi_chu.getText().trim();
            String loai_phong = (String) cbo_loai_phong.getSelectedItem();

            if (ma_phong.isEmpty() || ngay_nhan_phong.isEmpty()
                    || ngay_tra_phong.isEmpty() || email.isEmpty() || ma_dat_phong.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!kiemTraDinhDangNgay(ngay_nhan_phong) || !kiemTraDinhDangNgay(ngay_tra_phong)) {
                JOptionPane.showMessageDialog(null, "Định dạng ngày phải là yyyy-MM-dd!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!kiemTraDinhDangEmail(email)) {
                JOptionPane.showMessageDialog(null, "Email không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ThongTinDatPhong ttdp = new ThongTinDatPhong();
            ThongTinDatPhongDAO ttdpDAO = new ThongTinDatPhongDAO();
            String ma_khach_hang = ttdpDAO.layMaKhachHangTheoEmail(email);

            if (ma_khach_hang == null) {
                JOptionPane.showMessageDialog(null, "Email không tồn tại trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                ttdp.setMa_phong(ma_phong);
                ttdp.setMa_khach_hang(Integer.parseInt(ma_khach_hang));
                ttdp.setLoai_phong(loai_phong);
                ttdp.setNgay_dat_phong(sdf.parse(ngay_dat_phong));
                ttdp.setTong_tien(new BigDecimal(tong_tien));
                ttdp.setGhi_chu(ghi_chu);
                ttdp.setNgay_nhan_phong(sdf.parse(ngay_nhan_phong));
                ttdp.setNgay_tra_phong(sdf.parse(ngay_tra_phong));

                if (ttdpDAO.update(ttdp)) {
                    JOptionPane.showMessageDialog(this,
                            "✅ Sửa đặt phòng thành công!",
                            "Thành công",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "❌ Sửa đặt phòng thất bại! Vui lòng kiểm tra lại.",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
                fillTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "❌ Lỗi! Vui lòng kiểm tra lại.",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chỉ có quản lý mới được sửa");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (AuthKhachHang.user == null) {

        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng gửi yêu cầu hỗ trợ tới Lễ Tân để được hủy đặt phòng. Xin cảm ơn quý khách!");
            return;
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(ThongTinDatPhongFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinDatPhongFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinDatPhongFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinDatPhongFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongTinDatPhongFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbo_loai_phong;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbl_ds_phong_dat;
    private javax.swing.JTable tbl_ds_phong_trong;
    private javax.swing.JTextField txt_email_kh;
    private javax.swing.JTextArea txt_ghi_chu;
    private javax.swing.JTextField txt_ma_dp;
    private javax.swing.JTextField txt_ma_phong;
    private javax.swing.JTextField txt_ngay_dat_phong;
    private javax.swing.JTextField txt_ngay_nhan_phong;
    private javax.swing.JTextField txt_ngay_tra_phong;
    private javax.swing.JTextField txt_tong_tien;
    // End of variables declaration//GEN-END:variables
}
