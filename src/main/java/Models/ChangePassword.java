/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author doqua
 */
public class ChangePassword {

    private String emailKH;
    private String emailNV;
    private String mkKH;
    private String mkNV;

    public ChangePassword() {
    }

    public ChangePassword(String emailKH, String emailNV, String mkKH, String mkNV) {
        this.emailKH = emailKH;
        this.emailNV = emailNV;
        this.mkKH = mkKH;
        this.mkNV = mkNV;
    }

    public String getEmailKH() {
        return emailKH;
    }

    public void setEmailKH(String emailKH) {
        this.emailKH = emailKH;
    }

    public String getEmailNV() {
        return emailNV;
    }

    public void setEmailNV(String emailNV) {
        this.emailNV = emailNV;
    }

    public String getMkKH() {
        return mkKH;
    }

    public void setMkKH(String mkKH) {
        this.mkKH = mkKH;
    }

    public String getMkNV() {
        return mkNV;
    }

    public void setMkNV(String mkNV) {
        this.mkNV = mkNV;
    }

}
