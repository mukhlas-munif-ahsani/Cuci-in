package com.munifahsan.gowash.PilihCucian;

import com.google.firebase.firestore.DocumentId;

public class BajuModel {

    @DocumentId
    private String id;
    private String nNama;
    private String nGambar;
    private int nHarga;
    private int nBerat;
    private boolean nVisibility;

    private int nJumlah;

    public BajuModel(){}

    public BajuModel(String id, String nNama, String nGambar, int nHarga, int nBerat, boolean nVisibility, int nJumlah) {
        this.id = id;
        this.nNama = nNama;
        this.nGambar = nGambar;
        this.nHarga = nHarga;
        this.nBerat = nBerat;
        this.nVisibility = nVisibility;
        this.nJumlah = nJumlah;
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

    public int getnJumlah() {
        return nJumlah;
    }
}
