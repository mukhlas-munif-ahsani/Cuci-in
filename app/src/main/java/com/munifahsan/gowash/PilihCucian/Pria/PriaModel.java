package com.munifahsan.gowash.PilihCucian.Pria;

import com.google.firebase.firestore.DocumentId;

public class PriaModel {
    @DocumentId
    private String id;
    private String nNama;
    private String nGambar;
    private int nHarga;
    private int nBerat;
    private int nTipe;
    private boolean nVisibility;

    public PriaModel(){}

    public PriaModel(String id, String nNama, String nGambar, int nHarga, int nBerat,int nTipe, boolean nVisibility) {
        this.id = id;
        this.nNama = nNama;
        this.nGambar = nGambar;
        this.nHarga = nHarga;
        this.nBerat = nBerat;
        this.nTipe = nTipe;
        this.nVisibility = nVisibility;
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

    public int getnTipe() {
        return nTipe;
    }

    public boolean isnVisibility() {
        return nVisibility;
    }
}
