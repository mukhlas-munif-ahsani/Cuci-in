package com.munifahsan.gowash.PesanPilihCucian.model;

import com.google.firebase.firestore.DocumentId;

public class BajuModel {

    @DocumentId
    private String id;
    private String nNama;
    private String nGambar;
    private int nHarga;
    private int nBerat;
    private boolean nVisibility;

    //--
    private int nJumlah;
    //--

    //---
    private int nTotalHarga;
    private int nTotalBaju;
    private int nTotalBerat;
    //---

    public BajuModel() {
    }

    public BajuModel(String id, String nNama, String nGambar, int nHarga, int nBerat, boolean nVisibility, int nJumlah, int nTotalHarga, int nTotalBaju, int nTotalBerat) {
        this.id = id;
        this.nNama = nNama;
        this.nGambar = nGambar;
        this.nHarga = nHarga;
        this.nBerat = nBerat;
        this.nVisibility = nVisibility;
        //--
        this.nJumlah = nJumlah;
        //--
        //---
        this.nTotalHarga = nTotalHarga;
        this.nTotalBaju = nTotalBaju;
        this.nTotalBerat = nTotalBerat;
        //---
    }

    public String getId() {
        return id;
    }

    public String getnNama() {
        return nNama;
    }

    public String getnGambar() {
        return nGambar;
    }

    public int getnHarga() {
        return nHarga;
    }

    public int getnBerat() {
        return nBerat;
    }

    public boolean isnVisibility() {
        return nVisibility;
    }

    //--
    public int getnJumlah() {
        return nJumlah;
    }

    //---
    public int getnTotalHarga() {
        return nTotalHarga;
    }

    public int getnTotalBaju() {
        return nTotalBaju;
    }

    public int getnTotalBerat() {
        return nTotalBerat;
    }
}
