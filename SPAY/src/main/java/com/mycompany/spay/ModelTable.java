/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spay;



import java.sql.Date;

public class ModelTable {
    String bulan;
    Date tanggal;
    public ModelTable(String bulan,Date tanggal){
        this.bulan = bulan;
        this.tanggal = tanggal;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getBulan() {
        return bulan;
    }

    public Date getTanggal() {
        return tanggal;
    }
}
