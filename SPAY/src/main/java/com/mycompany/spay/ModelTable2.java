/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spay;

import java.sql.Date;
public class ModelTable2 {
    String nama_lengkap;
    String bulan;
    Date tanggal;
    
    public ModelTable2(String nama_lengkap, String bulan, Date tanggal){
        this.nama_lengkap=nama_lengkap;
        this.bulan=bulan;
        this.tanggal=tanggal;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    
}
