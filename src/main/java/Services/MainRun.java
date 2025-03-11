/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Views.MainFrame;
import Views.ProgressBarFrame;
import java.util.Random;

/**
 *
 * @author ADMIN
 */
public class MainRun {

    public static void main(String[] args) {
        ProgressBarFrame proBar = new ProgressBarFrame();
        MainFrame mainF = new MainFrame();

        // Đặt cửa sổ ở giữa màn hình
        proBar.setLocationRelativeTo(null);
        mainF.setLocationRelativeTo(null);

        proBar.setVisible(true);

        Random random = new Random();
        int progress = 0;

        while (progress <= 100) {
            try {
                Thread.sleep(random.nextInt(100) + 20);

                progress += random.nextInt(5) + 1;

                if (progress > 100) {
                    progress = 100;
                }

                proBar.progressBar.setValue(progress);

                if (progress % 2 == 0) {
                    proBar.lblPleaseWait.setText("Please wait..");
                } else {
                    proBar.lblPleaseWait.setText("Please wait...");
                }

                if (progress == 100) {
                    Thread.sleep(500);
                    proBar.setVisible(false);
                    mainF.setVisible(true);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
