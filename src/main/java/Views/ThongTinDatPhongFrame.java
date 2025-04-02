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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
        addDocumentListener();
        initTable();
        fillTable();
        onLoad();
        phanQuyen();
    }

    public void phanQuyen() {
        btnDat.setEnabled(false);
        btnHuy.setEnabled(false);
        btnSua.setEnabled(false);

        if (AuthKhachHang.user != null) {
            btnDat.setEnabled(true);
            btnHuy.setEnabled(true);
        } else if (AuthNhanVien.isManager() != 1) {
            btnSua.setEnabled(true);
        }
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
        Date today = new Date();

        jdc_ngay_dat_phong.setDate(today);
        jdc_ngay_nhan_phong.setDate(today);

        if (jdc_ngay_nhan_phong.getDate() == null) {
            jdc_ngay_tra_phong.setDate(today);
        }

        jdc_ngay_nhan_phong.getDateEditor().addPropertyChangeListener("date", e -> capNhatTongTien());
        jdc_ngay_tra_phong.getDateEditor().addPropertyChangeListener("date", e -> capNhatTongTien());

        cbo_loai_phong.addActionListener(e -> capNhatGiaPhong());

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
        Date ngayNhan = jdc_ngay_nhan_phong.getDate();
        Date ngayTra = jdc_ngay_tra_phong.getDate();

        if (ngayNhan == null || ngayTra == null) {
            txt_tong_tien.setText("");
            return;
        }

        long soNgay = (ngayTra.getTime() - ngayNhan.getTime()) / (1000 * 60 * 60 * 24);

        if (soNgay <= 0) {
            txt_tong_tien.setText("Ngày không hợp lệ");
            return;
        }

        String loaiPhong = (String) cbo_loai_phong.getSelectedItem();
        int giaPhong = ThongTinDatPhongDAO.getGiaPhong(loaiPhong);

        int tongTien = (int) (soNgay * giaPhong);
        txt_tong_tien.setText(tongTien + "");
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

    private void addDocumentListener() {
        txt_ma_phong.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLoaiPhong();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLoaiPhong();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLoaiPhong();
            }
        });
    }

    private void updateLoaiPhong() {
        String maPhong = txt_ma_phong.getText().trim();
        if (!maPhong.isEmpty()) {
            ThongTinDatPhongDAO dao = new ThongTinDatPhongDAO();
            String loaiPhong = dao.selectTheoMaPhong(maPhong);

            if (loaiPhong != null) {
                cbo_loai_phong.setSelectedItem(loaiPhong);
            } else {
                cbo_loai_phong.setSelectedItem("");
            }
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_ma_phong = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbo_loai_phong = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
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
        btnDat = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jdc_ngay_nhan_phong = new com.toedter.calendar.JDateChooser();
        jdc_ngay_tra_phong = new com.toedter.calendar.JDateChooser();
        jdc_ngay_dat_phong = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_ds_phong_trong = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Mã phòng");

        txt_ma_phong.setPreferredSize(new java.awt.Dimension(64, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Loại phòng");

        cbo_loai_phong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phòng thường", "Phòng VIP", "Phòng đặc biệt" }));
        cbo_loai_phong.setEnabled(false);
        cbo_loai_phong.setMinimumSize(new java.awt.Dimension(114, 30));
        cbo_loai_phong.setPreferredSize(new java.awt.Dimension(114, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Ngày đặt phòng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Ngày nhận phòng");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Ngày trả phòng");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Tổng tiền");

        txt_tong_tien.setEditable(false);
        txt_tong_tien.setPreferredSize(new java.awt.Dimension(64, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Ghi chú");

        txt_ghi_chu.setColumns(20);
        txt_ghi_chu.setRows(5);
        jScrollPane1.setViewportView(txt_ghi_chu);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Mã đặt phòng");

        txt_ma_dp.setPreferredSize(new java.awt.Dimension(64, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Gmail khách hàng");

        txt_email_kh.setPreferredSize(new java.awt.Dimension(64, 30));

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
        tbl_ds_phong_dat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ds_phong_datMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_ds_phong_dat);

        btnDat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDat.setText("Đặt phòng");
        btnDat.setPreferredSize(new java.awt.Dimension(72, 30));
        btnDat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Làm mới");
        jButton2.setPreferredSize(new java.awt.Dimension(72, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setText("Cập nhật");
        btnSua.setPreferredSize(new java.awt.Dimension(72, 30));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHuy.setText("Hủy đặt phòng");
        btnHuy.setPreferredSize(new java.awt.Dimension(110, 30));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jdc_ngay_nhan_phong.setPreferredSize(new java.awt.Dimension(88, 30));

        jdc_ngay_tra_phong.setPreferredSize(new java.awt.Dimension(88, 30));

        jdc_ngay_dat_phong.setPreferredSize(new java.awt.Dimension(88, 30));

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
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ma_dp, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbo_loai_phong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_tong_tien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jdc_ngay_nhan_phong, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(147, 147, 147)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ma_phong, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_email_kh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jdc_ngay_tra_phong, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jdc_ngay_dat_phong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnDat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ma_dp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_ma_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbo_loai_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdc_ngay_dat_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jdc_ngay_nhan_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdc_ngay_tra_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tong_tien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_email_kh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(132, 132, 132))
        );

        jTabbedPane1.addTab("ĐẶT PHÒNG", jPanel1);

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("DANH SÁCH PHÒNG TRỐNG", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnDatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatActionPerformed
        // TODO add your handling code here:
        String ma_phong = txt_ma_phong.getText().trim();
        Date ngay_dat_phong = jdc_ngay_dat_phong.getDate();
        Date ngay_nhan_phong = jdc_ngay_nhan_phong.getDate();
        Date ngay_tra_phong = jdc_ngay_tra_phong.getDate();
        String email = txt_email_kh.getText().trim();
        String tong_tien = txt_tong_tien.getText().trim();
        String ghi_chu = txt_ghi_chu.getText().trim();
        String loai_phong = (String) cbo_loai_phong.getSelectedItem();

        if (ma_phong.isEmpty() || ngay_nhan_phong == null || ngay_tra_phong == null || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đặt phòng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            ttdp.setMa_phong(ma_phong);
            ttdp.setMa_khach_hang(Integer.parseInt(ma_khach_hang));
            ttdp.setLoai_phong(loai_phong);
            ttdp.setNgay_dat_phong(ngay_dat_phong);
            ttdp.setTong_tien(new BigDecimal(tong_tien));
            ttdp.setGhi_chu(ghi_chu);
            ttdp.setNgay_nhan_phong(ngay_nhan_phong);
            ttdp.setNgay_tra_phong(ngay_tra_phong);

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
    }//GEN-LAST:event_btnDatActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        txt_email_kh.setText("");
        txt_ghi_chu.setText("");
        txt_ma_dp.setText("");
        txt_ma_phong.setText("");
        jdc_ngay_nhan_phong.setDate(null);
        jdc_ngay_tra_phong.setDate(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (AuthNhanVien.user != null && (AuthNhanVien.isManager() == 0 || AuthNhanVien.isManager() == 2)) {
            String ma_dat_phong = txt_ma_dp.getText().trim();
            String ma_phong = txt_ma_phong.getText().trim();
            Date ngay_dat_phong = jdc_ngay_dat_phong.getDate();
            Date ngay_nhan_phong = jdc_ngay_nhan_phong.getDate();
            Date ngay_tra_phong = jdc_ngay_tra_phong.getDate();
            String email = txt_email_kh.getText().trim();
            String tong_tien = txt_tong_tien.getText().trim();
            String ghi_chu = txt_ghi_chu.getText().trim();
            String loai_phong = (String) cbo_loai_phong.getSelectedItem();

            if (ma_phong.isEmpty() || ngay_nhan_phong == null || ngay_tra_phong == null || email.isEmpty() || ma_dat_phong.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

            int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa đặt phòng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (choice != JOptionPane.YES_OPTION) {
                return;
            }

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                ttdp.setMa_dat_phong(Integer.parseInt(ma_dat_phong));
                ttdp.setMa_phong(ma_phong);
                ttdp.setMa_khach_hang(Integer.parseInt(ma_khach_hang));
                ttdp.setLoai_phong(loai_phong);
                ttdp.setNgay_dat_phong(ngay_dat_phong);
                ttdp.setTong_tien(new BigDecimal(tong_tien));
                ttdp.setGhi_chu(ghi_chu);
                ttdp.setNgay_nhan_phong(ngay_nhan_phong);
                ttdp.setNgay_tra_phong(ngay_tra_phong);

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
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        if (AuthKhachHang.user == null) {
            String ma_dat_phong = txt_ma_dp.getText().trim();

            if (ma_dat_phong.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mã đặt phòng để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ThongTinDatPhongDAO ttdpDAO = new ThongTinDatPhongDAO();

            try {
                if (!ttdpDAO.exists(ma_dat_phong)) {
                    JOptionPane.showMessageDialog(null, "❌ Mã đặt phòng không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy đặt phòng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                if (ttdpDAO.delete(ma_dat_phong)) {
                    JOptionPane.showMessageDialog(this, "✅ Hủy phòng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Hủy phòng thất bại! Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                fillTable();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng gửi yêu cầu hỗ trợ tới Lễ Tân để được hủy đặt phòng. Xin cảm ơn quý khách!");
            return;
        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void tbl_ds_phong_datMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ds_phong_datMouseClicked
        // TODO add your handling code here:
        int row = tbl_ds_phong_dat.getSelectedRow();
        if (row >= 0) {
            String maDatPhong = tbl_ds_phong_dat.getValueAt(row, 0).toString();
            String maPhong = tbl_ds_phong_dat.getValueAt(row, 1).toString();
            String maKhachHang = tbl_ds_phong_dat.getValueAt(row, 2).toString();
            String loaiPhong = tbl_ds_phong_dat.getValueAt(row, 3).toString();
            String ngayDatPhong = tbl_ds_phong_dat.getValueAt(row, 4).toString();
            String tongTien = tbl_ds_phong_dat.getValueAt(row, 5).toString();
            String ngayNhanPhong = tbl_ds_phong_dat.getValueAt(row, 6).toString();
            String ngayTraPhong = tbl_ds_phong_dat.getValueAt(row, 7).toString();
            String ghiChu = tbl_ds_phong_dat.getValueAt(row, 8).toString();

            ThongTinDatPhongDAO thongTinDatPhongDAO = new ThongTinDatPhongDAO();
            String emailKhachHang = thongTinDatPhongDAO.getEmailByMaKhachHang(maKhachHang);

            txt_email_kh.setText(emailKhachHang);
            txt_ma_dp.setText(maDatPhong);
            txt_ma_phong.setText(maPhong);
            cbo_loai_phong.setSelectedItem(loaiPhong);
            txt_tong_tien.setText(tongTien);
            txt_ghi_chu.setText(ghiChu);

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateNhanPhong = sdf.parse(ngayNhanPhong);
                Date dateTraPhong = sdf.parse(ngayTraPhong);
                Date dateDatPhong = sdf.parse(ngayDatPhong);

                jdc_ngay_nhan_phong.setDate(dateNhanPhong);
                jdc_ngay_tra_phong.setDate(dateTraPhong);
                jdc_ngay_dat_phong.setDate(dateDatPhong);
            } catch (ParseException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_tbl_ds_phong_datMouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        if (AuthKhachHang.user == null) {
            this.dispose();
            SystemFrame sFrame = new SystemFrame();
            sFrame.setLocationRelativeTo(null);
            sFrame.setVisible(true);
            return;
        } else {
            this.dispose();
            ClientFrame sFrame = new ClientFrame();
            sFrame.setLocationRelativeTo(null);
            sFrame.setVisible(true);
            return;
        }
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
    private javax.swing.JButton btnDat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnSua;
    private javax.swing.JComboBox<String> cbo_loai_phong;
    private javax.swing.JButton jButton2;
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
    private com.toedter.calendar.JDateChooser jdc_ngay_dat_phong;
    private com.toedter.calendar.JDateChooser jdc_ngay_nhan_phong;
    private com.toedter.calendar.JDateChooser jdc_ngay_tra_phong;
    private javax.swing.JTable tbl_ds_phong_dat;
    private javax.swing.JTable tbl_ds_phong_trong;
    private javax.swing.JTextField txt_email_kh;
    private javax.swing.JTextArea txt_ghi_chu;
    private javax.swing.JTextField txt_ma_dp;
    private javax.swing.JTextField txt_ma_phong;
    private javax.swing.JTextField txt_tong_tien;
    // End of variables declaration//GEN-END:variables
}
