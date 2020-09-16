package com.munifahsan.gowash.PesanReview;

public class ReviewCucianEvent {
    public static final int onError = 0;
    public static final int onSuccess = 1;

    private int eventType;
    private String errorMessage;
    private int totalBerat, totalHarga;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getTotalBerat() {
        return totalBerat;
    }

    public void setTotalBerat(int totalBerat) {
        this.totalBerat = totalBerat;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }
}
