/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DAO.LichLamViecDAO;
import Models.LichLamViec;
import Views.LoginSystem;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class CaLamViecCheck {

    public static void checkGioLamViec() {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String maNV = AuthNhanVien.getMaNhanVien();
                LichLamViecDAO lvDao = new LichLamViecDAO();
                LichLamViec lv = lvDao.findByMaNV(maNV);
                if (lv != null) {
                    String caLamViec = lv.getCaLamViec();

                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
                    int timeInt = Integer.parseInt(sdf.format(d));
                    System.out.println(timeInt);

                    boolean ngoaiGioLam = false;

                    if (caLamViec.equals("Ca 1: 06h00 - 14h00") && (timeInt < 600 || timeInt >= 1400)) {
                        ngoaiGioLam = true;
                    } else if (caLamViec.equals("Ca 2: 14h00 - 22h00") && (timeInt < 1400 || timeInt >= 2200)) {
                        ngoaiGioLam = true;
                    } else if (caLamViec.equals("Ca 3: 22h00 - 06h00") && !(timeInt >= 2200 || timeInt < 600)) {
                        ngoaiGioLam = true;
                    }

                    if (ngoaiGioLam) {
                        timer.cancel();

                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, "Bạn bị thoát vì bạn đang làm quá giờ làm của mình");
                            LoginSystem lsFrame = new LoginSystem();
                            lsFrame.setLocationRelativeTo(null);
                            lsFrame.setVisible(true);
                        });
                    }
                } else {
                    timer.cancel();
                }
            }
        }, 0, 100_000);
    }
}
