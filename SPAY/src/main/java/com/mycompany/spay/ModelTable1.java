/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spay;

import java.sql.Date;


public class ModelTable1 {
    int id;
    String nd;
    Date tanggal;
    String nb;
    String email;
    int saldo;
    public ModelTable1(int id,String nd,String nb, int saldo,Date tanggal ,String email){
        this.id=id;
        this.nd = nd;
        this.tanggal = tanggal;
        this.nb = nb;
        this.email = email;
        this.saldo =saldo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNd() {
        return nd;
    }

    public int getId() {
        return id;
    }

    public String getNb() {
        return nb;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public void setNb(String nb) {
        this.nb = nb;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }



    public String getEmail() {
        return email;
    }

    public int getSaldo() {
        return saldo;
    }

 
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }


    public Date getTanggal() {
        return tanggal;
    }
}
