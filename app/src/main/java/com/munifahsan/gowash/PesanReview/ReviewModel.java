package com.munifahsan.gowash.PesanReview;

import com.google.firebase.firestore.DocumentId;

public class ReviewModel {

    @DocumentId
    private String nId;
    private String nNama;
    private int nBerat;
    private int nHarga;
    private int nJumlah;

    //---
    private int nTotalBerat;
    private int nTotalHarga;
    //---

    public ReviewModel() {
    }

    public ReviewModel(String nId, String nNama, int nBerat, int nHarga, int nJumlah, int nTotalBerat, int nTotalHarga) {
        this.nId = nId;
        this.nNama = nNama;
        this.nBerat = nBerat;
        this.nHarga = nHarga;
        this.nJumlah = nJumlah;
        this.nTotalBerat = nTotalBerat;
        this.nTotalHarga = nTotalHarga;
    }

    public String getnId() {
        return nId;
    }

    public String getnNama() {
        return nNama;
    }

    public int getnBerat() {
        return nBerat;
    }

    public int getnHarga() {
        return nHarga;
    }

    public int getnJumlah() {
        return nJumlah;
    }

    public int getnTotalBerat() {
        return nTotalBerat;
    }

    public int getnTotalHarga() {
        return nTotalHarga;
    }
}
